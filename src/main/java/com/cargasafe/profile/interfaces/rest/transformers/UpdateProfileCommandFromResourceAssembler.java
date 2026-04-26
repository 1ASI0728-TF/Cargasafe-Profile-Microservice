package com.cargasafe.profile.interfaces.rest.transformers;

import com.cargasafe.profile.domain.model.commands.UpdateProfileCommand;
import com.cargasafe.profile.domain.model.valueobjects.DocumentType;
import com.cargasafe.profile.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {

    public static UpdateProfileCommand toCommandFromResource(Long id, UpdateProfileResource resource) {
        return new UpdateProfileCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.birthDate(),
                resource.phoneNumber(),
                DocumentType.valueOf(resource.documentType()),
                resource.document()
        );
    }
}
