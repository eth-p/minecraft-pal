package dev.ethp.pal.text.action

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import dev.ethp.apistub.Export
import dev.ethp.pal.client.Client
import dev.ethp.pal.text.placeholder.PlaceholderResolver

/**
 * An action that can be applied to minecraft text.
 *
 * @since 1.0
 */
@Export
interface Action {

	// -------------------------------------------------------------------------------------------------------------
	// Methods:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Gets the action as JSON properties for Minecraft text.
	 *
	 * @param client The client information.
	 * @param resolver The placeholder resolver.
	 * 
	 * @return The JSON object.
	 *
	 * @since 1.0
	 */
	@Export
	fun toJson(client: Client?, resolver: PlaceholderResolver?): JsonObject
	
}
