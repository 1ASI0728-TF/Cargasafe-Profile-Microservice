package com.cargasafe.profile.interfaces.rest.transformers;

import com.cargasafe.profile.domain.model.aggregates.Profile;
import com.cargasafe.profile.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {

    public static ProfileResource toResourceFromEntity(Profile profile) {
        return new ProfileResource(
                profile.getId(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getPhoneNumber(),
                profile.getBirthDate(),
                profile.getDocumentType() != null ? profile.getDocumentType().name() : null,
                profile.getDocument(),
                profile.getUserId()
        );
    }
}
