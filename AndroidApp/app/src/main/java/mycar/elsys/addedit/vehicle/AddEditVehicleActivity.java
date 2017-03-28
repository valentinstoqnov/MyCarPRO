package mycar.elsys.addedit.vehicle;

import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import mycar.elsys.utils.TextInputUtils;
import mycar.elsys.base.activity.BaseActivity;
import registration.elsys.R;

public class AddEditVehicleActivity extends BaseActivity implements AddEditVehicleContract.View{

    @BindView(R.id.spn_add_vehicle_type) Spinner spnType;
    @BindView(R.id.til_add_vehicle_name) TextInputLayout tilName;
    @BindView(R.id.til_add_vehicle_make) TextInputLayout tilMake;
    @BindView(R.id.til_add_vehicle_model) TextInputLayout tilModel;
    @BindView(R.id.til_add_vehicle_horse_power) TextInputLayout tilHorsePower;
    @BindView(R.id.til_add_vehicle_registration_plate) TextInputLayout tilRegistrationPlate;
    @BindView(R.id.til_add_vehicle_info) TextInputLayout tilInfo;
    @BindView(R.id.btn_add_vehicle_add_tank) Button btnAddFuelTank;
    @BindView(R.id.img_btn_add_vehicle_color) ImageButton imgBtnColor;
    @BindView(R.id.fab_add_edit_vehicle) FloatingActionButton fab;

    private AddEditVehiclePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_vehicle);
        ButterKnife.bind(this);
        mPresenter = new AddEditVehiclePresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        fab.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        fab.setIndeterminate(false);
    }

    @Override
    public void setName(String name) {
        TextInputUtils.setTextToTil(tilName, name);
    }

    @Override
    public void setMake(String make) {
        TextInputUtils.setTextToTil(tilMake, make);
    }

    @Override
    public void setModel(String model) {
        TextInputUtils.setTextToTil(tilModel, model);
    }

    @Override
    public void setType(String type) {
        //hmm
    }

    @Override
    public void setHorsePower(String horsePower) {
        TextInputUtils.setTextToTil(tilHorsePower, horsePower);
    }

    @Override
    public void setRegistrationPlate(String registrationPlate) {
        TextInputUtils.setTextToTil(tilRegistrationPlate, registrationPlate);
    }

    @Override
    public void setInfo(String info) {
        TextInputUtils.setTextToTil(tilInfo, info);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getMake() {
        return TextInputUtils.getTextFromTil(tilMake);
    }

    @Override
    public String getModel() {
        return TextInputUtils.getTextFromTil(tilModel);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getHorsePower() {
        return TextInputUtils.getTextFromTil(tilHorsePower);
    }

    @Override
    public String getRegistrationPlate() {
        return TextInputUtils.getTextFromTil(tilRegistrationPlate);
    }

    @Override
    public String getInfo() {
        return TextInputUtils.getTextFromTil(tilInfo);
    }

    @Override
    public void setNameError() {

    }

    @Override
    public void setMakeError() {

    }

    @Override
    public void setModelError() {

    }

    @Override
    public void setTypeError() {

    }

    @Override
    public void setHorsePowerError() {

    }

    @Override
    public void setRegistrationPlateError() {

    }

    @Override
    public void setInfoError() {

    }

    @Override
    public void setFuelTankError() {

    }

    @Override
    public void navigateToHome() {

    }
}
