package com.coditas.democoditas2.service;

import com.coditas.democoditas2.dto.AddressDTO;
import com.coditas.democoditas2.dto.CustomResponseDTO;
import com.coditas.democoditas2.dto.EmployeeDTO;
import com.coditas.democoditas2.dto.SearchCriteriaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.util.List;

public interface EmployeeService {
    CustomResponseDTO saveEmployee(EmployeeDTO employeeDTO);

    CustomResponseDTO updateEmployee(EmployeeDTO employeeDTO);

    CustomResponseDTO addAddress(int id, AddressDTO addressDTO);

    CustomResponseDTO updateAddress(int id, AddressDTO addressDTO);

    CustomResponseDTO getEmployeeById(int id);

    List<CustomResponseDTO> allEmployees(Pageable pageable, @Nullable SearchCriteriaDTO searchCriteriaDTO);


    CustomResponseDTO deleteEmployee(int id);
}
