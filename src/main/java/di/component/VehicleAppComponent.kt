package di.component

import dagger.Component
import di.module.*
import org.glassfish.grizzly.http.server.HttpServer

@Component(
    modules = [
        HttpModule::class,
        ConfigModule::class,
        MapperModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
interface VehicleAppComponent {
    fun server() : HttpServer
}