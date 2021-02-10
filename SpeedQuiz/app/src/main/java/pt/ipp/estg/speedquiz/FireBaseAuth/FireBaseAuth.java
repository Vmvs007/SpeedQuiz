package pt.ipp.estg.speedquiz.FireBaseAuth;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import pt.ipp.estg.speedquiz.R;

public class FireBaseAuth extends BasicActivity implements View.OnClickListener {
    private static final String TAG = "EmailPassword";

    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private EditText mEmailField;
    private EditText mPasswordField;
    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView t3;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseUser myuserobj;

    private UtilizadorViewModel utilizadorViewModel;

    SharedPreferences mUser;
    SharedPreferences.Editor mEditor;

    // [END declare_auth]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mUser = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mUser.edit();
        utilizadorViewModel= ViewModelProviders.of(FireBaseAuth.this).get(UtilizadorViewModel.class);
        FirebaseApp.initializeApp(FireBaseAuth.this);

        if (user == null){
            setContentView(R.layout.activity_fire_base_auth);
            FacebookSdk.sdkInitialize(getApplicationContext());
            loginButton= findViewById(R.id.login_button);
            loginButton.setReadPermissions("email","public_profile");
            callbackManager= CallbackManager.Factory.create();
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    String userid = loginResult.getAccessToken().getUserId();
                    GraphRequest graphRequest= GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            displayUserInfo(object);

                        }
                    });

                    Bundle parameters= new Bundle();

                    graphRequest.setParameters(parameters);
                    graphRequest.executeAsync();

                }
                public void displayUserInfo(JSONObject object)
                {
                    String first_name, last_name, email,id;
                    try{
                        first_name=object.getString("first_name");
                        last_name=object.getString("last_name");
                        email=object.getString("email");
                        id=object.getString("id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });
            t3=findViewById(R.id.textView3);
        }
        else{
            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);


        // Views



        mDetailTextView = findViewById(R.id.detail);
        mEmailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);
        mStatusTextView=findViewById(R.id.status);

        // Buttons
        findViewById(R.id.emailSignInButton).setOnClickListener(this);
        findViewById(R.id.emailCreateAccountButton).setOnClickListener(this);
        findViewById(R.id.signOutButton).setOnClickListener(this);
        findViewById(R.id.verifyEmailButton).setOnClickListener(this);

        // [START initialize_auth]
        // Initialize Firebase Auth


        // [END auth_sign_out]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }

    public void buttonclickLoginFb(View v){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                handleFacebookToken(loginResult.getAccessToken());



            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Utilizador cancelou", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful())
                        {
                            FirebaseUser myuserobj = mAuth.getCurrentUser();
                            updateUIFB(myuserobj);


                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Não é possível fazer o registo na firebase", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }




    public void openRegister(View v){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }

    public void openLogin(View v){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }



    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            utilizadorViewModel.getUtilizadorList().observe(FireBaseAuth.this, new Observer<List<Utilizador>>() {
                                @Override
                                public void onChanged(List<Utilizador> eventList) {
                                    List<Utilizador> utilList = eventList;
                                    if (!utilList.isEmpty()){
                                        mEditor.putString("name",utilList.get(0).getNome());
                                        mEditor.putString("cont", String.valueOf(utilList.get(0).getContacto()));
                                        mEditor.commit();
                                    }
                                }
                            });
                            updateUI(user);
                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(FireBaseAuth.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            utilizadorViewModel.getUtilizadorList().observe(FireBaseAuth.this, new Observer<List<Utilizador>>() {
                                @Override
                                public void onChanged(List<Utilizador> eventList) {
                                    List<Utilizador> utilList = eventList;
                                    if (!utilList.isEmpty()){
                                        mEditor.putString("name",utilList.get(0).getNome());
                                        mEditor.putString("cont", String.valueOf(utilList.get(0).getContacto()));
                                        mEditor.commit();
                                    }
                                }
                            });
                            //updateUI(user);
                            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(FireBaseAuth.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText(R.string.auth_failed);
                        }
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    public void signOut() {
        mAuth.signOut();
        updateUI(null);


    }

    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.verifyEmailButton).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.verifyEmailButton).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(FireBaseAuth.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(FireBaseAuth.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void  updateUIFB(FirebaseUser myuserobj){
        t3.setText(myuserobj.getEmail());

    }
    private void updateUI(FirebaseUser user) {

        hideProgressDialog();

        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));

            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
            findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);

            findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
            findViewById(R.id.signedInButtons).setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.emailCreateAccountButton) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.emailSignInButton) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.signOutButton) {
            signOut();




        } else if (i == R.id.verifyEmailButton) {
            //sendEmailVerification();
            utilizadorViewModel.getUtilizadorList().observe(FireBaseAuth.this, new Observer<List<Utilizador>>() {
                @Override
                public void onChanged(List<Utilizador> eventList) {
                    List<Utilizador> utilList = eventList;
                    if (!utilList.isEmpty()){
                        mEditor.putString("name",utilList.get(0).getNome());
                        mEditor.putString("cont", String.valueOf(utilList.get(0).getContacto()));
                        mEditor.commit();
                    }
                }
            });
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
        }
    }
}

