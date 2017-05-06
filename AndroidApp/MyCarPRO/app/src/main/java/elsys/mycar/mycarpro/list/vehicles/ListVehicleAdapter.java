package elsys.mycar.mycarpro.list.vehicles;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.DateUtils;

public class ListVehicleAdapter extends RecyclerView.Adapter<ListVehicleAdapter.ViewHolder> implements ListVehicleContract.Adapter{

    private List<Vehicle> mVehicles;
    private OnCardActionListener mListener;

    public ListVehicleAdapter(OnCardActionListener mListener) {
        this.mVehicles = new ArrayList<>();
        this.mListener = mListener;
    }

    @Override
    public void addVehicles(List<Vehicle> vehicles) {
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
        Log.d("ADAPTER REMOVE POS =", " " + position);
        Log.d("DATASET SIZE", "BEFORE DELETION = " + mVehicles.size());
        mVehicles.remove(position);
        notifyDataSetChanged();
        Log.d("DATASET SIZE", "AFRER DELETION = " + mVehicles.size());
        //this.notifyItemRemoved(position);
        //this.notifyDataSetChanged();
        Log.d("DATASET SIZE", "AFTER NOTIFY DELETION = " + mVehicles.size());
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
        holder.setContent(vehicle);
        setCardActionClickListeners(holder, vehicle.getId(), position);
    }

    @Override
    public int getItemCount() {
        return mVehicles.size();
    }

    private void setCardActionClickListeners(final ViewHolder holder, final String vehicleId, final int position) {
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onViewClick(vehicleId);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteClick(vehicleId, position);
            }
        });
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_view_card_vehicle_photo) ImageView imgPhoto;
        @BindView(R.id.tv_card_vehicle_name) TextView tvName;
        @BindView(R.id.tv_card_vehicle_make_and_model) TextView tvMakeModel;
        @BindView(R.id.tv_card_vehicle_day) TextView tvDay;
        @BindView(R.id.tv_card_vehicle_month) TextView tvMonth;
        @BindView(R.id.tv_card_vehicle_year) TextView tvYear;
        @BindView(R.id.btn_card_vehicle_delete) TextView btnDelete;
        @BindView(R.id.btn_card_vehicle_view) TextView btnView;
        @BindView(R.id.tv_card_vehicle_odometer) TextView tvOdometer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setContent(Vehicle vehicle) {

      /*      //TODO: wait api photo support
            Picasso p = Picasso.with(imgPhoto.getContext());
                    p.setLoggingEnabled(true);
                    p.setIndicatorsEnabled(true);
                    p.load("http://i.imgur.com/DvpvklR.png").into(imgPhoto);*/

            imgPhoto.setBackgroundColor(vehicle.getColor());

            tvName.setText(vehicle.getName());

            String makeModel = String.format("%s %s", vehicle.getMake(), vehicle.getModel());
            tvMakeModel.setText(makeModel);

            tvOdometer.setText(String.valueOf(vehicle.getOdometer()));

            String date = vehicle.getManufactureDate();

            String day = DateUtils.getTextDayFromTextDate(date);
            tvDay.setText(day);

            String month = DateUtils.getTextDayFromTextMonth(date);
            tvMonth.setText(month);

            String year = DateUtils.getTextDayFromTextYear(date);
            tvYear.setText(year);
        }
    }

    interface OnCardActionListener {

        void onViewClick(String vehicleId);

        void onDeleteClick(String id, int position);
    }
}
