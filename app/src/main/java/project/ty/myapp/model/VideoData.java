package project.ty.myapp.model;

import com.google.gson.annotations.Expose;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "videos_table")
public class VideoData {

    @PrimaryKey
    @NonNull
    private String id;
    private String isFavorite;
    private String recordId;
    private String title;
    private String userId;
    private String duration;
    @Ignore
    private Object description;
    @Ignore
    private Object image;
    private String isMobile;
    @Ignore
    private Object _public;
    private String status;
    private String device;
    private String createdAt;
    private String countViews;
    private String countLikes;
    private String countComments;
    private String channelId;
    private String link;
    private String isLiked;
    @Ignore
    private List<Image> images = null;
    @Ignore
    private List<Tag> tags = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    public Object getPublic() {
        return _public;
    }

    public void setPublic(Object _public) {
        this._public = _public;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCountViews() {
        return countViews;
    }

    public void setCountViews(String countViews) {
        this.countViews = countViews;
    }

    public String getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(String countLikes) {
        this.countLikes = countLikes;
    }

    public String getCountComments() {
        return countComments;
    }

    public void setCountComments(String countComments) {
        this.countComments = countComments;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(String isLiked) {
        this.isLiked = isLiked;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
}
