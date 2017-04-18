package elsys.mycar.mycarpro.list.activities.services;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.model.Service;
import elsys.mycar.mycarpro.util.PriceUtils;

public class ListServicesAdapter extends RecyclerView.Adapter<ListServicesAdapter.ViewHolder> {

    private List<Service> mServices;
    private String mFormat;
    private String mCurrency;

    public ListServicesAdapter(List<Service> mServices, String mFormat, String mCurrency) {
        this.mServices = mServices;
        this.mFormat = mFormat;
        this.mCurrency = mCurrency;
    }

    public void addServices(List<Service> services) {
        if (mServices == null) {
            this.mServices = services;
        }else {
            mServices.addAll(services);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Service service = mServices.get(position);
        String res = String.format(mFormat, service.getDate(), PriceUtils.longToString(new BigDecimal(service.getPrice())), mCurrency);
        holder.tvType.setText(service.getType());
        holder.tvDatePrice.setText(res);
    }

    @Override
    public int getItemCount() {
        return mServices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_list_service_type) TextView tvType;
        @BindView(R.id.tv_list_service_date_price) TextView tvDatePrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
