package com.cargasafe.profile.interfaces.rest.resources;

import java.time.LocalDate;

public record ProfileResource(Long id, String firstName, String lastName, String phoneNumber,
                              LocalDate birthDate, String documentType, String document, Long userId) {
}
