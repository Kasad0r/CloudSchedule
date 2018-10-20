package org.ssunion.cloudschedule.domain.base;


import javax.persistence.*;
import java.util.List;

@Entity@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String groupName;
    @ElementCollection(targetClass = Day.class, fetch = FetchType.EAGER)
    @OneToMany(cascade=CascadeType.ALL)
    private List<Day> weekSchedule;
    private String lastUpdate;

    public Group() {

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Day> getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(List<Day> weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", weekSchedule=" + weekSchedule +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
