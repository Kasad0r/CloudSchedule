package org.ssunion.cloudschedule.telegram.adminbot.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kasad0r
 */
@Entity
@Table(name = "botadmins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column()
    private long userToken;
    @Column(name = "usernames")
    private String username;
    @Column(name = "first")
    private String firstname;
    @Column(name = "last")
    private String lastname;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "tiggers")
    @Enumerated(EnumType.STRING)
    private AdminTrigger adminTrigger;
    private boolean activated = false;
    @ElementCollection(fetch = FetchType.EAGER, targetClass = AdminMessage.class)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AdminMessage> adminMessage = new ArrayList<>();
    private long currentMessage;
    public Admin() {

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AdminTrigger getAdminTrigger() {
        return adminTrigger;
    }

    public void setAdminTrigger(AdminTrigger adminTrigger) {
        this.adminTrigger = adminTrigger;
    }

    public List<AdminMessage> getAdminMessage() {
        return adminMessage;
    }

    public void setAdminMessage(List<AdminMessage> adminMessage) {
        this.adminMessage = adminMessage;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userToken=" + userToken +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", adminTrigger=" + adminTrigger +
                '}';
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public long getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(long currentMessage) {
        this.currentMessage = currentMessage;
    }
}
