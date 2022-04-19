package com.example.ahar_fooddonationapp.Models;

import java.time.OffsetDateTime;
import java.util.Date;

public class Token {
    private String token;
    private Date expirationDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
