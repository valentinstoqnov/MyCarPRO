package elsys.mycar.mycarpro.list.activities.insurances;

import java.util.ArrayList;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.model.Insurance;
import elsys.mycar.mycarpro.list.base.BaseActivitiesFragment;

public class ListInsurancesFragment extends BaseActivitiesFragment<Insurance> {

    @Override
    public void showDetailItemUi(String itemId) {
        showMessage("detail item " + itemId);
    }

    @Override
    protected void initRecyclerViewAdapter() {
        recyclerViewAdapter = new ListInsurancesAdapter(new ArrayList<Insurance>(0), R.drawable.ic_gas_station);
    }
}
