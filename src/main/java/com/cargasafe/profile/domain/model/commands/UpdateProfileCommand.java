package com.cargasafe.profile.domain.model.commands;

import com.cargasafe.profile.domain.model.valueobjects.DocumentType;

import java.time.LocalDate;

public record UpdateProfileCommand(Long id, String firstName, String lastName, LocalDate birthDate,
                                   String phoneNumber, DocumentType documentType, String document) {
}
