package com.alexmalotky.entity;

import com.alexmalotky.key.LogPK;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Log")
@Table(name = "log")
@IdClass(LogPK.class)
public class Log {

    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name="machine_id")
    private Machine machine;

    @Id
    @Column(name="entry")
    private String date;

    private String value;

    public Log() {
        date = "";
        value = "";
    }

    public Log(JSONObject object) {
        date = object.getString("date");
        value = object.getJSONObject("value").toString();
    }

    @Override
    public String toString() {
        return "'Log'"+ toJson();
    }

    public String toJson() {
        return "{ \"date\":\"" + date + '\"' +
                ", \"value\":" + value + '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
