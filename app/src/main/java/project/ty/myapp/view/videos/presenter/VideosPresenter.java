package project.ty.myapp.view.videos.presenter;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import project.ty.myapp.model.VideoResponse;
import project.ty.myapp.repository.DbRepository;
import project.ty.myapp.storage.LocalStorage;

public class VideosPresenter {
    public static final String VIDEOS_URL = "https://api.screenlife.com/api-mobile/search";
    public final MutableLiveData<VideoResponse> videoLd = new MutableLiveData<>();
    public final MutableLiveData<String> errorLd = new MutableLiveData<>();
    private final Gson gson = new Gson();
    public DbRepository repository;


    public void getVideosList(String id) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder url = HttpUrl.parse(VIDEOS_URL).newBuilder();
        url.addQueryParameter("offset", "0");
        url.addQueryParameter("limit", "9");
        url.addQueryParameter("type", "video");
        url.addQueryParameter("channel", id);

        Request channelsRequest = new Request.Builder()
            .addHeader("Authentication", "Bearer " + LocalStorage.getInstance().getToken())
            .url(url.build())
            .get()
            .build();

        client.newCall(channelsRequest).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                VideoResponse channelResponse = gson.fromJson(response.body().string(), VideoResponse.class);
                videoLd.postValue(channelResponse);
                if (repository != null) {
                    repository.saveVideos(channelResponse.getData().getVideo().getData());
                }
            }



            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                errorLd.postValue(e.getMessage());
            }
        });

    }
}
