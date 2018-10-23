package org.ssunion.cloudschedule.telegram.adminbot.domain;

import javax.persistence.*;

/**
 * @author kasad0r
 */
@Entity
@Table(name = "bot_admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column()
    private long userToken;
    @Column(name = "user_name")
    private String username;
    @Column(name = "first")
    private String firstname;
    @Column(name = "last")
    private String lastname;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "invited_by")
    private long invitedBy;
    @Column(name = "tiggers")
    @Enumerated(EnumType.STRING)
    private AdminTrigger adminTrigger;

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

    public long getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(long invitedBy) {
        this.invitedBy = invitedBy;
    }

    public AdminTrigger getAdminTrigger() {
        return adminTrigger;
    }

    public void setAdminTrigger(AdminTrigger adminTrigger) {
        this.adminTrigger = adminTrigger;
    }
}
