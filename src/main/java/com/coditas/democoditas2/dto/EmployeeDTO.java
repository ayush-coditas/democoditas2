package com.coditas.democoditas2.dto;

import java.time.Instant;
import java.util.List;

public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String dateOfBirth;
    private String createdBy;
    private Instant createdDate;
    private String updatedBy;
    private Instant updatedDate;
    private List<AddressDTO> address;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return Instant.now();
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = Instant.now();
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedDate() {
        return Instant.now();
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = Instant.now();
    }

    public List<AddressDTO> getAddress() {
        return address;
    }

    public void setAddress(List<AddressDTO> address) {
        this.address = address;
    }
}
