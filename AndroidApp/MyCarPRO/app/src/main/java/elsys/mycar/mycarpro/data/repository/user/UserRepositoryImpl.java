package elsys.mycar.mycarpro.data.repository.user;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import elsys.mycar.mycarpro.data.Constants;
import elsys.mycar.mycarpro.data.model.User;

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

    private void saveUserInFirebaseDatabase(User user, OnUserSignCallback callback) {
        mFirebaseDatabase.child(Constants.USER)
                .child(user.getId())
                .setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(user.getEmail());
                    }else {
                        callback.onFailure();
                    }
                });
    }
}
