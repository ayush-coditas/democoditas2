package com.coditas.democoditas2.builder;

import com.coditas.democoditas2.domain.Address;
import com.coditas.democoditas2.domain.Employee;
import com.coditas.democoditas2.dto.AddressDTO;
import com.coditas.democoditas2.dto.CustomResponseDTO;
import com.coditas.democoditas2.dto.EmployeeDTO;
import com.coditas.democoditas2.dto.SearchCriteriaDTO;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockBuilder {

    @Mock
    private static JavaMailSender javaMailSender;

    //    @Mock
//    private static CustomResponseDTO customResponseDTO;
    private static Logger log = LoggerFactory.getLogger(MockBuilder.class);

    public static MimeMessage getMimeMessage() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        return mimeMessage;
    }

    public static EmployeeDTO getEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Salman");
        employeeDTO.setLastName("khan");
        employeeDTO.setEmail("salman@gmail.com");
        employeeDTO.setPhone("98765");
        employeeDTO.setDateOfBirth("1997-04-12");
        employeeDTO.setCreatedBy("Ayush");
        employeeDTO.setCreatedDate(Instant.now());
        employeeDTO.setUpdatedBy("Ayush");
        employeeDTO.setUpdatedDate(Instant.now());
        employeeDTO.setAddress(getAddressDTOList());
        return employeeDTO;
    }

    private static List<AddressDTO> getAddressDTOList() {
        List<AddressDTO> addressDTOList = new ArrayList<>();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressType("Office");
        addressDTO.setFullAddress("202, bagadganj");
        addressDTO.setCity("Nagpur");
        addressDTO.setState("Maharashtra");
        addressDTO.setCountry("India");
        addressDTO.setPincode(440008);
        addressDTOList.add(addressDTO);
        return addressDTOList;

    }

    public static Employee getEmployee() {
        Employee employee = new Employee();

        employee.setFirstName(employee.getFirstName());
        employee.setDateOfBirth("1997-04-12");
        employee.setFirstName("Salman");
        employee.setLastName("khan");
        employee.setPhone("1234567890");
        employee.setEmail("salman@gmail.com");
        employee.setCreatedBy("Ayush");
        employee.setCreatedDate(Instant.now());
        employee.setUpdatedBy("Ayush");
        employee.setUpdatedDate(Instant.now());
        employee.setAddress(getAddressList());
        return employee;
    }
