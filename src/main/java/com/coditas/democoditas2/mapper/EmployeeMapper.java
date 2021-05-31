package com.coditas.democoditas2.mapper;

import com.coditas.democoditas2.domain.Employee;
import com.coditas.democoditas2.dto.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EmployeeMapper {
    private final Logger log = LoggerFactory.getLogger(AddressMapper.class);

    @Autowired
    private AddressMapper addressMapper;

    public Employee toDomain(EmployeeDTO employeeDTO) {
//        log.info("Mapping Employee Domain to EmployeeDTO");
        if (employeeDTO != null) {
            Employee employee = new Employee();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setPhone(employeeDTO.getPhone());
            employee.setEmail(employeeDTO.getEmail());
            employee.setDateOfBirth(employeeDTO.getDateOfBirth());
            employee.setCreatedBy(employeeDTO.getCreatedBy());
            employee.setCreatedDate(employeeDTO.getCreatedDate());
            employee.setUpdatedBy(employeeDTO.getUpdatedBy());
            employee.setUpdatedDate(employeeDTO.getUpdatedDate());
            employee.setAddress(employeeDTO.getAddress().stream().map(addressMapper::toDomain).collect(Collectors.toList()));
            return employee;
        }
        return null;
    }

    public EmployeeDTO toDTO(Employee employee) {
//        log.info("Mapping EmployeeDTO to Employee Domain");
        if (employee != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setPhone(employee.getPhone());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setDateOfBirth(employee.getDateOfBirth());
            employeeDTO.setCreatedBy(employee.getCreatedBy());
            employeeDTO.setCreatedDate(employee.getCreatedDate());
            employeeDTO.setUpdatedBy(employee.getUpdatedBy());
            employeeDTO.setUpdatedDate(employee.getUpdatedDate());
            employeeDTO.setAddress(employee.getAddress().stream().map(addressMapper::toDTO).collect(Collectors.toList()));

            return employeeDTO;
        }
        return null;
    }
}
