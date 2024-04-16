package com.mgranik.conferences.repository;

import com.mgranik.conferences.entity.Conference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends CrudRepository<Conference, Integer> {

    boolean existsByName(String name);

}
