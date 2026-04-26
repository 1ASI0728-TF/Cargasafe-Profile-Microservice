package com.cargasafe.profile.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CreateProfileResource(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull LocalDate birthDate,
        @NotBlank String phoneNumber,
        @NotBlank @Pattern(regexp = "DNI|CEX|PASSPORT", message = "documentType must be one of: DNI, CEX, PASSPORT") String documentType,
        @NotBlank String document,
        @NotNull Long userId) {
}
