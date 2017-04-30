package elsys.mycar.mycarpro.addedit.insurance;

import com.google.common.base.Preconditions;

import java.text.ParseException;
import java.util.UUID;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.util.DateUtils;
import elsys.mycar.mycarpro.util.PriceUtils;
import elsys.mycar.mycarpro.util.StringUtils;

public class AddEditInsurancePresenter implements AddEditInsuranceContract.Presenter{

    private String mVehicleId;
    private String mInsuranceId;
    private VehicleRepository mVehicleRepository;
    private AddEditInsuranceContract.View mView;
    private boolean mIsDataMissing;

    public AddEditInsurancePresenter(String vehicleId, String insuranceId, VehicleRepository mVehicleRepository, AddEditInsuranceContract.View mView, boolean mIsDataMissing) {
        this.mVehicleId = Preconditions.checkNotNull(vehicleId, "vehicle id cannot be null");
        this.mInsuranceId = insuranceId;
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing && isNewInsurance()) {
            mView.setDate(DateUtils.getTextCurrentDate());
        }
        mView.addCompanies(mVehicleRepository.getCompanyNames());
    }

    @Override
    public void saveInsurance(String companyName, String price, String odometer, String date, String expirationDate, String note) {
        if (StringUtils.checkNotNullOrEmpty(companyName, price, odometer, date, expirationDate, note)) {
            try {
                long parsedOdometer = Long.decode(odometer);
                long parsedPrice = PriceUtils.stringToLong(price);
                Insurance insurance = new Insurance(companyName, parsedPrice, parsedOdometer, DateUtils.parseValidTextDateFromText(date), DateUtils.parseValidTextDateFromText(expirationDate), note);
                insurance.setId(UUID.randomUUID().toString());
                mVehicleRepository.addInsurance(mVehicleId, insurance);
                mView.showMessage("Insurance successfully saved!");
                mView.exit();
            }catch (NumberFormatException e) {
                mView.showMessage("Price and odometer fields expect numbers only");
            }catch (ParseException | IllegalArgumentException e) {
                e.printStackTrace();
                mView.showMessage("Incorrect date");
            }
        }else {
            mView.showMessage("Please, make sure everything is filled!");
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

    private boolean isNewInsurance() {
        return mInsuranceId == null;
    }
}
