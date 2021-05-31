package com.coditas.democoditas2.repository;

import com.coditas.democoditas2.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' ', e.lastName, ' ', e.email) LIKE %?1%")
    Page<Employee> findByText(Pageable pageable, String text);

    @Query("SELECT e FROM Employee e WHERE e.phone LIKE %?1%")
    Page<Employee> findByPhone(Pageable pageable, String phone);

    @Query("SELECT e FROM Employee e WHERE e.dateOfBirth LIKE %?1%")
    Page<Employee> findByDateOfBirth(Pageable pageable, String dateOfBirth);

    @Query("SELECT id,firstName,lastName,phone,email,dateOfBirth,createdBy,createdDate,updatedBy,updatedDate FROM Employee e WHERE e.id LIKE %:employeeID and e.firstName LIKE %:employeeFirstName and e.lastName LIKE %:employeeLastName and e.phone LIKE %:employeePhone and e.email LIKE %:employeeEmail and e.dateOfBirth LIKE %:dateOfBirth and e.createdBy LIKE %:employeeCreatedBy and e.createdDate LIKE %:employeeCreatedDate and e.updatedBy LIKE %:employeeUpdatedBy and e.updatedDate LIKE %:employeeUpdatedDate")
    String findById(String employeeID, String employeeFirstName, String employeeLastName, String employeePhone, String employeeEmail, String dateOfBirth, String employeeCreatedBy, String employeeCreatedDate, String employeeUpdatedBy, String employeeUpdatedDate);

    @Query("SELECT id,firstName,lastName,phone,email,dateOfBirth,createdBy,createdDate,updatedBy,updatedDate FROM Employee e WHERE e.id LIKE %:employeeID and e.firstName LIKE %:employeeFirstName and e.lastName LIKE %:employeeLastName and e.phone LIKE %:employeePhone and e.email LIKE %:employeeEmail and e.dateOfBirth LIKE %:dateOfBirth and e.createdBy LIKE %:employeeCreatedBy and e.createdDate LIKE %:employeeCreatedDate and e.updatedBy LIKE %:employeeUpdatedBy and e.updatedDate LIKE %:employeeUpdatedDate")
    String findAll(String employeeID, String employeeFirstName, String employeeLastName, String employeePhone, String employeeEmail, String dateOfBirth, String employeeCreatedBy, String employeeCreatedDate, String employeeUpdatedBy, String employeeUpdatedDate);

    @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' ', e.lastName, ' ', e.phone, ' ', e.email) LIKE %?1%")
    List<Employee> search(String keyword);


    @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' ', e.lastName, ' ', e.phone, ' ', e.email) LIKE %?1%")
    Page<Employee> findBySearch(Pageable pageable);
}
