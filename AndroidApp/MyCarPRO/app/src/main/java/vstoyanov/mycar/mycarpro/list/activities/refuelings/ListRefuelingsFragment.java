package vstoyanov.mycar.mycarpro.list.activities.refuelings;

import android.widget.Toast;

import java.util.ArrayList;

import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.data.model.Refueling;
import vstoyanov.mycar.mycarpro.list.base.BaseActivitiesFragment;

public class ListRefuelingsFragment extends BaseActivitiesFragment<Refueling> {

    @Override
    protected void initRecyclerViewAdapter() {
        recyclerViewAdapter = new ListRefuelingsAdapter(new ArrayList<>(0), R.drawable.ic_insurance);
    }

    @Override
    public void showDetailsItemUi(String itemId) {
        Toast.makeText(getContext(), "detail item " + itemId, Toast.LENGTH_SHORT).show();
    }
}
