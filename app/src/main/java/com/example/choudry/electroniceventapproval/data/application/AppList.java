package com.example.choudry.electroniceventapproval.data.application;

import java.util.ArrayList;

/**
 * Created by Choudry on 4/22/2017.
 */

public class AppList {

    private String message;

    private String status;

    private ArrayList<ApplicationData> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ApplicationData> getData() {
        return data;
    }

    public void setData(ArrayList<ApplicationData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", status = " + status + ", data = " + data + "]";
    }
}
