package com.codechallenge.apocalypse.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.codechallenge.apocalypse.entity.Location;
import com.codechallenge.apocalypse.entity.Survivors;
import com.codechallenge.apocalypse.repository.LocationRepository;
import com.codechallenge.apocalypse.repository.SurvivorsRepository;
import com.codechallenge.apocalypse.service.SurvivorsService;

@RestController
@RequestMapping("/api")
public class SurvivorsController {

	@Autowired
	SurvivorsService survivorsService;

	@Autowired
	SurvivorsRepository survivorsRepository;

	@Autowired
	LocationRepository locationRepository;

	/**
	 * List all survivors
	 * 
	 * @return
	 */
	@GetMapping(path = "/survivors")
	public ResponseEntity<?> listSurvivors() {
		System.out.println("Controller:  Get Survivors");
		List<Survivors> resource = survivorsService.getSurvivors();
		return ResponseEntity.ok(resource);
	}

	/**
	 * Add a survivor
	 * 
	 * @param survivor
	 * @return
	 */
	@PostMapping(path = "/survivor")
	public ResponseEntity<?> saveSurvivor(@RequestBody Survivors survivor) {
		System.out.println("UsersController:  Add Survivors");
		Survivors resource = survivorsService.saveSurvivor(survivor);
		return ResponseEntity.ok(resource);
	}

	/**
	 * Get all infected survivors
	 * 
	 * @return
	 */
	@GetMapping("/survivors/infected")
	public ResponseEntity<List<Survivors>> findByInfected() {
		try {
			List<Survivors> survivors = survivorsRepository.findByInfected(true);
			if (survivors.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(survivors, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Percentage of infected survivors
	 * 
	 * @return
	 */
	@GetMapping("/survivors/infectedAsPercentage")
	public ResponseEntity<String> getInfectedAsPercentage() {
		try {
			int infected = survivorsRepository.findByInfected(true).size();
			int total = survivorsService.getSurvivors().size();
			int percentage = infected * 100 / total;
			return new ResponseEntity<>("Percentage Infected : " + percentage, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Percentage of not infected survivors
	 * 
	 * @return
	 */
	@GetMapping("/survivors/notInfectedAsPercentage")
	public ResponseEntity<String> getNotInfectedAsPercentage() {
		try {
			int notinfected = survivorsRepository.findByInfected(false).size();
			int total = survivorsService.getSurvivors().size();
			int percentage = notinfected * 100 / total;
			return new ResponseEntity<>("Percentage Not Infected : " + percentage, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get all not infected survivors
	 * 
	 * @return
	 */
	@GetMapping("/survivors/notInfected")
	public ResponseEntity<List<Survivors>> findByNotInfected() {
		try {
			List<Survivors> survivors = survivorsRepository.findByInfected(false);
			if (survivors.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(survivors, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update survivor location
	 * 
	 * @param id
	 * @param location
	 * @return
	 */
	@PatchMapping("/survivor/{id}")
	public ResponseEntity<Optional<Survivors>> updateSurvivorLocation(@PathVariable("id") long id,
			@RequestBody Location location) {
		Optional<Survivors> survivorData = survivorsRepository.findById(id);
		if (survivorData.isPresent()) {
			Survivors _survivor = survivorData.get();
			Location _location = _survivor.getLocation();
			location.setId(_location.getId());
			locationRepository.save(location);
			return new ResponseEntity<>(survivorsRepository.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Flag survivor as infected
	 * 
	 * @param id
	 * @return
	 */
	@PatchMapping("/survivor/{id}/reportInfected")
	public ResponseEntity<String> reportSurvivorInfected(@PathVariable("id") long id) {
		Optional<Survivors> survivorData = survivorsRepository.findById(id);
		if (survivorData.isPresent()) {
			Survivors _survivor = survivorData.get();
			long infectionRate = _survivor.getTotalInfection();
			infectionRate++;
			if (infectionRate > 3) {
				return new ResponseEntity<>("Survivor is already Infected!!", HttpStatus.OK);
			} else if (infectionRate == 3) {
				_survivor.setTotalInfection(infectionRate);
				_survivor.setInfected(true);
				survivorsService.saveSurvivor(_survivor);
				return new ResponseEntity<>("Survivor has been reported Infected!!", HttpStatus.OK);
			} else {
				_survivor.setTotalInfection(infectionRate);
				_survivor.setInfected(false);
				survivorsService.saveSurvivor(_survivor);
				return new ResponseEntity<>("Survivor is showing infection", HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * List all robots
	 * @return
	 */
	@GetMapping(path = "/robots")
	public ResponseEntity<?> listRobots() {
		System.out.println("Controller:  Get all Robots");
		String url = "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";
		RestTemplate restTemplate = new RestTemplate();
		Object[] robots = restTemplate.getForObject(url, Object[].class);
		return ResponseEntity.ok(Arrays.asList(robots));
	}

}
