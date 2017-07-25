package vstoyanov.mycar.mycarpro.list.activities.services;

import android.widget.Toast;

import java.util.ArrayList;

import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.data.model.Service;
import vstoyanov.mycar.mycarpro.list.base.BaseActivitiesFragment;

public class ListServicesFragment extends BaseActivitiesFragment<Service> {

    @Override
    protected void initRecyclerViewAdapter() {
        recyclerViewAdapter = new ListServiceAdapter(new ArrayList<Service>(0), R.drawable.ic_service);
    }

    @Override
    public void showDetailsItemUi(String itemId) {
        Toast.makeText(getContext(), "detail item " + itemId, Toast.LENGTH_SHORT).show();
    }
}
