package org.ssunion.cloudschedule.domain.base;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author kasad0r
 */
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String groupName;
    @ElementCollection(targetClass = Day.class, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) &&
                Objects.equals(weekSchedule, group.weekSchedule) &&
                Objects.equals(lastUpdate, group.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, weekSchedule, lastUpdate);
    }

    @Override
    public String toString() {
        return null;
    }

    public List<String> getScheduleForTelegram() {
        StringBuilder result = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (Day d : this.weekSchedule
        ) {
            result.append("<b>").append(d.getDayName()).append("</b>").append("\n");
            for (Lesson l : d.getLessons()) {
                result.append("<i>").append(l.getStartTime()).append("</i>\n");
                if (l.getUpperWeek().getName() != null && !l.getUpperWeek().getName().isEmpty()) {
                    result.append("<b>").append(l.getUpperWeek().getName()).append("    ")
                            .append(l.getUpperWeek().getTeacher()).append("</b>\n");
                }
                if (l.getDownWeek().getName() != null && !l.getDownWeek().getName().isEmpty()) {
                    result.append("<b>").append(l.getUpperWeek().getName()).append("    ")
                            .append(l.getUpperWeek().getTeacher()).append("</b>\n");
                }
                result.append("****************\n");
            }
            list.add(result.toString());
            result = new StringBuilder();
        }
        return list;
    }
}
