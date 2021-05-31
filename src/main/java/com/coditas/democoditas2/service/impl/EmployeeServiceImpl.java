package com.coditas.democoditas2.service.impl;

import com.coditas.democoditas2.domain.Address;
import com.coditas.democoditas2.domain.Employee;
import com.coditas.democoditas2.dto.AddressDTO;
import com.coditas.democoditas2.dto.CustomResponseDTO;
import com.coditas.democoditas2.dto.EmployeeDTO;
import com.coditas.democoditas2.dto.SearchCriteriaDTO;
import com.coditas.democoditas2.mapper.AddressMapper;
import com.coditas.democoditas2.mapper.EmployeeMapper;
import com.coditas.democoditas2.repository.AddressRepository;
import com.coditas.democoditas2.repository.EmployeeRepository;
import com.coditas.democoditas2.service.EmployeeService;
import com.coditas.democoditas2.validation.EmployeeValidatorService;
import com.coditas.democoditas2.validation.constants.MessageConstants;
import com.coditas.democoditas2.validation.constants.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeValidatorService employeeValidatorService;

    private static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public CustomResponseDTO saveEmployee(EmployeeDTO employeeDTO) {
        CustomResponseDTO customResponseDTO;
        log.info("Rest Request to Save Employee for Employee {}", employeeDTO.getFirstName());

        customResponseDTO = employeeValidatorService.validateEmployee(employeeDTO);
        if (customResponseDTO.getStatus() == "ERROR") {
            return customResponseDTO;
        }

        Employee employee = employeeMapper.toDomain(employeeDTO);


        employeeRepository.save(employee);
        List<Address> addressArrayList = new ArrayList<>();
        employeeDTO.getAddress().forEach(addressDTO ->
        {
            Address address = addressMapper.toDomain(addressDTO);
            address.setEmployee(employee);
            addressArrayList.add(address);
            addressRepository.save(address);
        });
        EmployeeDTO employeeDTO1 = employeeMapper.toDTO(employee);
        customResponseDTO.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        customResponseDTO.setMessage(MessageConstants.EMPLOYEE_SAVED);
        customResponseDTO.setStatus(MessageConstants.SUCCESS);
        customResponseDTO.setData(employeeDTO1);

        return customResponseDTO;

    }

    @Override
    public CustomResponseDTO updateEmployee(EmployeeDTO employeeDTO) {
        log.info("Rest Request to update Employee for Employee {}", employeeDTO.getFirstName());

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeDTO.getId());
        CustomResponseDTO customResponseDTO;

        if (employeeOptional.isPresent()) {

            Employee employee = employeeOptional.get();


            customResponseDTO = employeeValidatorService.validateEmployee(employeeDTO);
            if (customResponseDTO.getStatus() == "ERROR") {
                return customResponseDTO;
            }


            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setPhone(employeeDTO.getPhone());
            employee.setEmail(employeeDTO.getEmail());
            employee.setDateOfBirth(employeeDTO.getDateOfBirth());
            employee.setCreatedBy(employeeDTO.getCreatedBy());
            employee.setCreatedDate(employeeDTO.getCreatedDate());
            employee.setUpdatedBy(employeeDTO.getUpdatedBy());
            employee.setUpdatedDate(employeeDTO.getUpdatedDate());


            employeeRepository.save(employee);
            EmployeeDTO employeeDTO1 = employeeMapper.toDTO(employee);

            customResponseDTO.setStatusCode(StatusCode.SUCCESS.getStatusCode());
            customResponseDTO.setMessage(MessageConstants.EMPLOYEE_UPDATED);
            customResponseDTO.setStatus(MessageConstants.SUCCESS);
            customResponseDTO.setData(employeeDTO1);


        } else {
            customResponseDTO = new CustomResponseDTO();
            customResponseDTO.setStatusCode(StatusCode.FAILURE.getStatusCode());
            customResponseDTO.setMessage(MessageConstants.EMPLOYEE_NOT_FOUND);
            customResponseDTO.setStatus(MessageConstants.FAILURE);
        }
        return customResponseDTO;
    }

    @Override
    public CustomResponseDTO addAddress(int id, AddressDTO addressDTO) throws NullPointerException {
        log.info("Rest Request to add Address");
        CustomResponseDTO customResponseDTO;

        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);


            customResponseDTO = employeeValidatorService.validateEmployee(employeeDTO);
            if (customResponseDTO.getStatus() == "ERROR") {
                return customResponseDTO;
            }

            if (customResponseDTO.getStatus() == MessageConstants.ERROR) {
                return customResponseDTO;
            }
            Address address = addressRepository.findByEmployeeIdAndAddressType(id, addressDTO.getAddressType());
            if (address != null) {
                address.setFullAddress(addressDTO.getFullAddress());
                address.setAddressType(addressDTO.getAddressType());
                address.setCity(addressDTO.getCity());
                address.setState(addressDTO.getState());
                address.setCountry(addressDTO.getCountry());
                address.setPincode(addressDTO.getPincode());
                address.setEmployee(employee);

                addressRepository.save(address);

                AddressDTO addressDTO1 = addressMapper.toDTO(address);

                customResponseDTO.setStatusCode(200);
                customResponseDTO.setMessage("Updated Address");
                customResponseDTO.setStatus("Success");
                customResponseDTO.setStatusCode(StatusCode.SUCCESS.getStatusCode());
                customResponseDTO.setMessage(MessageConstants.ADDRESS_UPDATED);
                customResponseDTO.setStatus(MessageConstants.SUCCESS);
                customResponseDTO.setData(addressDTO1);

            } else {
                Address address2 = new Address();

                address2.setAddressType(addressDTO.getAddressType());
                address2.setFullAddress(addressDTO.getFullAddress());
                address2.setCity(addressDTO.getCity());
                address2.setState(addressDTO.getState());
                address2.setCountry(addressDTO.getCountry());
                address2.setPincode(addressDTO.getPincode());
                address2.setEmployee(employee);

                addressRepository.save(address2);

                AddressDTO addressDTO1 = addressMapper.toDTO(address2);


                customResponseDTO.setStatusCode(StatusCode.SUCCESS.getStatusCode());
                customResponseDTO.setMessage(MessageConstants.ADDRESS_SAVED);
                customResponseDTO.setStatus(MessageConstants.SUCCESS);
                customResponseDTO.setData(addressDTO1);
            }


        } else {
            customResponseDTO = new CustomResponseDTO();

            customResponseDTO.setStatusCode(StatusCode.FAILURE.getStatusCode());
            customResponseDTO.setMessage(MessageConstants.EMPLOYEE_NOT_FOUND);
            customResponseDTO.setStatus(MessageConstants.FAILURE);
        }
        return customResponseDTO;
    }

    @Override
    public CustomResponseDTO updateAddress(int addId, AddressDTO addressDTO) {
        log.info("Rest Request to update Address");
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        Optional<Address> addressOptional = addressRepository.findById(addId);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            address.setFullAddress(addressDTO.getFullAddress());
            address.setAddressType(addressDTO.getAddressType());
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setCountry(addressDTO.getCountry());
            address.setPincode(addressDTO.getPincode());
            addressRepository.save(address);
            AddressDTO addressDTO1 = addressMapper.toDTO(address);

            customResponseDTO.setStatusCode(StatusCode.SUCCESS.getStatusCode());
            customResponseDTO.setMessage(MessageConstants.ADDRESS_UPDATED);
            customResponseDTO.setStatus(MessageConstants.SUCCESS);
            customResponseDTO.setData(addressDTO1);


        } else {

            customResponseDTO.setStatusCode(StatusCode.FAILURE.getStatusCode());
            customResponseDTO.setMessage(MessageConstants.ADDRESS_NOT_FOUND);
            customResponseDTO.setStatus(MessageConstants.FAILURE);
        }

        return customResponseDTO;
    }

    public CustomResponseDTO getEmployeeById(int id) {
        log.info("Rest Request to search Employee by ID {} ", id);
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
            customResponseDTO.setData(employeeDTO);
            customResponseDTO.setStatusCode(StatusCode.SUCCESS.getStatusCode());
            customResponseDTO.setMessage(MessageConstants.EMPLOYEE_FOUND);
            customResponseDTO.setStatus(MessageConstants.SUCCESS);
        } else {
            customResponseDTO.setStatusCode(StatusCode.FAILURE.getStatusCode());
            customResponseDTO.setMessage(MessageConstants.EMPLOYEE_NOT_FOUND);
            customResponseDTO.setStatus(MessageConstants.FAILURE);
        }
        return customResponseDTO;
    }

    public List<CustomResponseDTO> allEmployees(Pageable pageable, SearchCriteriaDTO searchCriteriaDTO) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<CustomResponseDTO> customResponseDTOList = new ArrayList<>();
        if (searchCriteriaDTO == null) {
            if (pageable.isPaged()) {
                log.info("Rest Request to get All Employees in page");

                Page<Employee> pagedResult = employeeRepository.findAll(pageable);
                List<Employee> employeeList = pagedResult.getContent();
                if (employeeList.isEmpty()) {
                    customResponseDTO.setData(employeeDTOList);
                    customResponseDTO.setStatusCode(StatusCode.FAILURE.getStatusCode());
                    customResponseDTO.setMessage(MessageConstants.EMPLOYEE_NOT_FOUND);
                    customResponseDTO.setStatus(MessageConstants.FAILURE);
                    customResponseDTOList.add(customResponseDTO);
                    return customResponseDTOList;
                }
                if (pagedResult.hasContent()) {
                    for (Employee employee : employeeList
                    ) {
                        EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
                        employeeDTOList.add(employeeDTO);
                        customResponseDTOList.add(customResponseDTO);
                    }
                }

            } else {
                List<Employee> employeeList = employeeRepository.findAll();
                log.info("Rest Request to get list of All Employees");

                for (Employee employee : employeeList
                ) {
                    EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
                    employeeDTOList.add(employeeDTO);
                    customResponseDTOList.add(customResponseDTO);
                }
            }
        }

        if (searchCriteriaDTO != null) {
            if (searchCriteriaDTO.getSearchByText() != null) {
                log.info("Rest Request to search Employee by Text");

                Page<Employee> pagedResult = employeeRepository.findByText(pageable, searchCriteriaDTO.getSearchByText());
                List<Employee> employeeList = pagedResult.getContent();
                if (employeeList.isEmpty()) {
                    customResponseDTO.setData(employeeDTOList);
                    customResponseDTO.setStatusCode(StatusCode.FAILURE.getStatusCode());
                    customResponseDTO.setMessage(MessageConstants.EMPLOYEE_NOT_FOUND);
                    customResponseDTO.setStatus(MessageConstants.FAILURE);
                    customResponseDTOList.add(customResponseDTO);
                    return customResponseDTOList;
                }
                for (Employee employee : employeeList
                ) {
                    EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
                    employeeDTOList.add(employeeDTO);
                    customResponseDTOList.add(customResponseDTO);
                }

            } else if (searchCriteriaDTO.getSearchByPhone() != null) {
                log.info("Rest Request to search Employee by Phone");

                Page<Employee> pagedResult = employeeRepository.findByPhone(pageable, searchCriteriaDTO.getSearchByPhone());
                List<Employee> employeeList = pagedResult.getContent();
                if (pagedResult.isEmpty()) {

                    customResponseDTO.setStatusCode(StatusCode.FAILURE.getStatusCode());
                    customResponseDTO.setMessage(MessageConstants.EMPLOYEE_NOT_FOUND);
                    customResponseDTO.setStatus(MessageConstants.FAILURE);
                    customResponseDTO.setData(employeeDTOList);
                    customResponseDTOList.add(customResponseDTO);
                    return customResponseDTOList;
                }
                for (Employee employee : employeeList
                ) {
                    EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
                    employeeDTOList.add(employeeDTO);
                    customResponseDTOList.add(customResponseDTO);
                }
            } else if (searchCriteriaDTO.getSearchByDateOfBirth() != null) {
                log.info("Rest Request to search Employee by Date of Birth");

                Page<Employee> pagedResult = employeeRepository.findByDateOfBirth(pageable, searchCriteriaDTO.getSearchByDateOfBirth());
                List<Employee> employeeList = pagedResult.getContent();
                if (employeeList.isEmpty()) {
                    customResponseDTO.setData(employeeDTOList);

                    customResponseDTO.setStatusCode(StatusCode.FAILURE.getStatusCode());
                    customResponseDTO.setMessage(MessageConstants.EMPLOYEE_NOT_FOUND);
                    customResponseDTO.setStatus(MessageConstants.FAILURE);
                    customResponseDTOList.add(customResponseDTO);
                    return customResponseDTOList;
                }
                for (Employee employee : employeeList
                ) {
                    EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
                    employeeDTOList.add(employeeDTO);
                    customResponseDTOList.add(customResponseDTO);
                }
            }
        }
        customResponseDTO.setData(employeeDTOList);
        customResponseDTO.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        customResponseDTO.setMessage(MessageConstants.EMPLOYEE_FOUND);
        customResponseDTO.setStatus(MessageConstants.SUCCESS);
        return customResponseDTOList;

    }

    public CustomResponseDTO deleteEmployee(int id) {
        log.info("Rest Request to Delete Employee by Id");
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        Employee employee = employeeRepository.getOne(id);
        customResponseDTO.setData(employee.getFirstName());
        addressRepository.deleteAll(employee.getAddress());
        employeeRepository.deleteById(id);
        customResponseDTO.setStatusCode(StatusCode.SUCCESS.getStatusCode());
        customResponseDTO.setMessage(MessageConstants.DELETED);
        customResponseDTO.setStatus(MessageConstants.SUCCESS);
        return customResponseDTO;
    }







}
