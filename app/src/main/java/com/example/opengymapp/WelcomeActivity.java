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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class WelcomeActivity extends AppCompatActivity {


    Button logInButton, signUpButton;
    EditText emailET, passwordET;

    String email, password;
    public static FirebaseHelper firebaseHelper;
    public final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        firebaseHelper = new FirebaseHelper();
        logInButton = findViewById(R.id.logIn);
        signUpButton = findViewById(R.id.signUp);
        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }

    public void updateUI() {
        Log.d(TAG, "inside updateUI: " + firebaseHelper.getmAuth().getUid());
        if (firebaseHelper.getmAuth().getUid() != null) {
            firebaseHelper.attachReadDataToUser();
            Intent intent = new Intent(WelcomeActivity.this, SelectGymActivity.class);
            startActivity(intent);
        }
    }

    public void signUpClicked(View view) {
        Log.i(TAG, "Sign up clicked");
        if (getValues()) {
            firebaseHelper.getmAuth().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                firebaseHelper.updateUid(firebaseHelper.getmAuth().getUid());
                                Log.d(TAG, email + " created and logged in");

                                firebaseHelper.addUserToFirestore(email,
                                        firebaseHelper.getmAuth().getUid());
                                firebaseHelper.attachReadDataToUser();

                                Intent intent = new Intent(WelcomeActivity.this,
                                        SelectGymActivity.class);
                                startActivity(intent);
                            }
                            else {
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
        if (getValues()) {
            firebaseHelper.getmAuth().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                firebaseHelper.updateUid(firebaseHelper.getmAuth().getUid());
                                firebaseHelper.attachReadDataToUser();

                                Log.d(TAG, email + " logged in");

                                Intent intent = new Intent(WelcomeActivity.this, SelectGymActivity.class);
                                startActivity(intent);
                            }
                            else {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Log in failed for " + email + " " + password + e.getMessage());
                                } catch (FirebaseAuthInvalidUserException e) {
                                    // wrong email, no user found with this email
                                    Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Log in failed for " + email + " " + password + e.getMessage());
                                } catch (Exception e) {
                                    Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Log in failed for " + email + " " + password + e.getMessage());
                                }
                            }
                            Log.d(TAG, "Log in failed for " + email + " " + password +
                                    " because of \n"+ task.getException());
                        }

                    });

        }
    }


    private boolean getValues() {
        email = emailET.getText().toString();
        password = passwordET.getText().toString();

        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter all fields",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 char long",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Log.i(TAG, email + " " + password + " is set after getValues(), return true");
            email = removeTrailingSpaces(email);
            return true;
        }
    }

    private String removeTrailingSpaces(String email) {
        String lastChar = email.substring(email.length() -1);
        if (lastChar.equals(" "))
            email = email.substring(0, email.length()-1);
        return email;
    }

}



