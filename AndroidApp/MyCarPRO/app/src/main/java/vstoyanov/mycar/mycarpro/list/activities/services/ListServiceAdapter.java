package vstoyanov.mycar.mycarpro.list.activities.services;

import java.util.List;

import vstoyanov.mycar.mycarpro.data.model.Service;
import vstoyanov.mycar.mycarpro.list.activities.recyclerview.BaseRecyclerViewAdapter;
import vstoyanov.mycar.mycarpro.list.activities.recyclerview.ItemViewHolder;

public class ListServiceAdapter extends BaseRecyclerViewAdapter<Service> {

    public ListServiceAdapter(List<Service> items, int resId) {
        super(items, resId);
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
