package elsys.mycar.mycarpro.addedit.service;

import com.google.common.base.Preconditions;

import java.text.ParseException;

import elsys.mycar.mycarpro.data.Data;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.repository.OnSaveOrUpdateCallback;
import elsys.mycar.mycarpro.data.repository.activities.service.ServiceRepository;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.util.DateUtils;
import elsys.mycar.mycarpro.util.PriceUtils;
import elsys.mycar.mycarpro.util.StringUtils;

public class AddEditServicePresenter implements AddEditServiceContract.Presenter, OnSaveOrUpdateCallback<Service> {

    private String mVehicleId;
    private String mServiceId;
    private ServiceRepository mServiceRepository;
    private VehicleRepository mVehicleRepository;
    private AddEditServiceContract.View mView;
    private boolean mIsDataMissing;

    public AddEditServicePresenter(String vehicleId, String serviceId, ServiceRepository serviceRepository, VehicleRepository vehicleRepository, AddEditServiceContract.View view, boolean isDataMissing) {
        mVehicleId = Preconditions.checkNotNull(vehicleId);
        mServiceId = serviceId;
        mServiceRepository = Preconditions.checkNotNull(serviceRepository);
        mVehicleRepository = Preconditions.checkNotNull(vehicleRepository);
        mView = Preconditions.checkNotNull(view);
        mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (isVehicleNull()) {
            mView.showMessage("No such vehicle");
        }else {
            if (mIsDataMissing) {
                if (isNewService()) {
                    mView.setDate(DateUtils.getTextCurrentDate());
                    mView.setTime(DateUtils.getTextCurrentTime());
                } else {
                    //mServiceRepository.getServiceById();
                }
                mView.showServiceTypes(Data.getServiceTypes());
            }
        }
    }

    @Override
    public void saveService(String serviceType, String price, String odometer, String date, String time, String note) {
        mView.showProgress();
        if (StringUtils.checkNotNullOrEmpty(serviceType, price, odometer, date, time)) {
            try {
                long parsedOdometer = Long.decode(odometer);
                long parsedPrice = PriceUtils.stringToLong(price);
                Service service = new Service(serviceType, DateUtils.getTextDateTime(date, time), parsedPrice, parsedOdometer, note);

                if (isNewService()) {
                    createService(service);
                }else {
                    updateService(service);
                }

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

    private void createService(Service service) {
        mServiceRepository.saveService(mVehicleId, service, this);
    }

    private void updateService(Service service) {
        if (isNewService()) {
            throw new RuntimeException("updateService called but service is new");
        }
        mServiceRepository.updateService(mVehicleId, mServiceId, service, this);
    }

    private boolean isNewService() {
        return mServiceId == null;
    }

    private boolean isVehicleNull() {
        return mVehicleId == null;
    }

    @Override
    public void onSuccess(Service item) {
        mView.showMessage("Service successfully saved!");
        mView.hideProgress();
        mView.exit();
    }

    @Override
    public void onFailure() {
        mView.showMessage("Something went wrong, please try again");
        mView.hideProgress();
    }
}
