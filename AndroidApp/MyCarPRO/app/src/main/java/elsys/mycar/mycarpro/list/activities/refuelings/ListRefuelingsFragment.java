package elsys.mycar.mycarpro.list.activities.refuelings;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import elsys.mycar.mycarpro.model.Refueling;

public class ListRefuelingsFragment extends Fragment implements ListRefuelingsContract.View {

    @BindView(R.id.rv_list) RecyclerView recyclerView;
    @BindView(R.id.textView_list) TextView textViewMessage;
    @BindView(R.id.progress_bar_list) ProgressBar progressBar;
    @BindString(R.string.date_price_placeholder) String placeholder;

    private ListRefuelingsAdapter mAdapter;
    private ListRefuelingsContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    public static ListRefuelingsFragment newInstance() {
        return new ListRefuelingsFragment();
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
    public void setPresenter(ListRefuelingsContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showRefuelings(List<Refueling> items) {
        if (recyclerView.getVisibility() == View.GONE) {
            recyclerView.setVisibility(View.VISIBLE);
            textViewMessage.setVisibility(View.GONE);
        }
        mAdapter.replaceData(items);
    }

    @Override
    public void showDetailItemUi(String itemId) {
        //TODO
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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

        mAdapter = new ListRefuelingsAdapter(new ArrayList<Refueling>(0), new BaseRecyclerViewAdapter.ActivitiesItemListener<Refueling>() {
            @Override
            public void onItemClick(Refueling refueling) {
                mPresenter.openRefuelingDetails(refueling);
            }
        }, R.drawable.ic_insurance, placeholder, "lv.");

        recyclerView.setAdapter(mAdapter);
    }
}
