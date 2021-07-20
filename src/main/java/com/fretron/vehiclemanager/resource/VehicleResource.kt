package com.fretron.vehiclemanager.resource

import com.fasterxml.jackson.databind.ObjectMapper
import com.fretron.vehiclemanager.model.Vehicle
import com.fretron.vehiclemanager.service.VehicleService
import java.lang.Exception
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path(AppConstants.BASE_URL)
class VehicleResource @Inject constructor(
    private val vehicleService: VehicleService,
    private val objectMapper: ObjectMapper)
{
    @POST
    @Path(AppConstants.VEHICLE)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    fun addVehicle(request : String) : Response{
        try {
            val vehicle = vehicleService.addVehicle(objectMapper.readValue(request , Vehicle::class.java))
            return Response.ok(vehicle.toString()).build()
        }catch (e : Exception){
            e.printStackTrace()
        }
      return Response.ok("Hello Exception").build()
    }

    @GET
    @Path("vehicle/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getVehicle(@PathParam("uuid") id: String):Response {
        val vehicle = vehicleService.getVehicle(id)
        return Response.ok(vehicle.toString()).build()
    }

    @PUT
    @Path("vehicle")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateVehicle(request: String): Response{
        val vehicle = vehicleService.updateVehicle(objectMapper.readValue(request, Vehicle::class.java))
        return  Response.ok(vehicle.toString()).build()
    }

    @DELETE
    @Path("vehicle/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteVehicle(@PathParam("uuid") id: String) : Response{
        val vehicle = vehicleService.deleteVehicle(id)
        return Response.ok(vehicle.toString()).build()
    }
}