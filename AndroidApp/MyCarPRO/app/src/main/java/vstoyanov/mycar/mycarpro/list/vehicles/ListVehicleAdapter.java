package vstoyanov.mycar.mycarpro.list.vehicles;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vstoyanov.mycar.mycarpro.R;
import vstoyanov.mycar.mycarpro.data.model.Vehicle;

public class ListVehicleAdapter extends RecyclerView.Adapter<ListVehicleAdapter.ViewHolder> implements ListVehicleContract.Adapter{

    private List<Vehicle> mVehicles;
    private OnCardActionListener mListener;
    private String mVehicleInfoFormat;

    public ListVehicleAdapter(List<Vehicle> mVehicles, OnCardActionListener mListener, String mVehicleInfoFormat) {
        this.mVehicles = mVehicles;
        this.mListener = mListener;
        this.mVehicleInfoFormat = mVehicleInfoFormat;
    }

    @Override
    public void addVehicles(List<Vehicle> vehicles) {
        mVehicles.clear();
        mVehicles.addAll(vehicles);
        this.notifyDataSetChanged();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        mVehicles.add(vehicle);
        this.notifyItemInserted(mVehicles.size());
    }

    @Override
    public void removeVehicle(int position) {
        mVehicles.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_list_vehicle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vehicle vehicle = mVehicles.get(position);
        holder.setContent(vehicle, mVehicleInfoFormat);
        holder.cardView.setOnClickListener(v -> mListener.onItemViewClick(vehicle));
    }

    @Override
    public int getItemCount() {
        return mVehicles.size();
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view_list_view) CardView cardView;
        @BindView(R.id.tv_card_vehicle_info) TextView tvVehicleInfo;
        @BindView(R.id.tv_card_vehicle_name) TextView tvVehicleName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setContent(Vehicle vehicle, String format) {
            cardView.setCardBackgroundColor(vehicle.getColor());
            tvVehicleName.setText(vehicle.getName());
            String info = String.format(format, vehicle.getMake(), vehicle.getModel(), vehicle.getManufactureDate(), vehicle.getOdometer(), "km");
            tvVehicleInfo.setText(info);
        }
    }

    interface OnCardActionListener {

        void onItemViewClick(Vehicle vehicle);
    }
}
