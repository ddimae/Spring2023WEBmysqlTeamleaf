package ntukhpi.semit.dde.CommonSpring2023.controller;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import ntukhpi.semit.dde.CommonSpring2023.service.EmployeeService;
import ntukhpi.semit.dde.CommonSpring2023.utils.EmailSender;
import ntukhpi.semit.dde.CommonSpring2023.utils.ExcelUtilities;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ntukhpi.semit.dde.CommonSpring2023.utils.EmailSender.rfc5987_encode;
import static ntukhpi.semit.dde.CommonSpring2023.utils.ExcelUtilities.RESULTS_FOLDER;
import static ntukhpi.semit.dde.CommonSpring2023.utils.ExcelUtilities.readFromWBExcel;

@Controller
public class FilesController {
    @Autowired
    private EmployeeService employeeService;

    public FilesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/download")
    public ResponseEntity download() throws IOException {
        List<Employee> employeeList = employeeService.getAllEmployees();
        String fullfilename = ExcelUtilities.saveToWBExcel(employeeList);//"employees.xlsx";
        Path path = Paths.get(fullfilename);
        String filename = path.getFileName().toString();
        System.out.println(filename);
        File file = new File(ExcelUtilities.RESULTS_FOLDER + filename);
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, ExcelUtilities.ATTACHMENT_FILENAME + rfc5987_encode(filename) + "\"")
                .body(FileUtils.readFileToByteArray(file));
    }

    // https://spring.io/guides/gs/uploading-files/
    @PostMapping("/employees/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path path = Paths.get(RESULTS_FOLDER+fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filenameWBToSaveInDB = file.getOriginalFilename();
        String[] parts = filenameWBToSaveInDB.split("\\.");
        if (!parts[1].equals("xlsx")) {
            return "redirect:/employees";
        }
        List<Employee> listEmployeeFromExcel;
        try {
            listEmployeeFromExcel = readFromWBExcel(filenameWBToSaveInDB);
            employeeService.saveEployeesToDB(listEmployeeFromExcel);
        } finally {
            return "redirect:/employees";
        }
    }

//    @PostMapping("/employees/upload")
//    public String upload(@RequestParam("file") MultipartFile file) {
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        Path path = Paths.get(fileName);
//        try {
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return readExcel(file.getOriginalFilename());
//    }
//
//    @RequestMapping("/read_excel")
//    public String readExcel(@RequestParam("path") String path) {
//        String[] parts = path.split("\\.");
//        if (!parts[1].equals("xlsx")) {
//            return "redirect:/employees";
//        }
//        List<Employee> listFromExcel = new ArrayList<>();
//        try {
//            listFromExcel = readFromWBExcel(path);
//            employeeService.saveEployeesToDB(listFromExcel);
//        } finally {
//            return "redirect:/employees";
//        }
//    }

    @PostMapping("/employees/send_email")
    public String sendEmail(@RequestParam("email") String emailTo) throws IOException {
        if (emailTo!=null&&emailTo.length()>0) {
            List<Employee> employeeList = employeeService.getAllEmployees();
            String fullfilename = ExcelUtilities.saveToWBExcel(employeeList);//"employees.xlsx";
            System.out.println(fullfilename);
            Path path = Paths.get(fullfilename);
            String filename = path.getFileName().toString();
            System.out.println(filename);
            EmailSender.sendEmail(emailTo, filename);
        }
        return "redirect:/employees";
    }

}
