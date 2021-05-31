package com.coditas.democoditas2.validation;

import com.coditas.democoditas2.domain.Employee;
import com.coditas.democoditas2.dto.CustomResponseDTO;
import com.coditas.democoditas2.dto.EmployeeDTO;

import java.text.ParseException;
import java.util.List;

public interface EmployeeValidatorService {
     CustomResponseDTO validateEmployee(EmployeeDTO employee);
}
