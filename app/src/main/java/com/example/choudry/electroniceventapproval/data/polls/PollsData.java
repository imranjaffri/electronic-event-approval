package com.example.choudry.electroniceventapproval.data.polls;

public class PollsData
{
    private String second_nominee;

    private String second_nominee_votes;

    private String first_nominee;

    private String poll_name;

    private String first_nominee_votes;

    private String poll_id;

    public String getSecond_nominee ()
    {
        return second_nominee;
    }

    public void setSecond_nominee (String second_nominee)
    {
        this.second_nominee = second_nominee;
    }

    public String getSecond_nominee_votes ()
    {
        return second_nominee_votes;
    }

    public void setSecond_nominee_votes (String second_nominee_votes)
    {
        this.second_nominee_votes = second_nominee_votes;
    }

    public String getFirst_nominee ()
    {
        return first_nominee;
    }

    public void setFirst_nominee (String first_nominee)
    {
        this.first_nominee = first_nominee;
    }

    public String getPoll_name ()
    {
        return poll_name;
    }

    public void setPoll_name (String poll_name)
    {
        this.poll_name = poll_name;
    }

    public String getFirst_nominee_votes ()
    {
        return first_nominee_votes;
    }

    public void setFirst_nominee_votes (String first_nominee_votes)
    {
        this.first_nominee_votes = first_nominee_votes;
    }

    public String getPoll_id ()
    {
        return poll_id;
    }

    public void setPoll_id (String poll_id)
    {
        this.poll_id = poll_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [second_nominee = "+second_nominee+", second_nominee_votes = "+second_nominee_votes+", first_nominee = "+first_nominee+", poll_name = "+poll_name+", first_nominee_votes = "+first_nominee_votes+", poll_id = "+poll_id+"]";
    }
}