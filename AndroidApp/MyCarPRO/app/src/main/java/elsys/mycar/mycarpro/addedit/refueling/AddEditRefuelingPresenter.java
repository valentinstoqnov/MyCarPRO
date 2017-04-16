package elsys.mycar.mycarpro.addedit.refueling;

import com.google.common.base.Preconditions;

import java.text.ParseException;
import java.util.Date;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.DateUtils;
import elsys.mycar.mycarpro.util.PriceUtils;
import elsys.mycar.mycarpro.util.StringUtils;

public class AddEditRefuelingPresenter implements AddEditRefuelingContract.Presenter{

    private String mVehicleId;
    private String mRefuelingId;
    private AddEditRefuelingContract.View mView;
    private VehicleRepository mVehicleRepository;
    private  boolean mIsDataMissing;

    public AddEditRefuelingPresenter(String mVehicleId, String mRefuelingId, AddEditRefuelingContract.View mView, VehicleRepository mVehicleRepository, boolean mIsDataMissing) {
        this.mVehicleId = Preconditions.checkNotNull(mVehicleId);
        this.mRefuelingId = mRefuelingId;
        this.mView = Preconditions.checkNotNull(mView);
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing && isNewRefueling()) {
            mView.setDate(DateUtils.getTextCurrentDate());
            mView.setTime(DateUtils.getTextCurrentTime());
        }
    }

    @Override
    public void saveRefueling(String companyName, String quantity, String price, String odometer, String date, String time, String note, boolean isFull) {
        if (StringUtils.checkNotNullOrEmpty(companyName, quantity, price, odometer, date, time, note)) {
            try {
                long parsedOdometer = Long.decode(odometer);
                long parsedPrice = PriceUtils.stringToLong(price);
                double parsedQuantity = Double.parseDouble(quantity);
                String dateTime = DateUtils.getTextDateTime(date, time);

                Refueling refueling = new Refueling(companyName, dateTime, parsedQuantity, parsedPrice, parsedOdometer, note);
                mVehicleRepository.addRefueling(mVehicleId, refueling);
            }catch (NumberFormatException e) {
                mView.showMessage("Price and odometer fields expect numbers only");
            }catch (ParseException | IllegalArgumentException e) {
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

    private boolean isNewRefueling() {
        return mRefuelingId == null;
    }
}
