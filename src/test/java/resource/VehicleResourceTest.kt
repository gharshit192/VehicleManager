package resource

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import di.component.DaggerVehicleAppTestComponent
import helper.TestData
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.json.JSONObject
import org.junit.*
import org.junit.Assert.*
import org.mockito.Mockito.mock
import service.VehicleService
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType


class VehicleResourceTest : JerseyTest() {

    private val baseUrl = "vehicle/v1"
    private var uuid = "1a1c5fe5-3ee0-453d-8425-5fec44961029"
    private val objectMapper = ObjectMapper()
    private var vehicleService: VehicleService = mock(VehicleService::class.java)


    override fun configure(): Application {
        val testComponent = DaggerVehicleAppTestComponent.builder().build()
        return ResourceConfig().register(VehicleResource(testComponent.service(),testComponent.mapper())).application
    }

    @Test
    fun return_200_create_vehicle() {
        val request = TestData.getCreateVehicle()
        val response = target("$baseUrl/vehicle").request().post(Entity.entity(request, MediaType.APPLICATION_JSON))
        println("Response Status : ${response.status}")
        assertTrue("Response Status : ", response.status == 200)
        val responseJson = JSONObject(response.readEntity(String::class.java))
        uuid = responseJson.get("uuid").toString()
        assert(responseJson.get("uuid") != null)
        println(" UUID -> ${responseJson.get("uuid")}")
        println("Response Json -> $responseJson")
   }

    @Test
    fun return_200_get_vehicle(){
        val testVehicle = TestData.getVehicle()
        whenever(vehicleService.getVehicle(any())).thenReturn(testVehicle)
        val response = target("$baseUrl/vehicle/$uuid").request().get()
        println(response.status)
        assertTrue("Response Status : " ,response.status == 200)
        val responseJson = JSONObject(response.readEntity(String::class.java))
        println("response  uuid : ${responseJson.get("uuid")}")
    }

    @Test
    fun return_200_update_vehicle(){
        val updatedRequest = TestData.getUpdateVehicle()
        val response = target("$baseUrl/vehicle").request().put(Entity.entity(updatedRequest,MediaType.APPLICATION_JSON))
        println(response.status)
        assertTrue("Response Status : " ,response.status == 200)

    }
    @Test
    fun return_200_after_delete(){
        val response = target("$baseUrl/vehicle/$uuid").request().delete()
        println(response.status)
        assertTrue("Response Status : " ,response.status == 200)
        val responseJson = JSONObject(response.readEntity(String::class.java))
        println("response  uuid : ${responseJson.get("uuid")}")
    }
}
