package elsys.mycar.mycarpro.list.idk;

import android.content.Intent;
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
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.list.activities.ListActivitiesContract;
import elsys.mycar.mycarpro.list.activities.recyclerview.BaseRecyclerViewAdapter;
import elsys.mycar.mycarpro.list.activities.recyclerview.RecyclerViewDivider;

public abstract class IDKFragment<T> extends Fragment implements ListActivitiesContract.View<ListActivitiesContract.Presenter<T>, T> {

    @BindView(R.id.rv_list) RecyclerView recyclerView;
    @BindView(R.id.progress_bar_list) ProgressBar progressBar;

    private ListActivitiesContract.Presenter<T> mPresenter;
    private Unbinder mUnbinder;
    protected BaseRecyclerViewAdapter<T> recyclerViewAdapter;

    protected abstract void initRecyclerViewAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FloatingActionMenu fabMenu = (FloatingActionMenu) getActivity().findViewById(R.id.fab_menu_main);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fabMenu.getVisibility() == View.VISIBLE) {
                    fabMenu.hideMenu(true);
                } else if (dy < 0 && fabMenu.getVisibility() != View.VISIBLE) {
                    fabMenu.showMenu(true);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setPresenter(ListActivitiesContract.Presenter<T> presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNoItemsFound() {
        showMessage(getString(R.string.no_items_found));
    }

    @Override
    public void showItems(List<T> items) {
        recyclerViewAdapter.replaceData(items);
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
        initRecyclerViewAdapter();
        recyclerViewAdapter.setDatePriceFormat(getString(R.string.date_price_placeholder));
        recyclerViewAdapter.setCurrency("lv.");
        recyclerViewAdapter.setItemListener(new BaseRecyclerViewAdapter.ActivitiesItemListener<T>() {
            @Override
            public void onItemClick(T a) {
                mPresenter.openItemDetails(a);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
