import com.fretron.vehiclemanager.di.component.DaggerVehicleAppComponent

fun main() {
        val component = DaggerVehicleAppComponent.builder().build()
        val httpServer = component.server()
        httpServer.start()
}
