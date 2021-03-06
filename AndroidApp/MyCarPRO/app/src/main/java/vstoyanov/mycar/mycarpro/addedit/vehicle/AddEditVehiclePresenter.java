package vstoyanov.mycar.mycarpro.addedit.vehicle;

import com.google.common.base.Preconditions;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

import vstoyanov.mycar.mycarpro.data.Data;
import vstoyanov.mycar.mycarpro.data.model.Vehicle;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;
import vstoyanov.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import vstoyanov.mycar.mycarpro.util.DateUtils;
import vstoyanov.mycar.mycarpro.util.StringUtils;

public class AddEditVehiclePresenter implements AddEditVehicleContract.Presenter, OnSaveUpdateDeleteCallback {

    private static final String FUEL_TANK_FORMAT = "%s%nCap.:%d%nCons.:%s";

    private String mUserId;
    private String mVehicleId;
    private VehicleRepository mVehicleRepository;
    private AddEditVehicleContract.View mView;
    private boolean mIsDataMissing;

    private String mFuelType;
    private int mCapacity;
    private double mConsumption;

    public AddEditVehiclePresenter(String userId, String vehicleId, VehicleRepository vehicleRepository, AddEditVehicleContract.View view, boolean isDataMissing) {
        mUserId = Preconditions.checkNotNull(userId, "userId cannot be null");
        mVehicleId = vehicleId;
        mVehicleRepository = Preconditions.checkNotNull(vehicleRepository, "vehicle repository cannot be null");
        mView = Preconditions.checkNotNull(view, "view cannot be null");
        mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            if (isNewVehicle()) {
                mView.setDate(DateUtils.getTextCurrentDate());
            }else {
                mView.showProgress();
                mVehicleRepository.fetchVehicleById(mVehicleId, new OnItemFetchedCallback<Vehicle>() {
                    @Override
                    public void onSuccess(Vehicle item) {
                        if (mIsDataMissing) {
                            populateVehicle(item);
                        }
                        mView.hideProgress();
                    }

                    @Override
                    public void onFailure() {
                        mView.hideProgress();
                        mView.showNoSuchVehicle();
                    }
                });
            }
            mView.showMakes(Data.getMakes());
        }
    }

    @Override
    public void onDatePicked(int year, int month, int day) {
        mView.setManufactureDate(DateUtils.textDateFromInts(year, month, day));
    }

    @Override
    public void onFuelTankPicked(String fuelType, int fuelTankCapacity, double fuelConsumption) {
        mFuelType = fuelType;
        mCapacity = fuelTankCapacity;
        mConsumption = fuelConsumption;

        mView.setFuelTank(formatFuelTank(fuelType, fuelTankCapacity, fuelConsumption));
    }

    @Override
    public void saveVehicle(String name, String make, String model, String manufactureDate, String horsePower, String odometer, int color, String note) {
        mView.showProgress();
        if (StringUtils.checkNotNullOrEmpty(name, make, model, manufactureDate, odometer, horsePower, note, mFuelType)) {
            try {
                String parsedDate = DateUtils.parseValidTextDateFromText(manufactureDate);
                int parsedOdometer = Integer.parseInt(odometer);
                int parsedHorsePower = Integer.parseInt(horsePower);
                Vehicle vehicle = new Vehicle(name, make, model, parsedDate, parsedHorsePower, parsedOdometer, mFuelType, mCapacity, mConsumption, color, note, mUserId);

                if (isNewVehicle()) {
                    createVehicle(vehicle);
                }else {
                    updateVehicle(vehicle);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                mView.hideProgress();
                mView.showPriceOrOdometerParseError();
            } catch (ParseException | IllegalArgumentException e) {
                e.printStackTrace();
                mView.showDateError();
                mView.hideProgress();
            }
        }else {
            mView.showEmptyFieldsError();
            mView.hideProgress();
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private void createVehicle(Vehicle vehicle) {
        mVehicleRepository.saveVehicle(vehicle, this);
    }

    private void updateVehicle(Vehicle vehicle) {
        if (isNewVehicle()) {
            throw new RuntimeException("updateVehicle was called but vehicle is new");
        }
        mVehicleRepository.updateVehicle(mVehicleId, vehicle, this);
    }

    private void populateVehicle(Vehicle vehicle) {
        if (mView.isActive()) {
            mView.setName(vehicle.getName());
            mView.setMake(vehicle.getMake());
            mView.setDate(vehicle.getManufactureDate());
            mView.setModel(vehicle.getModel());
            mView.setFuelTank(formatFuelTank(vehicle.getFuelType(), vehicle.getFuelTankCapacity(), vehicle.getFuelConsumption()));
            mView.setColor(vehicle.getColor());
            mView.setOdometer(String.valueOf(vehicle.getOdometer()));
            mView.setHorsePower(String.valueOf(vehicle.getHorsePower()));
            mView.setNote(vehicle.getNote());
            mIsDataMissing = false;
        }
    }

    private boolean isNewVehicle() {
        return mVehicleId == null;
    }

    private String formatFuelTank(String fuelType, int capacity, double consumption) {
        DecimalFormat df = new DecimalFormat("###.#");
        return String.format(Locale.US, FUEL_TANK_FORMAT, fuelType, capacity, df.format(consumption));
    }

    @Override
    public void onSuccess(String name) {
        mView.showVehicleSuccessfullySaved(name);
        mView.hideProgress();
    }

    @Override
    public void onFailure() {
        mView.showVehicleSaveError();
        mView.hideProgress();
    }
}
