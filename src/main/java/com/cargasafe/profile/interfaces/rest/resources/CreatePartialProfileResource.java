package com.cargasafe.profile.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePartialProfileResource(
        @NotNull Long userId,
        @NotBlank String firstName,
        @NotBlank String lastName) {
}
