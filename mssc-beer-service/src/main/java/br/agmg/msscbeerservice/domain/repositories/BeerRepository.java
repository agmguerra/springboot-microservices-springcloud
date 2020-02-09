package br.agmg.msscbeerservice.domain.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.agmg.msscbeerservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>{

}
