package project.ty.myapp.view.videos;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import project.ty.myapp.R;
import project.ty.myapp.repository.DBRepositoryImpl;
import project.ty.myapp.view.videos.presenter.VideosPresenter;

import android.os.Bundle;
import android.widget.Toast;


public class VideoListActivity extends AppCompatActivity {
    Integer channelId;
    private final VideosPresenter presenter = new VideosPresenter();
    private VideoListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        presenter.repository = new DBRepositoryImpl(getApplication());
        Bundle args = getIntent().getExtras();
        if (args != null) {
            channelId = args.getInt("channel_id");
            if (channelId != null) {
                presenter.getVideosList(channelId.toString());
            }
        }

        initUi();
        initObservers();
    }

    private void initObservers() {
        if (presenter.repository != null) {
            presenter.repository.getVideos(String.valueOf(channelId)).observe(this, videoData ->
            {
                if (adapter.getItemCount() == 0) {
                    adapter.updateData(videoData);
                }
            });
        }
        presenter.videoLd.observe(this, response -> {
            adapter.updateData(response.getData().getVideo().getData());
        });
        presenter.errorLd.observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    private void initUi() {
        RecyclerView recyclerView = findViewById(R.id.rvVideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideoListAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}