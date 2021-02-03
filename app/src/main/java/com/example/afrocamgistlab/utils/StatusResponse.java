package com.example.afrocamgistlab.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusResponse {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private AffectedEntity data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AffectedEntity getAffectedEntity() {
        return data;
    }

    public void setAffectedEntity(AffectedEntity data) {
        this.data = data;
    }

}
