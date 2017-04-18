package elsys.mycar.mycarpro.list.activities.services;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.model.Service;

public class ListServicesFragment extends Fragment implements ListServiceContract.View{

    @BindView(R.id.rv_list) RecyclerView recyclerView;
    @BindString(R.string.date_price_placeholder) String placeholder;

    private Unbinder mUnbinder;
    private ListServiceContract.Presenter mPresenter;
    private ListServicesAdapter mAdapter;

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

    }

    private void setUpRecyclerView() {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.line_divider);
        RecyclerViewDivider divider = new RecyclerViewDivider(drawable);
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ListServicesAdapter(new ArrayList<Service>(), placeholder, "lv.");
        recyclerView.setAdapter(mAdapter);

        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setPresenter(ListServiceContract.Presenter presenter) {
        this.mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void addServices(List<Service> items) {
        mAdapter.addServices(items);
        if (items != null) {
            Log.d("SERVICES", "size = " + items.size());
        }else {
            Log.d("SERVICES", "null");
        }
    }

    @Override
    public void showNoSuchVehicle() {
        Log.d("NSV ========", "!!!!!!NoSuchVehicle");
    }
}
