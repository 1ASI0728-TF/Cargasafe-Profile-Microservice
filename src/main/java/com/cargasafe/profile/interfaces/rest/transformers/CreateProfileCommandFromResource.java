package com.cargasafe.profile.interfaces.rest.transformers;

import com.cargasafe.profile.domain.model.commands.CreateProfileCommand;
import com.cargasafe.profile.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResource {

    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.firstName(),
                resource.lastName(),
                resource.birthDate(),
                resource.phoneNumber(),
                resource.documentType(),
                resource.document(),
                resource.userId()
        );
    }
}
