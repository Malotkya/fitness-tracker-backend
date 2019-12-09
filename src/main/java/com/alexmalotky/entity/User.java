package com.alexmalotky.entity;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @Column(name="user_name")
    private String userName;

    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String email;

    @Column(name="static")
    private String text;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="log",
            joinColumns =        {@JoinColumn(name="user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="machine_id", nullable = false, updatable = false)})
    private Set<Machine> machines;

    public User() {
        id = 0;
        userName = "";
        password = "";
        firstName = "";
        lastName = "";
        email = "";
        text = "";
        machines = new HashSet<>();
    }

    public User(JSONObject object){
        if(object.isNull("id"))
            id = null;
        else
            id = object.getInt("id");
        userName = object.getString("userName");
        firstName = object.getString("firstName");
        lastName = object.getString("lastName");
        email = object.getString("email");
        text = "";
        if( !object.isNull("Activities"))
            machines = buildActivities(object.getJSONArray("Activities"), this);
    }

    public User(String userName, String password, String firstName, String lastName, String email) {
        this.id = 0;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.text = "";
        this.machines = new HashSet<>();
    }

    public int getId() {
        if(id == null)
            return -1;
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Machine> getMachines() {
        return machines;
    }

    public void setMachines(Set<Machine> machines){this.machines = machines;}

    @Override
    public String toString() {
        return "'User':" + toJson();
    }

    public String toJson() {
        String output =   "{\"id\":" + id +
                ", \"userName\":\"" + userName + '\"' +
                ", \"firstName\":\"" + firstName + '\"' +
                ", \"lastName\":\"" + lastName + '\"' +
                ", \"email\":\"" + email + '\"';

        if( !text.equals("") )
            output += ", " + text;

        output += ", \"Activities\": [";
        for (Iterator<Machine> it = machines.iterator(); it.hasNext(); ) {
            Machine m = it.next();
            output = output.concat(m.toJson(id));
            if(it.hasNext())
                output = output.concat(", ");
        }

        output += "]}";

        return output;
    }

    private static Set<Machine> buildActivities(JSONArray list, User user) {
        Set<Machine> output = new HashSet<>();
        for(Object object: list)
            output.add(new Machine( (JSONObject)object, user));

        return output;
    }

    public void set(User rhs) {
        userName = rhs.userName;
        firstName = rhs.firstName;
        lastName = rhs.lastName;
        email = rhs.email;
        text = rhs.text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, firstName, lastName, email);
    }
}