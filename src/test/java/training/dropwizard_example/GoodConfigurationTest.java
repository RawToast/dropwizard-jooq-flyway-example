package training.dropwizard_example;

import static org.junit.Assert.assertEquals;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.junit.ClassRule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import training.dropwizard.example.TrainingApplication;
import training.dropwizard.example.TrainingConfiguration;
import training.dropwizard.example.core.Saying;

public class GoodConfigurationTest {
	private static final ObjectMapper mapper = new ObjectMapper();

	@ClassRule
	public static final DropwizardAppRule<TrainingConfiguration> RULE = new DropwizardAppRule<TrainingConfiguration>(TrainingApplication.class,
			ResourceHelpers.resourceFilePath("testing-conf.yaml"));

	@Test
	public void generatesAValidSayingWithNoParameters() throws ClientProtocolException, IOException {
		HttpClient client = new HttpClientBuilder(RULE.getEnvironment()).build("Test-Client-1");

		HttpUriRequest request = new HttpGet("http://localhost:18080/saying");
		HttpResponse response = client.execute(request);
		assertEquals(200, response.getStatusLine().getStatusCode());

		String body = EntityUtils.toString(response.getEntity(), "UTF-8");
		Saying saying = mapper.readValue(body, Saying.class);
		assertEquals("Hello, Stranger!", saying.getContent());
	}

	@Test
	public void generatesAValidSayingWithValidParameters() throws ClientProtocolException, IOException {
		HttpClient client = new HttpClientBuilder(RULE.getEnvironment()).build("Test-Client-2");

		HttpUriRequest request = new HttpGet("http://localhost:18080/saying?name=You");
		HttpResponse response = client.execute(request);
		assertEquals(200, response.getStatusLine().getStatusCode());

		String body = EntityUtils.toString(response.getEntity(), "UTF-8");
		Saying saying = mapper.readValue(body, Saying.class);
		assertEquals("Hello, You!", saying.getContent());
	}

	@Test
	public void checkHealthCheck() throws ClientProtocolException, IOException {
		HttpClient client = new HttpClientBuilder(RULE.getEnvironment()).build("Test-Client-3");

		HttpUriRequest request = new HttpGet("http://localhost:18081/healthcheck");
		HttpResponse response = client.execute(request);
		assertEquals(200, response.getStatusLine().getStatusCode());

		String body = EntityUtils.toString(response.getEntity(), "UTF-8");
		assertEquals("{\"deadlocks\":{\"healthy\":true},\"template\":{\"healthy\":true}}", body);
	}
}
