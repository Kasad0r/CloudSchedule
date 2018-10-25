package org.ssunion.cloudschedule.telegram.adminbot.domain;

import javax.persistence.*;

/**
 * @author kasad0r
 */
@Entity
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "code")
    private String uuid;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "whogenerate")
    private Admin generatedBy;
    private boolean activated = false;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "whoactivate")
    private Admin activatedBy;

    public ActivationCode() {

    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Admin getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(Admin generatedBy) {
        this.generatedBy = generatedBy;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Admin getActivatedBy() {
        return activatedBy;
    }

    public void setActivatedBy(Admin activatedBy) {
        this.activatedBy = activatedBy;
    }
}
