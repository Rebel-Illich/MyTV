package project.ty.myapp.model;

import java.util.List;

public class Video {

    private String count;
    private List<VideoData> data = null;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<VideoData> getData() {
        return data;
    }

    public void setData(List<VideoData> data) {
        this.data = data;
    }


}
