package elsys.mycar.mycarpro.addedit.refueling;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.common.base.Preconditions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.util.StringUtils;
import elsys.mycar.mycarpro.util.TextInputUtils;

public class AddEditRefuelingFragment extends Fragment implements AddEditRefuelingContract.View{

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_refueling, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companyName = TextInputUtils.getTextFromAutoComplete(tilCompanyName);
                String price = TextInputUtils.getTextFromTil(tilPrice);
                String odometer = TextInputUtils.getTextFromTil(tilOdometer);
                String quantity = TextInputUtils.getTextFromTil(tilQuantity);
                String note = TextInputUtils.getTextFromTil(tilNote);
                String date = btnDate.getText().toString();
                String time = btnTime.getText().toString();

                mPresenter.saveRefueling(companyName, quantity, price, odometer, date, time, note, aSwitch.isChecked());
            }
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
}
