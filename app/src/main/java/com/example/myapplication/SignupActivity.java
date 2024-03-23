package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

        private EditText editTextEmail, editTextPassword, editViewRewritePassword;
        private Spinner spinnerVille;
        private RadioGroup radioGroupVousEtes;
        private RadioButton radioButtonRecruteur, radioButtonCandidat;
        private Button buttonCreerCompte;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            // Initialize views
            editTextEmail = findViewById(R.id.editTextEmail);
            editTextPassword = findViewById(R.id.editTextPassword);
            editViewRewritePassword = findViewById(R.id.editViewRewritePassword);
            spinnerVille = findViewById(R.id.spinnerVille);
            radioGroupVousEtes = findViewById(R.id.radioGroupVousEtes);
            radioButtonRecruteur = findViewById(R.id.radioButtonRecruteur);
            radioButtonCandidat = findViewById(R.id.radioButtonCandidat);
            buttonCreerCompte = findViewById(R.id.button2);

            // Set onClickListener for button
            buttonCreerCompte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get input values
                    String email = editTextEmail.getText().toString().trim();
                    String password = editTextPassword.getText().toString();
                    String confirmPassword = editViewRewritePassword.getText().toString();
                    String selectedVille = spinnerVille.getSelectedItem().toString();

                    // Get selected radio button text
                    RadioButton selectedRadioButton = findViewById(radioGroupVousEtes.getCheckedRadioButtonId());
                    String userType = selectedRadioButton.getText().toString();

                    // Check if passwords match
                    if (!password.equals(confirmPassword)) {
                        Toast.makeText(SignupActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Perform signup logic here (e.g., store user data in database)

                    // Display a success message
                    Toast.makeText(SignupActivity.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();

                    // Finish activity
                    finish();
                }
            });
        }
    }
