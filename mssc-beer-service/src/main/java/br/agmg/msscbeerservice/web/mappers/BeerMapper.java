package br.agmg.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;

import br.agmg.msscbeerservice.domain.Beer;
import br.agmg.msscbeerservice.web.model.BeerDto;

@Mapper(uses= {DateMapper.class})
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	Beer beerDtoToBeer(BeerDto beerDto);


}
