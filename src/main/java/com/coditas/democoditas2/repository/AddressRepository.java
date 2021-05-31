package com.coditas.democoditas2.repository;

import com.coditas.democoditas2.domain.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {


    @Query("SELECT fullAddress,addressType,city,state,country,pincode FROM Address a WHERE a.fullAddress LIKE %:employeeFullAddress% and a.addressType LIKE %:employeeAddressType% and a.city LIKE %:employeeCity% and a.state LIKE %:employeeState% and a.country LIKE %:employeeCountry% and a.pincode LIKE :employeePincode")
    String findByName(String employeeFullAddress, String employeeAddressType, String employeeCity, String employeeState, String employeeCountry, Integer employeePincode);

    @Query("SELECT id, fullAddress,addressType,city,state,country,pincode FROM Address a WHERE a.id LIKE :id and a.fullAddress LIKE %:employeeFullAddress% and a.addressType LIKE %:employeeAddressType% and a.city LIKE %:employeeCity% and a.state LIKE %:employeeState% and a.country LIKE %:employeeCountry% and a.pincode LIKE :employeePincode")
    String findById(String id, String employeeFullAddress, String employeeAddressType, String employeeCity, String employeeState, String employeeCountry, Integer employeePincode);


    Address findByEmployeeIdAndAddressType(Integer employeeId, String addressType);

    Address findByEmployeeId(Integer id);
}
