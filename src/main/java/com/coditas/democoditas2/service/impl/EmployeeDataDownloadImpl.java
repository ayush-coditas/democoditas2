package com.coditas.democoditas2.service.impl;

import com.coditas.democoditas2.domain.Employee;
import com.coditas.democoditas2.dto.EmployeeDTO;
import com.coditas.democoditas2.mapper.EmployeeMapper;
import com.coditas.democoditas2.repository.EmployeeRepository;
import com.coditas.democoditas2.service.EmployeeDataDownload;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDataDownloadImpl implements EmployeeDataDownload {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    private String[] columns = {"first_name", "last_name", "phone", "email", "date_of_birth", "created_by", "created_date", "updated_by", "updated_date"};
    private List<Employee> employees = new ArrayList<>();
    private List<EmployeeDTO> employeeDTOList = new ArrayList<>();
    private final Logger log = LoggerFactory.getLogger(EmployeeDataDownloadImpl.class);

    @Override
    public void downloadWorkbook() throws IOException {
        log.info("Executing downloadWorkbook at: {}", Instant.now());

        log.info("Creating new Workbook");

        Workbook workbook = new XSSFWorkbook();

        CreationHelper createHelper = workbook.getCreationHelper();
        log.info("Creating new Sheet in Workbook");

        Sheet sheet = workbook.createSheet("employee");
        log.info("Setting up font for header");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        log.info("Creating Row Header for xlsx");

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        List<Employee> employeeList = employeeRepository.findAll();

        log.info("Adding data in xlsx rows");
        int rowNum = 1;
        for (Employee employee : employeeList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(employee.getFirstName());

            row.createCell(1)
                    .setCellValue(employee.getLastName());

            row.createCell(2)
                    .setCellValue(employee.getPhone());

            row.createCell(3)
                    .setCellValue(employee.getEmail());

            row.createCell(4)
                    .setCellValue(employee.getDateOfBirth().toString());

            row.createCell(5)
                    .setCellValue(employee.getCreatedBy());

            row.createCell(6)
                    .setCellValue(employee.getCreatedDate().toString());

            row.createCell(7)
                    .setCellValue(employee.getUpdatedBy());

            row.createCell(8)
                    .setCellValue(employee.getUpdatedDate().toString());
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream("src/main/resources/files/employee.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

    }
}
