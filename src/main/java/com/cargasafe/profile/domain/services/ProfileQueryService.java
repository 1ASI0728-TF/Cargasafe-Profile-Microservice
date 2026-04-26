package com.cargasafe.profile.domain.services;

import com.cargasafe.profile.domain.model.aggregates.Profile;
import com.cargasafe.profile.domain.model.queries.GetAllProfilesQuery;
import com.cargasafe.profile.domain.model.queries.GetProfileByIdQuery;
import com.cargasafe.profile.domain.model.queries.GetProfileByUserIdQuery;

import java.util.List;

public interface ProfileQueryService {
    List<Profile> handle(GetAllProfilesQuery query);
    Profile handle(GetProfileByIdQuery query);
    Profile handle(GetProfileByUserIdQuery query);
}
