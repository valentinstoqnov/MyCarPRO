package vstoyanov.mycar.mycarpro.addedit.vehicle.fueltank;

import vstoyanov.mycar.mycarpro.data.Data;
import vstoyanov.mycar.mycarpro.util.StringUtils;

public class AddEditFuelTankPresenter implements AddEditFuelTankContract.Presenter {

    private AddEditFuelTankContract.View mView;

    public AddEditFuelTankPresenter(AddEditFuelTankContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.showFuelTypes(Data.getFuelTypes());
    }

    @Override
    public void addFuelTank(String fuelType, String capacity, String consumption) {
        boolean isValid = true;
        int parsedCapacity = 0;
        double parsedConsumption = 0;

        if (StringUtils.checkNotNullOrEmpty(capacity)) {
            try {
                parsedCapacity = Integer.parseInt(capacity);
            }catch (NumberFormatException e) {
                isValid = false;
                mView.showCapacityError("Capacity must be numeric");
                e.printStackTrace();
            }
        }else {
            mView.showCapacityError("Capacity is empty");
            isValid = false;
        }

        if (StringUtils.checkNotNullOrEmpty(consumption)) {
            try {
                parsedConsumption = Double.parseDouble(consumption);
            }catch (NumberFormatException e) {
                isValid = false;
                mView.showConsumptionError("Consumption must be numeric");
                e.printStackTrace();
            }
        }else {
            mView.showCapacityError("Consumption is empty");
            isValid = false;
        }

        if (isValid) {
            mView.onInputValidated(fuelType, parsedCapacity, parsedConsumption);
        }
    }
}
