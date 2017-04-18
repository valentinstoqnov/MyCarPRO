package elsys.mycar.mycarpro.list.activities;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.model.Insurance;
import elsys.mycar.mycarpro.model.Refueling;
import elsys.mycar.mycarpro.model.Service;

public class ListActivitiesAdapter extends RecyclerView.Adapter<ListActivitiesAdapter.ViewHolder> {

    private List<Service> mServices;
    private List<Insurance> mInsurances;
    private List<Refueling> mRefuelings;

    private String mDatePriceFormat;
    private String mCurrency;
    private int mItemCount;
    private int mResId;

    public ListActivitiesAdapter(String mDatePriceFormat, String mCurrency, int mResId) {
        this.mDatePriceFormat = mDatePriceFormat;
        this.mCurrency = mCurrency;
        this.mResId = mResId;
    }

    public void setServices(List<Service> services) {
        this.mServices = services;
        this.mItemCount = services.size();
    }

    public void setInsurances(List<Insurance> insurances) {
        this.mInsurances = insurances;
        this.mItemCount = insurances.size();
    }

    public void setRefuelings(List<Refueling> refuelings) {
        this.mRefuelings = refuelings;
        this.mItemCount = refuelings.size();
    }

    private boolean isForServices() {
        return mServices != null && mInsurances == null && mRefuelings == null;
    }

    private boolean isForInsurances() {
        return mInsurances != null && mServices == null && mRefuelings == null;
    }

    private boolean isForRefuelings() {
        return mRefuelings != null && mInsurances == null && mServices == null;
    }

    public void addServices(List<Service> services) {
        Preconditions.checkNotNull(mServices).addAll(services);
        Log.d("service addition", "size = " + services.size());
        mItemCount = mServices.size();
        this.notifyDataSetChanged();
    }

    public void addInsurances(List<Insurance> insurances) {
        Preconditions.checkNotNull(mInsurances).addAll(insurances);
        mItemCount = mInsurances.size();
        this.notifyDataSetChanged();
    }

    public void addRefuelings(List<Refueling> refuelings) {
        Preconditions.checkNotNull(mRefuelings).addAll(refuelings);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_service, parent, false);

        Log.d("ON CREATE VH", "something happens");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String primaryText = "";
        String secondaryText = "";

        Log.d("onBindViewHolder: ", "all ====================");
        if (isForServices()) {
            Log.d("onBindViewHolder: ", "service");
            Service service = mServices.get(position);
            primaryText = service.getType();
            secondaryText = String.format(mDatePriceFormat, service.getDate(), service.getPrice(), mCurrency);
        }else if (isForInsurances()) {
            Insurance insurance = mInsurances.get(position);
            primaryText = insurance.getCompanyName();
            secondaryText = String.format(mDatePriceFormat, insurance.getDate(), insurance.getPrice(), mCurrency);
        }else if (isForRefuelings()) {
            Refueling refueling = mRefuelings.get(position);
            primaryText = refueling.getCompanyName();
            secondaryText = String.format(mDatePriceFormat, refueling.getDate(), refueling.getPrice(), mCurrency);
        }

        holder.setContent(mResId, primaryText, secondaryText);
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_view_list_service) CircleImageView imageView;
        @BindView(R.id.tv_list_service_type) TextView tvPrimaryText;
        @BindView(R.id.tv_list_service_date_price) TextView tvSecondaryText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setContent(int resId, String primaryText, String secondaryText) {
            imageView.setImageResource(resId);
            tvPrimaryText.setText(primaryText);
            tvSecondaryText.setText(secondaryText);
        }
    }
}
