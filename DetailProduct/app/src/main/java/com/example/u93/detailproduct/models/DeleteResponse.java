package com.example.u93.detailproduct.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteResponse {

    @SerializedName("status")
    @Expose
    private boolean status;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
