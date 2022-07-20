package com.getman.varnabeach.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity (
        indices = {
                @Index(value = {"name", "description"}, unique = true),
                @Index(value = {"name", "description", "lat", "lng"}, unique = true)
        }
)
public class Beach implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "lat")
    public String lat;

    @ColumnInfo(name = "lng")
    public String lng;

    @ColumnInfo(name = "image_uri")
    public String imageURI;

    public Beach(String name, String description, String lat, String lng, String imageURI) {
        this.name = name;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.imageURI = imageURI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beach beach = (Beach) o;
        return name.equals(beach.name) &&
                Objects.equals(description, beach.description) &&
                lat.equals(beach.lat) && lng.equals(beach.lng) &&
                Objects.equals(imageURI, beach.imageURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, lat, lng, imageURI);
    }
}
