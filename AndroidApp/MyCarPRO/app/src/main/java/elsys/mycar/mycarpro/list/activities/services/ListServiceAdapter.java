package elsys.mycar.mycarpro.list.activities.services;

import java.util.List;

import elsys.mycar.mycarpro.list.activities.recyclerview.BaseRecyclerViewAdapter;
import elsys.mycar.mycarpro.list.activities.recyclerview.ItemViewHolder;
import elsys.mycar.mycarpro.data.model.Service;

public class ListServiceAdapter extends BaseRecyclerViewAdapter<Service> {

    public ListServiceAdapter(List<Service> items, ActivitiesItemListener<Service> itemListener, int resId, String datePriceFormat, String currency) {
        super(items, itemListener, resId, datePriceFormat, currency);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Service service = items.get(position);
        String primaryText = service.getType();
        String secondaryText = String.format(datePriceFormat, service.getDate(), service.getPrice(), currency);
        holder.setContent(resId, primaryText, secondaryText);
    }
}
