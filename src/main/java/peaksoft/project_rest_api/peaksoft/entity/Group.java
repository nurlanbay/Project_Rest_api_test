package peaksoft.project_rest_api.peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "groups")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "group_name",nullable = false)
    String groupName;

    @JoinColumn(name = "date_of_start",nullable = false)
    LocalDate dateOfStart;

    @JoinColumn(name = "date_of_finish",nullable = false)
    LocalDate dateOfFinish;

    @OneToMany(mappedBy = "group",cascade = {REMOVE,MERGE,PERSIST,REFRESH},fetch = FetchType.LAZY)
    @ToString.Exclude
    Set<Student> students;

    @JsonIgnore
    @ManyToMany
    @ToString.Exclude
    Set<Course> course = new HashSet<>();

    public void addCourse(Course course) {
        this.course.add(course);
    }
}