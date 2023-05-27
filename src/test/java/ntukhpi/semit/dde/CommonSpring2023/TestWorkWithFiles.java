package ntukhpi.semit.dde.CommonSpring2023;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import ntukhpi.semit.dde.CommonSpring2023.repository.EmployeeRepository;
import ntukhpi.semit.dde.CommonSpring2023.service.EmployeeService;
import ntukhpi.semit.dde.CommonSpring2023.service.impl.EmployeeServiceImpl;
import ntukhpi.semit.dde.CommonSpring2023.utils.EmailSender;
import ntukhpi.semit.dde.CommonSpring2023.utils.ExcelUtilities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestWorkWithFiles {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Test
    String saveToWBExcel(){
        employeeService = new EmployeeServiceImpl(employeeRepository);
        List<Employee> employeeList = employeeService.getAllEmployees();
        employeeList.stream().forEach(System.out::println);
        try {
            return ExcelUtilities.saveToWBExcel(employeeList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSendEmail() {
        EmailSender.sendEmail("ddimae72@gmail.com", "test_file_from_results.txt");
    }

    @Test
    void sendListToMail(){
        String filename = null;
        filename = saveToWBExcel();
        Path path = Paths.get(filename);
        EmailSender.sendEmail("ddimae72@gmail.com", path.getFileName().toString());
    }

    @Test
    void testReadExcelFile(){
        try {
            List<Employee> list = ExcelUtilities.readFromWBExcel(ExcelUtilities.RESULTS_FOLDER+"employees.xlsx");
            list.stream().forEach(System.out::println);
            System.out.println("ВІДНОВЛЕНЕ!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testWriteReadExcelFile(){
        //!!!! ЗАКРИЙТЕ ФАЙЛ /RESULTS/EMPLOYEES.XLSX  !!!!!
        System.out.println("============ start Save - Restore =============");
        saveToWBExcel();
        testReadExcelFile();

    }

    @Test
    void testReadExcelFileAndSaveToDB() {
        List<Employee> listFromExcel = new ArrayList<>();
        try {
            listFromExcel = ExcelUtilities.readFromWBExcel(ExcelUtilities.RESULTS_FOLDER+"employees2.xlsx");
            listFromExcel.stream().forEach(System.out::println);
            System.out.println("ВІДНОВЛЕНЕ!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        employeeService.saveEployeesToDB(listFromExcel);
        System.out.println("ЗБЕРЕЖЕНЕ!!!");
        List<Employee> listFromDB = employeeService.getAllEmployees();
        listFromExcel.stream().forEach(System.out::println);
        System.out.println("кІНЕЦЬ!!!");

    }


}
