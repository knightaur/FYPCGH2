package sg.edu.rp.c346.fypcgh;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText etLoginID, etPassword;
    private Button btnLogin;
    private AsyncHttpClient client;
    Intent intent;

    boolean check = false;

    String pw = "";
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        etLoginID = findViewById(R.id.editTextLoginID);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        client = new AsyncHttpClient();

        btnLogin.setOnClickListener(v -> {
            String username = etLoginID.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.equalsIgnoreCase("")) {
                Toast.makeText(LoginActivity.this, "Login failed. Please enter username.", Toast.LENGTH_LONG).show();

            } else if (password.equalsIgnoreCase("")) {
                Toast.makeText(LoginActivity.this, "Login failed. Please enter password.", Toast.LENGTH_LONG).show();

            } else {
                // proceed to authenticate user

                RequestParams params = new RequestParams();
                params.add("staffID", username);
                params.add("password", password);

                client.post("http://10.0.2.2/FYPCGH/doLogin.php", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();

                            check = true;
                            pw = response.getString("password");
                            id = response.getString("staffID");
                            intent.putExtra("staffID", id);
                            intent.putExtra("password", pw);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (check) {

                    intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    etLoginID.setText("");
                    etPassword.setText("");
                }

            }
        });
    }
}