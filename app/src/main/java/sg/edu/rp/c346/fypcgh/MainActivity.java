package sg.edu.rp.c346.fypcgh;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    AsyncHttpClient client;
    Button btnAll, btnEmergency, btnNonEmergency;
    String url;
    RequestParams params;
    ArrayList<Content> con = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Home");

        btnAll = findViewById(R.id.btnAll);
        btnEmergency = findViewById(R.id.btnEmergency);
        btnNonEmergency = findViewById(R.id.btnNonEmergency);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Title> titles = new ArrayList<>();
//        content.add(new Content("NIL", "12-12-2019 10-05-00", "12-12-2019 10-05-00", "1", "1", "1"));
//        titles.add(new Title("",content, "Francis", "T0073082Z", "18", "1", "M", "1", "1"));

        client = new AsyncHttpClient();

        ContentAdapter adapter = new ContentAdapter(titles);
        rv.setAdapter(adapter);

        url = "http://10.0.2.2/FYPCGH/getAdmissionAllInfo.php";
        params = new RequestParams();

        client.post(url, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject category = response.getJSONObject(i);
                        ArrayList<Content> content = new ArrayList<>();
                        String drug = category.getString("drug_allergy");
                        String fast = category.getString("fasting_start");
                        String adm = category.getString("admission_date");
                        String ni = category.getString("necessary_investigations");
                        String sc = category.getString("surgical_consent");
                        String ac = category.getString("anaesthesia_consent");
                        String id = category.getString("admission_id");
                        String queue = category.getString("queue_no");
                        String nric1 = category.getString("nric");

                        String name = category.getString("name");
                        String nric = category.getString("nric");
                        String age = category.getString("age");
                        String ward = category.getString("ward");
                        String bed = category.getString("bed");
                        String gender = category.getString("gender");
                        String priority = category.getString("priority_status_id");

                        content.add(new Content(drug, fast, adm, ni, sc, ac, id, queue, nric1));
                        titles.add(new Title("", content, name, nric, age, ward, gender, priority, bed));

                    }
                    Log.e("HelpMePls", response.length() + "");
                    ContentAdapter adapter = new ContentAdapter(titles);
                    rv.setAdapter(adapter);

                } catch (JSONException e) {

                }
            }
        });

        btnAll.setOnClickListener((v) -> {
            url = "http://10.0.2.2/FYPCGH/getAdmissionAllInfo.php";
            params = new RequestParams();
            titles.clear();
            btnAll.setBackgroundResource(R.drawable.allchitsclicked);
            btnEmergency.setBackgroundResource(R.drawable.emergency);
            btnNonEmergency.setBackgroundResource(R.drawable.nonemergency);

            client.post(url, params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject category = response.getJSONObject(i);
                            ArrayList<Content> content = new ArrayList<>();
                            String drug = category.getString("drug_allergy");
                            String fast = category.getString("fasting_start");
                            String adm = category.getString("admission_date");
                            String ni = category.getString("necessary_investigations");
                            String sc = category.getString("surgical_consent");
                            String ac = category.getString("anaesthesia_consent");
                            String id = category.getString("admission_id");
                            String queue = category.getString("queue_no");
                            String nric1 = category.getString("nric");

                            String name = category.getString("name");
                            String nric = category.getString("nric");
                            String age = category.getString("age");
                            String ward = category.getString("ward");
                            String bed = category.getString("bed");
                            String gender = category.getString("gender");
                            String priority = category.getString("priority_status_id");

                            content.add(new Content(drug, fast, adm, ni, sc, ac, id, queue, nric1));
                            titles.add(new Title("", content, name, nric, age, ward, gender, priority, bed));

                        }
                        ContentAdapter adapter = new ContentAdapter(titles);
                        rv.setAdapter(adapter);

                    } catch (JSONException e) {

                    }
                }
            });
        });

        btnEmergency.setOnClickListener((v) -> {
            String url = "http://10.0.2.2/FYPCGH/getEmergency.php";
            RequestParams params = new RequestParams();
            titles.clear();
            btnAll.setBackgroundResource(R.drawable.allchits);
            btnEmergency.setBackgroundResource(R.drawable.emergencyclicked);
            btnNonEmergency.setBackgroundResource(R.drawable.nonemergency);

            params.add("priority_status_id", "1");

            client.get(url, params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject category = response.getJSONObject(i);
                            ArrayList<Content> content = new ArrayList<>();
                            String drug = category.getString("drug_allergy");
                            String fast = category.getString("fasting_start");
                            String adm = category.getString("admission_date");
                            String ni = category.getString("necessary_investigations");
                            String sc = category.getString("surgical_consent");
                            String ac = category.getString("anaesthesia_consent");
                            String id = category.getString("admission_id");
                            String queue = category.getString("queue_no");
                            String nric1 = category.getString("nric");

                            String name = category.getString("name");
                            String nric = category.getString("nric");
                            String age = category.getString("age");
                            String ward = category.getString("ward");
                            String bed = category.getString("bed");
                            String gender = category.getString("gender");
                            String priority = category.getString("priority_status_id");

                            content.add(new Content(drug, fast, adm, ni, sc, ac, id, queue, nric1));
                            titles.add(new Title("", content, name, nric, age, ward, gender, priority, bed));

                        }
                        ContentAdapter adapter = new ContentAdapter(titles);
                        rv.setAdapter(adapter);

                    } catch (JSONException e) {

                    }
                }
            });
        });

        btnNonEmergency.setOnClickListener((v) -> {
            String url = "http://10.0.2.2/FYPCGH/getEmergency.php";
            RequestParams params = new RequestParams();
            titles.clear();
            btnAll.setBackgroundResource(R.drawable.allchits);
            btnEmergency.setBackgroundResource(R.drawable.emergency);
            btnNonEmergency.setBackgroundResource(R.drawable.nonemergencyclicked);

            params.add("priority_status_id", "2");

            client.get(url, params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject category = response.getJSONObject(i);
                            ArrayList<Content> content = new ArrayList<>();
                            String drug = category.getString("drug_allergy");
                            String fast = category.getString("fasting_start");
                            String adm = category.getString("admission_date");
                            String ni = category.getString("necessary_investigations");
                            String sc = category.getString("surgical_consent");
                            String ac = category.getString("anaesthesia_consent");
                            String id = category.getString("admission_id");
                            String queue = category.getString("queue_no");
                            String nric1 = category.getString("nric");

                            String name = category.getString("name");
                            String nric = category.getString("nric");
                            String age = category.getString("age");
                            String ward = category.getString("ward");
                            String bed = category.getString("bed");
                            String gender = category.getString("gender");
                            String priority = category.getString("priority_status_id");

                            content.add(new Content(drug, fast, adm, ni, sc, ac, id, queue, nric1));
                            titles.add(new Title("", content, name, nric, age, ward, gender, priority, bed));

                        }
                        ContentAdapter adapter = new ContentAdapter(titles);
                        rv.setAdapter(adapter);

                    } catch (JSONException e) {

                    }
                }
            });
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_buttons_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_patient) {
            Intent i = new Intent(getBaseContext(), AddPatient.class);
            startActivity(i);

        }
        else if(id == R.id.action_logout){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }

//    @Override
//    public void onItemClick(ArrayList<Content> a) {
//        this.con = a;
//
//        String url = "http://10.0.2.2/FYPCGH/editAdmissionInfo_1.php";
//        RequestParams params = new RequestParams();
//
//        params.add("admission_id", con.get(0).id);
//        params.add("queue", con.get(0).queue);
//        params.add("nric", con.get(0).nric);
//        params.add("fasting", con.get(0).fast);
//        params.add("anaesthesia_consent", con.get(0).ac);
//        params.add("surgical_consent", con.get(0).sc);
//        params.add("necessary_investigations", con.get(0).ni);
//        params.add("drug", con.get(0).drug);
//
//        Log.e("hello", con.get(0).drug);
//
//        client.get(url, params, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                try {
//
//                    for (int i = 0; i < response.length(); i++) {
//                        JSONObject category = response.getJSONObject(i);
//                        String success = category.getString("success");
//
//                        if (success.equals("1")) {
//                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "Fail To Edit", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                } catch (JSONException e) {
//
//                }
//            }
//        });
//        con.clear();
//    }
}