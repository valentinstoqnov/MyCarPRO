package vstoyanov.mycar.mycarpro.addedit.service;

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
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.common.base.Preconditions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.util.DatePickerUtils;
import vstoyanov.mycar.mycarpro.util.TextInputUtils;

public class AddEditServiceFragment extends Fragment implements AddEditServiceContract.View, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.til_add_service_type) TextInputLayout tilType;
    @BindView(R.id.btn_add_service_date) Button btnDate;
    @BindView(R.id.btn_add_service_time) Button btnTime;
    @BindView(R.id.til_add_service_odometer) TextInputLayout tilOdometer;
    @BindView(R.id.til_add_service_price) TextInputLayout tilPrice;
    @BindView(R.id.til_add_service_note) TextInputLayout tilNote;

    private FloatingActionButton fab;
    private AddEditServiceContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    public static AddEditServiceFragment newInstance() {
        return new AddEditServiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_service, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        btnDate.setOnClickListener(v -> DatePickerUtils.showDatePicker(getContext(), AddEditServiceFragment.this));

        btnTime.setOnClickListener(v -> DatePickerUtils.showTimePicker(getContext(), AddEditServiceFragment.this));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_service);

        fab.setOnClickListener(v -> {
            String type = TextInputUtils.getTextFromAutoComplete(tilType);
            String price = TextInputUtils.getTextFromTil(tilPrice);
            String odometer = TextInputUtils.getTextFromTil(tilOdometer);
            String date = btnDate.getText().toString();
            String time = btnTime.getText().toString();
            String note = TextInputUtils.getTextFromTil(tilNote);

            mPresenter.saveService(type, price, odometer, date, time, note);
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
    public void setPresenter(AddEditServiceContract.Presenter presenter) {
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
    public void showServiceRetrievalError() {
        Toast.makeText(getContext(), R.string.service_retrieval_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDateError() {
        Toast.makeText(getContext(), R.string.date_parse_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(getContext(), R.string.empty_fields_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPriceOrOdometerParseError() {
        Toast.makeText(getContext(), R.string.price_odometer_parse_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showServiceSuccessfullySaved(String name) {
        String message = String.format(getString(R.string.service_successfully_saved), name);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void showServiceSaveError() {
        Toast.makeText(getContext(), R.string.service_save_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setType(String type) {
        TextInputUtils.setTextToAutoComplete(tilType, type);
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
    public void setDate(String date) {
        btnDate.setText(date);
    }

    @Override
    public void setTime(String time) {
        btnTime.setText(time);
    }

    @Override
    public void setNote(String note) {
        TextInputUtils.setTextToTil(tilNote, note);
    }

    @Override
    public void showServiceTypes(List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) tilType.getEditText();
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setAdapter(adapter);
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mPresenter.onDatePicked(year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mPresenter.onTimePicked(hourOfDay, minute);
    }
}
