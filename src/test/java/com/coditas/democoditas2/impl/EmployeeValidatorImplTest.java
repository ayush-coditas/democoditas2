package com.coditas.democoditas2.impl;

import com.coditas.democoditas2.service.impl.EmployeeDataDownloadImpl;
import com.coditas.democoditas2.validation.impl.EmployeeValidatorImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeValidatorImplTest {

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @InjectMocks
    EmployeeValidatorImpl employeeValidator;



}
