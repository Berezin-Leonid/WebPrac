package com.berezin.WebPrak.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.berezin.WebPrak.models.Employee;
import com.berezin.WebPrak.DAO.EmployeeDAO;
import com.berezin.WebPrak.DAO.impl.EmployeeDAOImpl;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @GetMapping("/employees")
    public String employeesListPage(Model model) {
        List<Employee> employees = (List<Employee>) employeeDAO.getAll();
        model.addAttribute("employees", employees);
        model.addAttribute("employeeService", employeeDAO);
        return "employees";
    }

    @GetMapping("/employee")
    public String employeePage(@RequestParam(name = "employeeId") Integer employeeId, Model model) {
        Employee employee = employeeDAO.getById(employeeId);

        if (employee == null) {
            model.addAttribute("error_msg", "В базе нет человека с ID: " + employeeId);
            return "errorPage";
        }

        model.addAttribute("employee", employee);
        model.addAttribute("employeeService", employeeDAO);
        return "employee";
    }

    @GetMapping("/employee/add")
    public String addEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeDAO.save(employee);
        return "redirect:/employees";
    }

    @PostMapping("/employee/delete")
    public String deleteEmployee(@RequestParam("employeeId") Integer employeeId, RedirectAttributes redirectAttributes) {
        Employee employee = employeeDAO.getById(employeeId);

        if (employee == null) {
            redirectAttributes.addFlashAttribute("error_msg", "Сотрудник с ID " + employeeId + " не найден");
            return "redirect:/employees";
        }

        employeeDAO.delete(employee);
        redirectAttributes.addFlashAttribute("success_msg", "Сотрудник успешно удалён");
        return "redirect:/employees";
    }


}