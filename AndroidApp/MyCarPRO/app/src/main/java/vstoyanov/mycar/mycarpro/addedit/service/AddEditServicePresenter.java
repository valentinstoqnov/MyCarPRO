package vstoyanov.mycar.mycarpro.addedit.service;

import com.google.common.base.Preconditions;

import java.text.ParseException;

import vstoyanov.mycar.mycarpro.data.Data;
import vstoyanov.mycar.mycarpro.data.model.Service;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;
import vstoyanov.mycar.mycarpro.data.repository.activities.service.ServiceRepository;
import vstoyanov.mycar.mycarpro.util.DateUtils;
import vstoyanov.mycar.mycarpro.util.PriceUtils;
import vstoyanov.mycar.mycarpro.util.StringUtils;

public class AddEditServicePresenter implements AddEditServiceContract.Presenter, OnSaveUpdateDeleteCallback {

    private String mVehicleId;
    private String mServiceId;
    private ServiceRepository mServiceRepository;
    private AddEditServiceContract.View mView;
    private boolean mIsDataMissing;

    public AddEditServicePresenter(String vehicleId, String serviceId, ServiceRepository serviceRepository, AddEditServiceContract.View view, boolean isDataMissing) {
        mVehicleId = Preconditions.checkNotNull(vehicleId);
        mServiceId = serviceId;
        mServiceRepository = Preconditions.checkNotNull(serviceRepository);
        mView = Preconditions.checkNotNull(view);
        mIsDataMissing = isDataMissing;
    }

    @Override
    public void start() {
        if (isVehicleNull()) {
            mView.showNoSuchVehicle();
        }else {
            if (mIsDataMissing) {
                if (isNewService()) {
                    mView.setDate(DateUtils.getTextCurrentDate());
                    mView.setTime(DateUtils.getTextCurrentTime());
                } else {
                    mServiceRepository.fetchServiceById(mServiceId, new OnItemFetchedCallback<Service>() {
                        @Override
                        public void onSuccess(Service item) {
                            if (mIsDataMissing) {
                                populateService(item);
                            }
                            mView.hideProgress();
                        }

                        @Override
                        public void onFailure() {
                            mView.showServiceRetrievalError();
                            mView.hideProgress();
                        }
                    });
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
                Service service = new Service(serviceType, DateUtils.getTextDateTime(date, time), parsedPrice, parsedOdometer, note, mVehicleId);

                if (isNewService()) {
                    createService(service);
                }else {
                    updateService(service);
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

    private void createService(Service service) {
        mServiceRepository.saveService(service, this);
    }

    private void updateService(Service service) {
        if (isNewService()) {
            throw new RuntimeException("updateService called but service is new");
        }
        mServiceRepository.updateService(mServiceId, service, this);
    }

    private boolean isNewService() {
        return mServiceId == null;
    }

    private boolean isVehicleNull() {
        return mVehicleId == null;
    }

    @Override
    public void onSuccess(String name) {
        mView.hideProgress();
        mView.showServiceSuccessfullySaved(name);
    }

    @Override
    public void onFailure() {
        mView.hideProgress();
        mView.showServiceSaveError();
    }

    private void populateService(Service item) {
        if (mView.isActive()) {
            mView.setType(item.getType());
            mView.setPrice(PriceUtils.longToString(item.getPrice()));
            mView.setOdometer(String.valueOf(item.getOdometer()));
            mView.setNote(item.getNote());
            mIsDataMissing = false;
        }
    }
}
