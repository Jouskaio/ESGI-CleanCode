package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreatorTest {
    @InjectMocks
    private DrivingLicenceCreatorService creator;

    @Mock
    private InMemoryDatabase database;

    @Mock
    private DrivingLicenceIdGenerationService idGeneration;

    @Mock
    private DrivingLicenceValidatorService validator;


    @Test
    void create_DrivingLicence() {
        final var id = UUID.randomUUID();
        final var numberSecurity = "123456789098765";
        final var point = 12;

        final var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber(numberSecurity).id(id).build();

        // Add conditions
        when(idGeneration.generateNewDrivingLicenceId()).thenReturn(id);
        when(validator.check_security_number(drivingLicence)).thenReturn(true);
        when(database.save(id, drivingLicence)).thenReturn(drivingLicence);

        // Create value
        final var actual = creator.save(drivingLicence);

        // Check conformity
        // mock nombre securite social
        assertThat(actual).isEqualTo(drivingLicence);
        verify(validator).check_security_number(drivingLicence);
        // mock generation id
        verify(database).save(id, drivingLicence);
        verifyNoMoreInteractions(database);
        // mock sauvegarde
        verifyNoMoreInteractions(validator);

    }
}
