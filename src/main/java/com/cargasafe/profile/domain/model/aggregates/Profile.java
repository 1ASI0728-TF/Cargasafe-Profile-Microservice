package com.cargasafe.profile.domain.model.aggregates;

import com.cargasafe.profile.domain.model.commands.CreateProfileCommand;
import com.cargasafe.profile.domain.model.valueobjects.DocumentType;
import com.cargasafe.profile.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    private String document;
    private Long userId;

    public Profile(CreateProfileCommand command) {
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.birthDate = command.birthDate();
        this.phoneNumber = command.phoneNumber();
        this.document = command.document();
        this.userId = command.userId();
        this.documentType = DocumentType.valueOf(command.documentType());
    }

    public void updateFrom(String firstName, String lastName, LocalDate birthDate,
                           String phoneNumber, DocumentType documentType, String document) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.documentType = documentType;
        this.document = document;
    }
}
