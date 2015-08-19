package saare

import org.junit.Test
import org.junit.Assert.*

val ii = object : DefaultConfigTest.i {}
public class DefaultConfigTest {
	interface i {
		companion object : DefaultConfig<i>( defaultValue = ii, type = javaClass<i>() ) {

				}
	}

	@Test
	public fun testGetDefault () {
		val default = i.from(1)
		assertSame(default, ii)

		val ii2 = object : DefaultConfigTest.i {}

		val default2 = i.from(ii2)
		assertSame(default2, ii2)
		assertNotSame(default2, ii)
	}
}