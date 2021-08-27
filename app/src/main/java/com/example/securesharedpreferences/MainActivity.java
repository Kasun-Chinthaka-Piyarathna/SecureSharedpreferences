package com.example.securesharedpreferences;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBox1, checkBox2;
    private EditText secretNameEditText, secretValueEditText, secreteNameEditTextForFetch;
    private TextView secretValueTextView;
    private static final String SECRET_STORE = "Secrets";
    private static final String SECURE_SECRET_STORE = "Secure Secrets";
    private String secretName, secretValue;
    private SharedPref sharedPref;
    private SecureSharedPref secureSharedPref;
    private boolean isSecureOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = new SharedPref(this, SECRET_STORE);
        secureSharedPref = new SecureSharedPref(this, SECURE_SECRET_STORE);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        secretNameEditText = findViewById(R.id.secrete_name_edittext);
        secretValueEditText = findViewById(R.id.secrete_value_edittext);
        secreteNameEditTextForFetch = findViewById(R.id.fetch_secrete_name_edittext);
        secretValueTextView = findViewById(R.id.fetch_secrete_value_textview);
        Button saveSecretButton = findViewById(R.id.save_secret_btn);
        Button fetchSecretButton = findViewById(R.id.fetch_secret_btn);

        checkBox1.setOnClickListener(v -> {
            if (checkBox1.isChecked()) {
                checkBox2.setChecked(false);
            }
            isSecureOn = false;
        });
        checkBox2.setOnClickListener(v -> {
            if (checkBox2.isChecked()) {
                checkBox1.setChecked(false);
            }
            isSecureOn = true;
        });

        saveSecretButton.setOnClickListener(v -> {
            secretName = secretNameEditText.getText().toString().trim();
            secretValue = secretValueEditText.getText().toString().trim();
            if (secretName.equals("")) {
                secretNameEditText.setError("Required!");
            } else if (secretValue.equals("")) {
                secretValueEditText.setError("Required!");
            } else {
                if (isSecureOn) {
                    secureSharedPref.put(secretName, secretValue);
                    Toast.makeText(this, "secret is saved", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPref.put(secretName, secretValue);
                    Toast.makeText(this, "secret is saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fetchSecretButton.setOnClickListener(v -> {
            secretName = secreteNameEditTextForFetch.getText().toString().trim();
            if (secretName.equals("")) {
                secreteNameEditTextForFetch.setError("Required!");
            } else {
                String secretValue;
                if (isSecureOn) {
                    secretValue = secureSharedPref.get(secretName);
                } else {
                    secretValue = sharedPref.get(secretName);
                }
                Toast.makeText(this, "secret is fetched", Toast.LENGTH_SHORT).show();
                secretValueTextView.setText(secretValue);
            }
        });
    }
}