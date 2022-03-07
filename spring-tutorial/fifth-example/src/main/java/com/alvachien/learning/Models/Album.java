package com.alvachien.learning.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Album {
    private int id;
    private String title;
    private String desp;
    private String createdBy;
    private LocalDateTime createdAt;
    private boolean isPublic;
    private String accessCodeHint;
    private String accessCode;    

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
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(LocalDateTime ldt) {
        this.createdAt = ldt;
    }
    public Boolean getIsPublic() {
        return this.isPublic;
    }
    public void setIsPublic(Boolean ispub) {
        this.isPublic = ispub;
    }
    public String getAccessCodeHint() {
        return this.accessCodeHint;
    }
    public void setAccessCodeHint(String hint) {
        this.accessCodeHint = hint;
    }
    public String getAccessCode() {
        return this.accessCode;
    }
    public void setAccessCode(String code) {
        this.accessCode = code;
    }

    public void loadDBResult(ResultSet rs) throws SQLException {
        this.setId(rs.getInt("AlbumID"));
        this.setTitle(rs.getString("Title"));
        this.setDesp(rs.getString("Desp"));
        this.setCreatedBy(rs.getString("CreatedBy"));
        // this.setCreatedAt(rs.getDate("CreatedAt"));
    }

    @Override
    public String toString() {
        var zhFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm", Locale.CHINA);
        String createdAtStr = "";
        if (this.createdAt != null) 
            createdAtStr = zhFormatter.format(this.createdAt);

        return "ID: " + this.id
            + ", Title: " + this.title
            + ( this.isPublic ? "(Public)" : "(Private)" )
            + ", Desp: " + this.desp
            + ", created at: " + createdAtStr;
    }
}
