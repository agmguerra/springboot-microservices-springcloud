package guru.springframework.msscbreweryclient.config;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class NIORestTemplateCustomizer implements RestTemplateCustomizer {

	private final Integer connectionTimeout;
	private final Integer ioThreadCount;
	private final Integer soTimeout;
	private final Integer maxTotalConnections;
	private final Integer defaultMaxPerRouteConnections;
		
	public NIORestTemplateCustomizer(@Value("${sfg.httpclient.connectiontimeout}") Integer connectionTimeout, 
									 @Value("${sfg.httpclient.iothreadcount}") Integer ioThreadCount, 
									 @Value("${sfg.httpclient.sotimeout}") Integer soTimeout,
									 @Value("${sfg.httpclient.maxtotalconnections}") Integer maxTotalConnections, 
									 @Value("${sfg.httpclient.defaultmaxperrouteconnections}") Integer defaultMaxPerRouteConnections) {

		this.connectionTimeout = connectionTimeout;
		this.ioThreadCount = ioThreadCount;
		this.soTimeout = soTimeout;
		this.maxTotalConnections = maxTotalConnections;
		this.defaultMaxPerRouteConnections = defaultMaxPerRouteConnections;
	}

	public ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
        final DefaultConnectingIOReactor ioreactor = new DefaultConnectingIOReactor(IOReactorConfig.custom().
                setConnectTimeout(connectionTimeout).
                setIoThreadCount(ioThreadCount).
                setSoTimeout(soTimeout).
                build());

        final PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioreactor);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRouteConnections);
        connectionManager.setMaxTotal(maxTotalConnections);

        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        return new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);

    }

    @Override
    public void customize(RestTemplate restTemplate) {
        try {
            restTemplate.setRequestFactory(clientHttpRequestFactory());
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
    }
}
