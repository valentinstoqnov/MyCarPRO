package vstoyanov.mycar.mycarpro.addedit.vehicle.fueltank;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.common.base.Preconditions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.util.TextInputUtils;

public class AddEditFuelTankDialog extends DialogFragment implements AddEditFuelTankContract.View{

    public static final String TAG = "AddEditFuelTankDialog";

    @BindView(R.id.spn_add_fuel_tank_fuel_type) Spinner spnFuelType;
    @BindView(R.id.til_add_fuel_tank_capacity) TextInputLayout tilCapacity;
    @BindView(R.id.til_add_fuel_tank_consumption) TextInputLayout tilConsumption;

    private AddEditFuelTankContract.Presenter mPresenter;
    private FuelTankCallback mCallback;
    private Unbinder mUnbinder;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_fuel_tank, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setView(view);

        mUnbinder = ButterKnife.bind(this, view);

        mPresenter.start();

        return builder.create();
    }

    @OnClick(R.id.btn_dialog_add_fuel_tank_add)
    public void onOkClicked() {
        String fuelType = spnFuelType.getSelectedItem().toString();
        String capacity = TextInputUtils.getTextFromTil(tilCapacity);
        String consumption = TextInputUtils.getTextFromTil(tilConsumption);

        mPresenter.addFuelTank(fuelType, capacity, consumption);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mUnbinder.unbind();
    }

    @Override
    public void setPresenter(AddEditFuelTankContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showFuelTypes(List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, items);
        spnFuelType.setAdapter(adapter);
    }

    @Override
    public void showCapacityError(String message) {
        tilCapacity.setError(message);
    }

    @Override
    public void showConsumptionError(String message) {
        tilConsumption.setError(message);
    }

    @Override
    public void onInputValidated(String fuelType, int capacity, double consumption) {
        mCallback.onEntered(fuelType, capacity, consumption);
        dismiss();
    }

    public void setCallback(FuelTankCallback callback) {
        mCallback = callback;
    }
}
