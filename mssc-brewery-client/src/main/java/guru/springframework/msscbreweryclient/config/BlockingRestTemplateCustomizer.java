package guru.springframework.msscbreweryclient.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

	private final Integer maxTotalConnection;
	private final Integer defaultMaxPerRouteConnections;
	private final Integer connectionRequestTimeout;
	private final Integer socketTimeout;
	
	public BlockingRestTemplateCustomizer(@Value("${sfg.httpclient.maxtotalconnections}") Integer maxTotalConnection, 
										  @Value("${sfg.httpclient.defaultmaxperrouteconnections}") Integer defaultMaxTotalConnections,
										  @Value("${sfg.httpclient.connectionrequesttimeout}") Integer connectionRequestTimeout, 
										  @Value("${sfg.httpclient.sockettimeout}") Integer socketTimeout) {
		this.maxTotalConnection = maxTotalConnection;
		this.defaultMaxPerRouteConnections = defaultMaxTotalConnections;
		this.connectionRequestTimeout = connectionRequestTimeout;
		this.socketTimeout = socketTimeout;
	}

	public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnection);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRouteConnections);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
	
	@Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }

}
