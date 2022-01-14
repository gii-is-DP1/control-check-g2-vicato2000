package org.springframework.samples.petclinic.feeding;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "feedings_type")
public class FeedingType extends BaseEntity {

    @Size(min = 3, max = 30)
    @NotEmpty
    @Column(name = "name", unique = true)
    String name;

    @NotEmpty
    String description;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "pet_type_id")
    PetType petType;
}
