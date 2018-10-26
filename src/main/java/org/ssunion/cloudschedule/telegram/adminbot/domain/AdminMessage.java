package org.ssunion.cloudschedule.telegram.adminbot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author kasad0r
 */
@Entity
public class AdminMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    private String time;

    private boolean ready = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public AdminMessage setText(String text) {
        this.text = text;
        return this;
    }

    public String getTime() {
        return time;
    }

    public AdminMessage setTime(String time) {
        this.time = time;
        return this;
    }


    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public String toString() {
        return "AdminMessage{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", ready=" + ready +
                '}';
    }
}
