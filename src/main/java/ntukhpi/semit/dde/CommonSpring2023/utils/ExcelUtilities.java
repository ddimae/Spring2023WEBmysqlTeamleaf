package ntukhpi.semit.dde.CommonSpring2023.utils;

import lombok.extern.log4j.Log4j2;
import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class ExcelUtilities {

    public static final String RESULTS_FOLDER = "results/";
    public static final String ATTACHMENT_FILENAME = "attachment; filename=\"";

    public static String saveToWBExcel(List<Employee> list) throws IOException {
//Create empty workbook Excel
        Workbook workbook = new XSSFWorkbook();
//        Create sheet
        Sheet sheet = workbook.createSheet("Співробітники " + LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MMM-dd")));

        // Стилі комірок
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setBorderTop(BorderStyle.THIN);
        boldStyle.setBorderBottom(BorderStyle.THIN);
        boldStyle.setBorderLeft(BorderStyle.THIN);
        boldStyle.setBorderRight(BorderStyle.THIN);
        boldStyle.setFont(font);
        boldStyle.setAlignment(HorizontalAlignment.CENTER);
//      Add rows in table (Header absent)
        int headerRows = 0;
        // add header
        int rowIndex = 0;
        for (Employee employee : list) {
            Row row = sheet.createRow(headerRows + rowIndex++);
            Cell number = row.createCell(0);
            number.setCellStyle(style);
            number.setCellValue(rowIndex);
            //addRow(row, employee, style);
            Cell nameCell = row.createCell(1);
            nameCell.setCellStyle(style);
            nameCell.setCellValue(employee.getName());

            Cell ageCell = row.createCell(2);
            ageCell.setCellStyle(style);
            ageCell.setCellValue(Double.valueOf(employee.getAge()));

            Cell polCell = row.createCell(3);
            polCell.setCellStyle(style);
            polCell.setCellValue(employee.isPol() ? "mail" : "femail");

            Cell salaryCell = row.createCell(4);
            salaryCell.setCellStyle(style);
            salaryCell.setCellValue(Double.valueOf(employee.getSalary()));
        }
//      Create file for save excel workbook
        String filename = "Співробітники.xlsx";
        Path filePath = Paths.get(ExcelUtilities.RESULTS_FOLDER +filename);//
        try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
            workbook.write(outputStream);
        }
      workbook.close(); //!!!!
        return filePath.toAbsolutePath().toString();
    }

    public static void addRow(Row row, Employee employee, CellStyle style) {

        Cell nameCell = row.createCell(1);
        nameCell.setCellStyle(style);
        nameCell.setCellValue(employee.getName());

        Cell ageCell = row.createCell(2);
        ageCell.setCellStyle(style);
        ageCell.setCellValue(Double.valueOf(employee.getAge()));

        Cell polCell = row.createCell(3);
        polCell.setCellStyle(style);
        polCell.setCellValue(employee.isPol() ? "mail" : "femail");

        Cell salaryCell = row.createCell(4);
        salaryCell.setCellStyle(style);
        salaryCell.setCellValue(Double.valueOf(employee.getSalary()));
    }

    public static List<Employee> readFromWBExcel(String filename) throws IOException {
        List<Employee> list = new ArrayList<>();
        Path path = Paths.get(RESULTS_FOLDER+filename); //отримуємо шлях до файлу
        FileInputStream inputStream = new FileInputStream(path.toFile());
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // отримуємо перший аркуш
        for (Row row : sheet) {
            //Student student = new Student(row);
            //Комірка 1 - Name
            String name = row.getCell(1).getStringCellValue();
            //Комірка 2 - age
            int age = (int) row.getCell(2).getNumericCellValue();
            //Комірка 3 - pol - if mail ==> true, otherwise (femail) ==> false
            boolean pol = "mail".equals(row.getCell(3).getStringCellValue());
            //Комірка 4 - salary
            double salary =row.getCell(4).getNumericCellValue();
            //Создаем сотрудника и в коллекцию
            list.add(new Employee(0l, name, pol, age, salary));
        }
        workbook.close();

        return list;
    }

}
