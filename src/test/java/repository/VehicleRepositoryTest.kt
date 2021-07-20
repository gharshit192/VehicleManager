package repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import helper.EmbeddedMongoDb
import helper.TestData
import org.junit.Assert.*
import com.fretron.vehiclemanager.model.Vehicle
import com.fretron.vehiclemanager.repository.VehicleRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class VehicleRepositoryTest {

    private val uuid: String = "1a1c5fe5-3ee0-453d-8425-5fec44961029"
    private lateinit var embeddedMongoDb: EmbeddedMongoDb
    private lateinit var database: MongoDatabase
    private val objectMapper = ObjectMapper()
    private lateinit var  vehicleRepository: VehicleRepository

    @Before
    fun configure() {

        startMongoDb()
        val mongoClient = MongoClient("localhost", embeddedMongoDb.port)
        database = mongoClient.getDatabase("vehicle")
        vehicleRepository = VehicleRepository(database, ObjectMapper())
    }

    @After
    fun closeConnections() {
        embeddedMongoDb.stop()
    }

    private fun startMongoDb() {
        val rand = Random()
        val n = rand.nextInt(99) + 9900
        embeddedMongoDb = EmbeddedMongoDb(n)
        embeddedMongoDb.start()
    }
    @Test
    fun create_vehicle(){
        val vehicleRepository = VehicleRepository( database,objectMapper)
        val request = TestData.getCreateVehicle()
        val vehicle = objectMapper.readValue(request, Vehicle::class.java)
        val vehicleCreated = vehicleRepository.addVehicle(vehicle)
        print(vehicleCreated.toString())
        assertNotNull(vehicleCreated)
    }

    @Test
    fun get_vehicle(){
        val vehicleRepository = VehicleRepository( database,objectMapper)
        val vehicle = TestData.getVehicle()
        val vehicleCreated = vehicleRepository.addVehicle(vehicle)
        println("Vehicle -> $vehicleCreated")
        val vehicleInDb = vehicleRepository.getVehicle(uuid)
        println("Vehicle Db -> $vehicleInDb")
        assertNotNull(vehicleCreated)
    }
    @Test
    fun update_vehicle(){
        val vehicleRepository = VehicleRepository( database,objectMapper)
        val vehicle = TestData.updateVehicleRequest()
        val vehicleCreated = vehicleRepository.addVehicle(vehicle)
        println("Vehicle -> $vehicleCreated")
        val vehicleInDb = vehicleRepository.updateVehicle(vehicle)
        println("Updated Vehicle In Db -> $vehicleInDb")
        assertNotNull(vehicleCreated)

    }
    @Test
    fun deleted_vehicle(){
        val vehicleRepository = VehicleRepository(database, objectMapper)
        val vehicle = TestData.getVehicle()
        val vehicleCreated = vehicleRepository.addVehicle(vehicle)
        println("Vehicle -> $vehicleCreated")
        val vehicleDeleted = vehicleRepository.deleteVehicle(uuid)
        println("Deleted Vehicle from DB -> $vehicleDeleted")
        assertNotNull(vehicleCreated)
    }
}