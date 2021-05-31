package com.coditas.democoditas2.validation.constants;

public enum StatusCode {
    ERROR(500),
    SUCCESS(200),
    FAILURE(404),
    PHONE_NOT_VALID(91),
    EMAIL_NOT_VALID(92),
    DATEOFBIRTH_NOT_VALID(93),
    ADDRESS_TYPE_NOT_VALID(94),
    COUNTRY_NOT_FOUND(95),
    STATE_NOT_FOUND(96),
    CITY_NOT_FOUND(97),
    PINCODE_NOT_FOUND(98),
    ADDRESS_NOT_FOUND(99);

    private final int status;

    StatusCode(int status) {
        this.status = status;
    }

    public int getStatusCode() {
        return this.status;
    }
}
