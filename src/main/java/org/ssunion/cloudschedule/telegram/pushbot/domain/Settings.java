package org.ssunion.cloudschedule.telegram.pushbot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Settings {
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
