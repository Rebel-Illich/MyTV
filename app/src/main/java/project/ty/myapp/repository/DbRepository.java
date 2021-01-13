package project.ty.myapp.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import project.ty.myapp.model.Channel;
import project.ty.myapp.model.VideoData;

public interface DbRepository {
    public void saveChannels(List<Channel> channelList);

    LiveData<List<Channel>> getChannels();

    void saveVideos(List<VideoData> videoList);

    LiveData<List<VideoData>> getVideos(String channelId);
}
