package sg.edu.rp.c346.fypcgh;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;

public class AddPatient extends AppCompatActivity {

    Button btnPatientDetails, btnMedicalDetails;
    Fragment f1 = new AddPatientDetails();
    Fragment f2 = new AddMedicalDetails();

    private AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        setTitle("Add");

        Intent i = getIntent();
        client = new AsyncHttpClient();

        btnPatientDetails = findViewById(R.id.btnPatient);
        btnMedicalDetails = findViewById(R.id.btnMedical);

        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();

        btnPatientDetails.setOnClickListener(v -> {

            btnPatientDetails.setBackgroundResource(R.drawable.patientclicked);
            btnPatientDetails.setClickable(false);
            openFragment(f1);

        });

        btnMedicalDetails.setOnClickListener(v -> {

            btnMedicalDetails.setBackgroundResource(R.drawable.medicalclicked);
            btnMedicalDetails.setClickable(false);
            openFragment(f2);
        });
    }

    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}