package vstoyanov.mycar.mycarpro.list.activities.insurances;

import android.widget.Toast;

import java.util.ArrayList;

import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.data.model.Insurance;
import vstoyanov.mycar.mycarpro.list.base.BaseActivitiesFragment;

public class ListInsurancesFragment extends BaseActivitiesFragment<Insurance> {

    @Override
    protected void initRecyclerViewAdapter() {
        recyclerViewAdapter = new ListInsurancesAdapter(new ArrayList<>(0), R.drawable.ic_gas_station);
    }

    @Override
    public void showDetailsItemUi(String itemId) {
        Toast.makeText(getContext(), "detail item " + itemId, Toast.LENGTH_SHORT).show();
    }
}
