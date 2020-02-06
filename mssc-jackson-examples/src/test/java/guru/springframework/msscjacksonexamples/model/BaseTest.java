package guru.springframework.msscjacksonexamples.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

class BaseTest {

	@Autowired
	ObjectMapper objectMapper;

	BeerDto getDto() {
		return BeerDto.builder()
				.beerName("BeerName")
				.beerStyle("Ale")
				.createdDate(OffsetDateTime.now())
				.id(UUID.randomUUID())
				.lastUpdatedDate(OffsetDateTime.now())
				.price(new BigDecimal("12.99"))
				.upc(1212121212L).build();
	}

}
