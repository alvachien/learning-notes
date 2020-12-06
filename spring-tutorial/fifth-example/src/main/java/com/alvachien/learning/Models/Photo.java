package com.alvachien.learning.Models;

import java.time.LocalDateTime;

public class Photo {
    private int id;
    private String title;
    private String desp;
    private int width;
    private int height;
    private int thumbWidth;
    private int thumbHeight;
    private LocalDateTime uploadedAt;
    private String uploadedBy;
    // private String orgFileName;
    // private String photoUrl;
    // private String photoThumbUrl;

    public int getId() {
        return this.id;
    }
    public void SetId(int nid) {
        this.id = nid;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String ntitle) {
        this.title = ntitle;
    }
    public String getDesp() {
        return this.desp;
    }
    public void setDesp(String ndesp) {
        this.desp = ndesp;
    }
    public int getWidth() {
        return this.width;
    }
    public void setWidth(int wid) {
        this.width = wid;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int hght) {
        this.height = hght;
    }
    public int getThumbWidth() {
        return this.thumbWidth;
    }
    public void setThumbWidht(int wid) {
        this.thumbWidth = wid;
    }
    public int getThumbHeight() {
        return this.thumbHeight;
    }
    public void setThumbHeight(int hght) {
        this.thumbHeight = hght;
    }
    public LocalDateTime getUploadedAt() {
        return this.uploadedAt;
    }
    public void setUploadedAt(LocalDateTime ldt) {
        this.uploadedAt = ldt;
    }
    public String getUploadedBy() {
        return this.uploadedBy;
    }
    public void setUploadedBy(String upd) {
        this.uploadedBy = upd;
    }

    @Override
    public String toString() {
        return "ID: " + this.id
            + ", Title: " + this.title
            + ", Desp: " + this.desp;
    }
}
