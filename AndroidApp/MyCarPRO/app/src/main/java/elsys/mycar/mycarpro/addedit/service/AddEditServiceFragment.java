package elsys.mycar.mycarpro.addedit.service;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.common.base.Preconditions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.addedit.insurance.AddEditInsuranceContract;
import elsys.mycar.mycarpro.util.StringUtils;
import elsys.mycar.mycarpro.util.TextInputUtils;

public class AddEditServiceFragment extends Fragment implements AddEditServiceContract.View{

    @BindView(R.id.til_add_service_type) TextInputLayout tilType;
    @BindView(R.id.btn_add_service_date) Button btnDate;
    @BindView(R.id.btn_add_service_time) Button btnTime;
    @BindView(R.id.til_add_service_odometer) TextInputLayout tilOdometer;
    @BindView(R.id.til_add_service_price) TextInputLayout tilPrice;
    @BindView(R.id.til_add_service_note) TextInputLayout tilNote;

    private FloatingActionButton fab;
    private AddEditServiceContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_service, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_service);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = TextInputUtils.getTextFromAutoComplete(tilType);
                String price = TextInputUtils.getTextFromTil(tilPrice);
                String odometer = TextInputUtils.getTextFromTil(tilOdometer);
                String date = btnDate.getText().toString();
                String time = btnTime.getText().toString();
                String note = TextInputUtils.getTextFromTil(tilNote);

                mPresenter.saveService(type, price, odometer, date, time, note);
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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
}
