package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceRemoverService {
    private final InMemoryDatabase database;

    public DrivingLicence removePoints(int points, UUID id) throws ResourceNotFoundException {
        return database.findById(id)
                .map(licence -> licence.withAvailablePoints(newPoint(licence,points)))
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    private int newPoint(DrivingLicence drivingLicence, int pointsToRemove) {
        return Math.max(drivingLicence.getAvailablePoints() - pointsToRemove, 0);

    }

}
