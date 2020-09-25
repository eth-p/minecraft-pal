import org.assertj.core.api.AbstractAssert

/**
 * A base class for AssertJ assertions written in Kotlin.
 */
open class Assert<Self, Actual>(actual: Actual?, type: Class<in Self>) : AbstractAssert<Self, Actual?>(actual, type)
		where Self : Assert<Self, Actual> {

	/**
	 * The actual value provided to the assertion.
	 */
	val actually: Actual
	get() {
		this.isNotNull
		return super.actual!!
	}

	/**
	 * The actual value provided to the assertion.
	 * May be null.
	 */
	val actuallyNullable: Actual?
	get() {
		return super.actual
	}
	
	/**
	 * Returns the self object.
	 * This saves extra casting.
	 */
	@Suppress("UNCHECKED_CAST")
	protected fun self(): Self {
		return this as Self
	}
	
}
