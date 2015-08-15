package saare

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

public class CIStringTest {
	@Test
	public fun test() {
		assertEquals(CIString(""), CIString.EMPTY)

		assertEquals(CIString("Test").toString(), "Test")

		assertEquals(CIString("Test").hashCode(), CIString("test").hashCode())

		assertEquals(CIString("Test"), CIString("test"))

		val host = CIString("host")
		val Host = CIString("Host")

		assertEquals(Host.hashCode(), host.hashCode())

		assertEquals(Host, host)

		var map =  mapOf(host to null)

		var map2 = mapOf(Host to null)

		assertEquals(map, map2)

		assertTrue(map.containsKey(Host))

		assertTrue(map2.containsKey(host))

		map += Host to null

		assertEquals(map.size(), 1)

		map2 += host to null

		assertEquals(map2.size(), 1)
	}
}