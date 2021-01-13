package project.ty.myapp.view.account.presenter;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import project.ty.myapp.model.GeneralResponse;
import project.ty.myapp.storage.LocalStorage;

public class AccountPresenter {
    public static final String TOKEN = "HGWYUsnd83bdsjdfwoi4c";
    private static final String TAG = "AccountPresenter";
    public static final String LOGOUT_URL = "https://api.screenlife.com/api-mobile/user/logout";
    public final MutableLiveData<GeneralResponse> logoutLd = new MutableLiveData<>();
    public final MutableLiveData<String> errorLd = new MutableLiveData<>();
    private final Gson gson = new Gson();

    public void logout() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        RequestBody formBody = new FormBody.Builder()
            .add("device_token", TOKEN)
            .build();

        Request logoutRequest = new Request.Builder()
            .addHeader("Authentication", "Bearer " + LocalStorage.getInstance().getToken())
            .url(LOGOUT_URL)
            .post(formBody)
            .build();

        client.newCall(logoutRequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: ", e);
                errorLd.postValue(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                GeneralResponse logoutResponse = gson.fromJson(response.body().string(), GeneralResponse.class);
                logoutLd.postValue(logoutResponse);
            }
        });

    }

}
