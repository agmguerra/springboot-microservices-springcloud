package br.agmg.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.agmg.msscbeerservice.services.BeerService;
import br.agmg.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {


	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {

		BeerDto dto = beerService.getyId(beerId);
		return new ResponseEntity<>(dto, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {

		beerService.saveNewBeer(beerDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<?> updateBeerById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {

		beerService.updateBeer(beerId, beerDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
