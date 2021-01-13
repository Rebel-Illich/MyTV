package project.ty.myapp.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import project.ty.myapp.R;
import project.ty.myapp.model.User;
import project.ty.myapp.storage.LocalStorage;
import project.ty.myapp.view.channels.ChannelListActivity;
import project.ty.myapp.view.login.LoginActivity;

public class SplashActivity  extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LocalStorage.init(getApplicationContext());
        User user = LocalStorage.getInstance().getUser();
        if (user == null) {
            Log.d(TAG, "onCreate: user is null");
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        } else {
            Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
            Intent mainIntent = new Intent(this, ChannelListActivity.class);
            startActivity(mainIntent);
        }
        finish();
    }

    native void nativeHttpCall();
}
