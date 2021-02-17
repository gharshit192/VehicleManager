package di.module

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import resource.VehicleResource
import service.VehicleService

@Module
class ResourceModule {
//
//    @Provides
//    fun providesVehicleResource(
//        objectMapper: ObjectMapper,
//        vehicleService : VehicleService
//    ): VehicleResource {
//        return VehicleResource(objectMapper,vehicleService)
//    }
}