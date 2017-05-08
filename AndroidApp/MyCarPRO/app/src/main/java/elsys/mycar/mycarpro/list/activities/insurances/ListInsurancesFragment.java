package elsys.mycar.mycarpro.list.activities.insurances;

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
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.list.activities.recyclerview.BaseRecyclerViewAdapter;
import elsys.mycar.mycarpro.list.activities.recyclerview.RecyclerViewDivider;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.list.idk.IDKFragment;

public class ListInsurancesFragment extends IDKFragment<Insurance> {

    @Override
    public void showDetailItemUi(String itemId) {
        showMessage("detail item " + itemId);
    }

    @Override
    protected void initRecyclerViewAdapter() {
        recyclerViewAdapter = new ListInsurancesAdapter(new ArrayList<Insurance>(0), R.drawable.ic_gas_station);
    }
}
