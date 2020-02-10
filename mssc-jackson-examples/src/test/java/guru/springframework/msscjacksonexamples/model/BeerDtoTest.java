package guru.springframework.msscjacksonexamples.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;



@JsonTest
class BeerDtoTest extends BaseTest {

	@Test
	void testSerializeDto() throws JsonProcessingException {
		BeerDto beerDto = getDto();
		String jsonString = objectMapper.writeValueAsString(beerDto);

		System.out.println(jsonString);
	}

	@Test
	void testDeserializeDto() throws JsonMappingException, JsonProcessingException {
		String beerStr = "{\"beerName\":\"BeerName\",\"beerStyle\":\"Ale\",\"upc\":1212121212,\"price\":\"12.99\",\"createdDate\":\"2020-02-10T16:39:41-0200\",\"lastUpdatedDate\":\"2020-02-10T16:39:41.2987512-02:00\",\"myLocalDate\":\"20200210\",\"beerId\":\"f6feeaa1-72b4-4ebd-ae44-dfc9be0d407b\"}";

		BeerDto beerDto = objectMapper.readValue(beerStr, BeerDto.class);

		System.out.println(beerDto);
	}

}
