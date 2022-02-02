package com.mitocode.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.adapter.entity.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

}
