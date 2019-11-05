package com.alexmalotky.entity;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity(name = "Machine")
@Table(name = "machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @Column(name="title")
    private String name;

    private String settings;
    private String notes;

    @Column(name="public")
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

    public Machine(JSONObject object) {
        if(object.isNull("id"))
            id = null;
        else
            id = object.getInt("id");
        name = object.getString("name");
        settings = object.getJSONArray("settings").toString();
        notes = object.getString("notes");
        viewable = object.getBoolean("public");
        logs = buildLogs(object.getJSONArray("logs"));
    }

    @Override
    public String toString() {
        return "'Machine'{" + toJson() + "}";
    }

    public String toJson() {
        String output = "{ \"id\":" + id +
                "\"name\":\"" + name + '\"' +
                ", \"settings\":" + settings +
                ", \"notes\":\"" + notes + '\"' +
                ", \"public\":" + viewable +
                ", \"logs\":[ ";

        for (Iterator<Log> it = logs.iterator(); it.hasNext(); ) {
            Log l = it.next();
            output = output.concat( l.toJson() );
            if(it.hasNext())
                output = output.concat(", ");
        }

        output += " ]}";

        return output;
    }

    public int getId() {
        if(id == null)
            return -1;
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

    private static Set<Log> buildLogs(JSONArray list) {
        Set<Log> output = new HashSet<>();
        for(Object object: list)
            output.add(new Log((JSONObject)object));

        return output;
    }
}
