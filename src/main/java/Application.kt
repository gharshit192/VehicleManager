import di.component.DaggerVehicleAppComponent
import java.lang.Exception

fun main() {
        val component = DaggerVehicleAppComponent.builder().build()
        val httpServer = component.server()
        httpServer.start()
}
