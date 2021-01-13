package project.ty.myapp.view.account;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import project.ty.myapp.R;
import project.ty.myapp.storage.LocalStorage;
import project.ty.myapp.view.account.presenter.AccountPresenter;
import project.ty.myapp.view.splash.SplashActivity;

public class AccountActivity extends AppCompatActivity {

    private Button logout;
    private AccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
    }

    private void initUi() {
        setContentView(R.layout.activity_dash_board);
        this.presenter = new AccountPresenter();
        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(v -> {
            logOut();
        });
    }

    private void logOut() {
        presenter.logout();
        LocalStorage.clear();
        finish();
        Intent intent = new Intent(AccountActivity.this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}