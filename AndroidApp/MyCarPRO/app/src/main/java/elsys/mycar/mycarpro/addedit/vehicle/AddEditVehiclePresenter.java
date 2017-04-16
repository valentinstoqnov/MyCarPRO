package elsys.mycar.mycarpro.addedit.vehicle;

import java.text.ParseException;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.DateUtils;
import elsys.mycar.mycarpro.util.StringUtils;

public class AddEditVehiclePresenter implements AddEditVehicleContract.Presenter {

    private String mVehicleId;
    private VehicleRepository mVehicleRepository;
    private AddEditVehicleContract.View mView;
    private boolean mIsDataMissing;

    public AddEditVehiclePresenter(VehicleRepository mVehicleRepository, AddEditVehicleContract.View mView) {
        this.mVehicleRepository = mVehicleRepository;
        this.mView = mView;
    }

    @Override
    public void start() {
        if (mIsDataMissing && isNewVehicle()) {
            mView.setDate(DateUtils.getTextCurrentDate());
        }
    }

    @Override
    public void onDatePicked(int year, int month, int day) {
        mView.setManufactureDate(DateUtils.textDateFromInts(year, month, day));
    }

    @Override
    public void saveVehicle(String name, String make, String model, String manufactureDate, String odometer, String horsePower, String notes) {
        if (StringUtils.checkNotNullOrEmpty(name, make, model, manufactureDate, odometer, horsePower, notes)) {
            try {
                String parsedDate = DateUtils.parseValidTextDateFromText(manufactureDate);
                int parsedOdometer = Integer.parseInt(odometer);
                int parsedHorsePower = Integer.parseInt(horsePower);

                Vehicle vehicle = new Vehicle(name, make, model, parsedDate, parsedOdometer, parsedHorsePower, notes);
                mVehicleRepository.save(vehicle);
                mView.showMessage("Vehicle saved!");
                mView.exit();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                mView.showMessage("Odometer and horse power must be numeric");
            } catch (ParseException e) {
                e.printStackTrace();
                mView.showMessage("Invalid date");
            }
        }else {
            mView.showMessage("Please, make sure everything is filled!");
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private boolean isNewVehicle() {
        return mVehicleId == null;
    }
}
