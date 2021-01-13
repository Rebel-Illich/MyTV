package project.ty.myapp.view.signup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import project.ty.myapp.R;
import project.ty.myapp.view.login.LoginActivity;

public class SingUpActivity extends AppCompatActivity {


    EditText etName, etEmail, etPassword;
    Button btnRegister;
    final String url_Register= "https://api.screenlife.com/api-mobile/user/create";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName= (EditText) findViewById(R.id.et_name);
        etEmail= (EditText) findViewById(R.id.et_reg_email);
        etPassword= (EditText) findViewById(R.id.et_reg_password);
        btnRegister= (Button) findViewById(R.id.btn_register);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name= etName.getText().toString();
                String Email= etEmail.getText().toString();
                String Password=etPassword.getText().toString();

                new RegisterUser().execute(Name, Email, Password);

            }
        });
    }


    public class RegisterUser extends AsyncTask<String,Void, String> {
        private static final String TAG = "RegisterUser";
        @Override
        protected String doInBackground(String... strings) {
            String Name= strings[0];
            String Email= strings[1];
            String Password=strings[2];

            RequestBody formBody = new FormBody.Builder()
                .add("email", Email)
                .add("username", Name)
                .add("password", Password)
                .build();

            OkHttpClient okHttpClient=new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url_Register)
                    .post(formBody)
                    .build();


            //checking server response and inserting data

            Response response= null;

            try {
                response = okHttpClient.newCall(request).execute();
                Log.d(TAG, "doInBackground: " + response);
                if (response.code() == 200) {
                    String result = response.body().string();
                    showToast(result);
                    Intent i = new Intent(SingUpActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Log.e(TAG, "doInBackground3: ", new Exception(response.message()));
                    showToast(response.message());
                }

            }
            catch (Exception e){
                Log.e(TAG, "doInBackground4: ", e);
                e.printStackTrace();
            }


            return null;
        }
    }

    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SingUpActivity.this, Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
