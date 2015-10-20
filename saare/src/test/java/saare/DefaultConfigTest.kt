package saare

import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Test

public class DefaultConfigTest {
	interface TestConfigType {
		companion object : DefaultConfig<TestConfigType>(
				defaultValue = object : DefaultConfigTest.TestConfigType {},
				type = TestConfigType::class.java
		) {
		}
	}

	@Test
	public fun testGetDefault () {
		val config1 = TestConfigType(1)
		assertSame(config1, TestConfigType.DEFAULT)

		val instance2 = object : DefaultConfigTest.TestConfigType {}

		val config2 = TestConfigType(instance2)
		assertSame(config2, instance2)
		assertNotSame(config2, TestConfigType.DEFAULT)
	}
}