//    public static Employee getEmployeeInvalidPhone() {
//        Employee employee = new Employee();
//
//        employee.setFirstName(employee.getFirstName());
//        employee.setDateOfBirth("2023-04-12");
//        employee.setFirstName("Salman");
//        employee.setLastName("khan");
//        employee.setPhone("123");
//        employee.setEmail("salman@gmail.com");
//        employee.setCreatedBy("Ayush");
//        employee.setCreatedDate(Instant.now());
//        employee.setUpdatedBy("Ayush");
//        employee.setUpdatedDate(Instant.now());
//        employee.setAddress(getAddressList());
//        return employee;
//    }
//    public static Employee getEmployeeInvalidEmail() {
//        Employee employee = new Employee();
//
//        employee.setFirstName(employee.getFirstName());
//        employee.setDateOfBirth("1997-04-12");
//        employee.setFirstName("Salman");
//        employee.setLastName("khan");
//        employee.setPhone("1234567890");
//        employee.setEmail("salmangmail.com");
//        employee.setCreatedBy("Ayush");
//        employee.setCreatedDate(Instant.now());
//        employee.setUpdatedBy("Ayush");
//        employee.setUpdatedDate(Instant.now());
//        employee.setAddress(getAddressList());
//        return employee;
//    }
//    public static Employee getEmployeeInvalidDOB() {
//        Employee employee = new Employee();
//
//        employee.setFirstName(employee.getFirstName());
//        employee.setDateOfBirth("2023-04-12");
//        employee.setFirstName("Salman");
//        employee.setLastName("khan");
//        employee.setPhone("1234567890");
//        employee.setEmail("salman@gmail.com");
//        employee.setCreatedBy("Ayush");
//        employee.setCreatedDate(Instant.now());
//        employee.setUpdatedBy("Ayush");
//        employee.setUpdatedDate(Instant.now());
//        employee.setAddress(getAddressList());
//        return employee;
//    }

    public static Optional<Employee> getEmployeeOptional() {
        Optional<Employee> employeeOptional = Optional.of(new Employee());
        Employee employee = employeeOptional.get();
        employee.setDateOfBirth("1997-04-12");
        employee.setFirstName("Salman");
        employee.setLastName("khan");
        employee.setPhone("9876543211");
        employee.setEmail("salman@gmail.com");
        employee.setCreatedBy("Ayush");
        employee.setCreatedDate(Instant.now());
        employee.setUpdatedBy("Ayush");
        employee.setUpdatedDate(Instant.now());
//        employee.setAddress(getAddressList());
        return employeeOptional;
    }

    private static List<Address> getAddressList() {
        List<Address> addressList = new ArrayList<>();
        Address address = new Address();
//        address.setEmployee(getEmployee());
        address.setAddressType("Office");
        address.setFullAddress("202, old bagadganj");
        address.setCity("Nagpur");
        address.setState("Maharshtra");
        address.setCountry("India");
        address.setPincode(440008);

        addressList.add(address);
        return addressList;
    }

    public static AddressDTO getAddressDTO() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressType("Office");
        addressDTO.setFullAddress("202,bagadganj");
        addressDTO.setCity("Nagpur");
        addressDTO.setState("Maharashtra");
        addressDTO.setCountry("India");
        addressDTO.setPincode(440008);
        return addressDTO;
    }

    public static Address getAddress() {
        Address address = new Address();
//        address.setEmployee(getEmployee());
        address.setAddressType("Office");
        address.setFullAddress("202, bagadganj");
        address.setCity("Nagpur");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPincode(440008);
        return address;
    }

    public static Optional<Address> getAddressOptional() {
        Optional<Address> addressOptional = Optional.of(new Address());
        Address address = addressOptional.get();
//        address.setEmployee(getEmployee());
        address.setAddressType("Office");
        address.setFullAddress("202, bagadganj");
        address.setCity("Nagpur");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPincode(440008);
        return addressOptional;

    }


    public static Pageable getPageable() {
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 1;
            }

            @Override
            public int getPageSize() {
                return 2;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        return pageable;
    }

    public static List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();

        employee.setDateOfBirth("1997-04-12");
        employee.setFirstName("Salman");
        employee.setLastName("khan");
        employee.setPhone("9876543211");
        employee.setEmail("salman@gmail.com");
        employee.setCreatedBy("Ayush");
        employee.setCreatedDate(Instant.now());
        employee.setUpdatedBy("Ayush");
        employee.setUpdatedDate(Instant.now());

        return employeeList;
    }

    public static SearchCriteriaDTO getSearchCriteriaDTO() {
        SearchCriteriaDTO searchCriteriaDTO = new SearchCriteriaDTO();
        searchCriteriaDTO.setSearchByText("Ayush");
        searchCriteriaDTO.setSearchByDateOfBirth("1997-04-12");
        searchCriteriaDTO.setSearchByEmail("ayush@gmail.com");
        searchCriteriaDTO.setSearchByPhone("7972827615");

        return searchCriteriaDTO;
    }

    public static Page<Employee> getEmployeePage() {
        Page<Employee> employeePage = new Page<Employee>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 5;
            }

            @Override
            public <U> Page<U> map(Function<? super Employee, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<Employee> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Employee> iterator() {
                return null;
            }
        };
        return employeePage;
    }

    public static CustomResponseDTO getCustomResponseDTO() {
        log.info("CRDTO of MockBuilder");
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        customResponseDTO.setStatusCode(500);
        customResponseDTO.setMessage("ERROR");
        customResponseDTO.setStatus("ERROR");
        customResponseDTO.setData("No data");

        return customResponseDTO;
    }
}
