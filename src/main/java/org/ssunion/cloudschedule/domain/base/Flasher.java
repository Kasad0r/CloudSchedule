package org.ssunion.cloudschedule.domain.base;


import javax.persistence.*;
import java.util.Objects;

/**
 * @author kasad0r
 */
@Entity
@Table(name = "flashers")
public class Flasher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Flasher() {

    }

    public Flasher(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Flasher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flasher flasher = (Flasher) o;
        return Objects.equals(name, flasher.name) &&
                Objects.equals(teacher, flasher.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teacher);
    }
}
