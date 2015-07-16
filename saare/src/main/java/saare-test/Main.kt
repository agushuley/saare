package saare

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.GenericServlet
import javax.servlet.Servlet
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import kotlin.platform.platformStatic

/**
 * Created by andriy on 14/07/15.
 */

@EnableAutoConfiguration
@Configuration
public open class Main : SpringBootServletInitializer() {
	@Bean
	open public fun dispatcherServlet(): Servlet {
		return Main;
	}

	override protected fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(javaClass<Main>());
	}

	companion object : GenericServlet() {
		override public fun service(req: ServletRequest, res: ServletResponse) {
			val req = req as HttpServletRequest
			res.setContentType("text/plain");
			res.getWriter().append("Hello World from ${req.getRequestURI()}");
		}

		@platformStatic
		public fun main(args: Array<String>) {
			SpringApplication.run(arrayOf(javaClass<Main>()), args);
		}
	}
}

