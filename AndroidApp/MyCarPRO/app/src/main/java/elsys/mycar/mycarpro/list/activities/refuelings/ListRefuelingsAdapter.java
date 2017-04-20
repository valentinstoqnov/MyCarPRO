package elsys.mycar.mycarpro.list.activities.refuelings;

import java.util.List;

import elsys.mycar.mycarpro.list.activities.BaseRecyclerViewAdapter;
import elsys.mycar.mycarpro.list.activities.ItemViewHolder;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.util.PriceUtils;

public class ListRefuelingsAdapter extends BaseRecyclerViewAdapter<Refueling> {

    public ListRefuelingsAdapter(List<Refueling> items, ActivitiesItemListener<Refueling> itemListener, int resId, String datePriceFormat, String currency) {
        super(items, itemListener, resId, datePriceFormat, currency);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Refueling refueling = items.get(position);
        String primaryText = refueling.getCompanyName();
        String secondaryText = String.format(datePriceFormat, refueling.getDate(), PriceUtils.longToString(refueling.getPrice()), currency);
        holder.setContent(resId, primaryText, secondaryText);
    }
}
