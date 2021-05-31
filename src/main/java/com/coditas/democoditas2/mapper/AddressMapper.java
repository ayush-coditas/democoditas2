package com.coditas.democoditas2.mapper;

import com.coditas.democoditas2.domain.Address;
import com.coditas.democoditas2.dto.AddressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {
    private final Logger log = LoggerFactory.getLogger(AddressMapper.class);
    public Address toDomain(AddressDTO addressDTO) {
        if (addressDTO != null) {
//            log.info("Mapping AddressDTO to Address Domain ");
            Address address = new Address();
            address.setFullAddress(addressDTO.getFullAddress());
            address.setAddressType(addressDTO.getAddressType());
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setCountry(addressDTO.getCountry());
            address.setPincode(addressDTO.getPincode());
            return address;
        }
        return null;
    }

    public AddressDTO toDTO(Address address) {
        if (address != null) {
//            log.info("Mapping Address Domain to AddressDTO ");

            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(address.getId());
            addressDTO.setFullAddress(address.getFullAddress());
            addressDTO.setAddressType(address.getAddressType());
            addressDTO.setCity(address.getCity());
            addressDTO.setState(address.getState());
            addressDTO.setCountry(address.getCountry());
            addressDTO.setPincode(address.getPincode());
            return addressDTO;
        }
        return null;

    }
}
