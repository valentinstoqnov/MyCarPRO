package elsys.mycar.mycarpro.list.activities.services;

import java.util.ArrayList;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.list.base.BaseActivitiesFragment;

public class ListServicesFragment extends BaseActivitiesFragment<Service> {

    @Override
    public void showDetailItemUi(String itemId) {
        showMessage("detail item " + itemId);
    }

    @Override
    protected void initRecyclerViewAdapter() {
        recyclerViewAdapter = new ListServiceAdapter(new ArrayList<Service>(0), R.drawable.ic_service);
    }
}
