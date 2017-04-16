package elsys.mycar.mycarpro.addedit.service;

import com.google.common.base.Preconditions;

import java.text.ParseException;
import java.util.UUID;

import elsys.mycar.mycarpro.data.VehicleRepository;
import elsys.mycar.mycarpro.model.Service;
import elsys.mycar.mycarpro.util.DateUtils;
import elsys.mycar.mycarpro.util.PriceUtils;
import elsys.mycar.mycarpro.util.StringUtils;

public class AddEditServicePresenter implements AddEditServiceContract.Presenter{

    private String mVehicleId;
    private String mServiceId;
    private VehicleRepository mVehicleRepository;
    private AddEditServiceContract.View mView;
    private boolean mIsDataMissing;

    public AddEditServicePresenter(String mVehicleId, String mServiceId, VehicleRepository mVehicleRepository, AddEditServiceContract.View mView, boolean mIsDataMissing) {
        this.mVehicleId = Preconditions.checkNotNull(mVehicleId);
        this.mServiceId = mServiceId;
        this.mVehicleRepository = Preconditions.checkNotNull(mVehicleRepository);
        this.mView = Preconditions.checkNotNull(mView);
        this.mIsDataMissing = mIsDataMissing;
    }

    @Override
    public void start() {
        if (mIsDataMissing && isNewService()) {
            mView.setDate(DateUtils.getTextCurrentDate());
            mView.setTime(DateUtils.getTextCurrentTime());
        }
    }

    @Override
    public void saveService(String serviceType, String price, String odometer, String date, String time, String note) {
        if (StringUtils.checkNotNullOrEmpty(serviceType, price, odometer, date, time, note)) {
            try {
                long parsedOdometer = Long.decode(odometer);
                long parsedPrice = PriceUtils.stringToLong(price);
                Service service = new Service(serviceType, DateUtils.parseValidTextDateFromText(date), parsedPrice, parsedOdometer, note);
                service.setId(UUID.randomUUID().toString());
                mVehicleRepository.addService(mVehicleId, service);
            }catch (NumberFormatException e) {
                mView.showMessage("Price and odometer fields expect numbers only");
            }catch (ParseException e) {
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

    private boolean isNewService() {
        return mServiceId == null;
    }
}
