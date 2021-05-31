package com.coditas.democoditas2.service.impl;

import com.coditas.democoditas2.domain.Employee;
import com.coditas.democoditas2.repository.EmployeeRepository;
import com.coditas.democoditas2.service.EmployeeToCsv;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeToCsvImpl implements EmployeeToCsv {

    @Autowired
    EmployeeRepository employeeRepository;

    private final Logger log = LoggerFactory.getLogger(EmployeeToCsvImpl.class);

    @Override
    public void downloadCsvWorkbook() throws IOException {
        log.info("Executing download csv workbook at {}", Instant.now());
        String[] csvHeader = {"first_name", "last_name", "phone", "email", "date_of_birth", "created_by", "created_date", "updated_by", "updated_date"};
        List<Employee> employeeList = employeeRepository.findAll();

        List<String[]> rows = new ArrayList<String[]>();

        rows.add(csvHeader);
        for (Employee employee : employeeList
        ) {
            String[] databaseRow = {employee.getFirstName(), employee.getLastName(), employee.getPhone(), employee.getEmail(), employee.getDateOfBirth(), employee.getCreatedBy(), String.valueOf(employee.getCreatedDate()), employee.getUpdatedBy(), String.valueOf(employee.getUpdatedDate())};
            rows.add(databaseRow);
        }

        FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/files/employee.csv");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        CSVWriter csvWriter = new CSVWriter(outputStreamWriter);
        csvWriter.writeAll(rows);
        csvWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();

    }
}
