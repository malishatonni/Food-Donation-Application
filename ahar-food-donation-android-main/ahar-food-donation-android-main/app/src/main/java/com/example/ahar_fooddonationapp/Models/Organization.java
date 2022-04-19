package com.example.ahar_fooddonationapp.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Organization {
    private Organization org;
    private String OrganizationName,
            OrganizationEmail,
            OrganizationPhone,
            OrganizationLocation,
            OrganizationCode;

    public Organization( String organizationName, String organizationEmail, String organizationPhone, String organizationLocation, String organizationCode) {
        OrganizationName = organizationName;
        OrganizationEmail = organizationEmail;
        OrganizationPhone = organizationPhone;
        OrganizationLocation = organizationLocation;
        OrganizationCode = organizationCode;
    }

    public Organization getOrg(){
        return this;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "org=" + org +
                ", OrganizationName='" + OrganizationName + '\'' +
                ", OrganizationEmail='" + OrganizationEmail + '\'' +
                ", OrganizationPhone='" + OrganizationPhone + '\'' +
                ", OrganizationLocation='" + OrganizationLocation + '\'' +
                ", OrganizationCode='" + OrganizationCode + '\'' +
                '}';
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getOrganizationEmail() {
        return OrganizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        OrganizationEmail = organizationEmail;
    }

    public String getOrganizationPhone() {
        return OrganizationPhone;
    }

    public void setOrganizationPhone(String organizationPhone) {
        OrganizationPhone = organizationPhone;
    }

    public String getOrganizationLocation() {
        return OrganizationLocation;
    }

    public void setOrganizationLocation(String organizationLocation) {
        OrganizationLocation = organizationLocation;
    }

    public String getOrganizationCode() {
        return OrganizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        OrganizationCode = organizationCode;
    }
}
