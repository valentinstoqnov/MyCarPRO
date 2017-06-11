package elsys.mycar.mycarpro.detail.vehicle;

import com.google.common.base.Preconditions;

import java.util.Locale;

import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;

public class DetailVehiclePresenter implements DetailVehicleContract.Presenter, OnItemFetchedCallback<Vehicle> {

    private static final String FUEL_TANK_FORMAT = "Fuel tank: \nType: %s,\nCapacity: %d L,\nConsumption: %f";

    private DetailVehicleContract.View mView;
    private VehicleRepository mVehicleRepository;
    private String mVehicleId;
    private boolean mIsDataMissing;

    public DetailVehiclePresenter(DetailVehicleContract.View mView, VehicleRepository mVehicleRepository, String mVehicleId, boolean mIsDataMissing) {
        this.mView = Preconditions.checkNotNull(mView);
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mVehicleId = Preconditions.checkNotNull(mVehicleId, "detail vehicle presenter expects not null vehicle id");
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (isDataMissing()) {
            fetchVehicle();
        }
    }

    @Override
    public void onSuccess(Vehicle vehicle) {
        if (vehicle != null) {
            populateVehicle(vehicle);
        }else {
            mView.showNoSuchVehicleError();
        }
    }

    @Override
    public void onFailure() {
        mView.showMessage("Couldn't fetch the vehicle, please try again");
    }

    @Override
    public void openEditVehicleUi() {
        mView.showEditVehicleUi(mVehicleId);
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private void fetchVehicle() {
        mVehicleRepository.fetchVehicleById(mVehicleId, this);
    }

    private void populateVehicle(Vehicle vehicle) {
        vehicle = Preconditions.checkNotNull(vehicle, "attempt to populate null vehicle");

        mView.setColor(vehicle.getColor());
        mView.setName("Name: " + vehicle.getName());
        mView.setMake("Make: " + vehicle.getMake());
        mView.setModel("Model: " + vehicle.getModel());
        mView.setManufactureDate("Manufacture date: " + vehicle.getManufactureDate());
        mView.setHorsePower("Horse power" + String.valueOf(vehicle.getHorsePower()));
        mView.setOdometer("Odometer: " + vehicle.getOdometer());
        String fuelTank = String.format(Locale.US, FUEL_TANK_FORMAT, vehicle.getFuelType(), vehicle.getFuelTankCapacity(), vehicle.getFuelConsumption());
        mView.setFuelTank(fuelTank);

        mView.setNote(vehicle.getNote());
    }
}
