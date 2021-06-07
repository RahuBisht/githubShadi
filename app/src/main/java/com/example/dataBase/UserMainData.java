package com.example.dataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class UserMainData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // Create text column
    @ColumnInfo(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @ColumnInfo(name = "uid")
    private String uid;

    @ColumnInfo(name = "image")
    private String image;

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    @ColumnInfo(name = "accept")
    private boolean accept;

    public boolean isDecline() {
        return decline;
    }

    public void setDecline(boolean decline) {
        this.decline = decline;
    }

    @ColumnInfo(name = "decline")
    private boolean decline;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "phone")
    private String phone;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
