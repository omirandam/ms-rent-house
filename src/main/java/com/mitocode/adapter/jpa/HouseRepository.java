package com.mitocode.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.adapter.entity.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

}
