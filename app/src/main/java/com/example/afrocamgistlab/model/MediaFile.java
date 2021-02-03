package com.example.afrocamgistlab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaFile {

    @SerializedName("mimetype")
    @Expose
    private String mimeType;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("path")
    @Expose
    private String path;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
