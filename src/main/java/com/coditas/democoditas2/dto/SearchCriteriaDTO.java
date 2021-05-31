package com.coditas.democoditas2.dto;


public class SearchCriteriaDTO {
    String searchByText;
    String searchByPhone;
    String searchByEmail;
    String searchByDateOfBirth;

    public String getSearchByText() {
        return searchByText;
    }

    public void setSearchByText(String searchByText) {
        this.searchByText = searchByText;
    }

    public String getSearchByPhone() {
        return searchByPhone;
    }

    public void setSearchByPhone(String searchByPhone) {
        this.searchByPhone = searchByPhone;
    }

    public String getSearchByEmail() {
        return searchByEmail;
    }

    public void setSearchByEmail(String searchByEmail) {
        this.searchByEmail = searchByEmail;
    }

    public String getSearchByDateOfBirth() {
        return searchByDateOfBirth;
    }

    public void setSearchByDateOfBirth(String searchByDateOfBirth) {
        this.searchByDateOfBirth = searchByDateOfBirth;
    }
}
