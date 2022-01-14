package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "feedings")
public class Feeding extends BaseEntity {

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "start_date")
    LocalDate startDate;

    @NotNull
    @Min(1)
    @Column(name = "weeks_duration")
    double weeksDuration;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "pet_id")
    Pet pet;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "feeding_type")
    FeedingType feedingType;
}
