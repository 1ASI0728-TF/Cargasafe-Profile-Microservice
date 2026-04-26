package com.cargasafe.profile.domain.services;

import com.cargasafe.profile.domain.model.aggregates.Profile;
import com.cargasafe.profile.domain.model.commands.CreatePartialProfileCommand;
import com.cargasafe.profile.domain.model.commands.CreateProfileCommand;
import com.cargasafe.profile.domain.model.commands.UpdateProfileCommand;

public interface ProfileCommandService {
    Profile handle(CreateProfileCommand command);
    Profile handle(UpdateProfileCommand command);
    Profile handle(CreatePartialProfileCommand command);
}
