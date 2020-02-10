package br.agmg.msscbeerservice.services;

import java.util.UUID;

import javax.validation.Valid;

import br.agmg.msscbeerservice.web.model.BeerDto;

public interface BeerService {

	BeerDto getyId(UUID beerId);

	BeerDto saveNewBeer(@Valid BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, @Valid BeerDto beerDto);

}
