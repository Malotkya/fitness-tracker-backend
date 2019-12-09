package com.alexmalotky.entity;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Machine")
@Table(name = "machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name="title")
    private String name;

    private String settings;
    private String notes;
    private Boolean viewable;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    private Set<Log> logs;

    public Machine() {
        name = "";
        settings = "";
        notes = "";
        viewable = false;
        logs = new HashSet<>();
    }

    public Machine(JSONObject object, User user) {
        if(object.isNull("id"))
            id = -1;
        else
            id = object.getInt("id");
        name = object.getString("name");
        settings = object.getJSONArray("settings").toString();
        notes = object.getString("notes");
        viewable = object.getBoolean("public");
        logs = buildLogs(object.getJSONArray("logs"), user, this);
    }

    @Override
    public String toString() {
        return "'Machine'{" + toJson(null) + "}";
    }

    public String toJson(Integer userId) {
        String output = "{ \"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"settings\":" + settings +
                ", \"notes\":\"" + notes + '\"' +
                ", \"public\":" + viewable +
                ", \"logs\":[ ";

        boolean first = true;
        for (Log l : logs )
            if(userId == null || userId == l.getUser().getId()) {
                if(first) {
                    first = false;
                } else {
                    output = output.concat(", ");
                }
                output = output.concat( l.toJson() );
        }

        output += " ]}";

        return output;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getViewable() {
        return viewable;
    }

    public void setViewable(Boolean viewable) {
        this.viewable = viewable;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    private static Set<Log> buildLogs(JSONArray list, User user, Machine machine) {
        Set<Log> output = new HashSet<>();
        for(Object object: list)
            output.add(new Log((JSONObject)object, user, machine));

        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Machine)) return false;
        Machine machine = (Machine) o;
        return Objects.equals(name, machine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
