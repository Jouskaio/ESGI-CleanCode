package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;

import java.util.regex.Pattern;

public class DrivingLicenceValidator {
    public static boolean checkSecurityNumber(DrivingLicence drivingLicence) throws InvalidDriverSocialSecurityNumberException {
        var actual = drivingLicence.getDriverSocialSecurityNumber();
        if(actual == null) {
            throw  new InvalidDriverSocialSecurityNumberException("Null");
        } else if (actual.length() != 15) {
            throw  new InvalidDriverSocialSecurityNumberException("Not the good length");
        } else if (!Pattern.matches("\\d*", actual)) {
            throw  new InvalidDriverSocialSecurityNumberException("No, digit number please");
        }
        return true;
    }



}
