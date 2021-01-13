package project.ty.myapp.view.login;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import project.ty.myapp.R;
import project.ty.myapp.model.LoginResponse;
import project.ty.myapp.storage.LocalStorage;
import project.ty.myapp.view.CustomTextWatcher;
import project.ty.myapp.view.signup.SingUpActivity;
import project.ty.myapp.view.channels.ChannelListActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView tvRegister;
    Button btnlogin;
    final String login_url = "https://api.screenlife.com/api-mobile/user/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        btnlogin.setEnabled(false);

        EditText[] edList = {etEmail, etPassword};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList, btnlogin);
        for (EditText editText : edList) editText.addTextChangedListener(textWatcher);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SingUpActivity.class);
                startActivity(i);
            }
        });

        //login activity

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();

                new LoginUser().execute(Email, Password);

            }
        });

    }

    //requesting email and password from server

    public class LoginUser extends AsyncTask<String, Void, String> {
        private static final String TAG = "LoginUser";
        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground() called with: strings = [" + Arrays.deepToString(strings) + "]");
            String Email= strings[0];
            String Password= strings[1];

            OkHttpClient okHttpClient= new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("email", Email)
                    .add("password", Password)
                    .build();

            Request request = new Request.Builder()
                    .url(login_url)
                    .post(formBody)
                    .build();


            //checking whether we are getting response from server or not

            Response response = null;
            try{
                response = okHttpClient.newCall(request).execute();
                Log.d(TAG, "doInBackground: " + response);
                if(response.code() == 200) {
                    String result = response.body().string();
                    LoginResponse loginResponse = new Gson().fromJson(result, LoginResponse.class);
                    LocalStorage.getInstance().saveUser(loginResponse.user);
                    LocalStorage.getInstance().saveToken(loginResponse.accessToken);
                    Intent i = new Intent(LoginActivity.this, ChannelListActivity.class);
                    startActivity(i);
                    finish();

                } else if(response.code() == 422){
                    showToast(response.message() + "Error, check your email or password !!!");
                }
            } catch (Exception e){
                Log.e(TAG, "doInBackground: ", e);
                e.printStackTrace();
            }

            return null;

        }
    }

    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}