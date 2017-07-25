package vstoyanov.mycar.mycarpro.list.activities.refuelings;

import java.util.List;

import vstoyanov.mycar.mycarpro.data.model.Refueling;
import vstoyanov.mycar.mycarpro.list.activities.recyclerview.BaseRecyclerViewAdapter;
import vstoyanov.mycar.mycarpro.list.activities.recyclerview.ItemViewHolder;
import vstoyanov.mycar.mycarpro.util.PriceUtils;

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
