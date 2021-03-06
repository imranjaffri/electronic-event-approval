package com.example.choudry.electroniceventapproval.data.polls;

import java.util.ArrayList;

public class PollsList {
    private String message;

    private String status;

    private ArrayList<PollsData> data;

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

    public ArrayList<PollsData> getData() {
        return data;
    }

    public void setData(ArrayList<PollsData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", status = " + status + ", data = " + data + "]";
    }
}