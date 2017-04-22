package com.example.choudry.electroniceventapproval.data.application;

/**
 * Created by Choudry on 4/22/2017.
 */

public class ApplicationData {

    private String created_date;

    private String title;

    private String updated_date;

    private String status;

    private String description;

    private String image;

    private String event_id;

    private String remarks;

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "ClassPojo [created_date = " + created_date + ", title = " + title + ", updated_date = " + updated_date + ", status = " + status + ", description = " + description + ", image = " + image + ", event_id = " + event_id + ", remarks = " + remarks + "]";
    }
}
