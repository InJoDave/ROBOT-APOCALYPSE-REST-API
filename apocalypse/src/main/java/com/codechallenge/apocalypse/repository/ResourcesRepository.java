package com.codechallenge.apocalypse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.codechallenge.apocalypse.entity.Resources;

@RepositoryRestResource()
public interface ResourcesRepository extends JpaRepository<Resources, Long>{

}
