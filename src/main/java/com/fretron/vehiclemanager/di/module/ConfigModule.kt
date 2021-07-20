package com.fretron.vehiclemanager.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ConfigModule {

    @Provides
    @Named(AppConstants.KEY_SERVER_HOST)
    fun provideHost() : String{
        return AppConstants.SERVER_HOST_NAME
    }
    @Provides
    @Named(AppConstants.KEY_SERVER_PORT)
    fun providePort() : Int{
        return AppConstants.SERVER_PORT
    }
}