package  com.berezin.WebPrak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.berezin.WebPrak.models.Employee;

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




}