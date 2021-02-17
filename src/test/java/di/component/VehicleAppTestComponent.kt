package di.component

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Component
import di.module.*
import org.glassfish.grizzly.http.server.HttpServer
import service.VehicleService


@Component(
    modules = [
        HttpModule::class,
        ConfigModule::class,
        MapperModule::class,
        RepositoryModule::class,
        DatabaseModule::class
    ]
)
interface VehicleAppTestComponent {
    fun service() : VehicleService
    fun mapper() : ObjectMapper

}