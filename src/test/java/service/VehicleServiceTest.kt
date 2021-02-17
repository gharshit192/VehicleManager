package service

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import helper.TestData
import model.Vehicle
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import repository.VehicleRepository


class VehicleServiceTest {

    private val mapper = ObjectMapper()
    private val vehicleRepository: VehicleRepository = mock(VehicleRepository::class.java)
    private var vehicleService = VehicleService(vehicleRepository)
    private lateinit var uuid: String


    @Test
    fun create_vehicle() {
        val request = TestData.getCreateVehicle()
        val vehicle = mapper.readValue(request, Vehicle::class.java)
        whenever(vehicleService.addVehicle(vehicle)).thenReturn(vehicle)
        val createdVehicle = vehicleService.addVehicle(vehicle)
        println(createdVehicle.toString())
        assertNotNull(createdVehicle)
        assertNotNull(createdVehicle?.getRegistrationNumber())
        assertNotNull(createdVehicle?.getChassisType())
        assertTrue(createdVehicle?.getUuid() != null)
        uuid = createdVehicle?.getUuid().toString()
    }


    @Test
    fun get_vehicle() {
        create_vehicle()
        val request = TestData.getVehicle().getUuid()
        assertTrue(request != null)
        println(request)
        assertNotEquals("", request)
    }

    @Test
    fun update_vehicle() {
        create_vehicle()
        val request = TestData.updateVehicleRequest()
        whenever(vehicleService.updateVehicle(request)).thenReturn(request)
        val updated = vehicleService.updateVehicle(request)
        println("Updated Vehicle -> $updated")
        assertTrue(updated?.getUuid() != null)
        println(" Update vehicle UUID ->  ${updated?.getUuid()}")
        assertNotEquals("", request.getUuid())

    }
    @Test
    fun delete_vehicle(){
        create_vehicle()
        val request = TestData.getVehicle()
        whenever(vehicleService.deleteVehicle(request.getUuid().toString())).thenReturn(request)
        val deleted = vehicleService.deleteVehicle(uuid)
        println("Deleted Vehicle -> $deleted")
        assertTrue(deleted?.getUuid() != null)
    }
}