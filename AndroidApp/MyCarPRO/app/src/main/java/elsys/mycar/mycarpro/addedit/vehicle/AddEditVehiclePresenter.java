package elsys.mycar.mycarpro.addedit.vehicle;

import com.google.common.base.Preconditions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import elsys.mycar.mycarpro.data.Data;
import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.DateUtils;
import elsys.mycar.mycarpro.util.StringUtils;

public class AddEditVehiclePresenter implements AddEditVehicleContract.Presenter, OnSaveOrUpdateCallback<Vehicle> {

    private String mVehicleId;
    private VehicleRepository mVehicleRepository;
    private AddEditVehicleContract.View mView;
    private boolean mIsDataMissing;

    public AddEditVehiclePresenter(String mVehicleId, VehicleRepository mVehicleRepository, AddEditVehicleContract.View mView, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing) {
            if (isNewVehicle()) {
                mView.setDate(DateUtils.getTextCurrentDate());
            }else {
                mView.showProgress();
                mVehicleRepository.getVehicleById(mVehicleId, new VehicleRepository.OnVehicleFetchedCallback() {
                    @Override
                    public void onSuccess(Vehicle vehicle) {
                        populateVehicle(vehicle);
                        mView.hideProgress();
                    }

                    @Override
                    public void onFailure() {
                        mView.showMessage("Failed to find such vehicle");
                        mView.hideProgress();
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
    public void saveVehicle(String name, String make, String model, String manufactureDate, String odometer, String horsePower, String notes) {
        mView.showProgress();
        if (StringUtils.checkNotNullOrEmpty(name, make, model, manufactureDate, odometer, horsePower)) {
            try {
                String parsedDate = DateUtils.parseValidTextDateFromText(manufactureDate);
                int parsedOdometer = Integer.parseInt(odometer);
                int parsedHorsePower = Integer.parseInt(horsePower);

                Vehicle vehicle = new Vehicle(name, make, model, parsedDate, parsedOdometer, parsedHorsePower, notes);

                if (isNewVehicle()) {
                    createVehicle(vehicle);
                }else {
                    updateVehicle(vehicle);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                mView.showMessage("Odometer and horse power must be numeric");
            } catch (ParseException | IllegalArgumentException e) {
                e.printStackTrace();
                mView.showMessage("Incorrect date");
            }
        }else {
            mView.showMessage("Please, make sure everything is filled!");
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
            throw new RuntimeException("updateVehicle(Vehicle vehicle) was called but vehicle is new");
        }
        mVehicleRepository.updateVehicle(mVehicleId, vehicle, this);
    }

    private void populateVehicle(Vehicle vehicle) {
        if (mView.isActive()) {
            mView.setName(vehicle.getName());
            mView.setMake(vehicle.getMake());
            mView.setDate(vehicle.getManufactureDate());
            mView.setModel(vehicle.getModel());
            mView.setFuelTank(vehicle.getFuelType(), vehicle.getFuelTankCapacity(), vehicle.getFuelConsumption());
            mView.setColor(vehicle.getColor());
            mIsDataMissing = false;
        }
    }

    private boolean isNewVehicle() {
        return mVehicleId == null;
    }

    @Override
    public void onSuccess(Vehicle item) {
        mView.showMessage(item.getName() + " successfully saved!");
        mView.hideProgress();
        mView.exit();
    }

    @Override
    public void onFailure() {
        mView.showMessage("Something went wrong, please try again");
        mView.hideProgress();
    }
}
