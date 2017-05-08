package elsys.mycar.mycarpro.addedit.refueling;

import com.google.common.base.Preconditions;

import java.text.ParseException;

import elsys.mycar.mycarpro.data.Data;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import elsys.mycar.mycarpro.data.repository.activities.insurance.InsuranceRepository;
import elsys.mycar.mycarpro.data.repository.activities.refueling.RefuelingRepositoryImpl;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.util.DateUtils;
import elsys.mycar.mycarpro.util.PriceUtils;
import elsys.mycar.mycarpro.util.StringUtils;

public class AddEditRefuelingPresenter implements AddEditRefuelingContract.Presenter, OnSaveOrUpdateCallback<Refueling> {

    private String mVehicleId;
    private String mRefuelingId;
    private RefuelingRepositoryImpl mRefuelingRepository;
    private AddEditRefuelingContract.View mView;
    private VehicleRepository mVehicleRepository;
    private  boolean mIsDataMissing;

    public AddEditRefuelingPresenter(String mVehicleId, String mRefuelingId, RefuelingRepositoryImpl mRefuelingRepository, AddEditRefuelingContract.View mView, VehicleRepository mVehicleRepository, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mRefuelingId = mRefuelingId;
        this.mRefuelingRepository = mRefuelingRepository;
        this.mView = mView;
        this.mVehicleRepository = mVehicleRepository;
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (isVehicleNull()) {
            mView.showMessage("No such vehicle");
        }else {
            if (mIsDataMissing) {
                if (isNewRefueling()) {
                    mView.setDate(DateUtils.getTextCurrentDate());
                    mView.setTime(DateUtils.getTextCurrentTime());
                }else {
                    //get refueling by id
                }
                mView.addGasStations(Data.getGasStationCompanies());
            }
        }
    }

    @Override
    public void saveRefueling(String companyName, String quantity, String price, String odometer, String date, String time, String note, boolean isFull) {
        mView.showProgress();
        if (StringUtils.checkNotNullOrEmpty(companyName, quantity, price, odometer, date, time, note)) {
            try {
                long parsedOdometer = Long.decode(odometer);
                long parsedPrice = PriceUtils.stringToLong(price);
                double parsedQuantity = Double.parseDouble(quantity);
                String dateTime = DateUtils.getTextDateTime(date, time);
                Refueling refueling = new Refueling(companyName, dateTime, parsedQuantity, parsedPrice, parsedOdometer, note);

                if (isNewRefueling()) {
                    createRefueling(refueling);
                }else {
                    updateRefueling(refueling);
                }

                mView.showMessage("Refueling successfully saved!");
                mView.exit();
            }catch (NumberFormatException e) {
                mView.showMessage("Price and odometer fields expect numbers only");
                mView.hideProgress();
            }catch (ParseException | IllegalArgumentException e) {
                e.printStackTrace();
                mView.showMessage("Incorrect date");
                mView.hideProgress();
            }
        }else {
            mView.showMessage("Please, make sure everything is filled!");
            mView.hideProgress();
        }
    }

    @Override
    public void onDatePicked(int year, int month, int day) {
        mView.setDate(DateUtils.textDateFromInts(year, month, day));
    }

    @Override
    public void onTimePicked(int hour, int minute) {
        mView.setTime(DateUtils.textTimeFromInts(hour, minute));
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private boolean isNewRefueling() {
        return mRefuelingId == null;
    }

    public boolean isVehicleNull() {
        return mVehicleId == null;
    }

    private void createRefueling(Refueling refueling) {
        mRefuelingRepository.saveRefueling(mVehicleId, refueling, this);
    }

    private void updateRefueling(Refueling refueling) {
        if (isNewRefueling()) {
            throw new RuntimeException("updateRefueling called but refueling is new");
        }
        mRefuelingRepository.updateRefueling(mVehicleId, refueling.getId(), refueling, this);
    }

    @Override
    public void onSuccess(Refueling item) {
        mView.showMessage("Refueling successfully saved!");
        mView.hideProgress();
        mView.exit();
    }

    @Override
    public void onFailure() {
        mView.showMessage("Something went wrong, please try again");
        mView.hideProgress();
    }
}
