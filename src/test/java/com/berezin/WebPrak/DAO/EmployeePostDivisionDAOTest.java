package  com.berezin.WebPrak.DAO;


import com.berezin.WebPrak.models.EmployeePostDivision;
import com.berezin.WebPrak.models.PostDivision;
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
class EmployeePostDivisionDAOTest {

    @Autowired
    private EmployeePostDivisionDAO employeePostDivisionDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private PostDivisionDAO postDivisionDAO; // предполагаем, что есть DAO для PostDivision



    @Test
    void getAllPostDivisionExistingDataTest() {
        // Проверим данные для сотрудника с employee_id = 1
        Integer employeeId = 1;

        List<EmployeePostDivision> result = employeePostDivisionDAO.getAllPostDivision(employeeId);

        assertNotNull(result, "Результат не должен быть null");
        assertFalse(result.isEmpty(), "Результат не должен быть пустым");

        // Проверим, что все записи принадлежат нужному сотруднику
        assertTrue(result.stream().allMatch(epd -> epd.getEmployee().getId().equals(employeeId)),
                "Все записи должны принадлежать сотруднику с ID = " + employeeId);

        // Для наглядности можно вывести PostDivision
        result.forEach(epd ->
                System.out.println("Employee ID: " + epd.getEmployee().getId() +
                        ", PostDivision ID: " + epd.getPostDivision().getId())
        );
    }
}

/*
    @Test
    void getAllPostDivisionTest() {
        // Создаём сотрудника
        Employee employee = new Employee();
        employee.setName("Иван Иванов");
        employee.setBirthDay(LocalDate.of(1990, 1, 1));
        employeeDAO.save(employee);

        // Создаём посты и подразделения
        PostDivision pd1 = new PostDivision();
        pd1.setName("Менеджер - Отдел продаж");
        postDivisionDAO.save(pd1);

        PostDivision pd2 = new PostDivision();
        pd2.setName("Инженер - Отдел разработки");
        postDivisionDAO.save(pd2);

        // Связываем сотрудника с постами через EmployeePostDivision
        EmployeePostDivision epd1 = new EmployeePostDivision();
        epd1.setEmployee(employee);
        epd1.setPostDivision(pd1);
        epd1.setHireDate(LocalDate.of(2020, 1, 1));
        employeePostDivisionDAO.save(epd1);

        EmployeePostDivision epd2 = new EmployeePostDivision();
        epd2.setEmployee(employee);
        epd2.setPostDivision(pd2);
        epd2.setHireDate(LocalDate.of(2021, 2, 2));
        employeePostDivisionDAO.save(epd2);

        try {
            // Проверяем метод getAllPostDivision
            List<EmployeePostDivision> result = employeePostDivisionDAO.getAllPostDivision(employee.getId());

            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.stream().allMatch(epd -> epd.getEmployee().getId().equals(employee.getId())));

            // Проверяем, что можно получить PostDivision из результата
            List<PostDivision> postDivisions = result.stream()
                    .map(EmployeePostDivision::getPostDivision)
                    .toList();

            assertTrue(postDivisions.contains(pd1));
            assertTrue(postDivisions.contains(pd2));

        } finally {
            // Чистим тестовые данные
            employeePostDivisionDAO.delete(epd1);
            employeePostDivisionDAO.delete(epd2);
            postDivisionDAO.delete(pd1);
            postDivisionDAO.delete(pd2);
            employeeDAO.delete(employee);
        }
    }

 */

