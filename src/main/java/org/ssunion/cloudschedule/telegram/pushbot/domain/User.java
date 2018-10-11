package org.ssunion.cloudschedule.telegram.pushbot.domain;

import javax.persistence.*;

@Entity
@Table(name = "telegram_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "token")
    private long userToken;
    @Column(name = "user_name")
    private String username;
    @Column(name = "first")
    private String firstname;
    @Column(name = "last")
    private String lastname;
    @Column(name = "triggers")
    @Enumerated(EnumType.STRING)
    private Trigger trigger = Trigger.NONE;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "settings_id")
    private Settings settings;

    public User() {
    }

    public User(long id, String username, String firstname, String lastname, Settings settings) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.settings = settings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUserToken() {
        return userToken;
    }

    public void setUserToken(long userToken) {
        this.userToken = userToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userToken=" + userToken +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", trigger=" + trigger +
                ", settings=" + settings +
                '}';
    }
}