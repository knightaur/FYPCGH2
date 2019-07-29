package sg.edu.rp.c346.fypcgh;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMedicalDetails extends Fragment {

    public EditText etWard, etBed, etAdmissionDate, etNRIC, etLastMeal, etLastFluid, etConsult,
            etConsultTime, etLastFLuidTime, etLastMealTime, etAdmissionTime, etRemarks;
    public RadioGroup rg1, rg2, rg3, rg4;
    public RadioButton rbSigned, rbNotsigned, rbDone, rbNotDone, radioButtonSigned, radioButtonNotSigned, rbThreaten, rbNonThreaten;
    public Button btnGo2;

    Calendar calander = Calendar.getInstance();

    int Year = calander.get(Calendar.YEAR);
    int Day = calander.get(Calendar.DAY_OF_MONTH);
    int Month = calander.get(Calendar.MONTH);
    int Hour = calander.get(Calendar.HOUR_OF_DAY);
    int Minute = calander.get(Calendar.MINUTE);

    public AddMedicalDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_medical_details, container, false);

        etNRIC = rootView.findViewById(R.id.etNRIC);
        etBed = rootView.findViewById(R.id.etBed);
        etAdmissionDate = rootView.findViewById(R.id.etAdmissionDate);
        etAdmissionTime = rootView.findViewById(R.id.etAdmissionTime);
        etLastMeal = rootView.findViewById(R.id.etLastMealDay);
        etLastFluid = rootView.findViewById(R.id.etLastFluidDay);
        etWard = rootView.findViewById(R.id.etWard);
        etConsult = rootView.findViewById(R.id.etConsultDay);
        etConsultTime = rootView.findViewById(R.id.etConsultTime);
        etLastMealTime = rootView.findViewById(R.id.etLastMealTime);
        etLastFLuidTime = rootView.findViewById(R.id.etLastFluidTime);
        etRemarks = rootView.findViewById(R.id.etRemarks);
        btnGo2 = rootView.findViewById(R.id.btnGo2);

        rg1 = rootView.findViewById(R.id.radioGroup1);
        rg2 = rootView.findViewById(R.id.radioGroup2);
        rg3 = rootView.findViewById(R.id.radioGroup3);
        rg4 = rootView.findViewById(R.id.radioGroup4);

        rbDone = rootView.findViewById(R.id.radioButtonDone);
        rbNotDone = rootView.findViewById(R.id.radioButtonNotDone);
        rbSigned = rootView.findViewById(R.id.rbSigned);
        rbNotsigned = rootView.findViewById(R.id.rbNotSigned);
        radioButtonSigned = rootView.findViewById(R.id.radioButtonSigned);
        radioButtonNotSigned = rootView.findViewById(R.id.radioButtonNotSigned);
        rbThreaten = rootView.findViewById(R.id.radioButtonThreatening);
        rbNonThreaten = rootView.findViewById(R.id.radioButtonNonThreaten);

        etAdmissionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etAdmissionDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        Year = year;
                        Month = month;
                        Day = dayOfMonth;
                    }
                };
                DatePickerDialog myDateDialog = new DatePickerDialog(getContext(), myDateListener, Year, Month, Day);
                myDateDialog.show();
            }
        });

        etLastMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etLastMeal.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        Year = year;
                        Month = month;
                        Day = dayOfMonth;
                    }
                };
                DatePickerDialog myDateDialog = new DatePickerDialog(getContext(), myDateListener, Year, Month, Day);
                myDateDialog.show();
            }
        });

        etConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etConsult.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        Year = year;
                        Month = month;
                        Day = dayOfMonth;
                    }
                };
                DatePickerDialog myDateDialog = new DatePickerDialog(getContext(), myDateListener, Year, Month, Day);
                myDateDialog.show();
            }
        });

        etLastFluid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etLastFluid.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        Year = year;
                        Month = month;
                        Day = dayOfMonth;
                    }
                };
                DatePickerDialog myDateDialog = new DatePickerDialog(getContext(), myDateListener, Year, Month, Day);
                myDateDialog.show();
            }
        });

        etLastMealTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String currMin = "";
                        if (minute < 10) {
                            currMin += "0" + minute;
                        } else {
                            currMin += minute + "";
                        }
                        etLastMealTime.setText(hourOfDay + ":" + currMin);
                        Hour = hourOfDay;
                        Minute = minute;
                    }
                };
                TimePickerDialog myTimeDialog = new TimePickerDialog(getContext(), myTimeListener, Hour, Minute, true);
                myTimeDialog.show();
            }
        });


        etLastFLuidTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String currMin = "";
                        if (minute < 10) {
                            currMin += "0" + minute;
                        } else {
                            currMin += minute + "";
                        }
                        etLastFLuidTime.setText(hourOfDay + ":" + currMin);
                        Hour = hourOfDay;
                        Minute = minute;
                    }
                };
                TimePickerDialog myTimeDialog = new TimePickerDialog(getContext(), myTimeListener, Hour, Minute, true);
                myTimeDialog.show();
            }
        });


        etConsultTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String currMin = "";
                        if (minute < 10) {
                            currMin += "0" + minute;
                        } else {
                            currMin += minute + "";
                        }
                        etConsultTime.setText(hourOfDay + ":" + currMin);
                        Hour = hourOfDay;
                        Minute = minute;
                    }
                };
                TimePickerDialog myTimeDialog = new TimePickerDialog(getContext(), myTimeListener, Hour, Minute, true);
                myTimeDialog.show();
            }
        });

        etAdmissionTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String currMin = "";
                        if (minute < 10) {
                            currMin += "0" + minute;
                        } else {
                            currMin += minute + "";
                        }
                        etAdmissionTime.setText(hourOfDay + ":" + currMin);
                        Hour = hourOfDay;
                        Minute = minute;
                    }
                };
                TimePickerDialog myTimeDialog = new TimePickerDialog(getContext(), myTimeListener, Hour, Minute, true);
                myTimeDialog.show();
            }
        });

        btnGo2.setOnClickListener(v -> {
            String nric = etNRIC.getText().toString();
            String admissionDate = etAdmissionDate.getText().toString();
            String admissionTime = etAdmissionTime.getText().toString();
            String mealDate = etLastMeal.getText().toString();
            String mealTime = etLastMealTime.getText().toString();
            String fluidDate = etLastFluid.getText().toString();
            String fluidTime = etLastFLuidTime.getText().toString();
            String consultDate = etConsult.getText().toString();
            String consultTime = etConsultTime.getText().toString();
            String remarks = etRemarks.getText().toString();

            String ward = etWard.getText().toString();
            String bed = etBed.getText().toString();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:MM:SS");
            String date = simpleDateFormat.format(new Date());
            String time = simpleTimeFormat.format(new Date());
            String.format(date, admissionDate);
            String.format(date, mealDate);
            String.format(date, fluidDate);
            String.format(date, consultDate);

            String.format(time, admissionTime);
            String.format(time, mealTime);
            String.format(time, fluidTime);
            String.format(time, consultTime);

            String admissionDateTime = admissionDate + "" + admissionTime;
            String mealDateTime = mealDate + "" + mealTime;
            String fluidDateTime = fluidDate + "" + fluidTime;
            String consultDateTime = consultDate + "" + consultTime;

            int consent = 0;
            if (rbSigned.isChecked()) {
                consent = 1;
            }

            int investigate = 0;
            if (rbDone.isChecked()) {
                investigate = 1;
            }

            int anesthesia = 0;
            if (radioButtonSigned.isChecked()) {
                anesthesia = 1;
            }

            int priority = 0;
            if (rbThreaten.isChecked()) {
                priority = 1;
            }

            if (nric.length() == 0) {
                Toast.makeText(getContext(), "NRIC is a required field ! ", Toast.LENGTH_LONG).show();
            } else if (ward.length() == 0) {
                Toast.makeText(getContext(), "Ward is a required field !", Toast.LENGTH_LONG).show();
            } else if (bed.length() == 0) {
                Toast.makeText(getContext(), "Bed is a required field !", Toast.LENGTH_LONG).show();
            } else {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("nric", nric);
                params.add("ward", ward);
                params.add("bed", bed);
                params.add("remark", remarks);
                params.add("consultant", consultDateTime);
                params.add("fluid", fluidDateTime);
                params.add("fasting", mealDateTime);
                params.add("admission_date", admissionDateTime);
                params.add("surgical_consent", Integer.toString(consent));
                params.add("necessary_investigations", Integer.toString(investigate));
                params.add("anaesthesia_consent", Integer.toString(anesthesia));
                params.add("priority", Integer.toString(priority));
                params.add("lastEdit", Integer.toString(1));
                params.add("surgical_completed_time", "0000-00-00 00:00:00");
                params.add("last_ready_time", "0000-00-00 00:00:00");
                params.add("surgical_completed", Integer.toString(0));
                params.add("queue_no", Integer.toString(0));
                params.add("ready", Integer.toString(0));
                params.add("mo_queue", Integer.toString(0));


                client.post("http://10.0.2.2/FYPCGH/doAddChit.php", params, new JsonHttpResponseHandler() {
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

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);

        });


        return rootView;
    }

}