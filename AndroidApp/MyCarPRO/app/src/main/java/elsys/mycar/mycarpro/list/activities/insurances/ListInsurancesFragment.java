package elsys.mycar.mycarpro.list.activities.insurances;

import android.widget.Toast;

import java.util.ArrayList;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.list.base.BaseActivitiesFragment;

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
