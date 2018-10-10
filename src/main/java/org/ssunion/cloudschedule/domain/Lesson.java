package org.ssunion.cloudschedule.domain;


import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "upperfetch_id")
    private Flasher upperWeek;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
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
}
