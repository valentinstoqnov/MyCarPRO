package vstoyanov.mycar.mycarpro.data.repository.vehicle;

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
import vstoyanov.mycar.mycarpro.data.model.Vehicle;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import vstoyanov.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public class VehicleRepositoryImpl implements VehicleRepository {

    private static final String TAG = "VehicleRepositoryImpl";

    private DatabaseReference mDatabase;

    public VehicleRepositoryImpl() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference(Constants.VEHICLE);
    }

    @Override
    public void saveVehicle(Vehicle vehicle, OnSaveUpdateDeleteCallback callback) {
        String id = mDatabase.push().getKey();
        vehicle.setId(id);
        mDatabase.child(id)
                .setValue(vehicle)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(vehicle.getName());
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void updateVehicle(String vehicleId, Vehicle vehicle, OnSaveUpdateDeleteCallback callback) {
        vehicle.setId(vehicleId);
        mDatabase.child(vehicleId)
                .setValue(vehicle)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(vehicle.getName());
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void deleteVehicle(String vehicleId, OnSaveUpdateDeleteCallback callback) {
        mDatabase.child(vehicleId)
                .removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(Constants.VEHICLE);
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void fetchVehicleById(String vehicleId, OnItemFetchedCallback<Vehicle> callback) {
        mDatabase.child(vehicleId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Vehicle value = dataSnapshot.getValue(Vehicle.class);
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
    public void fetchVehicles(String userId, OnItemsFetchedCallback<Vehicle> callback) {
        mDatabase.orderByChild(Constants.USER_ID)
                .equalTo(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<HashMap<String, Vehicle>> typeIndicator = new GenericTypeIndicator<HashMap<String, Vehicle>>() {};
                        HashMap<String, Vehicle> dataSnapshotValue = dataSnapshot.getValue(typeIndicator);
                        List<Vehicle> vehicles = new ArrayList<>();
                        if (dataSnapshotValue != null) {
                            vehicles.addAll(dataSnapshotValue.values());
                        }
                        callback.onSuccess(vehicles);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, databaseError.getMessage());
                        callback.onFailure();
                    }
                });
    }
}
