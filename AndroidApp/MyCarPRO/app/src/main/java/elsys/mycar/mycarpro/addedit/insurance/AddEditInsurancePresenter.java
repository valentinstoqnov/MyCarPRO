package elsys.mycar.mycarpro.addedit.insurance;

import java.text.ParseException;

import elsys.mycar.mycarpro.data.Data;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;
import elsys.mycar.mycarpro.data.repository.activities.insurance.InsuranceRepository;
import elsys.mycar.mycarpro.util.DateUtils;
import elsys.mycar.mycarpro.util.PriceUtils;
import elsys.mycar.mycarpro.util.StringUtils;

public class AddEditInsurancePresenter implements AddEditInsuranceContract.Presenter, OnSaveUpdateDeleteCallback {

    private String mVehicleId;
    private String mInsuranceId;
    private InsuranceRepository mInsuranceRepository;
    private AddEditInsuranceContract.View mView;
    private boolean mIsDataMissing;

    public AddEditInsurancePresenter(String mVehicleId, String mInsuranceId, InsuranceRepository mInsuranceRepository, AddEditInsuranceContract.View mView, boolean mIsDataMissing) {
        this.mVehicleId = mVehicleId;
        this.mInsuranceId = mInsuranceId;
        this.mInsuranceRepository = mInsuranceRepository;
        this.mView = mView;
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (isVehicleNull()) {
            mView.showNoSuchVehicle();
        }else {
            if (mIsDataMissing) {
                if (isNewInsurance()) {
                    mView.setDate(DateUtils.getTextCurrentDate());
                } else {
                    mView.showProgress();
                    mInsuranceRepository.fetchInsuranceById(mInsuranceId, new OnItemFetchedCallback<Insurance>() {
                        @Override
                        public void onSuccess(Insurance item) {
                            if (mIsDataMissing) {
                                populateInsurance(item);
                            }
                            mView.hideProgress();
                        }

                        @Override
                        public void onFailure() {
                            mView.showInsuranceRetrievalError();
                            mView.hideProgress();
                        }
                    });
                }
                mView.addCompanies(Data.getInsuranceCompanies());
            }
        }
    }

    @Override
    public void saveInsurance(String companyName, String price, String odometer, String date, String expirationDate, String note) {
        mView.showProgress();
        if (StringUtils.checkNotNullOrEmpty(companyName, price, odometer, date, expirationDate, note)) {
            try {
                long parsedOdometer = Long.decode(odometer);
                long parsedPrice = PriceUtils.stringToLong(price);
                Insurance insurance = new Insurance(companyName, parsedPrice, parsedOdometer, DateUtils.parseValidTextDateFromText(date), DateUtils.parseValidTextDateFromText(expirationDate), note, mVehicleId);

                if (isNewInsurance()) {
                    createInsurance(insurance);
                }else {
                    updateInsurance(insurance);
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
    public void onDatePicked(int year, int month, int day, boolean isExpiration) {
        if (isExpiration){
            mView.setExpirationDate(DateUtils.textDateFromInts(year, month, day));
        }else {
            mView.setDate(DateUtils.textDateFromInts(year, month, day));
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private void createInsurance(Insurance insurance) {
        mInsuranceRepository.saveInsurance(insurance, this);
    }

    private void updateInsurance(Insurance insurance) {
        if (isNewInsurance()) {
            throw new RuntimeException("updateService called but service is new");
        }
        mInsuranceRepository.updateInsurance(mInsuranceId, insurance, this);
    }

    private boolean isNewInsurance() {
        return mInsuranceId == null;
    }

    private boolean isVehicleNull() {
        return mVehicleId == null;
    }

    @Override
    public void onSuccess(String name) {
        mView.hideProgress();
        mView.showInsuranceSuccessfullySaved(name);
    }

    @Override
    public void onFailure() {
        mView.hideProgress();
        mView.showInsuranceSaveError();
    }

    private void populateInsurance(Insurance insurance) {
        if (mView.isActive()) {
            mView.setCompanyName(insurance.getCompanyName());
            mView.setPrice(PriceUtils.longToString(insurance.getPrice()));
            mView.setOdometer(String.valueOf(insurance.getOdometer()));
            mView.setNote(insurance.getNote());
            mView.setDate(insurance.getDate());
            mView.setExpirationDate(insurance.getExpirationDate());
            mIsDataMissing = false;
        }
    }
}
