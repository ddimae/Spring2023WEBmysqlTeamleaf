package ntukhpi.semit.dde.CommonSpring2023.controller;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import ntukhpi.semit.dde.CommonSpring2023.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    // handler method to handle list students and return mode and view
    @GetMapping("/")
    public String helloPage() {
        return "hellopage";
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }
	
	@GetMapping("/employees/new")
    public String createEmployeeForm(Model model) {
        // create Employee object to hold employee form data
        Employee employee = new Employee(1l,"",true,18,10000.);
        model.addAttribute("employee", employee);
        return "create_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id,
                                @ModelAttribute("employee") Employee employee,
                                Model model) {
        // get Employee from database by id
        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setId(id);
        //LOOK aT YOUR CLASS!!!!!!!
        existingEmployee.setName(employee.getName());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setPol(employee.isPol());
        existingEmployee.setSalary(employee.getSalary());
        // save updated Employee object
        employeeService.updateEmployee(existingEmployee);
        return "redirect:/employees";
    }

    // handler method to handle delete Employee request
    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }


}
