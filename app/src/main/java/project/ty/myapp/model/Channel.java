package project.ty.myapp.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "channels_table")
public class Channel {


    @PrimaryKey
    private Integer id;
    private String title;
    private Integer createdAt;
    private Integer updatedAt;
    private Integer position;
    private String titleLocalized;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTitleLocalized() {
        return titleLocalized;
    }

    public void setTitleLocalized(String titleLocalized) {
        this.titleLocalized = titleLocalized;
    }
}
