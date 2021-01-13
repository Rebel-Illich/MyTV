package project.ty.myapp.view.channels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.ty.myapp.R;
import project.ty.myapp.model.Channel;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ChannelViewHolder> {

    private final List<Channel> items  = new ArrayList<>();
    private OnChannelSelectedListener onChannelSelectedListener;

    public void setOnChannelSelectedListener(OnChannelSelectedListener onChannelSelectedListener) {
        this.onChannelSelectedListener = onChannelSelectedListener;
    }

    public void updateItems(List<Channel> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_item, parent, false);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelsAdapter.ChannelViewHolder holder, int position) {
        Channel channel = items.get(position);
        holder.bind(channel);
        holder.itemView.setOnClickListener(v -> {
            if (this.onChannelSelectedListener != null) {
                onChannelSelectedListener.onChannelSelected(channel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ChannelViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Channel channel) {
            title = itemView.findViewById(R.id.channelTitle);
            title.setText("Channel: " + channel.getTitle());
        }
    }

    public interface OnChannelSelectedListener {
        void onChannelSelected(Channel channel);
    }
}
