package di.component

import com.fasterxml.jackson.databind.ObjectMapper
import com.fretron.vehiclemanager.avro.di.module.*
import dagger.Component
import di.module.*
import di.module.MapperModule
import org.glassfish.grizzly.http.server.HttpServer
import com.fretron.vehiclemanager.service.VehicleService
import com.fretron.vehiclemanager.di.module.*


@Component(
    modules = [
        HttpModule::class,
        ConfigModule::class,
        com.fretron.vehiclemanager.di.module.MapperModule::class,
        RepositoryModule::class,
        DatabaseModule::class
    ]
)
interface VehicleAppTestComponent {
    fun service() : VehicleService
    fun mapper() : ObjectMapper

}