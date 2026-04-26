package com.cargasafe.profile.domain.model.commands;

import java.time.LocalDate;

public record CreateProfileCommand(String firstName, String lastName, LocalDate birthDate,
                                   String phoneNumber, String documentType, String document,
                                   Long userId) {
}
