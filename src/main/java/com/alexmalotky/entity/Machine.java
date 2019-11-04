package com.alexmalotky.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Machine")
@Table(name = "machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String name;
    private String objects;
    private String notes;

    @Column(name="public")
    private Boolean viewable;

    //@OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    //private Set<Machine>

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

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects;
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

    @Override
    public String toString() {
        return "'Machine'{" +
                ", name:'" + name + '\'' +
                ", objects:" + objects +
                ", notes:'" + notes + '\'' +
                '}';
    }
}
