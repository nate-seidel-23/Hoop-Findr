package com.example.opengymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class WelcomeActivity extends AppCompatActivity {
    // xml elements
    Button logInB, signUpB;
    EditText emailET, passwordET;

    // values to pass to firebase auth for log in/sign up
    String email, password;

    // this variable will be used for communicating with Firebase auth and firestore db
    public static FirebaseHelper firebaseHelper;

    // Use the same TAG all the time for Log statements. Feel free to change the value of TAG
    public final String TAG = "Denna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        firebaseHelper = new FirebaseHelper();
        logInB = findViewById(R.id.logIn);
        signUpB = findViewById(R.id.signUp);
        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        updateUI();
    }

    public void updateUI() {
        // if the user is already logged in, then they bypass this screen
        Log.d(TAG, "inside updateUI: " + firebaseHelper.getmAuth().getUid());
        if (firebaseHelper.getmAuth().getUid() != null) {
            firebaseHelper.attachReadDataToUser();
            Intent intent = new Intent(WelcomeActivity.this, SelectGymActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Method first checks if the input is valid.  If it meets the screening criteria from
     * getValues(), then the username (which is an email) and password are sent to the FirebaseHelper
     * class to call upon a method of Firebase auth called createUserWithEmailAndPassword
     *
     * This method will return a result once it is complete, and we are listening for that result
     *
     * No matter what, the method will complete, either successfully or not
     * If successfully, that means the user account was created and we can now access the UID of
     * the signed in user.
     * After doing that we switch screens to the SelectActionActivity
     *
     * If unsuccessful, then we will Log that we failed, and include the exception message which will help
     * us gain insight into WHY it didn't work.
     *
     * @param view
     */


    public void signUpClicked(View view) {
        Log.i(TAG, "Sign up clicked");
        if (getValues()) {      // get username and password
            // Try to create an account using auth
            firebaseHelper.getmAuth().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // FOR SOME REASON IF THE CREATE USER IS NOT WORKING, THIS IS CRASHING
                            // NOT SURE WHY?  I TRIED WITH A DUPLICATE EMAIL ADDRESS AND THAT CRASHED IT.
                            // LOGCAT SHOWED THE MESSAGE BUT I COULDN'T GET IT TO SHOW IN A LOG STATEMENT

                            if (task.isSuccessful()){
                                // Sign up successful, update UI with the currently signed in user's info
                                firebaseHelper.updateUid(firebaseHelper.getmAuth().getUid());
                                Log.d(TAG, email + " created and logged in");

                                firebaseHelper.addUserToFirestore(email, firebaseHelper.getmAuth().getUid());
                                firebaseHelper.attachReadDataToUser();

                                Intent intent = new Intent(WelcomeActivity.this, SelectGymActivity.class);
                                startActivity(intent);
                            }
                            else {
                                /*
                               This prevents the app from CRASHING when the user enters bad items
                               (duplicate email or badly formatted email most likely)

                               https://stackoverflow.com/questions/37859582/how-to-catch-a-firebase-auth-specific-exceptions

                                */

                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    // poorly formatted email address
                                    Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Sign up failed for " + email + " " + password + e.getMessage());
                                } catch (FirebaseAuthEmailException e) {
                                    // duplicate email used
                                    Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Sign up failed for " + email + " " + password + e.getMessage());
                                } catch (Exception e) {
                                    Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Sign up failed for " + email + " " + password + e.getMessage());
                                }


                                // this log message will tell the name of the exception.  If you want to add this to the catch
                                // statement above, then just add another catch above the generic one at the end

                                Log.d(TAG, "Sign up failed for " + email + " " + password +
                                        " because of \n"+ task.getException());

                            }
                        }
                    });
        }
        else {
            Log.d(TAG, "Failed to pass getValues() method");
        }
    }

    public void logInClicked(View view) {
        Log.i(TAG, "Log in clicked");
        if (getValues()) {        // get username and password
            // if valid, log in user and then switch to next activity
            // Try to sign into an account using auth with given email and password
            firebaseHelper.getmAuth().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                // Sign in success, update currently signed in user's info
                                firebaseHelper.updateUid(firebaseHelper.getmAuth().getUid());

                                // we will implement this later
                                // updateIfLoggedIn();
                                // firebaseHelper.attachReadDataToUser();

                                Log.d(TAG, email + " logged in");

                                Intent intent = new Intent(WelcomeActivity.this, SelectGymActivity.class);
                                startActivity(intent);
                            }
                            else {
                                // if log in fails, display a message to the user along with the exception from firebase auth
                                Log.d(TAG, "Log in failed for " + email + " " + password +
                                        " because of \n"+ task.toString());
                            }
                        }
                    });




            // if invalid - prompt message that says why signin won't go through
        }
    }




    /**
     * Helper method to get userName and password whenever one of these buttons is clicked
     * The method makes sure both EditText boxes are filled in and also ensures the password
     * is at least 6 characters long.  If those tests pass, then it will send the values on
     * to be checked by Firebase auth
     *
     * @return true if values pass these tests, false otherwise
     */
    private boolean getValues() {
        email = emailET.getText().toString();
        password = passwordET.getText().toString();

        // verify all user data is entered
        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        // verify password is at least 6 char long (otherwise firebase will deny)
        else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 char long", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Log.i(TAG, email + " " + password + " is set after getValues(), return true");
            return true;
        }
    }

}



