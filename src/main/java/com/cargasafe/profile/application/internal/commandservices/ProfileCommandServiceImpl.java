package com.cargasafe.profile.application.internal.commandservices;

import com.cargasafe.profile.domain.exceptions.ProfileNotFoundException;
import com.cargasafe.profile.domain.model.aggregates.Profile;
import com.cargasafe.profile.domain.model.commands.CreatePartialProfileCommand;
import com.cargasafe.profile.domain.model.commands.CreateProfileCommand;
import com.cargasafe.profile.domain.model.commands.UpdateProfileCommand;
import com.cargasafe.profile.domain.services.ProfileCommandService;
import com.cargasafe.profile.infrastructure.persistence.jpa.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile handle(CreateProfileCommand command) {
        return profileRepository.save(new Profile(command));
    }

    @Override
    public Profile handle(UpdateProfileCommand command) {
        var profile = profileRepository.findById(command.id())
                .orElseThrow(() -> new ProfileNotFoundException("Profile with id " + command.id() + " not found"));

        profile.updateFrom(command.firstName(), command.lastName(), command.birthDate(),
                command.phoneNumber(), command.documentType(), command.document());

        return profileRepository.save(profile);
    }

    @Override
    public Profile handle(CreatePartialProfileCommand command) {
        var profile = new Profile();
        profile.setFirstName(command.firstName());
        profile.setLastName(command.lastName());
        profile.setUserId(command.userId());
        return profileRepository.save(profile);
    }
}
