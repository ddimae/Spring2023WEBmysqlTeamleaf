package ntukhpi.semit.dde.CommonSpring2023.controller;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import ntukhpi.semit.dde.CommonSpring2023.entity.INN;
import ntukhpi.semit.dde.CommonSpring2023.entity.Phone;
import ntukhpi.semit.dde.CommonSpring2023.service.EmployeeService;
import ntukhpi.semit.dde.CommonSpring2023.service.INNService;
import ntukhpi.semit.dde.CommonSpring2023.service.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EmployeeController {

    // handler method to handle list students and return mode and view
    @GetMapping("/")
    public String helloPage() {
        return "hellopage";
    }

    private final EmployeeService employeeService;
    private final INNService innService;
    private final PhoneService phoneService;

    // Insert into constructor NESSESARY
    public EmployeeController(EmployeeService employeeService, INNService innService, PhoneService phoneService) {
        super();
        this.employeeService = employeeService;
        this.innService = innService;
        this.phoneService = phoneService;
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

    //-------------------   For work with INN --------------------------

    // handler method to handle Info button click
    @GetMapping("/employees/info/{id}")
    public String infoEmployeeForm(@PathVariable Long id, Model model) {
        Employee owner = employeeService.getEmployeeById(id);
        //System.out.println(owner);
        INN inn = innService.getINNByOwner(owner);
        if (inn==null) {
            // create INN object to hold inn form data
            inn = new INN(-1l, 2000000000l, "",
                    LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,1,1),
                    owner);
        }
        //System.out.println(inn);
        model.addAttribute("inn", inn);
        model.addAttribute("empl", owner);
        model.addAttribute("editmode", false);
        return "info_employee";
    }

    // handler method to handle Update and Add Inn button click
    @GetMapping("/employees/info/{id}/edit")
    public String editINNMode(@PathVariable Long id, Model model) {
        Employee owner = employeeService.getEmployeeById(id);
        //System.out.println(owner);
        INN inn = innService.getINNByOwner(owner);
        if (inn==null) {
            // create INN object to hold inn form data
            inn = new INN(-1l, 2000000000l, "",
                    LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,1,1),
                    owner);
        }
        //System.out.println(inn);
        model.addAttribute("inn", inn);
        model.addAttribute("empl", owner);
        model.addAttribute("editmode", true);
        return "info_employee";
    }

    // handler method to handle Save button click
    @PostMapping("/employees/info/{id}/confirm")
    public String confirmEditINN(@PathVariable Long id,
                                 @ModelAttribute("inn") INN inn,
                                 Model model) {
        Employee owner = employeeService.getEmployeeById(id);
        INN innToUpdate = innService.getINNByOwner(owner);
        if (innToUpdate!=null) {
            //LOOK aT YOUR CLASS!!!!
            innToUpdate.setNumber(inn.getNumber());
            innToUpdate.setDateIssued(inn.getDateIssued());
            innToUpdate.setIssued(inn.getIssued());
            // save updated innToUpdate object
            innService.updateINN(innToUpdate);
        } else {
            inn.setOwner(owner);
            innService.insert(inn);
        }
        return "redirect:/employees/info/{id}";
    }

    // handler method to handle delete inn request
    @PostMapping("/employees/info/{id}")
    public String deleteINN(@PathVariable Long id) {
        Employee owner = employeeService.getEmployeeById(id);
        INN innToDelete = innService.getINNByOwner(owner);
        innService.deleteINNById(innToDelete.getId());
        return "redirect:/employees/info/{id}";
    }

    //-------------------   For work with PHONES --------------------------
    // handler method to handle Phones button click
    @GetMapping("/employees/phones/{id}")
    public String phonesEmployeeForm(@PathVariable Long id, Model model) {
        Employee owner = employeeService.getEmployeeById(id);
        System.out.println(owner);
        List<Phone> phones = phoneService.getPhonesByOwner(owner);
        System.out.println(phones);
        model.addAttribute("phones", phones);
        model.addAttribute("empl", owner);
        //model.addAttribute("editmode", false);
        return "phones_employee";
    }
}




