package com.coditas.democoditas2.scheduler;

import com.coditas.democoditas2.service.EmailService;
import com.coditas.democoditas2.service.EmployeeDataDownload;
import com.coditas.democoditas2.service.EmployeeToCsv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;

@Service
@EnableScheduling
public class Scheduler {
    @Autowired
    EmailService emailService;

    @Autowired
    EmployeeDataDownload employeeDataDownload;

    @Autowired
    EmployeeToCsv employeeToCsv;

    private final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(cron = "0 */55 * * * ?")
    public void EmailEmployeeData() throws IOException {
        log.info("Scheduler Running at: {}", Instant.now());
        employeeDataDownload.downloadWorkbook();
        employeeToCsv.downloadCsvWorkbook();
        emailService.sendEmail("src/main/resources/files/employee.csv");
        emailService.sendEmail("src/main/resources/files/employee.xlsx");



    }

}
