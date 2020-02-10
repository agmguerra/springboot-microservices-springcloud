package guru.springframework.msscbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;

@SpringBootTest
class BreweryClientTest {

	@Autowired
	BreweryClient client;
	
	@Autowired
	CustomerClient custClient;
	
	@Test
	void testGetBeerById() {		
		BeerDto dto = client.getBeerById(UUID.randomUUID());
		assertNotNull(dto);
	}

	@Test
	void testSaveNewBeer() {
		//Given
		BeerDto newBeer = BeerDto.builder().beerName("New Beer").build();
		
		URI uriNewBeer = client.saveNewBeer(newBeer);
		
		assertNotNull(uriNewBeer);
		
		System.out.println(uriNewBeer);
	}
	
	@Test
	void testUpdateBeer() {	
		UUID beerId = UUID.randomUUID();
		BeerDto newBeer = BeerDto.builder().beerName("Updated Beer").build();
		
		client.updateBeer(beerId, newBeer);
	}
	
	@Test
	void testDeleteBeerById() {
		UUID beerId = UUID.randomUUID();
		client.deleteBeerById(beerId);
	}
	
	@Test
	void testGetCustomerById() {		
		CustomerDto dto = custClient.getCustomerById(UUID.randomUUID());
		assertNotNull(dto);
	}

	@Test
	void testSaveNewCustomer() {
		//Given
		CustomerDto newCustomer = CustomerDto.builder().name("New Customer").build();
		
		URI uriNewCustomer = custClient.saveNewCustomer(newCustomer);
		
		assertNotNull(uriNewCustomer);
		
		System.out.println(uriNewCustomer);
	}
	
	@Test
	void testUpdateCustomer() {	
		UUID customerId = UUID.randomUUID();
		CustomerDto newCustomer = CustomerDto.builder().name("Updated Customer").build();
		
		custClient.updateCustomer(customerId, newCustomer);
	}
	
	@Test
	void testDeleteCustomerById() {
		UUID customerId = UUID.randomUUID();
		custClient.deleteCustomerById(customerId);
	}	
}
