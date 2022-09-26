package fr.esgi.cleancode.model;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.service.DrivingLicenceValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.UUID;

import static fr.esgi.cleancode.service.DrivingLicenceValidator.checkSecurityNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceTest {


    @Test
    void should_generate_valid_DriverLicence() {
        final var actual = "123456789012345";
        final var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber(actual).build();

        // drivingLicence.getDriverSocialSecurityNumber();
        assertThat(checkSecurityNumber(drivingLicence)).isTrue();
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
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(() -> checkSecurityNumber(drivingLicence));
    }

    @Test
    @BeforeEach
    void create_driverLicence() {
        final var id = UUID.randomUUID();
        final var numberSecurity = "18273792889N";
        final var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber(numberSecurity).id(id).build();

        // TODO:
        //when()

        DrivingLicence drivingLicence = mock(DrivingLicence.class);
        // mock nombre securite social
        // nmock generation id
        // mock sauvegarde

    }
}
