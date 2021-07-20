package com.fretron.vehiclemanager.di.component

import com.fretron.vehiclemanager.di.module.*
import dagger.Component
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