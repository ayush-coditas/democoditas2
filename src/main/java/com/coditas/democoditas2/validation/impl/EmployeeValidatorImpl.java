package com.coditas.democoditas2.validation.impl;

import com.coditas.democoditas2.dto.AddressDTO;
import com.coditas.democoditas2.dto.CustomResponseDTO;
import com.coditas.democoditas2.dto.EmployeeDTO;
import com.coditas.democoditas2.validation.EmployeeValidatorService;
import com.coditas.democoditas2.validation.constants.MessageConstants;
import com.coditas.democoditas2.validation.constants.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeValidatorImpl implements EmployeeValidatorService {


    private final Logger log = LoggerFactory.getLogger(EmployeeValidatorImpl.class);

    @Override
    public CustomResponseDTO validateEmployee(EmployeeDTO employee) {
        List<CustomResponseDTO> customResponseDTOList = new ArrayList<>();

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();



        customResponseDTOList.clear();
        log.info("Validating Employee {} at {}", employee.getFirstName(), Instant.now());
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (employee.getPhone().length() != 10) {
            customResponseDTO.setStatusCode(StatusCode.PHONE_NOT_VALID.getStatusCode());
            customResponseDTO.setStatus(MessageConstants.FAILURE);
            customResponseDTO.setMessage(MessageConstants.PHONE_NOT_VALID);
            customResponseDTOList.add(customResponseDTO);

        }
        if (!employee.getEmail().matches(regex)) {
            customResponseDTO.setStatusCode(StatusCode.EMAIL_NOT_VALID.getStatusCode());
            customResponseDTO.setStatus(MessageConstants.FAILURE);
            customResponseDTO.setMessage(MessageConstants.EMAIL_NOT_VALID);
            customResponseDTOList.add(customResponseDTO);

        }
        if (!employee.getDateOfBirth().isEmpty()) {
            String dateString = employee.getDateOfBirth();
            LocalDate myDate = LocalDate.parse(dateString);
            LocalDate today = LocalDate.now();
            if (!myDate.isBefore(today)) {
                customResponseDTO.setStatusCode(StatusCode.DATEOFBIRTH_NOT_VALID.getStatusCode());
                customResponseDTO.setStatus(MessageConstants.FAILURE);
                customResponseDTO.setMessage(MessageConstants.DATEOFBIRTH_NOT_VALID);
                customResponseDTOList.add(customResponseDTO);
            }
        }
        List<AddressDTO> addressList = employee.getAddress();
        for (AddressDTO address : addressList
        ) {
            if (address.getAddressType().isEmpty()) {
                customResponseDTO.setStatusCode(StatusCode.ADDRESS_TYPE_NOT_VALID.getStatusCode());
                customResponseDTO.setStatus(MessageConstants.FAILURE);
                customResponseDTO.setMessage(MessageConstants.ADDRESS_TYPE_NOT_VALID);
                customResponseDTOList.add(customResponseDTO);
            }
            if (address.getCountry().isEmpty()) {
                customResponseDTO.setStatusCode(StatusCode.COUNTRY_NOT_FOUND.getStatusCode());
                customResponseDTO.setStatus(MessageConstants.FAILURE);
                customResponseDTO.setMessage(MessageConstants.COUNTRY_NOT_FOUND);
                customResponseDTOList.add(customResponseDTO);
            }

            if (address.getState().isEmpty()) {
                customResponseDTO.setStatusCode(StatusCode.STATE_NOT_FOUND.getStatusCode());
                customResponseDTO.setStatus(MessageConstants.FAILURE);
                customResponseDTO.setMessage(MessageConstants.STATE_NOT_FOUND);
                customResponseDTOList.add(customResponseDTO);
            }

            if (address.getCity().isEmpty()) {
                customResponseDTO.setStatusCode(StatusCode.CITY_NOT_FOUND.getStatusCode());
                customResponseDTO.setStatus(MessageConstants.FAILURE);
                customResponseDTO.setMessage(MessageConstants.CITY_NOT_FOUND);
                customResponseDTOList.add(customResponseDTO);
            }

            if (address.getPincode().toString().isEmpty()) {
                customResponseDTO.setStatusCode(StatusCode.PINCODE_NOT_FOUND.getStatusCode());
                customResponseDTO.setStatus(MessageConstants.FAILURE);
                customResponseDTO.setMessage(MessageConstants.PINCODE_NOT_FOUND);
                customResponseDTOList.add(customResponseDTO);
            }

            if (address.getFullAddress().isEmpty()) {
                customResponseDTO.setStatusCode(StatusCode.ADDRESS_NOT_FOUND.getStatusCode());
                customResponseDTO.setStatus(MessageConstants.FAILURE);
                customResponseDTO.setMessage(MessageConstants.ADDRESS_NOT_FOUND);
                customResponseDTOList.add(customResponseDTO);
            }

        }

        CustomResponseDTO customResponseDTO1 = new CustomResponseDTO();

        if (customResponseDTOList.isEmpty()) {
            customResponseDTO1.setStatusCode(StatusCode.SUCCESS.getStatusCode());
            customResponseDTO1.setStatus(MessageConstants.SUCCESS);
            customResponseDTO1.setMessage(MessageConstants.SUCCESS);
            return customResponseDTO1;
        } else {
            customResponseDTO1.setStatusCode(StatusCode.ERROR.getStatusCode());
            customResponseDTO1.setStatus(MessageConstants.ERROR);
            customResponseDTO1.setMessage(MessageConstants.ERROR);
            customResponseDTO1.setData(customResponseDTOList);
            return customResponseDTO1;

        }

    }


}
