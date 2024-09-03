package com.airbnb.repository;

import com.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("select p from Property p JOIN Location l ON p.location=l.id JOIN Country c ON p.country=c.id where l.locationName=:locationName OR c.countryName=:locationName")
    List<Property> findPropertyByLocation(@Param("locationName") String locationName);
}