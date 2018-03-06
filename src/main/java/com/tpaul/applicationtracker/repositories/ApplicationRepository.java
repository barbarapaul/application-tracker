package com.tpaul.applicationtracker.repositories;

import com.tpaul.applicationtracker.entities.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
}
