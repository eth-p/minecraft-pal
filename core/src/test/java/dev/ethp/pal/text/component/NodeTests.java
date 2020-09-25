package dev.ethp.pal.text.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import dev.ethp.pal.client.Client;
import dev.ethp.pal.text.placeholder.PlaceholderResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import static dev.ethp.pal.text.component.NodeAssert.assertThat;
import static dev.ethp.pal.text.placeholder.PlaceholderResolverAssert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NodeTests {

	static private class ImplNode implements Node {

		@NotNull
		@Override
		public String toLegacyString() {
			return "toLegacyString";
		}

		@NotNull
		@Override
		public String toString() {
			return "toString";
		}

		@NotNull
		@Override
		public JsonElement toJson(@Nullable Client client, @Nullable PlaceholderResolver resolver) {
			if (client != null && resolver != null) return new JsonPrimitive("toJson + Client + Resolver");
			if (client != null) return new JsonPrimitive("toJson + Client");
			if (resolver != null) return new JsonPrimitive("toJson + Resolver");
			return new JsonPrimitive("toJson");
		}

	}

	/**
	 * Test that the default method implementations are correct.
	 */
	@Test
	void testDefaults() {
		// toString(PlaceholderResolver) --> toString()
		assertThat(new ImplNode())
				.isStringEqualTo("toString")
				.isStringWithResolverEqualTo(null, "toString")
				.isStringWithResolverEqualTo(mockThrowingResolver(), "toString");

		// toLegacyString(PlaceholderResolver) --> toLegacyString()
		assertThat(new ImplNode())
				.isLegacyStringEqualTo("toLegacyString")
				.isLegacyStringWithResolverEqualTo(null, "toLegacyString")
				.isLegacyStringWithResolverEqualTo(mockThrowingResolver(), "toLegacyString");

		// toJson() --> toJson(Client, PlaceholderResolver)
		// toJson(Client) --> toJson(Client, PlaceholderResolver)
		// toJson(PlaceholderResolver) --> toJson(Client, PlaceholderResolver)
		assertThat(new ImplNode())
				.isJsonEqualTo(new JsonPrimitive("toJson"))
				.isJsonWithClientEqualTo(null, () -> new JsonPrimitive("toJson"))
				.isJsonWithClientEqualTo(Client.LATEST, () -> new JsonPrimitive("toJson + Client"))
				.isJsonWithResolverEqualTo(null, () -> new JsonPrimitive("toJson"))
				.isJsonWithResolverEqualTo(mockThrowingResolver(), () -> new JsonPrimitive("toJson + Resolver"))
				.isJsonWithAllEqualTo(null, null, () -> new JsonPrimitive("toJson"))
				.isJsonWithAllEqualTo(Client.LATEST, mockThrowingResolver(), () -> new JsonPrimitive("toJson + Client + Resolver"));

		// toJsonString() --> toJsonString(Client, PlaceholderResolver)
		assertThat(new ImplNode().toJsonString()).isEqualTo("\"toJson\"");
		assertThat(new ImplNode().toJsonString(Client.LATEST, mockThrowingResolver())).isEqualTo("\"toJson + Client + Resolver\"");
	}

}

