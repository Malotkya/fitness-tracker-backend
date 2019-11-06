package com.alexmalotky.entity;

import com.alexmalotky.key.LogPK;
import jdk.jfr.Timestamp;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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
    @Column(name="entry", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String value;

    public Log() {
        date = new Date();
        value = "";
    }

    public Log(JSONObject object) {
        date = new Date(object.getLong("date"));
        value = object.getJSONObject("value").toString();
    }

    @Override
    public String toString() {
        return "'Log'"+ toJson();
    }

    public String toJson() {
        return "{ \"date\":" + date.getTime() +
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        Log log = (Log) o;
        return value.equals(log.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
