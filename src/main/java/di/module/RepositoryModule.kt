package di.module

import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import repository.VehicleRepository

@Module
class RepositoryModule {
    @Provides
    fun vehicleRepository(database: MongoDatabase, objectMapper: ObjectMapper) : VehicleRepository {
        return VehicleRepository(database,objectMapper)
    }
}