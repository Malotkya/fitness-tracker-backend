package com.alexmalotky.entity;

import com.alexmalotky.key.LogPK;
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
    @Column(name="entry") //, columnDefinition="DATETIME")
    //@Temporal(TemporalType.TIMESTAMP)
    private long date;

    private String value;

    public Log() {
        date = new Date().getTime();
        value = "";
    }

    public Log(JSONObject object, User u, Machine m) {
        user = u;
        machine = m;
        date = object.getLong("date");
        value = object.getJSONObject("value").toString();
    }

    @Override
    public String toString() {
        String output = "'Log'"+ toJson() + " UserID: " ;
        if( user == null )
            output += "null";
        else
            output += user.getId();

        output += " MachineID: ";

        if( machine == null )
            output += "null";
        else
            output += machine.getId();

        return output;
    }

    public String toJson() {
        return "{ \"date\":" + date +
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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
        boolean user, machine, date;

        if(this.user == null || log.user == null)
            user = (this.user == null) && (log.user == null);
        else
            user = this.user.equals(log.user);

        if(this.machine == null || log.machine == null)
            machine = (this.machine == null) && (log.machine == null);
        else
            machine = this.machine.equals(log.machine);

        date = this.date == log.date;

        return user && machine && date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, value);
    }
}
