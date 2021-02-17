package service

import exception.IllegalException
import model.Vehicle
import repository.VehicleRepository
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class VehicleService @Inject constructor(
    private val vehicleRepository: VehicleRepository
) {

    fun addVehicle(vehicle: Vehicle): Vehicle {
        if (vehicle.getRegistrationNumber().isNullOrEmpty())
            throw Exception(" Registration No is Null ")
        else if(vehicle.getChassisType().isNullOrEmpty())
            throw Exception(" Chassis Type is Null ")
        else
            vehicle.setUuid(UUID.randomUUID().toString())
            return vehicleRepository.addVehicle(vehicle)
    }

    fun getVehicle(id: String): Vehicle {
//        return  Vehicle()
        return vehicleRepository.getVehicle(id)
    }

    fun updateVehicle(vehicle: Vehicle): Vehicle?{
//        return vehicle
        return vehicleRepository.updateVehicle(vehicle)
    }

    fun deleteVehicle(id: String): Vehicle? {
        return vehicleRepository.deleteVehicle(id)
    }
}