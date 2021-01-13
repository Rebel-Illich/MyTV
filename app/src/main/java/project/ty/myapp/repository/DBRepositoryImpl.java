package project.ty.myapp.repository;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import project.ty.myapp.model.Channel;
import project.ty.myapp.model.VideoData;
import project.ty.myapp.roomdatabase.AppRoomDatabase;
import project.ty.myapp.roomdatabase.MediaListDao;

public class DBRepositoryImpl implements DbRepository {

    private MediaListDao mediaListDao;
    private LiveData<List<Channel>> allChannels;
    private LiveData<List<VideoData>> allVideo;

   public DBRepositoryImpl(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        mediaListDao = db.mediaDao();
        allChannels = mediaListDao.getChannels();

    }

    @Override
    public void saveChannels(List<Channel> channelList) {
       mediaListDao.insertChannels(channelList);

    }

    @Override
    public LiveData<List<Channel>> getChannels() {
        return allChannels;
    }

    @Override
    public void saveVideos(List<VideoData> videoList) {
        mediaListDao.insertVideos(videoList);

    }

    @Override
    public LiveData<List<VideoData>> getVideos(String channelId) {
        return  mediaListDao.getVideos(channelId);
    }
}
