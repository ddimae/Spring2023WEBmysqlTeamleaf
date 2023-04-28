package ntukhpi.semit.dde.CommonSpring2023;

import ntukhpi.semit.dde.CommonSpring2023.entity.*;
import ntukhpi.semit.dde.CommonSpring2023.repository.EmployeeRepository;
import ntukhpi.semit.dde.CommonSpring2023.repository.INNRepository;
import ntukhpi.semit.dde.CommonSpring2023.repository.PhoneRepository;
import ntukhpi.semit.dde.CommonSpring2023.repository.TeamRepository;
import ntukhpi.semit.dde.CommonSpring2023.service.EmployeeService;
import ntukhpi.semit.dde.CommonSpring2023.service.INNService;
import ntukhpi.semit.dde.CommonSpring2023.service.PhoneService;
import ntukhpi.semit.dde.CommonSpring2023.service.TeamService;
import ntukhpi.semit.dde.CommonSpring2023.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CommonSpring2023Application {

    public static void main(String[] args) {

        SpringApplication.run(CommonSpring2023Application.class, args);
    }

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    INNRepository innRepository;
    @Autowired
    INNService innService;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    PhoneService phoneService;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamService teamService;

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
    //        TestEmployeeINNPhoneTeam();
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
        List<Employee> employee = employeeService.getAllEmployees();
        employee.stream().forEach(System.out::println);


        System.out.println("END SHOW employees WITH Spring");
    }

    private void TestEmployeeINN() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Test Employee Spring ");
        employeeService = new EmployeeServiceImpl(employeeRepository);
        String[] names = new String[]{"Zhuk", "Kot", "Gusin", "Zhatova", "Shatova", "Svatok", "Katz", "Kotov", "Lomov", "Popova"};
        try {
            employeeService.saveEmployee(new Employee(0l, names[0], true, 35, 120000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[1], true, 55, 55000.0));
        } catch (Exception ex) {
            System.out.println(names[1] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[2], true, 34, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[2] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[3], false, 28, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[3] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[4], false, 29, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[4] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[5], true, 32, 70000.0));
        } catch (Exception ex) {
            System.out.println(names[5] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[6], true, 37, 75000.0));
        } catch (Exception ex) {
            System.out.println(names[6] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[7], true, 39, 55000.0));
        } catch (Exception ex) {
            System.out.println(names[7] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[8], true, 33, 90000.0));
        } catch (Exception ex) {
            System.out.println(names[8] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[9], false, 32, 50000.0));
        } catch (Exception ex) {
            System.out.println(names[9] + " - not created, present in employees");
        }
        //==========================================================================
        Employee owner = null;
        //=======================  INSERT IN inns ====================================
        try {
            owner = employeeService.getEmployeeByName(names[0]);
            innService.insert(
                    new INN(-1l, 2563474747l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[1]);
            innService.insert(
                    new INN(-1l, 2563272727l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[2]);
            innService.insert(
                    new INN(-1l, 3492767676l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[3]);
            innService.insert(
                    new INN(-1l, 2592292929l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );

        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[4]);
            innService.insert(
                    new INN(-1l, 2533334433l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[5]);
            innService.insert(
                    new INN(-1l, 2593939393l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[6]);
            innService.insert(
                    new INN(-1l, 3562626262l, "Podatkova Dergachivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[7]);
            innService.insert(
                    new INN(-1l, 3583839092l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[8]);
            innService.insert(
                    new INN(-1l, 2626552525l, "Podatkova Slobidskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[9]);
            innService.insert(
                    new INN(-1l, 3578787878l, "Podatkova Dergachivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        //============================================================================
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("\nEmploee list in spvr.employees");
//		employees.stream().forEach(System.out::println);
        employees.stream().forEach((empl) -> {
                    System.out.println(empl);
                    INN inn = innService.getINNByOwner(empl);
                    System.out.println(inn.toStringBase());
                }
        );
//		List<INN> inns =  innService.getAllINNs();
//		System.out.println("SHOW INNs Spring");
//		inns.stream().forEach(System.out::println);

        System.out.println("\n\nEND SHOW WITH Spring");

    }

    public void TestEmployeeINNPhone() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Test Employee+Inn+Phones Spring ");
        //inSERT Employee
        employeeService = new EmployeeServiceImpl(employeeRepository);
        String[] names = new String[]{"Zhuk", "Kot", "Gusin", "Zhatova", "Shatova", "Svatok", "Katz", "Kotov", "Lomov", "Popova"};
        try {
            employeeService.saveEmployee(new Employee(0l, names[0], true, 35, 120000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[1], true, 55, 55000.0));
        } catch (Exception ex) {
            System.out.println(names[1] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[2], true, 34, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[2] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[3], false, 28, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[3] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[4], false, 29, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[4] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[5], true, 32, 70000.0));
        } catch (Exception ex) {
            System.out.println(names[5] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[6], true, 37, 75000.0));
        } catch (Exception ex) {
            System.out.println(names[6] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[7], true, 39, 55000.0));
        } catch (Exception ex) {
            System.out.println(names[7] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[8], true, 33, 90000.0));
        } catch (Exception ex) {
            System.out.println(names[8] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[9], false, 32, 50000.0));
        } catch (Exception ex) {
            System.out.println(names[9] + " - not created, present in employees");
        }
        //==========================================================================
        Employee owner = null;
        //=======================  INSERT IN inns ====================================
        try {
            owner = employeeService.getEmployeeByName(names[0]);
            innService.insert(
                    new INN(-1l, 2563474747l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[1]);
            innService.insert(
                    new INN(-1l, 2563272727l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[2]);
            innService.insert(
                    new INN(-1l, 3492767676l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[3]);
            innService.insert(
                    new INN(-1l, 2592292929l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );

        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[4]);
            innService.insert(
                    new INN(-1l, 2533334433l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[5]);
            innService.insert(
                    new INN(-1l, 2593939393l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[6]);
            innService.insert(
                    new INN(-1l, 3562626262l, "Podatkova Dergachivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[7]);
            innService.insert(
                    new INN(-1l, 3583839092l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[8]);
            innService.insert(
                    new INN(-1l, 2626552525l, "Podatkova Slobidskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[9]);
            innService.insert(
                    new INN(-1l, 3578787878l, "Podatkova Dergachivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        //============================================================================
        //=======================  INSERT IN phones ====================================
        System.out.println("Insert test data in Phones");
        Random rnd = new Random();
        int numPhones;
        boolean activePhone;
        PhoneNumberType[] ptypes = PhoneNumberType.values();
        PhoneNumberType type;
        for (int i = 0; i < 10; i++) {
            owner = employeeService.getEmployeeByName(names[i]);
            List<Phone> phones = phoneService.getPhonesByOwner(owner);
            if (phones.isEmpty()) {
                // numPhones - how much pones will generated
                numPhones = rnd.nextInt(3) + 1;
                for (int j = 0; j < numPhones; j++) {
                    String generatedNums = "";
                    for (int k = 0; k < 9; k++) {
                        generatedNums += rnd.nextInt(10);
                    }
                    activePhone = Math.random() > 0.4999999999;
                    type = ptypes[rnd.nextInt(3)];
                    try {
                        phoneService.insert(
                                new Phone(-1l, "0" + generatedNums, type, activePhone, owner)
                        );
                    } catch (Exception ex) {
                        System.out.println("Phone not insert, maybe it present in Phones");
                    }
                }
            }
        }
        //============================================================================
        //======================== SHOW DATA IN DATABASE =========================
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("\nEmploee list in spvr.employees");
        employees.stream().forEach((empl) -> {
                    //Info about Employee
                    System.out.println(empl);
                    //Info about INN of empl
                    INN inn = innService.getINNByOwner(empl);
                    if (inn != null) {
                        System.out.println(inn.toStringBase());
                    } else {
                        System.out.println("inn absent");
                    }
                    //Employee phones
                    List<Phone> phones = phoneService.getPhonesByOwner(empl);
                    if (phones != null) {
                        phones.stream().forEach((phone) -> System.out.println(phone.toStringBase()));
                    } else {
                        System.out.println("phones absent");
                    }
                    System.out.println();
                }
        );
//        List<Phone> phones = phoneService.getAllPhones();
//        System.out.println("SHOW PHONEs Spring");
////        phones.stream().forEach(System.out::println);
//        phones.stream().forEach((phone) -> System.out.println(phone.toStringBase()));
//        System.out.println("\n\nEND SHOW WITH Spring");
    }

    public void TestEmployeeINNPhoneTeam() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Test Employees+Inn+Phones+Teams Spring ");
        //inSERT Employee
        employeeService = new EmployeeServiceImpl(employeeRepository);
        String[] names = new String[]{"Zhuk", "Kot", "Gusin", "Zhatova", "Shatova", "Svatok", "Katz", "Kotov", "Lomov", "Popova"};
        try {
            employeeService.saveEmployee(new Employee(0l, names[0], true, 35, 120000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[1], true, 55, 55000.0));
        } catch (Exception ex) {
            System.out.println(names[1] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[2], true, 34, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[2] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[3], false, 28, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[3] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[4], false, 29, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[4] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[5], true, 32, 70000.0));
        } catch (Exception ex) {
            System.out.println(names[5] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[6], true, 37, 75000.0));
        } catch (Exception ex) {
            System.out.println(names[6] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[7], true, 39, 55000.0));
        } catch (Exception ex) {
            System.out.println(names[7] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[8], true, 33, 90000.0));
        } catch (Exception ex) {
            System.out.println(names[8] + " - not created, present in employees");
        }
        try {
            employeeService.saveEmployee(new Employee(0l, names[9], false, 32, 50000.0));
        } catch (Exception ex) {
            System.out.println(names[9] + " - not created, present in employees");
        }
        //==========================================================================
        Employee owner = null;
        //=======================  INSERT IN inns ====================================
        try {
            owner = employeeService.getEmployeeByName(names[0]);
            innService.insert(
                    new INN(-1l, 2563474747l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[1]);
            innService.insert(
                    new INN(-1l, 2563272727l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[2]);
            innService.insert(
                    new INN(-1l, 3492767676l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[3]);
            innService.insert(
                    new INN(-1l, 2592292929l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );

        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[4]);
            innService.insert(
                    new INN(-1l, 2533334433l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[5]);
            innService.insert(
                    new INN(-1l, 2593939393l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[6]);
            innService.insert(
                    new INN(-1l, 3562626262l, "Podatkova Dergachivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[7]);
            innService.insert(
                    new INN(-1l, 3583839092l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert created, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[8]);
            innService.insert(
                    new INN(-1l, 2626552525l, "Podatkova Slobidskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = employeeService.getEmployeeByName(names[9]);
            innService.insert(
                    new INN(-1l, 3578787878l, "Podatkova Dergachivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        //============================================================================
        //=======================  INSERT IN phones ====================================
        System.out.println("Insert test data in Phones");
        Random rnd = new Random();
        int numPhones;
        boolean activePhone;
        PhoneNumberType[] ptypes = PhoneNumberType.values();
        PhoneNumberType type;
        for (int i = 0; i < 10; i++) {
            owner = employeeService.getEmployeeByName(names[i]);
            List<Phone> phones = phoneService.getPhonesByOwner(owner);
            if (phones.isEmpty()) {
                // numPhones - how much pones will generated
                numPhones = rnd.nextInt(3) + 1;
                for (int j = 0; j < numPhones; j++) {
                    String generatedNums = "";
                    for (int k = 0; k < 9; k++) {
                        generatedNums += rnd.nextInt(10);
                    }
                    activePhone = Math.random() > 0.4999999999;
                    type = ptypes[rnd.nextInt(3)];
                    try {
                        phoneService.insert(
                                new Phone(-1l, "0" + generatedNums, type, activePhone, owner)
                        );
                    } catch (Exception ex) {
                        System.out.println("Phone not insert, maybe it present in Phones");
                    }
                }
            }
        }
        //============================================================================
        //=======================  INSERT IN teams ====================================
        System.out.println("Insert test data in teams");
        try {
            Team team1 = new Team("Java001", ProgramLanguage.JAVA);
            teamService.saveTeam(team1);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        try {
            Team team2 = new Team("JavaScript", ProgramLanguage.JavaScript);
            teamService.saveTeam(team2);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        try {
            Team team3 = new Team("JavaScript02", ProgramLanguage.JavaScript);
            teamService.saveTeam(team3);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        try {
            Team team4 = new Team("Java002", ProgramLanguage.JAVA);
            teamService.saveTeam(team4);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        try {
            Team team5 = new Team("CSharp2022", ProgramLanguage.CSHARP);
            teamService.saveTeam(team5);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        //============================================================================
        //======================== SHOW DATA IN DATABASE =========================
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("\nEmploee list in spivr.employees");
        employees.stream().forEach((empl) -> {
                    //Info about Employee
                    System.out.println(empl);
                    //Info about INN of empl
                    INN inn = innService.getINNByOwner(empl);
                    if (inn != null) {
                        System.out.println(inn.toStringBase());
                    } else {
                        System.out.println("inn absent");
                    }
                    //Employee phones
                    List<Phone> phones = phoneService.getPhonesByOwner(empl);
                    if (phones != null) {
                        phones.stream().forEach((phone) -> System.out.println(phone.toStringBase()));
                    } else {
                        System.out.println("phones absent");
                    }
                    System.out.println();
                }
        );
        List<Team> teams = teamService.getAllTeams();
        System.out.println("\nTeam list in spivr.teams");
        teams.stream().forEach((team) -> System.out.println(team));
    }


}
