package vstoyanov.mycar.mycarpro.addedit.refueling;

import java.text.ParseException;

import vstoyanov.mycar.mycarpro.data.Data;
import vstoyanov.mycar.mycarpro.data.model.Refueling;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;
import vstoyanov.mycar.mycarpro.data.repository.activities.refueling.RefuelingRepositoryImpl;
import vstoyanov.mycar.mycarpro.util.DateUtils;
import vstoyanov.mycar.mycarpro.util.PriceUtils;
import vstoyanov.mycar.mycarpro.util.StringUtils;

public class AddEditRefuelingPresenter implements AddEditRefuelingContract.Presenter, OnSaveUpdateDeleteCallback {

    private String mVehicleId;
    private String mRefuelingId;
    private RefuelingRepositoryImpl mRefuelingRepository;
    private AddEditRefuelingContract.View mView;
    private boolean mIsDataMissing;

    public AddEditRefuelingPresenter(String mVehicleId, String mRefuelingId, RefuelingRepositoryImpl mRefuelingRepository, AddEditRefuelingContract.View mView, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mRefuelingId = mRefuelingId;
        this.mRefuelingRepository = mRefuelingRepository;
        this.mView = mView;
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (isVehicleNull()) {
            mView.showNoSuchVehicle();
        }else {
            if (mIsDataMissing) {
                if (isNewRefueling()) {
                    mView.setDate(DateUtils.getTextCurrentDate());
                    mView.setTime(DateUtils.getTextCurrentTime());
                }else {
                    mRefuelingRepository.fetchRefuelingById(mRefuelingId, new OnItemFetchedCallback<Refueling>() {
                        @Override
                        public void onSuccess(Refueling item) {
                            if (mIsDataMissing) {
                                populateRefueling(item);
                            }
                            mView.hideProgress();
                        }

                        @Override
                        public void onFailure() {
                            mView.hideProgress();
                            mView.showRefuelingRetrievalError();
                        }
                    });
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
                Refueling refueling = new Refueling(companyName, dateTime, parsedQuantity, parsedPrice, parsedOdometer, note, mVehicleId);

                if (isNewRefueling()) {
                    createRefueling(refueling);
                }else {
                    updateRefueling(refueling);
                }
            }catch (NumberFormatException e) {
                mView.showPriceOrOdometerParseError();
                mView.hideProgress();
            }catch (ParseException | IllegalArgumentException e) {
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
        mRefuelingRepository.saveRefueling(refueling, this);
    }

    private void updateRefueling(Refueling refueling) {
        if (isNewRefueling()) {
            throw new RuntimeException("updateRefueling called but refueling is new");
        }
        mRefuelingRepository.updateRefueling(refueling.getId(), refueling, this);
    }

    @Override
    public void onSuccess(String name) {
        mView.hideProgress();
        mView.showRefuelingSuccessfullySaved(name);
    }

    @Override
    public void onFailure() {
        mView.hideProgress();
        mView.showRefuelingSaveError();
    }

    private void populateRefueling(Refueling refueling) {
        if (mView.isActive()) {
            mView.setCompanyName(refueling.getCompanyName());
            mView.setPrice(PriceUtils.longToString(refueling.getPrice()));
            mView.setOdometer(String.valueOf(refueling.getOdometer()));
            mView.setNote(refueling.getNote());
            //TODO: FIX ME !!
            // view.setDate(refueling.getDate());
            // view.setTime(DateUtils);
            mView.setQuantity(String.valueOf(refueling.getQuantity()));
            // FIXME: 11.06.17 !!!! add is full fuel tank feature !!!!!
            mView.setFullFuelTank(false);
            mIsDataMissing = false;
        }
    }
}
