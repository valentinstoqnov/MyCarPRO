package vstoyanov.mycar.mycarpro.data.repository.activities.insurance;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vstoyanov.mycar.mycarpro.data.Constants;
import vstoyanov.mycar.mycarpro.data.model.Insurance;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public class InsuranceRepositoryImpl implements InsuranceRepository {

    private static final String TAG = "InsuranceRepositoryImpl";

    private DatabaseReference mDatabase;

    public InsuranceRepositoryImpl() {
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.INSURANCE);
    }

    @Override
    public void saveInsurance(Insurance insurance, OnSaveUpdateDeleteCallback callback) {
        String id = mDatabase.push().getKey();
        insurance.setId(id);
        mDatabase.child(id)
                .setValue(insurance)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(insurance.getCompanyName());
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void updateInsurance(String insuranceId, Insurance insurance, OnSaveUpdateDeleteCallback callback) {
        insurance.setId(insuranceId);
        mDatabase.child(insuranceId)
                .setValue(insurance)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(insurance.getCompanyName());
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void deleteInsurance(String insuranceId, OnSaveUpdateDeleteCallback callback) {
        mDatabase.child(insuranceId)
                .removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(Constants.INSURANCE);
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void fetchInsuranceById(String insuranceId, OnItemFetchedCallback<Insurance> callback) {
        mDatabase.child(insuranceId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Insurance value = dataSnapshot.getValue(Insurance.class);
                        if (value == null) {
                            callback.onFailure();
                        }else {
                            callback.onSuccess(value);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, databaseError.getMessage());
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void fetchInsurances(String vehicleId, OnItemsFetchedCallback<Insurance> callback) {
        mDatabase.orderByChild(Constants.VEHICLE_ID)
                .equalTo(vehicleId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<HashMap<String, Insurance>> typeIndicator = new GenericTypeIndicator<HashMap<String, Insurance>>() {};
                        HashMap<String, Insurance> dataSnapshotValue = dataSnapshot.getValue(typeIndicator);
                        List<Insurance> insurances = new ArrayList<>();
                        if (dataSnapshotValue != null) {
                            insurances.addAll(dataSnapshotValue.values());
                        }
                        callback.onSuccess(insurances);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, databaseError.getMessage());
                        callback.onFailure();
                    }
                });
    }
}
