package elsys.mycar.mycarpro.addedit.insurance;

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
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.common.base.Preconditions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.util.DatePickerUtils;

import static elsys.mycar.mycarpro.util.TextInputUtils.getTextFromAutoComplete;
import static elsys.mycar.mycarpro.util.TextInputUtils.getTextFromTil;
import static elsys.mycar.mycarpro.util.TextInputUtils.setTextToAutoComplete;
import static elsys.mycar.mycarpro.util.TextInputUtils.setTextToTil;

public class AddEditInsuranceFragment extends Fragment implements AddEditInsuranceContract.View {

    @BindView(R.id.til_add_insurance_company_name) TextInputLayout tilCompanyName;
    @BindView(R.id.til_add_insurance_odometer) TextInputLayout tilOdometer;
    @BindView(R.id.til_add_insurance_price) TextInputLayout tilPrice;
    @BindView(R.id.btn_add_insurance_date) Button btnDate;
    @BindView(R.id.btn_add_insurance_expiration_date) Button btnExpirationDate;
    @BindView(R.id.til_add_insurance_note) TextInputLayout tilNote;

    private Unbinder mUnbinder;
    private FloatingActionButton mFab;
    private AddEditInsuranceContract.Presenter mPresenter;

    public static AddEditInsuranceFragment newInstance() {
        return new AddEditInsuranceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_insurance, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        btnDate.setOnClickListener(v -> DatePickerUtils.showDatePicker(getContext(), (view1, year, month, dayOfMonth) -> mPresenter.onDatePicked(year, month, dayOfMonth, false)));

        btnExpirationDate.setOnClickListener(v -> DatePickerUtils.showDatePicker(getContext(), (view12, year, month, dayOfMonth) -> mPresenter.onDatePicked(year, month, dayOfMonth, true)));

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
        mFab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_insurance);
        mFab.setOnClickListener(v -> {
            String companyName = getTextFromAutoComplete(tilCompanyName);
            String price = getTextFromTil(tilPrice);
            String odometer = getTextFromTil(tilOdometer);
            String date = btnDate.getText().toString();
            String expirationDate = btnExpirationDate.getText().toString();
            String note = getTextFromTil(tilNote);

            mPresenter.saveInsurance(companyName, price, odometer, date, expirationDate, note);
        });
    }

    @Override
    public void setPresenter(AddEditInsuranceContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showProgress() {
        mFab.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        mFab.setIndeterminate(false);
    }

    @Override
    public void showNoSuchVehicle() {
        Toast.makeText(getContext(), R.string.no_vehicle_found, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInsuranceRetrievalError() {
        Toast.makeText(getContext(), R.string.insurance_retrieval_error, Toast.LENGTH_LONG).show();
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
    public void showInsuranceSuccessfullySaved(String name) {
        String message = String.format(getString(R.string.insurance_successfully_saved), name);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void showInsuranceSaveError() {
        Toast.makeText(getContext(), R.string.insurance_save_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCompanyName(String companyName) {
        setTextToAutoComplete(tilCompanyName, companyName);
    }

    @Override
    public void setPrice(String price) {
        setTextToTil(tilPrice, price);
    }

    @Override
    public void setOdometer(String odometer) {
        setTextToTil(tilOdometer, odometer);
    }

    @Override
    public void setDate(String date) {
        btnDate.setText(date);
    }

    @Override
    public void setExpirationDate(String expirationDate) {
        btnExpirationDate.setText(expirationDate);
    }

    @Override
    public void setNote(String note) {
        setTextToTil(tilNote, note);
    }

    @Override
    public void addCompanies(List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) tilCompanyName.getEditText();
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setAdapter(adapter);
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
