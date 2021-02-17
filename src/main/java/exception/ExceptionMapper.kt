package exception

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ExceptionMapper : ExceptionMapper<IllegalException> {
    override fun toResponse(exception: IllegalException): Response {

        when (exception) {
            is IllegalTypeException -> return Response.ok(200).entity("Invalid id or id not found").build()
            is VehicleException -> return Response.ok(200).entity("Vehicle already entered").build()
        }
        return Response.ok(200).entity("EXCEPTION Not FOUND").build()
    }
}
