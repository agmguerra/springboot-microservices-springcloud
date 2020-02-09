package br.agmg.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.agmg.msscbeerservice.domain.Beer;
import br.agmg.msscbeerservice.domain.repositories.BeerRepository;

@Component
public class BeerLoader implements CommandLineRunner {

	private final BeerRepository beerRepository;


	public BeerLoader(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();
	}



	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			beerRepository.save(Beer.builder()
				.beerName("Mango Bobs")
				.beerStyle("IPA")
				.minOnHand(12)
				.quantityToBrew(200)
				.upc(3370100000L)
				.price(new BigDecimal("12.95"))
				.build());

			beerRepository.save(Beer.builder()
					.beerName("Galaxy Cat")
					.beerStyle("PALE_ALE")
					.minOnHand(12)
					.quantityToBrew(200)
					.upc(3370200000L)
					.price(new BigDecimal("11.95"))
					.build());

			System.out.println("Loaded Beers = 2");

		}

	}

}
