package com.alexmalotky.key;

import com.alexmalotky.entity.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class LogPK implements Serializable {
    protected User user;
    protected Machine machine;
    protected Date date;

    public LogPK() {}

    public LogPK(User user, Machine machine, Date date) {
        this.user = user;
        this.machine = machine;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogPK)) return false;
        LogPK logPK = (LogPK) o;
        return user.equals(logPK.user) &&
                machine.equals(logPK.machine) &&
                date.equals(logPK.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, machine, date);
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
}
