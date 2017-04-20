package com.example.choudry.electroniceventapproval.data;

import java.util.ArrayList;

public class UserLogin {
    private String message;

    private String status;

    private ArrayList<User> data;

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

    public ArrayList<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", status = " + status + ", data = " + data + "]";
    }
}