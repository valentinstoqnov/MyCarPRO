package elsys.mycar.mycarpro.detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;

public class DetailVehicleFragment extends Fragment implements DetailVehicleContract.View{

    @BindView(R.id.tv_detail_vehicle_make) TextView tvMake;
    @BindView(R.id.tv_detail_vehicle_model) TextView tvModel;
    @BindView(R.id.tv_detail_vehicle_manufacture_date) TextView tvManufactureDate;
    @BindView(R.id.tv_detail_vehicle_horse_power) TextView tvHorsePower;
    @BindView(R.id.tv_detail_vehicle_odometer) TextView tvOdometer;
    @BindView(R.id.tv_detail_vehicle_note) TextView tvNote;

    private DetailVehicleContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    public static DetailVehicleFragment newInstance() {
        return new DetailVehicleFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_vehicle, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(DetailVehicleContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void setPhoto(Bitmap bitmap) {
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setMake(String make) {
        tvMake.setText(make);
    }

    @Override
    public void setModel(String model) {
        tvModel.setText(model);
    }

    @Override
    public void setManufactureDate(String date) {
        tvManufactureDate.setText(date);
    }

    @Override
    public void setHorsePower(String horsePower) {
        tvHorsePower.setText(horsePower);
    }

    @Override
    public void setFuelType(String fuelType) {

    }

    @Override
    public void setFuelTankCapacity(String capacity) {

    }

    @Override
    public void setFuelConsumption(String consumption) {

    }

    @Override
    public void setNote(String note) {
        tvNote.setText(note);
    }

    @Override
    public void showNoSuchVehicleError() {
        Snackbar.make(tvNote, "Vehicle not found, please retry", Snackbar.LENGTH_INDEFINITE);
    }
}
