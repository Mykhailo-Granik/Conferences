package com.mgranik.conferences.persistence;

import com.mgranik.conferences.entity.Conference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends CrudRepository<Conference, Integer> {

    boolean existsByName(String name);

    List<Conference> findByIdNot(Integer id);
}
