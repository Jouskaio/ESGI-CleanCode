package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceValidatorTest {

    @Test
    void should_generate_valid_DriverLicence() {
        final var actual = "123456789012345";
        final var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber(actual).build();
        final DrivingLicenceValidatorService service = new DrivingLicenceValidatorService();

        // drivingLicence.getDriverSocialSecurityNumber();
        assertThat(service.check_security_number(drivingLicence)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "lorem ipsuil",
            "dkjsfqlshjfbqklb",
            "2",
            "98Y9HIBFDSKJF9SEH",
    })
    void should_generate_invalid_DriverLicence(String invalidSSNumber) {
        final var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber(invalidSSNumber).build();
        final DrivingLicenceValidatorService service = new DrivingLicenceValidatorService();


        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(() -> service.check_security_number(drivingLicence));
    }
}
