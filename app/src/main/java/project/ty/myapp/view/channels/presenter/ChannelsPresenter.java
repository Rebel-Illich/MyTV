package project.ty.myapp.view.channels.presenter;

import android.util.Log;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import project.ty.myapp.model.ChannelResponse;
import project.ty.myapp.repository.DbRepository;
import project.ty.myapp.storage.LocalStorage;

public class ChannelsPresenter {
private static final String TAG = "ChannelsPresenter";
public static final String CHANNELS_URL = "https://api.screenlife.com/api/get-channels";
public final MutableLiveData<ChannelResponse> channelsLd = new MutableLiveData<>();
public final MutableLiveData<String> errorLd = new MutableLiveData<>();
private final Gson gson = new Gson();
public DbRepository repository;


public void getChannelsList() {
    OkHttpClient client = new OkHttpClient.Builder().build();
    Request channelsRequest = new Request.Builder()
        .addHeader("Authentication", "Bearer " + LocalStorage.getInstance().getToken())
        .url(CHANNELS_URL).get().build();
     client.newCall(channelsRequest).enqueue(new Callback() {
         @Override
         public void onFailure(@NotNull Call call, @NotNull IOException e) {
             Log.e(TAG, "onFailure: ", e);
             errorLd.postValue(e.getMessage());
         }

         @Override
         public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
             ChannelResponse channelResponse = gson.fromJson(response.body().string(), ChannelResponse.class);
             channelsLd.postValue(channelResponse);
             if (repository != null) {
                 repository.saveChannels(channelResponse.getData());
             }
         }
     });

}

}
