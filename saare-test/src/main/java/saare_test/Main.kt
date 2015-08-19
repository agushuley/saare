package saare_test

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
import saare.SaareMain
import saare_test.settings.Base
import javax.servlet.GenericServlet
import javax.servlet.Servlet
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import kotlin.platform.platformStatic

/**
 * Created by andriy on 14/07/15.
 */

public open class Main : SaareMain(Base) {
	companion object {
		@platformStatic
		public fun main(args: Array<String>) {
			saare.SaareMain.main(args, javaClass<SaareMain>());
		}
	}
}

