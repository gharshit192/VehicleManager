package com.fretron.vehiclemanager.di.module

import dagger.Module
import dagger.Provides
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig
import com.fretron.vehiclemanager.resource.VehicleResource
import javax.inject.Named
import javax.ws.rs.core.UriBuilder
import javax.ws.rs.ext.ExceptionMapper


@Module
class HttpModule {

    @Provides
    fun provideResource(vehicleResource: VehicleResource):ResourceConfig{
        return ResourceConfig().register(vehicleResource).register(com.fretron.vehiclemanager.exception.ExceptionMapper())
    }                                 //vehicleResource::class.java

    @Provides
    fun server(@Named(AppConstants.KEY_SERVER_HOST) host : String,
               @Named(AppConstants.KEY_SERVER_PORT) port :Int,
               config : ResourceConfig): HttpServer {
        val url = UriBuilder.fromUri(host).port(port).build()
        return GrizzlyHttpServerFactory.createHttpServer(url,config)
    }
}
