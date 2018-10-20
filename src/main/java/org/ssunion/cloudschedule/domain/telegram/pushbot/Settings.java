package org.ssunion.cloudschedule.domain.telegram.pushbot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Settings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String timeToSendSchedule;
    private String selectedGroup;
    private boolean adminNotice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeToSendSchedule() {
        return timeToSendSchedule;
    }

    public void setTimeToSendSchedule(String timeToSendSchedule) {
        this.timeToSendSchedule = timeToSendSchedule;
    }

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public boolean isAdminNotice() {
        return adminNotice;
    }

    public void setAdminNotice(boolean adminNotice) {
        this.adminNotice = adminNotice;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", timeToSendSchedule='" + timeToSendSchedule + '\'' +
                ", selectedGroup='" + selectedGroup + '\'' +
                ", adminNotice='" + adminNotice + '\'' +
                '}';
    }
}
