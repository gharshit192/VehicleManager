package repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.BasicDBObject
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import com.mongodb.util.JSON
import exception.IllegalException
import exception.IllegalTypeException
import exception.VehicleException
import model.Vehicle
import org.bson.Document
import org.bson.conversions.Bson
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val database: MongoDatabase,
    private val objectMapper: ObjectMapper
) {
    private val vehicleCollectionName = AppConstants.DB_COLLECTION_NAME
    private val collection: MongoCollection<Document> = database.getCollection(vehicleCollectionName)

    fun addVehicle(vehicle: Vehicle): Vehicle {
        val document = Document.parse(vehicle.toString())
        document["_id"] = vehicle.getUuid()
        val existingVehiclesWithRegistrationNo =
            collection.find(Filters.eq("registrationNumber", vehicle.getRegistrationNumber())).limit(1).iterator()
        val existingVehiclesWithChassisType =
            collection.find(Filters.eq("chassisType", vehicle.getChassisType())).limit(1).iterator()

        return if (existingVehiclesWithRegistrationNo.hasNext() || existingVehiclesWithChassisType.hasNext()) {
            println(
                "Already in DB with Registration Number and Chassis Type " +
                        "${vehicle.getRegistrationNumber()} AND ${vehicle.getChassisType()}"
            )
            throw VehicleException()
        } else {
            collection.insertOne(document)
            document.remove("_id")
            vehicle
        }
    }

    fun getVehicle(id: String): Vehicle {
        val query = BasicDBObject()
        query["_id"] = id
        val cursor = collection.find(query).limit(1).iterator()
        return if (cursor.hasNext()) {
            val document = cursor.next()
            document.remove("_id")
            val json = JSON.serialize(document)
            objectMapper.readValue(json, Vehicle::class.java)
        } else {
            throw IllegalTypeException()
        }
    }

    fun updateVehicle(vehicle: Vehicle): Vehicle? {
        val document: Document = collection.findOneAndDelete(Filters.eq("_id", vehicle.getUuid()))
            ?: throw Exception("Vehicle Not Found  with id :: ${vehicle.getUuid()}")
        document.remove("_id")
        val json = JSON.serialize(document)
        return vehicle
        //return objectMapper.readValue(json, Vehicle::class.java)
    }

    fun deleteVehicle(id: String): Vehicle? {
        val query = BasicDBObject()
        query["_id"] = id
        val doc = collection.findOneAndDelete(Filters.eq("_id", id))
        return if (doc != null) {
            doc.remove("_id")
            val json = JSON.serialize(doc)
            objectMapper.readValue(json, Vehicle::class.java)
        } else {
            throw IllegalTypeException()
        }
    }
}