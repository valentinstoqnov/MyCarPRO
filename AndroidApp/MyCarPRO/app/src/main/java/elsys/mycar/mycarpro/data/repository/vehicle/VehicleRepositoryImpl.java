package elsys.mycar.mycarpro.data.repository.vehicle;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import elsys.mycar.mycarpro.data.Constants;
import elsys.mycar.mycarpro.data.model.Vehicle;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public class VehicleRepositoryImpl implements VehicleRepository {

    private static final String TAG = "VehicleRepositoryImpl";

    private DatabaseReference mDatabase;

    public VehicleRepositoryImpl() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference(Constants.VEHICLE);
    }

    @Override
    public void saveVehicle(Vehicle vehicle, OnSaveUpdateDeleteCallback callback) {
        String id = mDatabase.push().getKey();
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
                        callback.onSuccess(value);
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
                        GenericTypeIndicator<List<Vehicle>> typeIndicator = new GenericTypeIndicator<List<Vehicle>>() {};
                        List<Vehicle> vehicles = dataSnapshot.getValue(typeIndicator);
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
