package com.alvachien.learning.Models;

public class Album {
    private int id;
    private String title;
    private String desp;
    private String createdBy;
    // private DateTime createdAt;

    public Album() {        
    }

    public int getId() {
        return this.id;
    }
    public void setId(int nid) {
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
    public String getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String crtby) {
        this.createdBy = crtby;
    }
}
