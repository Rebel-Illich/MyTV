package project.ty.myapp.view.channels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import project.ty.myapp.R;
import project.ty.myapp.model.Channel;
import project.ty.myapp.repository.DBRepositoryImpl;
import project.ty.myapp.storage.LocalStorage;
import project.ty.myapp.view.account.AccountActivity;
import project.ty.myapp.view.channels.presenter.ChannelsPresenter;
import project.ty.myapp.view.videos.VideoListActivity;

public class ChannelListActivity extends AppCompatActivity {

    private final ChannelsPresenter presenter = new ChannelsPresenter();
    private ChannelsAdapter adapter;
    private Button btnA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       presenter.repository = new DBRepositoryImpl(getApplication());


        initButton();

        initUi();
        initObservers();
        initListeners();
    }

    private void initButton() {
        btnA = findViewById(R.id.btn_account);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChannelListActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initUi() {
        RecyclerView channelsView = findViewById(R.id.rvChannels);
        adapter = new ChannelsAdapter();
        channelsView.setLayoutManager(new LinearLayoutManager(this));
        channelsView.setAdapter(adapter);

    }

    private void initListeners() {
        adapter.setOnChannelSelectedListener(new ChannelsAdapter.OnChannelSelectedListener() {
            @Override
            public void onChannelSelected(Channel channel) {
                Intent startVideoActivityIntent = new Intent(getBaseContext(), VideoListActivity.class);
                startVideoActivityIntent.putExtra("channel_id", channel.getId());
                startActivity(startVideoActivityIntent);
            }
        });
    }

    private void initObservers() {
        presenter.channelsLd.observe(this, response -> {
            adapter.updateItems(response.getData());
        });
        if (presenter.repository != null) {
            presenter.repository.getChannels().observe(this, dbData -> {
                if (adapter.getItemCount() == 0) {
                    adapter.updateItems(dbData);
                }
            });
        }
        presenter.errorLd.observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getChannelsList();
    }

}