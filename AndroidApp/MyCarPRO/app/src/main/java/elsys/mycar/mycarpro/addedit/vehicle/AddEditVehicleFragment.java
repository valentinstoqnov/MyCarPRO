package elsys.mycar.mycarpro.addedit.vehicle;

import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.addedit.vehicle.fueltank.AddEditFuelTankDialog;
import elsys.mycar.mycarpro.addedit.vehicle.fueltank.AddEditFuelTankPresenter;
import elsys.mycar.mycarpro.addedit.vehicle.fueltank.FuelTankCallback;
import elsys.mycar.mycarpro.util.DatePickerUtils;
import me.priyesh.chroma.ChromaDialog;
import me.priyesh.chroma.ColorMode;
import me.priyesh.chroma.ColorSelectListener;

import static com.google.common.base.Preconditions.checkNotNull;
import static elsys.mycar.mycarpro.util.TextInputUtils.setTextToTil;
import static elsys.mycar.mycarpro.util.TextInputUtils.getTextFromTil;
import static elsys.mycar.mycarpro.util.TextInputUtils.getTextFromAutoComplete;
import static elsys.mycar.mycarpro.util.TextInputUtils.setTextToAutoComplete;

public class AddEditVehicleFragment extends Fragment implements AddEditVehicleContract.View, DatePickerDialog.OnDateSetListener{

    @BindView(R.id.til_add_vehicle_name) TextInputLayout tilName;
    @BindView(R.id.til_add_vehicle_make) TextInputLayout tilMake;
    @BindView(R.id.til_add_vehicle_model) TextInputLayout tilModel;
    @BindView(R.id.til_add_vehicle_odometer) TextInputLayout tilOdometer;
    @BindView(R.id.til_add_vehicle_horse_power) TextInputLayout tilHorsePower;
    @BindView(R.id.til_add_vehicle_notes) TextInputLayout tilNotes;
    @BindView(R.id.btn_add_vehicle_manufacture_date) Button btnManufactureDate;
    @BindView(R.id.btn_add_vehicle_color) Button btnColor;
    @BindView(R.id.btn_add_vehicle_fuel_tank) Button btnFuelTank;

    private FloatingActionButton fab;
    private int mVehicleColor;
    private AddEditVehicleContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    public static AddEditVehicleFragment newInstance() {
        return new AddEditVehicleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_edit_vehicle, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mVehicleColor = ResourcesCompat.getColor(getResources(), R.color.colorVehicleTabSelected, null);

        btnManufactureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerUtils.showDatePicker(getContext(), AddEditVehicleFragment.this);
            }
        });

        btnFuelTank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AddEditFuelTankDialog dialog = new AddEditFuelTankDialog();
                dialog.setCallback(new FuelTankCallback() {
                    @Override
                    public void onEntered(String fuelType, int capacity, double consumption) {
                        mPresenter.onFuelTankPicked(fuelType, capacity, consumption);
                    }
                });
                AddEditFuelTankPresenter dialogPresenter = new AddEditFuelTankPresenter(dialog);
                dialog.setPresenter(dialogPresenter);
                dialog.show(getChildFragmentManager(), AddEditFuelTankDialog.TAG);
            }
        });

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ChromaDialog.Builder()
                        .initialColor(mVehicleColor)
                        .colorMode(ColorMode.RGB) // There's also ARGB and HSV
                        .onColorSelected(new ColorSelectListener() {
                            @Override
                            public void onColorSelected(@ColorInt int i) {
                                mVehicleColor = i;
                                changeColorOfDrawables(btnColor.getCompoundDrawables());
                            }
                        })
                        .create()
                        .show(getChildFragmentManager(), "ChromaDialog");
            }
        });

        return view;
    }

    private void changeColorOfDrawables(Drawable[] drawables) {
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setColorFilter(mVehicleColor, PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_vehicle);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getTextFromTil(tilName);
                String make = getTextFromAutoComplete(tilMake);
                String model = getTextFromTil(tilModel);
                String date = btnManufactureDate.getText().toString();
                String odometer = getTextFromTil(tilOdometer);
                String horsePower = getTextFromTil(tilHorsePower);
                String notes = getTextFromTil(tilNotes);

                mPresenter.saveVehicle(name, make, model, date, horsePower, odometer, mVehicleColor, notes);
            }
        });
    }

    @Override
    public void setPresenter(AddEditVehicleContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setName(String name) {
        setTextToTil(tilName, name);
    }

    @Override
    public void setMake(String make) {
        setTextToAutoComplete(tilMake, make);
    }

    @Override
    public void setDate(String date) {
        btnManufactureDate.setText(date);
    }

    @Override
    public void setModel(String model) {
        setTextToTil(tilModel, model);
    }

    @Override
    public void setManufactureDate(String date) {
        btnManufactureDate.setText(date);
    }

    @Override
    public void showMakes(List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) tilMake.getEditText();
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setAdapter(adapter);
        }
    }

    @Override
    public void setColor(int color) {
        mVehicleColor = color;
        changeColorOfDrawables(btnColor.getCompoundDrawables());
    }

    @Override
    public void setFuelTank(String fuelTank) {
        btnFuelTank.setText(fuelTank);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void exit() {
        getActivity().finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mPresenter.onDatePicked(year, month, dayOfMonth);
    }
}
