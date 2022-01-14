package org.springframework.samples.petclinic.feeding;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FeedingRepository extends CrudRepository<Feeding,Integer> {

    List<Feeding> findAll();

    @Query("SELECT ft FROM FeedingType ft")
    List<FeedingType> findAllFeedingTypes();

    Optional<Feeding> findById(int id);

    Feeding save(Feeding p);

    @Query("SELECT ft FROM FeedingType ft WHERE ft.name = :name")
    FeedingType findFeedingTypeByName(@Param("name") String name);
}
