package elsys.mycar.mycarpro.list.activities.insurances;

import java.util.List;

import elsys.mycar.mycarpro.list.activities.recyclerview.BaseRecyclerViewAdapter;
import elsys.mycar.mycarpro.list.activities.recyclerview.ItemViewHolder;
import elsys.mycar.mycarpro.data.model.Insurance;

public class ListInsurancesAdapter extends BaseRecyclerViewAdapter<Insurance> {

    public ListInsurancesAdapter(List<Insurance> items, ActivitiesItemListener<Insurance> itemListener, int resId, String datePriceFormat, String currency) {
        super(items, itemListener, resId, datePriceFormat, currency);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Insurance insurance = items.get(position);
        String primaryText = insurance.getCompanyName();
        String secondaryText = String.format(datePriceFormat, insurance.getDate(), insurance.getPrice(), currency);
        holder.setContent(resId, primaryText, secondaryText);
    }
}
