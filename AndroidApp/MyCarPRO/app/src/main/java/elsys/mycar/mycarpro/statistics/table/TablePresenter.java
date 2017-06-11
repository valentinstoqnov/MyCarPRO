package elsys.mycar.mycarpro.statistics.table;

import java.util.List;

import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.data.repository.vehicle.VehicleRepository;
import elsys.mycar.mycarpro.util.PriceUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class TablePresenter implements TableContract.Presenter, VehicleRepository.OnVehiclesFetchedCallback {

    private VehicleRepository mVehicleRepository;
    private TableContract.View mView;
    private String mVehicleId;

    @Override
    public void start() {
        mVehicleRepository.fetchVehicles(this);
    }

    @Override
    public void onSuccess(List<Vehicle> vehicles) {
        mView.setTotalVehicles(String.valueOf(vehicles.size()));
        findVehicleWithId(vehicles);
    }

    @Override
    public void onFailure() {
        mView.showMessage("Couldn't fetch your vehicles");
    }

    private void onVehicleFound(Vehicle vehicle) {
        BiFunction<Long, Long, Long> sum = new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(@NonNull Long aLong, @NonNull Long aLong2) throws Exception {
                return  aLong + aLong2;
            }
        };

        List<Service> services = vehicle.getServices();
        List<Insurance> insurances = vehicle.getInsurances();
        List<Refueling> refuelings = vehicle.getRefuelings();

        mView.setTotalServices(String.valueOf(services.size()));
        mView.setTotalInsurances(String.valueOf(insurances.size()));
        mView.setTotalRefuelings(String.valueOf(refuelings.size()));

        Observable.fromIterable(services)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map(new Function<Service, Long>() {
                @Override
                public Long apply(@NonNull Service service) throws Exception {
                    return service.getPrice();
                }
            })
            .reduce(sum)
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(@NonNull Long aLong) throws Exception {
                    mView.setServiceExpenses(PriceUtils.longToString(aLong));
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(@NonNull Throwable throwable) throws Exception {
                    mView.showMessage("Couldn't calculate service expenses");
                    throwable.printStackTrace();
                }
            });

        Observable.fromIterable(insurances)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Insurance, Long>() {
                    @Override
                    public Long apply(@NonNull Insurance insurance) throws Exception {
                        return insurance.getPrice();
                    }
                })
                .reduce(sum)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.setInsurnaceExpenses(PriceUtils.longToString(aLong));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showMessage("Couldn't calculate insurance expenses");
                        throwable.printStackTrace();
                    }
                });

        Observable.fromIterable(refuelings)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Refueling, Long>() {
                    @Override
                    public Long apply(@NonNull Refueling refueling) throws Exception {
                        return refueling.getPrice();
                    }
                })
                .reduce(sum)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.setRefuelingExpenses(PriceUtils.longToString(aLong));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showMessage("Couldn't calculate refueling expenses");
                        throwable.printStackTrace();
                    }
                });
    }

    private void findVehicleWithId(List<Vehicle> vehicles) {
        Observable.fromIterable(vehicles)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<Vehicle>() {
                    @Override
                    public boolean test(@NonNull Vehicle vehicle) throws Exception {
                        return mVehicleId.equals(vehicle.getId());
                    }
                })
                .firstOrError()
                .subscribe(new Consumer<Vehicle>() {
                    @Override
                    public void accept(@NonNull Vehicle vehicle) throws Exception {
                        onVehicleFound(vehicle);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        mView.showMessage("Couldn't find selected vehicle");
                    }
                });
    }
}
