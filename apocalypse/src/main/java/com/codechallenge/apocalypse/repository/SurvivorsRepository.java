package com.codechallenge.apocalypse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

import com.codechallenge.apocalypse.entity.Survivors;

@RepositoryRestResource()
public interface SurvivorsRepository extends JpaRepository<Survivors, Long>, JpaSpecificationExecutor<Survivors> {
	List<Survivors> findByInfected(boolean infected);
	
	Optional<Survivors> findById(Long id);
}
