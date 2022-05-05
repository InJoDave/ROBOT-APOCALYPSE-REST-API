package com.codechallenge.apocalypse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.codechallenge.apocalypse.entity.Location;

@RepositoryRestResource()
public interface LocationRepository extends JpaRepository<Location, Long>{

}
