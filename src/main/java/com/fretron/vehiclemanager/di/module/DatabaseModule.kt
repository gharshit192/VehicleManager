package com.fretron.vehiclemanager.di.module

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {

    @Provides
    fun provideMongoDb(): MongoDatabase {
        return MongoClient(AppConstants.DB_HOST, AppConstants.DB_PORT).getDatabase(AppConstants.DB_NAME)
    }
}