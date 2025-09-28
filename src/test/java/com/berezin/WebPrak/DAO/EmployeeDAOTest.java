package  com.berezin.WebPrak.DAO;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.berezin.WebPrak.models.Employee;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
class EmployeeDAOTest {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private SessionFactory sessionFactory;


    @Test
    void getAllEmployeeByNameTest() {
        // Создаём нескольких сотрудников с разными именами
        Employee e1 = new Employee();
        e1.setName("Уебан Иванов");
        e1.setBirthDay(LocalDate.of(1990, 1, 1));

        Employee e2 = new Employee();
        e2.setName("Уебан Петров");
        e2.setBirthDay(LocalDate.of(1991, 2, 2));

        Employee e3 = new Employee();
        e3.setName("Пётр Сидоров");
        e3.setBirthDay(LocalDate.of(1992, 3, 3));

        List<Employee> employees = List.of(e1, e2, e3);

        // Сохраняем всех в БД
        employeeDAO.saveCollection(employees);

        try {
            // Ищем всех с именем содержащим "Иван"
            List<Employee> result = employeeDAO.getAllEmployeeByName("Уебан");

            // Должны найти e1 и e2
            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.stream().allMatch(emp -> emp.getName().contains("Уебан")));

            // Ищем имя, которого нет
            List<Employee> emptyResult = employeeDAO.getAllEmployeeByName("НеСуществующееИмя");
            assertNull(emptyResult);

        } finally {
            // Чистим тестовые данные
            for (Employee e : employees) {
                employeeDAO.delete(e);
            }
        }
    }


}