package org.steemdata.graph;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import eu.bittrade.libs.steemj.communication.HttpClientRequestInitializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SteemDataGraphApplicationTests {

	@Test
	public void contextLoads() throws IOException, GeneralSecurityException {
		createTestNetAccount("my-sd-tests","P8N6sHEJu438dj4dwY9jx9c8deeKpPA6XWCr9aTC5SQ7MiCjMUm");
	}
	/**
	 * Create a new TestNet account as described in the TestNet main page
	 * (https://testnet.steem.vc).
	 *
	 * @param username
	 *            The account to create.
	 * @param password
	 *            The password to set for the <code>username</code>.
	 * @throws IOException
	 *             In case something went wrong.
	 * @throws GeneralSecurityException
	 *             In case something went wrong.
	 */
	private static void createTestNetAccount(String username, String password)
			throws IOException, GeneralSecurityException {
		NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
		// Disable SSL verification:
		builder.doNotValidateCertificate();
		HttpRequest httpRequest = builder.build().createRequestFactory(new HttpClientRequestInitializer())
				.buildPostRequest(new GenericUrl("https://testnet.steem.vc/create"), ByteArrayContent.fromString(
						"application/x-www-form-urlencoded", "username=" + username + "&password=" + password));
		try {
			httpRequest.execute();
		} catch (HttpResponseException e) {
			if (e.getStatusCode() != 409) {
				log.info("Account already existed.");
			}
		}
	}
}
