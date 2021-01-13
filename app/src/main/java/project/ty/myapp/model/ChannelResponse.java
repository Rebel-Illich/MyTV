package project.ty.myapp.model;

import java.util.List;

public class ChannelResponse {

    private Boolean status;
    private List<Channel> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Channel> getData() {
        return data;
    }

    public void setData(List<Channel> data) {
        this.data = data;
    }
}
