package elsys.mycar.mycarpro.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import elsys.mycar.mycarpro.R;

public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";

    @BindView(R.id.tv_profile_username) TextView tvUsername;
    @BindView(R.id.tv_profile_name) TextView tvName;
    @BindView(R.id.tv_profile_email) TextView tvEmail;

    @BindString(R.string.profile_data_placeholder) String textFormat;
    @BindString(R.string.email) String textEmail;
    @BindString(R.string.username) String textUsername;
    @BindString(R.string.name) String textName;

    private Unbinder mUnbinder;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setContent();

        return view;
    }

    @OnClick(R.id.btn_profile_logout)
    public void onLogoutClicked() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void setContent() {
        String username = "";
        String firstName = "";
        String lastName = "";
        String email = "";

        tvUsername.setText(String.format(textFormat, textUsername, username));
        tvName.setText(String.format(textFormat, textName, firstName + " " + lastName));
        tvEmail.setText(String.format(textFormat, textEmail, email));
    }
}
