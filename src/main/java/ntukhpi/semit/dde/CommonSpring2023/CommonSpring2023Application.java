package ntukhpi.semit.dde.CommonSpring2023;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import ntukhpi.semit.dde.CommonSpring2023.repository.EmployeeRepository;
import ntukhpi.semit.dde.CommonSpring2023.service.EmployeeService;
import ntukhpi.semit.dde.CommonSpring2023.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CommonSpring2023Application {

	public static void main(String[] args) {

		SpringApplication.run(CommonSpring2023Application.class, args);
	}

	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeRepository employeeRepository;
	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
//			System.out.println("In CommandLineRunnerImpl ");
//			TestEmployee();
		};
	}

	private void TestEmployee() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Test Employee Spring ");
		employeeService = new EmployeeServiceImpl(employeeRepository);
		String[] names = new String[]{"Zhuk", "Kot", "Gusin", "Zhatova", "Shatova", "Svatok", "Katz", "Kotov", "Lomov", "Popova"};
		employeeService.saveEmployee(new Employee(0l, names[0], true, 35, 120000.0));
		//session.save(new Employee(0l, "Kot", true, 51, 2500000.0));
		employeeService.saveEmployee(new Employee(0l, names[1], true, 55, 55000.0));
		//session.save(new Employee(0l, "Gus'", true, 60, 99000.0));
		employeeService.saveEmployee(new Employee(0l, names[2], true, 34, 99000.0));
		employeeService.saveEmployee(new Employee(0l, names[3], false, 28, 99000.0));
		employeeService.saveEmployee(new Employee(0l, names[4], false, 29, 99000.0));
		employeeService.saveEmployee(new Employee(0l, names[5], true, 32, 70000.0));
		employeeService.saveEmployee(new Employee(0l, names[6], true, 37, 75000.0));
		employeeService.saveEmployee(new Employee(0l, names[7], true, 39, 55000.0));
		employeeService.saveEmployee(new Employee(0l, names[8], true, 33, 90000.0));
		employeeService.saveEmployee(new Employee(0l, names[9], false, 32, 50000.0));

		System.out.println("\nEmploee list in spvr.employees");
		List<Employee> employee =  employeeService.getAllEmployees();
		employee.stream().forEach(System.out::println);


		System.out.println("END SHOW employees WITH Spring");
	}

}
