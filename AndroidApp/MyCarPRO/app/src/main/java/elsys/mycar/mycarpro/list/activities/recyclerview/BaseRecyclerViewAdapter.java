package elsys.mycar.mycarpro.list.activities.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import elsys.mycar.mycarpro.R;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<ItemViewHolder> {

    protected List<T> items;
    private ActivitiesItemListener<T> itemListener;
    protected int resId;
    protected String datePriceFormat;
    protected String currency;

    public BaseRecyclerViewAdapter(List<T> items, ActivitiesItemListener<T> itemListener, int resId, String datePriceFormat, String currency) {
        this.items = items;
        this.itemListener = itemListener;
        this.resId = resId;
        this.datePriceFormat = datePriceFormat;
        this.currency = currency;
    }

    public void replaceData(List<T> items) {
        setItems(items);
        notifyDataSetChanged();
    }

    private void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_service, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final T item = items.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ActivitiesItemListener<T> {

        void onItemClick(T a);
    }
}
