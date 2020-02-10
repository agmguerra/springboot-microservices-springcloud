package br.agmg.msscbeerservice.services;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.agmg.msscbeerservice.domain.Beer;
import br.agmg.msscbeerservice.domain.repositories.BeerRepository;
import br.agmg.msscbeerservice.web.controller.NotFoundException;
import br.agmg.msscbeerservice.web.mappers.BeerMapper;
import br.agmg.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Override
	public BeerDto getyId(UUID beerId) {

		return beerMapper.beerToBeerDto(
			beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
		);
	}

	@Override
	public BeerDto saveNewBeer(@Valid BeerDto beerDto) {

		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));


	}

	@Override
	public BeerDto updateBeer(UUID beerId, @Valid BeerDto beerDto) {

		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

}
