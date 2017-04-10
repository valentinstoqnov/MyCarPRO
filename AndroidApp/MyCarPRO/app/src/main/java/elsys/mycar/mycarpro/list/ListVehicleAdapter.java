package elsys.mycar.mycarpro.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsys.mycar.mycarpro.R;
import elsys.mycar.mycarpro.model.Vehicle;
import elsys.mycar.mycarpro.util.DateUtils;

public class ListVehicleAdapter extends RecyclerView.Adapter<ListVehicleAdapter.ViewHolder>{

    private List<Vehicle> mVehicles;

    public void setVehicle(List<Vehicle> mVehicle) {
        this.mVehicles = mVehicle;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_list_vehicle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.imgPhoto.getContext())
                .load("http://i.imgur.com/DvpvklR.png")
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(holder.imgPhoto);
        holder.setContent(mVehicles.get(position));
    }

    @Override
    public int getItemCount() {
        return mVehicles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

      /*  @BindView(R.id.img_view_card_vehicle_photo) */ImageView imgPhoto;
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
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_view_card_vehicle_photo);
            ButterKnife.bind(this, itemView);
        }

        public void setContent(Vehicle vehicle) {

            //TODO: wait api photo support
            /*Picasso.with(imgPhoto.getContext())
                    .load("http://i.imgur.com/DvpvklR.png")
                    .into(imgPhoto);*/

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
}
