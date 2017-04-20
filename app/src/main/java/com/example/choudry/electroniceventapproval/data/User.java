package com.example.choudry.electroniceventapproval.data;

/**
 * Created by maqsood on 15/04/2017.
 */

public class User {

    private String email;

    private String user_id;

    private String type;

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [email = "+email+", user_id = "+user_id+", type = "+type+"]";
    }
}
