package elsys.mycar.mycarpro.data.repository.activities.service;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import elsys.mycar.mycarpro.data.Constants;
import elsys.mycar.mycarpro.data.model.Service;
import elsys.mycar.mycarpro.data.repository.OnItemFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnItemsFetchedCallback;
import elsys.mycar.mycarpro.data.repository.OnSaveUpdateDeleteCallback;

public class ServiceRepositoryImpl implements ServiceRepository {

    private static final String TAG = "ServiceRepositoryImpl";

    private DatabaseReference mDatabase;

    public ServiceRepositoryImpl() {
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.SERVICE);
    }

    @Override
    public void saveService(Service service, OnSaveUpdateDeleteCallback callback) {
        String id = mDatabase.push().getKey();
        mDatabase.child(id)
                .setValue(service)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(service.getType());
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void updateService(String serviceId, Service service, OnSaveUpdateDeleteCallback callback) {
        mDatabase.child(serviceId)
                .setValue(service)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(service.getType());
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void deleteService(String serviceId, OnSaveUpdateDeleteCallback callback) {
        mDatabase.child(serviceId)
                .removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(Constants.SERVICE);
                    }else {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void fetchServiceById(String serviceId, OnItemFetchedCallback<Service> callback) {
        mDatabase.child(serviceId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Service value = dataSnapshot.getValue(Service.class);
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
    public void fetchServices(String vehicleId, OnItemsFetchedCallback<Service> callback) {
        mDatabase.orderByChild(Constants.VEHICLE_ID)
                .equalTo(vehicleId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<List<Service>> typeIndicator = new GenericTypeIndicator<List<Service>>() {};
                        List<Service> services = dataSnapshot.getValue(typeIndicator);
                        callback.onSuccess(services);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, databaseError.getMessage());
                        callback.onFailure();
                    }
                });
    }
}