package com.example.choudry.electroniceventapproval.data.polls;

import android.os.Parcel;
import android.os.Parcelable;

public class PollsData implements Parcelable
{
    private String second_nominee;

    private String second_nominee_votes;

    private String first_nominee;

    private String poll_name;

    private String first_nominee_votes;

    private String poll_id;

    protected PollsData(Parcel in) {
        second_nominee = in.readString();
        second_nominee_votes = in.readString();
        first_nominee = in.readString();
        poll_name = in.readString();
        first_nominee_votes = in.readString();
        poll_id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(second_nominee);
        dest.writeString(second_nominee_votes);
        dest.writeString(first_nominee);
        dest.writeString(poll_name);
        dest.writeString(first_nominee_votes);
        dest.writeString(poll_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PollsData> CREATOR = new Creator<PollsData>() {
        @Override
        public PollsData createFromParcel(Parcel in) {
            return new PollsData(in);
        }

        @Override
        public PollsData[] newArray(int size) {
            return new PollsData[size];
        }
    };

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