package elsys.mycar.mycarpro.addedit;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.DateUtils;

public class AddEditVehiclePresenter implements AddEditVehicleContract.Presenter {

    private VehicleRepository mVehicleRepository;
    private AddEditVehicleContract.View mView;

    public AddEditVehiclePresenter(VehicleRepository mVehicleRepository, AddEditVehicleContract.View mView) {
        this.mVehicleRepository = mVehicleRepository;
        this.mView = mView;
    }

    @Override
    public void start() {
        //TODO: implement
    }

    @Override
    public void onDatePicked(int year, int month, int day) {
        mView.setManufactureDate(DateUtils.textDateFromInts(year, month, day));
    }

    @Override
    public void saveVehicle(String name, String make, String model, String manufactureDate, String odometer, String horsePower, String notes) {
        if (validate(name, make, model, manufactureDate, odometer, horsePower, notes)) {
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
                mView.showMessage("Something went wrong...");
            }
        }else {
            mView.showMessage("Please, make sure everything is filled!");
        }
    }

    private boolean validate(String... input) {
        for (String str : input) {
            if (str == null || str.isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
