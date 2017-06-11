package elsys.mycar.mycarpro.detail.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.addedit.vehicle.AddEditVehicleActivity;
import elsys.mycar.mycarpro.data.Constants;
import elsys.mycar.mycarpro.homescreen.MainActivity;

public class DetailVehicleFragment extends Fragment implements DetailVehicleContract.View{

    @BindView(R.id.tv_detail_vehicle_make) TextView tvMake;
    @BindView(R.id.tv_detail_vehicle_model) TextView tvModel;
    @BindView(R.id.tv_detail_vehicle_manufacture_date) TextView tvManufactureDate;
    @BindView(R.id.tv_detail_vehicle_horse_power) TextView tvHorsePower;
    @BindView(R.id.tv_detail_vehicle_odometer) TextView tvOdometer;
    @BindView(R.id.tv_detail_vehicle_fuel_tank) TextView tvFuelTank;
    @BindView(R.id.tv_detail_vehicle_note) TextView tvNote;

    private AppBarLayout mAppBarLayout;
    private DetailVehicleContract.Presenter mPresenter;
    private Unbinder mUnbinder;
    private Toolbar mToolbar;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAppBarLayout = (AppBarLayout) getActivity().findViewById(R.id.app_bar_detail_vehicle);
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        getActivity().findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openEditVehicleUi();
            }
        });
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
    public void setColor(int color) {
        mAppBarLayout.setBackgroundColor(color);
    }

    @Override
    public void setName(String name) {
        mToolbar.setTitle(name);
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
    public void setFuelTank(String fuelTank) {
        tvFuelTank.setText(fuelTank);
    }

    @Override
    public void setOdometer(String odometer) {
        tvOdometer.setText(odometer);
    }

    @Override
    public void setNote(String note) {
        tvNote.setText(note);
    }

    @Override
    public void showEditVehicleUi(String id) {
        Intent intent = new Intent(getActivity(), AddEditVehicleActivity.class);
        intent.putExtra(Constants.VEHICLE_ID, id);
        startActivity(intent);
    }

    @Override
    public void showNoSuchVehicleError() {
        Snackbar.make(getView(), "Vehicle not found, please retry", Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }
}
