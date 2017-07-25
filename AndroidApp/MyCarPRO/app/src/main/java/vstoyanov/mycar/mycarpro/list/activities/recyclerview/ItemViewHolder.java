package vstoyanov.mycar.mycarpro.list.activities.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vstoyanov.mycar.mycarpro.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_view_list_service) CircleImageView imageView;
    @BindView(R.id.tv_list_service_type) TextView tvPrimaryText;
    @BindView(R.id.tv_list_service_date_price) TextView tvSecondaryText;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setContent(int resId, String primaryText, String secondaryText) {
        imageView.setImageResource(resId);
        tvPrimaryText.setText(primaryText);
        tvSecondaryText.setText(secondaryText);
    }
}