package com.coditas.democoditas2.controller;

import com.coditas.democoditas2.dto.AddressDTO;
import com.coditas.democoditas2.dto.CustomResponseDTO;
import com.coditas.democoditas2.dto.EmployeeDTO;
import com.coditas.democoditas2.dto.SearchCriteriaDTO;
import com.coditas.democoditas2.repository.EmployeeRepository;
import com.coditas.democoditas2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public CustomResponseDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.saveEmployee(employeeDTO);
    }

    @PostMapping("/update")
    public CustomResponseDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO);
    }

    @PostMapping("/addaddress")
    public CustomResponseDTO addAddress(@RequestParam int id, @RequestBody AddressDTO addressDTO) {
        return employeeService.addAddress(id, addressDTO);
    }

    @PostMapping("/updateaddress")
    public CustomResponseDTO updateAddress(@RequestParam int id, @RequestBody AddressDTO addressDTO) {
        return employeeService.updateAddress(id, addressDTO);
    }

    @GetMapping("/employeebyid")
    public CustomResponseDTO getEmployeeById(@RequestParam int id) {
        return employeeService.getEmployeeById(id);
    }


    @RequestMapping(value = "/allemployees", method = RequestMethod.GET)
    @GetMapping
    public List<CustomResponseDTO> allEmployees(@RequestBody(required = false) SearchCriteriaDTO searchCriteriaDTO, Pageable pageable) {
        return employeeService.allEmployees(pageable, searchCriteriaDTO);

    }

    @DeleteMapping("/delete")
    public CustomResponseDTO deleteEmployee(@RequestParam int id) {
        return employeeService.deleteEmployee(id);
    }
}
