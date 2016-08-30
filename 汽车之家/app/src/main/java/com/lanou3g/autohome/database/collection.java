package com.lanou3g.autohome.database;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "COLLECTION".
 */
@Entity
public class collection {
    private String name;

    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String url;
    private String imageUrl;

    @Generated
    public collection() {
    }

    public collection(Long id) {
        this.id = id;
    }

    @Generated
    public collection(String name, Long id, String title, String url, String imageUrl) {
        this.name = name;
        this.id = id;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
