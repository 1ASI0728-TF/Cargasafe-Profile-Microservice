package com.cargasafe.profile.interfaces.rest;

import com.cargasafe.profile.domain.exceptions.ProfileNotFoundException;
import com.cargasafe.profile.domain.model.commands.CreatePartialProfileCommand;
import com.cargasafe.profile.domain.model.queries.GetAllProfilesQuery;
import com.cargasafe.profile.domain.model.queries.GetProfileByIdQuery;
import com.cargasafe.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.cargasafe.profile.domain.services.ProfileCommandService;
import com.cargasafe.profile.domain.services.ProfileQueryService;
import com.cargasafe.profile.interfaces.rest.resources.CreatePartialProfileResource;
import com.cargasafe.profile.interfaces.rest.resources.CreateProfileResource;
import com.cargasafe.profile.interfaces.rest.resources.ProfileResource;
import com.cargasafe.profile.interfaces.rest.resources.UpdateProfileResource;
import com.cargasafe.profile.interfaces.rest.transformers.CreateProfileCommandFromResource;
import com.cargasafe.profile.interfaces.rest.transformers.ProfileResourceFromEntityAssembler;
import com.cargasafe.profile.interfaces.rest.transformers.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profiles", description = "Endpoint for managing profiles")
public class ProfileController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileResource>> getProfiles() {
        var profiles = profileQueryService.handle(new GetAllProfilesQuery());
        var resources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long id) {
        try {
            var profile = profileQueryService.handle(new GetProfileByIdQuery(id));
            return ResponseEntity.ok(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile));
        } catch (ProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable Long userId) {
        try {
            var profile = profileQueryService.handle(new GetProfileByUserIdQuery(userId));
            return ResponseEntity.ok(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile));
        } catch (ProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@Valid @RequestBody CreateProfileResource resource) {
        var command = CreateProfileCommandFromResource.toCommandFromResource(resource);
        var profile = profileCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile));
    }

    @PostMapping("/partial")
    public ResponseEntity<ProfileResource> createPartialProfile(@Valid @RequestBody CreatePartialProfileResource resource) {
        var command = new CreatePartialProfileCommand(resource.firstName(), resource.lastName(), resource.userId());
        var profile = profileCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long id,
                                                          @Valid @RequestBody UpdateProfileResource resource) {
        try {
            var command = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(id, resource);
            var profile = profileCommandService.handle(command);
            return ResponseEntity.ok(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile));
        } catch (ProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
