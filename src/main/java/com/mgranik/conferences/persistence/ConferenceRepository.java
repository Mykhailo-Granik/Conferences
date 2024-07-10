package com.mgranik.conferences.persistence;

import com.mgranik.conferences.entity.ConferenceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends CrudRepository<ConferenceEntity, Integer> {

    boolean existsByName(String name);

    List<ConferenceEntity> findByIdNot(Integer id);
}
