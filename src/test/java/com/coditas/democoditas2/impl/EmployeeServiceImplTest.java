package com.coditas.democoditas2.impl;

import com.coditas.democoditas2.builder.MockBuilder;
import com.coditas.democoditas2.dto.CustomResponseDTO;
import com.coditas.democoditas2.mapper.AddressMapper;
import com.coditas.democoditas2.mapper.EmployeeMapper;
import com.coditas.democoditas2.repository.AddressRepository;
import com.coditas.democoditas2.repository.EmployeeRepository;
import com.coditas.democoditas2.service.impl.EmployeeServiceImpl;
import com.coditas.democoditas2.validation.EmployeeValidatorService;
import com.coditas.democoditas2.validation.impl.EmployeeValidatorImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeServiceImplTest {

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeMapper employeeMapper;

    @Mock
    AddressMapper addressMapper;

    @Mock
    AddressRepository addressRepository;


    @Mock
    EmployeeValidatorService employeeValidatorService;

    @Test
    public void saveEmployeeSuccess() {

        Mockito.when(employeeValidatorService.validateEmployee(MockBuilder.getEmployeeDTO())).thenReturn(new CustomResponseDTO());
//        Mockito.when(customResponseDTO.getStatus()).thenReturn("ERROR");

//        employeeService.saveEmployee(MockBuilder.getEmployeeDTO());

    }
 
    @Test
    public void saveEmployeeInvalidPhone() throws ParseException {
        Mockito.when(employeeMapper.toDomain(MockBuilder.getEmployeeDTO())).thenReturn(MockBuilder.getEmployee());
//        Mockito.when(employeeValidatorService.validateEmployee(MockBuilder.getEmployee())).thenReturn(new CustomResponseDTO());
        Mockito.when(employeeRepository.save(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployee());
        Mockito.when(addressMapper.toDomain(MockBuilder.getAddressDTO())).thenReturn(MockBuilder.getAddress());
        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        Mockito.when(employeeMapper.toDTO(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployeeDTO());
        EmployeeValidatorService employeeValidatorService = new EmployeeValidatorImpl();
//        CustomResponseDTO customResponseDTO = employeeValidatorService.validateEmployee(MockBuilder.getEmployee());
//        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(91));
//        Assert.assertEquals(customResponseDTO.getStatus(), "FAILURE");
//        Assert.assertEquals(customResponseDTO.getMessage(), "GIVEN PHONE NUMBER IS INVALID");

    }

    @Test
    public void saveEmployeeInvalidEmail() throws ParseException {
        Mockito.when(employeeMapper.toDomain(MockBuilder.getEmployeeDTO())).thenReturn(MockBuilder.getEmployee());
        Mockito.when(employeeRepository.save(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployee());
        Mockito.when(addressMapper.toDomain(MockBuilder.getAddressDTO())).thenReturn(MockBuilder.getAddress());
        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        Mockito.when(employeeMapper.toDTO(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployeeDTO());
        EmployeeValidatorService employeeValidatorService = new EmployeeValidatorImpl();
//        CustomResponseDTO customResponseDTO = employeeValidatorService.validateEmployee(MockBuilder.getEmployee());
//        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(92));
//        Assert.assertEquals(customResponseDTO.getStatus(), "FAILURE");
//        Assert.assertEquals(customResponseDTO.getMessage(), "GIVEN EMAIL ADDRESS IS INVALID");

    }

    @Test
    public void saveEmployeeInvalidDOB() throws ParseException {
        Mockito.when(employeeMapper.toDomain(MockBuilder.getEmployeeDTO())).thenReturn(MockBuilder.getEmployee());
        Mockito.when(employeeRepository.save(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployee());
        Mockito.when(addressMapper.toDomain(MockBuilder.getAddressDTO())).thenReturn(MockBuilder.getAddress());
        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        Mockito.when(employeeMapper.toDTO(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployeeDTO());
        EmployeeValidatorService employeeValidatorService = new EmployeeValidatorImpl();
//        CustomResponseDTO customResponseDTO = employeeValidatorService.validateEmployee(MockBuilder.getEmployee());
//        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(93));
//        Assert.assertEquals(customResponseDTO.getStatus(), "FAILURE");
//        Assert.assertEquals(customResponseDTO.getMessage(), "GIVEN DATE OF BIRTH IS INVALID");

    }




    @Test
    public void updateEmployeeSuccess() {
        Mockito.when(addressRepository.findById(Mockito.anyInt())).thenReturn(MockBuilder.getAddressOptional());
        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        Mockito.when(addressMapper.toDTO(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddressDTO());
        CustomResponseDTO customResponseDTO = employeeService.updateAddress(Mockito.anyInt(), MockBuilder.getAddressDTO());
        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(200));
        Assert.assertEquals(customResponseDTO.getStatus(), "SUCCESS");
        Assert.assertEquals(customResponseDTO.getMessage(), "EMPLOYEE ADDRESS HAS BEEN UPDATED");

    }

    @Test
    public void updateEmployeeFailed() {

        Mockito.when(addressRepository.findById(null)).thenReturn(MockBuilder.getAddressOptional());
        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        Mockito.when(addressMapper.toDTO(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddressDTO());
        CustomResponseDTO customResponseDTO = employeeService.updateAddress(Mockito.anyInt(), MockBuilder.getAddressDTO());
        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(404));
        Assert.assertEquals(customResponseDTO.getStatus(), "FAILURE");
        Assert.assertEquals(customResponseDTO.getMessage(), "EMPLOYEE ADDRESS NOT FOUND");
    }

    @Test
    public void addAddressExisting() {
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(MockBuilder.getEmployeeOptional());
        Mockito.when(addressRepository.findByEmployeeIdAndAddressType(Mockito.anyInt(), Mockito.any())).thenReturn(MockBuilder.getAddress());
        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        Mockito.when(addressMapper.toDTO(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddressDTO());
        CustomResponseDTO customResponseDTO = employeeService.addAddress(Mockito.anyInt(), MockBuilder.getAddressDTO());
        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(200));
        Assert.assertEquals(customResponseDTO.getStatus(), "SUCCESS");
        Assert.assertEquals(customResponseDTO.getMessage(), "EMPLOYEE ADDRESS HAS BEEN UPDATED");
    }

    @Test
    public void addAddressNew() {
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(MockBuilder.getEmployeeOptional());
        Mockito.when(addressRepository.findByEmployeeIdAndAddressType(Mockito.anyInt(), Mockito.anyString())).thenReturn(null);
        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        Mockito.when(addressMapper.toDTO(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddressDTO());
        CustomResponseDTO customResponseDTO = employeeService.addAddress(Mockito.anyInt(), MockBuilder.getAddressDTO());
        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(200));
        Assert.assertEquals(customResponseDTO.getStatus(), "SUCCESS");
        Assert.assertEquals(customResponseDTO.getMessage(), "EMPLOYEE ADDRESS HAS BEEN SAVED");

    }

    @Test
    public void addAddressFailed() throws NullPointerException {
        Mockito.when(employeeRepository.findById(null)).thenReturn(MockBuilder.getEmployeeOptional());
        Mockito.when(addressRepository.findByEmployeeIdAndAddressType(Mockito.anyInt(), Mockito.anyString())).thenReturn(null);

        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        Mockito.when(addressMapper.toDTO(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddressDTO());
        CustomResponseDTO customResponseDTO = employeeService.addAddress(Mockito.anyInt(), MockBuilder.getAddressDTO());
        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(404));
        Assert.assertEquals(customResponseDTO.getStatus(), "FAILURE");
        Assert.assertEquals(customResponseDTO.getMessage(), "EMPLOYEE NOT FOUND");
    }

    @Test
    public void updateAddressSuccess() {
        Mockito.when(addressRepository.findById(Mockito.anyInt())).thenReturn(MockBuilder.getAddressOptional());
        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        CustomResponseDTO customResponseDTO = employeeService.updateAddress(Mockito.anyInt(), MockBuilder.getAddressDTO());
        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(200));
        Assert.assertEquals(customResponseDTO.getStatus(), "SUCCESS");
        Assert.assertEquals(customResponseDTO.getMessage(), "EMPLOYEE ADDRESS HAS BEEN UPDATED");

    }

    @Test
    public void updateAddressFailed() {
        Mockito.when(addressRepository.findById(null)).thenReturn(MockBuilder.getAddressOptional());
        Mockito.when(addressRepository.save(MockBuilder.getAddress())).thenReturn(MockBuilder.getAddress());
        CustomResponseDTO customResponseDTO = employeeService.updateAddress(Mockito.anyInt(), MockBuilder.getAddressDTO());
        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(404));
        Assert.assertEquals(customResponseDTO.getStatus(), "FAILURE");
        Assert.assertEquals(customResponseDTO.getMessage(), "EMPLOYEE ADDRESS NOT FOUND");
    }

    @Test
    public void getEmployeeByIdSuccess() {
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(MockBuilder.getEmployeeOptional());
        Mockito.when(employeeMapper.toDTO(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployeeDTO());
        CustomResponseDTO customResponseDTO = employeeService.getEmployeeById(Mockito.anyInt());
        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(200));
        Assert.assertEquals(customResponseDTO.getStatus(), "SUCCESS");
        Assert.assertEquals(customResponseDTO.getMessage(), "EMPLOYEE FOUND");

    }

    @Test
    public void getEmployeeByIdFailed() {
        Mockito.when(employeeRepository.findById(null)).thenReturn(MockBuilder.getEmployeeOptional());
        Mockito.when(employeeMapper.toDTO(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployeeDTO());
        CustomResponseDTO customResponseDTO = employeeService.getEmployeeById(Mockito.anyInt());
        Assert.assertEquals(customResponseDTO.getStatusCode(), Integer.valueOf(404));
        Assert.assertEquals(customResponseDTO.getStatus(), "FAILURE");
        Assert.assertEquals(customResponseDTO.getMessage(), "EMPLOYEE NOT FOUND");

    }

    @Test
    public void allEmployeeSuccess() {
        Mockito.when(employeeRepository.findAll(MockBuilder.getPageable())).thenReturn(MockBuilder.getEmployeePage());
        Mockito.when(employeeMapper.toDTO(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployeeDTO());
        Mockito.when(employeeRepository.findAll()).thenReturn(MockBuilder.getEmployeeList());
        Mockito.when(employeeMapper.toDTO(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployeeDTO());
        Mockito.when(employeeRepository.findByText(MockBuilder.getPageable(),MockBuilder.getSearchCriteriaDTO().getSearchByText())).thenReturn(MockBuilder.getEmployeePage());
        Mockito.when(employeeRepository.findByPhone(MockBuilder.getPageable(),MockBuilder.getSearchCriteriaDTO().getSearchByPhone())).thenReturn(MockBuilder.getEmployeePage());
        Mockito.when(employeeMapper.toDTO(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployeeDTO());
        Mockito.when(employeeRepository.findByDateOfBirth(MockBuilder.getPageable(),MockBuilder.getSearchCriteriaDTO().getSearchByDateOfBirth())).thenReturn(MockBuilder.getEmployeePage());
        Mockito.when(employeeMapper.toDTO(MockBuilder.getEmployee())).thenReturn(MockBuilder.getEmployeeDTO());
        List<CustomResponseDTO> customResponseDTOList = new ArrayList<>();
        customResponseDTOList = employeeService.allEmployees(MockBuilder.getPageable(),MockBuilder.getSearchCriteriaDTO());
        Assert.assertEquals(customResponseDTOList.get(0).getStatus(),"SUCCESS");

    }

}
