import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DatabaseStarter

fun main(args: Array<String>) {
    runApplication<DatabaseStarter>(*args)
}