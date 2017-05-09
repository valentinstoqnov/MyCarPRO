package elsys.mycar.mycarpro.list.activities.refuelings;

import java.util.ArrayList;

import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.list.base.BaseActivitiesFragment;

public class ListRefuelingsFragment extends BaseActivitiesFragment<Refueling> {

    @Override
    public void showDetailItemUi(String itemId) {
        showMessage("detail item " + itemId);
    }

    @Override
    protected void initRecyclerViewAdapter() {
        recyclerViewAdapter = new ListRefuelingsAdapter(new ArrayList<Refueling>(0), R.drawable.ic_insurance);
    }
}
