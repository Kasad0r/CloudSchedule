package org.ssunion.cloudschedule.telegram.adminbot.domain;

import javax.persistence.*;

/**
 * @author kasad0r
 */
@Entity
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "code")
    private String uuid;
    private long generatedBy;
    private boolean activated = false;
    private long activatedBy;

    public ActivationCode() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(long generatedBy) {
        this.generatedBy = generatedBy;
    }

    public long getActivatedBy() {
        return activatedBy;
    }

    public void setActivatedBy(long activatedBy) {
        this.activatedBy = activatedBy;
    }
}
