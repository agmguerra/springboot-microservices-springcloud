package br.agmg.msscbeerservice.services;

import java.util.UUID;

import br.agmg.msscbeerservice.web.model.BeerDto;

public interface BeerService {

	BeerDto getyId(UUID beerId);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, BeerDto beerDto);

}
