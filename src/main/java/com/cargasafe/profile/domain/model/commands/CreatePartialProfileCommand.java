package com.cargasafe.profile.domain.model.commands;

public record CreatePartialProfileCommand(String firstName, String lastName, Long userId) {
}
