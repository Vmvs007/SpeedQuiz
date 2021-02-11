package pt.ipp.estg.speedquiz.FireBaseAuth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pt.ipp.estg.speedquiz.R;

public class FireBaseAuth extends BasicActivity {
    private static final String TAG = "EmailPassword";
    private Context context;

    private EditText insertEmail;
    private EditText insertPassword;
    private Button registerButton;
    private Button loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_auth);
        this.context = getApplicationContext();

        mAuth = FirebaseAuth.getInstance();

        insertEmail = findViewById(R.id.fieldEmail);
        insertPassword = findViewById(R.id.fieldPassword);
        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.logInButton);

        TextKeyListener.clear(insertEmail.getText());
        TextKeyListener.clear(insertPassword.getText());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm() == true) {
                    String email = insertEmail.getText().toString();
                    String password = insertPassword.getText().toString();
                    signIn(email, password);
                } else {
                    Toast.makeText(context, "Preencha todos os dados!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm() == true) {
                    String email = insertEmail.getText().toString();
                    String password = insertPassword.getText().toString();
                    register(email, password);
                } else {
                    Toast.makeText(context, "Preencha todos os dados!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void register(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private boolean validateForm() {
        boolean valid = true;

        String email = insertEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            insertEmail.setError("Required.");
            valid = false;
        } else {
            insertEmail.setError(null);
        }

        String password = insertPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            insertPassword.setError("Required.");
            valid = false;
        } else {
            insertPassword.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
        }
    }
}
