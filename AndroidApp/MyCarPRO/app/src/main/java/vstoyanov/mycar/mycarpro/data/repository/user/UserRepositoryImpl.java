package vstoyanov.mycar.mycarpro.data.repository.user;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vstoyanov.mycar.mycarpro.data.Constants;
import vstoyanov.mycar.mycarpro.data.model.User;
import vstoyanov.mycar.mycarpro.data.repository.OnItemFetchedCallback;

public class UserRepositoryImpl implements UserRepository {

    private static final String TAG = "UserRepositoryImpl";

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mFirebaseDatabase;

    public UserRepositoryImpl() {
        this.mFirebaseAuth = FirebaseAuth.getInstance();
        this.mFirebaseDatabase = FirebaseDatabase.getInstance().getReference(Constants.USER);
    }

    @Override
    public void saveUser(User user, OnUserSignCallback callback) {
        mFirebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                       FirebaseUser firebaseUser = task.getResult().getUser();
                       user.setId(firebaseUser.getUid());
                       saveUserInFirebaseDatabase(user, callback);
                   }else {
                        callback.onFailure();
                        Log.e(TAG, "saveUser: ", task.getException());
                   }
                });
    }

    @Override
    public void loginUser(String email, String password, OnUserSignCallback callback) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(email);
                    }else {
                        callback.onFailure();
                        Log.e(TAG, "loginUser: ", task.getException());
                    }
                });
    }

    @Override
    public void fetchUserById(String id, OnItemFetchedCallback<User> callback) {
        mFirebaseDatabase.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = dataSnapshot.getValue(User.class);
                if (value != null) {
                    callback.onSuccess(value);
                }else {
                    callback.onFailure();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: ", databaseError.toException());
                callback.onFailure();
            }
        });
    }

    @Override
    public void signOutCurrentUser() {
        mFirebaseAuth.signOut();
    }

    private void saveUserInFirebaseDatabase(User user, OnUserSignCallback callback) {
        mFirebaseDatabase.child(user.getId())
                .setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(user.getEmail());
                    }else {
                        Log.e(TAG, "saveUserInFirebaseDatabase: ", task.getException());
                        callback.onFailure();
                    }
                });
    }
}
