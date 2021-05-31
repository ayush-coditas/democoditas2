package com.coditas.democoditas2.impl;

import com.coditas.democoditas2.service.impl.EmployeeDataDownloadImpl;
import com.coditas.democoditas2.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.coditas.democoditas2.repository.EmployeeRepository;
import com.coditas.democoditas2.builder.MockBuilder;


import static org.mockito.MockitoAnnotations.initMocks;


public class EmployeeDataDownloadImplTest {

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @InjectMocks
    EmployeeDataDownloadImpl employeeDataDownload;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void downloadWorkbookTest() {
        Mockito.when(employeeRepository.findAll()).thenReturn(MockBuilder.getEmployeeList());

    }


}
