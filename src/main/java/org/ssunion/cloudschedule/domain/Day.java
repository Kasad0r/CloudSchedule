package org.ssunion.cloudschedule.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "day_in_week")

public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dayName;
    @ElementCollection(targetClass = Lesson.class, fetch = FetchType.EAGER)
    @OneToMany(cascade=CascadeType.ALL)
    private List<Lesson> lessons;

    public Day(){

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
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", dayName='" + dayName + '\'' +
                ", lessons=" + lessons +
                '}';
    }
}
