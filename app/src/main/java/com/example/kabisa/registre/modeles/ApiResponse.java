package com.example.kabisa.registre.modeles;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("profile")
    private String status;
    @SerializedName("resultat_du_code")
    private int resultCode;
    @SerializedName("nom")
    private String name;

    public String getStatus() {
        return status;
    }
    public int getResultCode() {
        return resultCode;
    }
    public String getName() {
        return name;
    }
}
