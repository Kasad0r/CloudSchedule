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
    private String UUID;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "whogenerate")
    private Admin generatedBy;
    private boolean activated;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "whoactivate")
    private Admin activatedBy;

    public ActivationCode() {

    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
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
