package org.ssunion.cloudschedule.domain.base;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author kasad0r
 */
@Entity
@Table(name = "day_in_week")

public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dayName;
    @ElementCollection(targetClass = Lesson.class, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lesson> lessons;

    public Day() {

    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
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
        Day day = (Day) o;
        return Objects.equals(dayName, day.dayName) &&
                Objects.equals(lessons, day.lessons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayName, lessons);
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", dayName='" + dayName + '\'' +
                ", lessons=" + lessons +
                '}';
    }

    public String getScheduleForTelegram() {
        StringBuilder result = new StringBuilder();
        result.append("<b>").append(this.getDayName()).append("</b>");
        for (Lesson l : this.getLessons()) {
            result.append("\n");
            result.append("<i>").append(l.getStartTime()).append("</i>\n");
            if (l.getUpperWeek().getName() != null && !l.getUpperWeek().getName().isEmpty()) {
                result.append("<b>").append(l.getUpperWeek().getName()).append("    ")
                        .append(l.getUpperWeek().getTeacher()).append("</b>\n");
            } else {
                result.append("<b> Нижняя неделя </b>⬇️");
            }
            if (l.getDownWeek().getName() != null && !l.getDownWeek().getName().isEmpty()) {
                result.append("<b>").append(l.getDownWeek().getName()).append("    ")
                        .append(l.getDownWeek().getTeacher()).append("</b>\n");
            } else {
                result.append("<b> Верхняя неделя ⬆️</b>");
            }
        }
        result.append("\n");
        return result.toString();
    }
}
