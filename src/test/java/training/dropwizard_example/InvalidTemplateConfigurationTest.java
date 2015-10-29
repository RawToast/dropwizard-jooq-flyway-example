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

import training.dropwizard.example.TrainingApplication;
import training.dropwizard.example.TrainingConfiguration;
import training.dropwizard.example.core.Saying;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InvalidTemplateConfigurationTest {
	private static final ObjectMapper mapper = new ObjectMapper();

	@ClassRule
	public static final DropwizardAppRule<TrainingConfiguration> RULE = new DropwizardAppRule<TrainingConfiguration>(TrainingApplication.class,
			ResourceHelpers.resourceFilePath("dodgy-testing-conf.yaml"));

	@Test
	public void generatesAValidSayingWithNoParameters() throws ClientProtocolException, IOException {
		HttpClient client = new HttpClientBuilder(RULE.getEnvironment()).build("Test-Client-1");

		HttpUriRequest request = new HttpGet("http://localhost:18080/saying");
		HttpResponse response = client.execute(request);
		assertEquals(200, response.getStatusLine().getStatusCode());

		String body = EntityUtils.toString(response.getEntity(), "UTF-8");
		Saying saying = mapper.readValue(body, Saying.class);
		assertEquals("Hello!", saying.getContent());
	}

	@Test
	public void generatesAValidSayingWithValidParameters() throws ClientProtocolException, IOException {
		HttpClient client = new HttpClientBuilder(RULE.getEnvironment()).build("Test-Client-2");

		HttpUriRequest request = new HttpGet("http://localhost:18080/saying?name=You");
		HttpResponse response = client.execute(request);
		assertEquals(200, response.getStatusLine().getStatusCode());

		String body = EntityUtils.toString(response.getEntity(), "UTF-8");
		Saying saying = mapper.readValue(body, Saying.class);
		assertEquals("Hello!", saying.getContent());
	}

	@Test
	public void checkHealthCheck() throws ClientProtocolException, IOException {
		HttpClient client = new HttpClientBuilder(RULE.getEnvironment()).build("Test-Client-3");

		HttpUriRequest request = new HttpGet("http://localhost:18081/healthcheck");
		HttpResponse response = client.execute(request);
		assertEquals(500, response.getStatusLine().getStatusCode());

		String body = EntityUtils.toString(response.getEntity(), "UTF-8");
		assertEquals("{\"deadlocks\":{\"healthy\":true},\"template\":{\"healthy\":false,\"message\":\"template doesn't include a name\"}}", body);
	}
}
