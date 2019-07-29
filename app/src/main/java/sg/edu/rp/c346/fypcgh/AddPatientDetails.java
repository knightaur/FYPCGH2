package sg.edu.rp.c346.fypcgh;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class AddPatientDetails extends Fragment {

    public RadioGroup rg1;
    public RadioButton rbNotSigned, rbSigned;
    public EditText etName, etNRIC, etDOB, etContact, etDrugAllergy;
    public Button btnGo;

    Calendar calander = Calendar.getInstance();

    int Year = calander.get(Calendar.YEAR);
    int Day = calander.get(Calendar.DAY_OF_MONTH);
    int Month = calander.get(Calendar.MONTH);
    int Hour = calander.get(Calendar.HOUR_OF_DAY);
    int Minute = calander.get(Calendar.MINUTE);

    public AddPatientDetails() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_patient_details, container, false);

        etName = rootView.findViewById(R.id.etName);
        etNRIC = rootView.findViewById(R.id.etNRIC);
        etContact = rootView.findViewById(R.id.etFamilyContact);
        etDOB = rootView.findViewById(R.id.etAge);
        etDrugAllergy = rootView.findViewById(R.id.etDrugAllergy);
        btnGo = rootView.findViewById(R.id.btnGo);

        rg1 = rootView.findViewById(R.id.radioGroup1);
        rbNotSigned = rootView.findViewById(R.id.rbNotSigned);
        rbSigned = rootView.findViewById(R.id.rbSigned);
        rg1.check(R.id.rbSigned);

        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDOB.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        Year = year;
                        Month = month;
                        Day = dayOfMonth;
                    }
                };

                DatePickerDialog myDateDialog = new DatePickerDialog(getContext(), myDateListener, Year, Month, Day);
                myDateDialog.show();
            }
        });


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String nric = etNRIC.getText().toString();
                String contact = etContact.getText().toString();
                String drug = etDrugAllergy.getText().toString();
                String dob = etDOB.getText().toString();
                String gender ="Male";
                if (rbNotSigned.isChecked()){
                    gender ="female";
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(new Date());
                if (nric.length() == 0) {
                    Toast.makeText(getContext(), "NRIC is a required field ! ", Toast.LENGTH_LONG).show();
                }
                else if (name.length() == 0) {
                    Toast.makeText(getContext(), "Name is a required field !", Toast.LENGTH_LONG).show();
                }
                else if (contact.length() == 0) {
                    Toast.makeText(getContext(), "Contact is a required field !", Toast.LENGTH_LONG).show();
                }
                else {
//                DateFormat outputFormat = new SimpleDateFormat("MM/yyyy");
//                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//
//                Date date = null;
//                String inputText = dob;
//
//
//                    try {
//                        date = inputFormat.parse(inputText);
//                    } catch (ParseException e) {
//
//                        e.printStackTrace();
//                    }
//
//                String outputText = outputFormat.format(date);

                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.add("nric", nric);
                    params.add("name", name);
                    params.add("dob", date);
                    params.add("gender", gender);
                    params.add("familyContact", contact);
                    params.add("drugAllergy", drug);

                    client.post("http://10.0.2.2/FYPCGH/doAddPatients.php", params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                String message = response.getString("message");
                                Toast.makeText(getContext(), "Patient added successfully", Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {

                            }
                        }
                    });
                }

            }
        });
        return rootView;
    }
}