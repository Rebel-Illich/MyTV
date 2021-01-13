package project.ty.myapp.view.videos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.ty.myapp.R;
import project.ty.myapp.model.VideoData;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoItemViewHolder> {

    private final List<VideoData> items = new ArrayList<>();

    public void updateData(List<VideoData> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoItemViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VideoItemViewHolder extends RecyclerView.ViewHolder {

        private TextView videoTitle;
        private TextView videoLikes;
        private TextView videoComments;

        public VideoItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(VideoData videoData) {
            videoTitle = itemView.findViewById(R.id.videoTitle);
            videoLikes = itemView.findViewById(R.id.videoLikes);
            videoComments = itemView.findViewById(R.id.videoComments);

            videoTitle.setText("Video: " + videoData.getTitle());
            videoLikes.setText("Likes: " + videoData.getCountLikes());
            videoComments.setText("Comments: " + videoData.getCountComments());

        }
    }
}

