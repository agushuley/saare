package saare

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.servlet.Servlet

/**
 * Created by andriy on 14/07/15.
 */

@EnableAutoConfiguration
@Configuration
public open class SaareMain(public val saareConfiguration: Any) : SpringBootServletInitializer() {
	@Bean
	open public fun dispatcherServlet(): Servlet {
		return servlet;
	}

	override protected fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(SaareMain::class.java);
	}

	val servlet = HttpServlet(saareConfiguration)

	companion object {
		@JvmStatic
		public fun main(args: Array<String>, mainClass: Class<SaareMain>) {
			SpringApplication.run(arrayOf(mainClass), args);
		}
	}
}


