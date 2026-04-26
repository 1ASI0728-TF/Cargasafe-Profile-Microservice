package com.cargasafe.profile.application.internal.queryservices;

import com.cargasafe.profile.domain.exceptions.ProfileNotFoundException;
import com.cargasafe.profile.domain.model.aggregates.Profile;
import com.cargasafe.profile.domain.model.queries.GetAllProfilesQuery;
import com.cargasafe.profile.domain.model.queries.GetProfileByIdQuery;
import com.cargasafe.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.cargasafe.profile.domain.services.ProfileQueryService;
import com.cargasafe.profile.infrastructure.persistence.jpa.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }

    @Override
    public Profile handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.id())
                .orElseThrow(() -> new ProfileNotFoundException("Profile with id " + query.id() + " not found"));
    }

    @Override
    public Profile handle(GetProfileByUserIdQuery query) {
        return profileRepository.findByUserId(query.userId())
                .orElseThrow(() -> new ProfileNotFoundException("Profile with userId " + query.userId() + " not found"));
    }
}
