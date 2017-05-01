package elsys.mycar.mycarpro.list.activities.services;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.list.activities.recyclerview.BaseRecyclerViewAdapter;
import elsys.mycar.mycarpro.list.activities.recyclerview.RecyclerViewDivider;
import elsys.mycar.mycarpro.model.Service;

public class ListServicesFragment extends Fragment implements ListServicesContract.View{

    @BindView(R.id.rv_list) RecyclerView recyclerView;
    @BindView(R.id.textView_list) TextView textViewMessage;
    @BindView(R.id.progress_bar_list) ProgressBar progressBar;
    @BindString(R.string.date_price_placeholder) String placeholder;

    private Unbinder mUnbinder;
    private ListServicesContract.Presenter mPresenter;
    private ListServiceAdapter mAdapter;

    public static ListServicesFragment newInstance() {
        return new ListServicesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setUpRecyclerView();
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
    public void setPresenter(ListServicesContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showServices(List<Service> items) {
        if (recyclerView.getVisibility() == View.GONE) {
            recyclerView.setVisibility(View.VISIBLE);
            textViewMessage.setVisibility(View.GONE);
        }
        mAdapter.replaceData(items);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDetailItemUi(String itemId) {
        //TODO
        Snackbar.make(getView(), "Item click", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showNoItemsFound() {
        recyclerView.setVisibility(View.GONE);
        String message = getString(R.string.no_items_found);
        textViewMessage.setVisibility(View.VISIBLE);
        textViewMessage.setText(message);
    }

    @Override
    public void showNoSuchVehicle() {
        recyclerView.setVisibility(View.GONE);
        String message = getString(R.string.no_vehicle_found);
        textViewMessage.setVisibility(View.VISIBLE);
        textViewMessage.setText(message);
    }

    @Override
    public void showProgress() {
        if (progressBar.getVisibility() != View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }

        progressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        progressBar.setIndeterminate(false);

        if (progressBar.getVisibility() != View.GONE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private void setUpRecyclerView() {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.line_divider);
        RecyclerViewDivider divider = new RecyclerViewDivider(drawable);
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new ListServiceAdapter(new ArrayList<Service>(0), new BaseRecyclerViewAdapter.ActivitiesItemListener<Service>() {
            @Override
            public void onItemClick(Service service) {
                mPresenter.openServiceDetails(service);
            }
        }, R.drawable.ic_service, placeholder, "lv.");

        recyclerView.setAdapter(mAdapter);
    }
}
