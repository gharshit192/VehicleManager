package helper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fretron.vehiclemanager.model.Vehicle

object TestData {
    private var objectMapper = ObjectMapper()

    fun getCreateVehicle(): String {
        return "    {\n" +
                "   \"uuid\"  :  \"1a1c5fe5-3ee0-453d-8425-5fec44961029\",\n    " +
                "   \"registrationNumber\"  : \"HIASRHSAHIT\",\n    " +
                "   \"driverName\"  :  \"Driver Name\",\n   " +
                "   \"carryingCapacity\"  :  \"40\",\n  " +
                "   \"bodyType\"  :  \"Type-1\",\n  " +
                "   \"chassisType\"  :  \"Type-2\"\n    " +

                "}"
    }
    fun getVehicle(): Vehicle {
        return objectMapper.readValue(getCreateVehicle(), Vehicle::class.java)
    }
    fun getUpdateVehicle(): String {
        return "    {\n" +
                "   \"uuid\"  :  \"1a1c5fe5-3ee0-453d-8425-5fec44961029\",\n    " +
                "   \"registrationNumber\"  : \"HIASRHSAHITILY\",\n    " +
                "   \"driverName\"  :  \"Driver Name\",\n   " +
                "   \"carryingCapacity\"  :  \"40\",\n  " +
                "   \"bodyType\"  :  \"Type-1\",\n  " +
                "   \"chassisType\"  :  \"Type-5\"\n    " +
                "}"

    }
    fun updateVehicleRequest(): Vehicle {
        return objectMapper.readValue(getUpdateVehicle(), Vehicle::class.java)
    }
}