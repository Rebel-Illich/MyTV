package project.ty.myapp.roomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import project.ty.myapp.model.Channel;
import project.ty.myapp.model.VideoData;


@Dao
public interface MediaListDao {


    @Query("SELECT * FROM channels_table")
    LiveData<List<Channel>> getChannels();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Channel channel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChannels(List<Channel> channels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVideos(List<VideoData> videos);

    @Delete
    void delete(Channel channel);

    @Query("DELETE FROM channels_table")
    void deleteAllChannels();

    @Query("SELECT * FROM videos_table WHERE channelId = :channelId")
    LiveData<List<VideoData>> getVideos(String channelId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(VideoData videoData);

    @Delete
    void delete(VideoData videoData);

    @Query("DELETE FROM videos_table")
    void deleteAllVideos();


}

