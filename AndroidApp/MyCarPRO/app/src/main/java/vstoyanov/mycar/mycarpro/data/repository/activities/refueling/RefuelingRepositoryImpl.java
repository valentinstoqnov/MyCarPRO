package vstoyanov.mycar.mycarpro.data.repository.activities.refueling;

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
import vstoyanov.mycar.mycarpro.data.model.Refueling;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public class RefuelingRepositoryImpl implements RefuelingRepository {

    private static final String TAG = "RefuelingRepositoryImpl";

    private DatabaseReference mDatabase;

    public RefuelingRepositoryImpl() {
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.REFUELING);
    }

    @Override
    public void saveRefueling(Refueling refueling, OnSaveUpdateDeleteCallback callback) {
        String id = mDatabase.push().getKey();
        refueling.setId(id);
        mDatabase.child(id)
                .setValue(refueling)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(refueling.getCompanyName());
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void updateRefueling(String refuelingId, Refueling refueling, OnSaveUpdateDeleteCallback callback) {
        refueling.setId(refuelingId);
        mDatabase.child(refuelingId)
                .setValue(refueling)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(refueling.getCompanyName());
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void deleteRefueling(String refuelingId, OnSaveUpdateDeleteCallback callback) {
        mDatabase.child(refuelingId)
                .removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(Constants.REFUELING);
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void fetchRefuelingById(String refuelingId, OnItemFetchedCallback<Refueling> callback) {
        mDatabase.child(refuelingId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Refueling value = dataSnapshot.getValue(Refueling.class);
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
    public void fetchRefuelings(String vehicleId, OnItemsFetchedCallback<Refueling> callback) {
        mDatabase.orderByChild(Constants.VEHICLE_ID)
                .equalTo(vehicleId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<HashMap<String, Refueling>> typeIndicator = new GenericTypeIndicator<HashMap<String, Refueling>>() {};
                        HashMap<String, Refueling> dataSnapshotValue = dataSnapshot.getValue(typeIndicator);
                        List<Refueling> refuelings = new ArrayList<>();
                        if (dataSnapshotValue != null) {
                            refuelings.addAll(dataSnapshotValue.values());
                        }
                        callback.onSuccess(refuelings);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, databaseError.getMessage());
                        callback.onFailure();
                    }
                });
    }
}
