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
		String beerStr = "{\"beerId\":\"d3f0a3e8-9181-4c09-91f6-3d1f0d172242\",\"beerName\":\"BeerName\",\"beerStyle\":\"Ale\",\"upc\":1212121212,\"price\":\"12.99\",\"createdDate\":\"2020-02-06T11:51:09.5106895-02:00\",\"lastUpdatedDate\":\"2020-02-06T11:51:09.6501934-02:00\"}";

		BeerDto beerDto = objectMapper.readValue(beerStr, BeerDto.class);

		System.out.println(beerDto);
	}

}
