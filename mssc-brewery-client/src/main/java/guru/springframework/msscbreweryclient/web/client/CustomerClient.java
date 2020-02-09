package guru.springframework.msscbreweryclient.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;

@Component
@ConfigurationProperties(prefix="sfg.brewery", ignoreUnknownFields = false)
public class CustomerClient {
	
	private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
	private String apihost;
	private final RestTemplate restTemplate;

	public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public CustomerDto getCustomerById(UUID customerId) {
		return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + customerId.toString(), CustomerDto.class);
	}
	
	public URI saveNewCustomer(CustomerDto beerDto) {
		return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, beerDto);
	}

	public void updateCustomer(UUID customerId, CustomerDto customerDto) {
		restTemplate.put(apihost + CUSTOMER_PATH_V1 + customerId.toString(), customerDto);
	}
	
	public void deleteCustomerById(UUID customerId) {
		restTemplate.delete(apihost + CUSTOMER_PATH_V1 + customerId.toString());
	}
	
	public void setApihost(String apihost) {
		this.apihost = apihost;
	}


}
