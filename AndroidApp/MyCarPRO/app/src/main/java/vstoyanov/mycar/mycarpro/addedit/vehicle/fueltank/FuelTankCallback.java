package vstoyanov.mycar.mycarpro.addedit.vehicle.fueltank;

public interface FuelTankCallback {

    void onEntered(String fuelType, int capacity, double consumption);
}
