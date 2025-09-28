package  com.berezin.WebPrak.DAO;

import org.hibernate.Session;
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
public class CommonDAOTest {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    void getByIdTest() {
        Employee employee;

        employee = employeeDAO.getById(1);
        assertNotNull(employee);
        assertNotNull(employee.getName());
        assertEquals("Иван Иванов", employee.getName());
    }

    @Test
    void getAllTest() {
        List<Employee> employeeList = (List<Employee>) employeeDAO.getAll();
        assertEquals(8, employeeList.size());
    }

    @Test
    void saveDeleteTest() {
        Employee employee = new Employee();
        employee.setName("Тест Тестов");
        employee.setBirthDay(LocalDate.of(1990, 1, 1));

        // Сохраняем в БД
        employeeDAO.save(employee);
        assertNotNull(employee.getId()); // Hibernate должен проставить ID после save

        // Проверяем, что сотрудник появился в базе
        Employee fromDb = employeeDAO.getById(employee.getId());
        assertNotNull(fromDb);
        assertEquals("Тест Тестов", fromDb.getName());

        // Удаляем по ID
        employeeDAO.deleteById(employee.getId());

        // Проверяем, что сотрудник удалён
        Employee deleted = employeeDAO.getById(employee.getId());
        assertNull(deleted);
    }

    @Test
    void updateTest() {
        Employee employee = employeeDAO.getById(1);
        assertNotNull(employee);

        // Сохраняем старое имя
        String oldName = employee.getName();


        // Меняем имя
        employee.setName("Новое Имя");
        employeeDAO.update(employee);

        // Получаем снова и проверяем
        Employee updated = employeeDAO.getById(1);
        assertNotNull(updated);
        assertEquals("Новое Имя", updated.getName());

        // Возвращаем старое имя, чтобы не нарушить тестовые данные
        employee.setName(oldName);
        employeeDAO.update(employee);
    }

    @Test
    void saveCollectionTest() {
        Employee e1 = new Employee();
        e1.setName("Игорь Подзалупкин");
        e1.setBirthDay(LocalDate.of(1990, 1, 1));

        Employee e2 = new Employee();
        e2.setName("Иван Ананович");
        e2.setBirthDay(LocalDate.of(1991, 1, 1));

        List<Employee> employees = List.of(e1, e2);

        employeeDAO.saveCollection(employees);

        for (Employee e: employees) {
            assertNotNull(e.getId());
            Employee fromDb = employeeDAO.getById(e.getId());
            assertNotNull(fromDb);
        }

        for (Employee e: employees) {
            employeeDAO.delete(e);
        }

    }


}