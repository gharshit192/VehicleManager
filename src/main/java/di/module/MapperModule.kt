package di.module

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides


@Module
class MapperModule {

    @Provides
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}