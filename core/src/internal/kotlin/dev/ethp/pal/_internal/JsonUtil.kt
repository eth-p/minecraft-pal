@file:Suppress("NOTHING_TO_INLINE")

package dev.ethp.pal._internal

import com.google.gson.*

object JsonUtil {

	/**
	 * An internal singleton for converting to and from JSON.
	 * NOT FOR PUBLIC USE.
	 */
	val gson: Gson = Gson()
	

	// -------------------------------------------------------------------------------------------------------------
	// Kotlin Operators:
	// -------------------------------------------------------------------------------------------------------------

	inline operator fun JsonObject.set(key: String, value: JsonElement) {
		this.add(key, value)
	}
	
	inline operator fun JsonObject.set(key: String, value: List<JsonElement>) {
		val array = JsonArray(value.size)
		value.forEach { 
			array.add(it)
		}
		
		this.add(key, array)
	}

	inline operator fun JsonObject.set(key: String, value: String) {
		this.addProperty(key, value)
	}

	inline operator fun JsonObject.set(key: String, value: Boolean) {
		this.addProperty(key, value)
	}

	inline operator fun JsonObject.set(key: String, value: Number) {
		this.addProperty(key, value)
	}
	
	inline operator fun JsonObject.plusAssign(obj: JsonObject) {
		obj.entrySet().forEach {
			this.add(it.key, it.value)
		}
	}


	// -------------------------------------------------------------------------------------------------------------
	// Kotlin Casting:
	// -------------------------------------------------------------------------------------------------------------

	/**
	 * Creates a JSON object from pairs of elements.
	 *
	 * @param pairs The pairs of elements.
	 *
	 * @return The corresponding JSON object.
	 */
	fun jsonOf(vararg pairs: Pair<String, JsonElement>): JsonObject {
		val obj = JsonObject()

		pairs.forEach {
			obj.add(it.first, it.second)
		}

		return obj
	}
	
	/**
	 * Creates a JSON primitive from a [String].
	 *
	 * @return The JSON primitive.
	 */
	inline fun String.asJson(): JsonPrimitive {
		return JsonPrimitive(this)
	}

	/**
	 * Creates a JSON primitive from a [Boolean].
	 *
	 * @return The JSON primitive.
	 */
	inline fun Boolean.asJson(): JsonPrimitive {
		return JsonPrimitive(this)
	}

	/**
	 * Creates a JSON primitive from a [Short].
	 *
	 * @return The JSON primitive.
	 */
	inline fun Number.asJson(): JsonPrimitive {
		return JsonPrimitive(this)
	}

//	/**
//	 * Creates a JSON primitive from a [Short].
//	 *
//	 * @return The JSON primitive.
//	 */
//	inline fun Byte.asJson(): JsonPrimitive {
//		return JsonPrimitive(this)
//	}
//
//	/**
//	 * Creates a JSON primitive from a [Short].
//	 *
//	 * @return The JSON primitive.
//	 */
//	inline fun Short.asJson(): JsonPrimitive {
//		return JsonPrimitive(this)
//	}
//
//	/**
//	 * Creates a JSON primitive from an [Int].
//	 *
//	 * @return The JSON primitive.
//	 */
//	inline fun Int.asJson(): JsonPrimitive {
//		return JsonPrimitive(this)
//	}
//
//	/**
//	 * Creates a JSON primitive from a [Long].
//	 *
//	 * @return The JSON primitive.
//	 */
//	inline fun Long.asJson(): JsonPrimitive {
//		return JsonPrimitive(this)
//	}
//
//	/**
//	 * Creates a JSON primitive from a [Float].
//	 *
//	 * @return The JSON primitive.
//	 */
//	inline fun Float.asJson(): JsonPrimitive {
//		return JsonPrimitive(this)
//	}
//
//	/**
//	 * Creates a JSON primitive from a [Double].
//	 *
//	 * @return The JSON primitive.
//	 */
//	inline fun Double.asJson(): JsonPrimitive {
//		return JsonPrimitive(this)
//	}

}
