package saare

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by andriy on 14/07/15.
 */

@RestController
@EnableAutoConfiguration
public class Main {

    @RequestMapping("/")
    fun home(): String {
        return "Hello World!";
    }

}

public fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(javaClass<Main>()), args);
}
