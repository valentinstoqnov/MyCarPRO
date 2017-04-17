package elsys.mycar.mycarpro.addedit.vehicle;

import com.google.common.base.Preconditions;

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

    public AddEditVehiclePresenter(String mVehicleId, VehicleRepository mVehicleRepository, AddEditVehicleContract.View mView, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing && isNewVehicle()) {
            mView.setDate(DateUtils.getTextCurrentDate());
        }
        mView.addMakes(mVehicleRepository.getMakes());
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

    private boolean isNewVehicle() {
        return mVehicleId == null;
    }
}
