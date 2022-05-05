package com.codechallenge.apocalypse.service;

import java.util.List;
import org.springframework.stereotype.Component;

import com.codechallenge.apocalypse.entity.Survivors;
import com.codechallenge.apocalypse.repository.SurvivorsRepository;

@Component
public class SurvivorsService {
	
	private SurvivorsRepository survivorsRepository;

    public SurvivorsService(SurvivorsRepository survivorsRepository) {
        this.survivorsRepository = survivorsRepository;
    }

    public List<Survivors> getSurvivors() {
        return survivorsRepository.findAll();
    }

	public Survivors saveSurvivor(Survivors survivor) {
		return survivorsRepository.save(survivor);
	}

}
