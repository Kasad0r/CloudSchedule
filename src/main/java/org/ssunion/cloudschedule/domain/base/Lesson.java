package org.ssunion.cloudschedule.domain.base;


import javax.persistence.*;
import java.util.Objects;

/**
 * @author kasad0r
 */
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upperfetch_id")
    private Flasher upperWeek;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "downfetch_id")
    private Flasher downWeek;
    private String startTime;

    public Lesson() {

    }

    public Flasher getUpperWeek() {
        return upperWeek;
    }

    public void setUpperWeek(Flasher upperWeek) {
        this.upperWeek = upperWeek;
    }

    public Flasher getDownWeek() {
        return downWeek;
    }

    public void setDownWeek(Flasher downWeek) {
        this.downWeek = downWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", upperWeek=" + upperWeek +
                ", downWeek=" + downWeek +
                ", startTime='" + startTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lesson lesson = (Lesson) o;
        return Objects.equals(upperWeek, lesson.upperWeek) &&
                Objects.equals(downWeek, lesson.downWeek) &&
                Objects.equals(startTime, lesson.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upperWeek, downWeek, startTime);
    }
}
