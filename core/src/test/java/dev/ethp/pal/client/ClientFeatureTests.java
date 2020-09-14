package dev.ethp.pal.client;

import org.junit.jupiter.api.Test;
import static dev.ethp.pal.client.asserts.ClientFeatureAssert.assertThat;

public class ClientFeatureTests {

	/**
	 * Test {@link ClientFeature#TEXT_RGB}
	 */
	@Test
	void testTextRgb() {
		assertThat(ClientFeature.TEXT_RGB)
				.appliesTo(Client.DEFAULT_1_16_0)
				.notAppliesTo(Client.DEFAULT_1_15_0);
	}

	/**
	 * Test {@link ClientFeature#NAMESPACED_IDS}
	 */
	@Test
	void testNamespacedIds() {
		assertThat(ClientFeature.NAMESPACED_IDS)
				.appliesTo(Client.DEFAULT_1_13_0)
				.notAppliesTo(ClientVersion.parseUnsafe("1.12.2"));
	}


}

