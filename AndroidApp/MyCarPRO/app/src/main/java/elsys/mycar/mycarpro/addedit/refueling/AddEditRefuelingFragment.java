package elsys.mycar.mycarpro.addedit.refueling;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.common.base.Preconditions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.util.DatePickerUtils;
import elsys.mycar.mycarpro.util.TextInputUtils;

public class AddEditRefuelingFragment extends Fragment implements AddEditRefuelingContract.View, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.til_add_refueling_company_name) TextInputLayout tilCompanyName;
    @BindView(R.id.til_add_refueling_odometer) TextInputLayout tilOdometer;
    @BindView(R.id.til_add_refueling_price) TextInputLayout tilPrice;
    @BindView(R.id.til_add_refueling_quantity) TextInputLayout tilQuantity;
    @BindView(R.id.til_add_refueling_note) TextInputLayout tilNote;
    @BindView(R.id.switch_add_refueling) Switch aSwitch;
    @BindView(R.id.btn_add_refueling_date) Button btnDate;
    @BindView(R.id.btn_add_refueling_time) Button btnTime;

    private Unbinder mUnbinder;
    private FloatingActionButton fab;
    private AddEditRefuelingContract.Presenter mPresenter;

    public static AddEditRefuelingFragment newInstance() {
        return new AddEditRefuelingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_refueling, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        btnDate.setOnClickListener(v -> DatePickerUtils.showDatePicker(getContext(), AddEditRefuelingFragment.this));

        btnTime.setOnClickListener(v -> DatePickerUtils.showTimePicker(getContext(), AddEditRefuelingFragment.this));
        return view;
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
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_refueling);

        fab.setOnClickListener(v -> {
            String companyName = TextInputUtils.getTextFromAutoComplete(tilCompanyName);
            String price = TextInputUtils.getTextFromTil(tilPrice);
            String odometer = TextInputUtils.getTextFromTil(tilOdometer);
            String quantity = TextInputUtils.getTextFromTil(tilQuantity);
            String note = TextInputUtils.getTextFromTil(tilNote);
            String date = btnDate.getText().toString();
            String time = btnTime.getText().toString();

            mPresenter.saveRefueling(companyName, quantity, price, odometer, date, time, note, aSwitch.isChecked());
        });
    }

    @Override
    public void setPresenter(AddEditRefuelingContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
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
    public void showNoSuchVehicle() {
        Toast.makeText(getContext(), R.string.no_vehicle_found, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRefuelingRetrievalError() {
        Toast.makeText(getContext(), R.string.refueling_retrieval_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDateError() {
        Toast.makeText(getContext(), R.string.date_parse_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRefuelingSaveError() {
        Toast.makeText(getContext(), R.string.service_save_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPriceOrOdometerParseError() {
        Toast.makeText(getContext(), R.string.price_odometer_parse_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(getContext(), R.string.empty_fields_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRefuelingSuccessfullySaved(String name) {
        String message = String.format(getString(R.string.service_successfully_saved), name);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void setDate(String date) {
        btnDate.setText(date);
    }

    @Override
    public void setTime(String time) {
        btnTime.setText(time);
    }

    @Override
    public void setCompanyName(String companyName) {
        TextInputUtils.setTextToAutoComplete(tilCompanyName, companyName);
    }

    @Override
    public void setQuantity(String quantity) {
        //TODO : SWITCH impl
        TextInputUtils.setTextToTil(tilQuantity, quantity);
    }

    @Override
    public void setPrice(String price) {
        TextInputUtils.setTextToTil(tilPrice, price);
    }

    @Override
    public void setOdometer(String odometer) {
        TextInputUtils.setTextToTil(tilOdometer, odometer);
    }

    @Override
    public void setNote(String note) {
        TextInputUtils.setTextToTil(tilNote, note);
    }

    @Override
    public void setFullFuelTank(boolean isFull) {

    }

    @Override
    public void addGasStations(List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) tilCompanyName.getEditText();
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setAdapter(adapter);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mPresenter.onDatePicked(year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mPresenter.onTimePicked(hourOfDay, minute);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
