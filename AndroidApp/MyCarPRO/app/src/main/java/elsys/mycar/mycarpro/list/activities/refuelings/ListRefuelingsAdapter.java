package elsys.mycar.mycarpro.list.activities.refuelings;

import java.util.List;

import elsys.mycar.mycarpro.list.activities.recyclerview.BaseRecyclerViewAdapter;
import elsys.mycar.mycarpro.list.activities.recyclerview.ItemViewHolder;
import elsys.mycar.mycarpro.data.model.Refueling;
import elsys.mycar.mycarpro.util.PriceUtils;

public class ListRefuelingsAdapter extends BaseRecyclerViewAdapter<Refueling> {

    public ListRefuelingsAdapter(List<Refueling> items, int resId) {
        super(items, resId);
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
