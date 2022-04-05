package peaksoft.project_rest_api.peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "course_name", nullable = false)
    String courseName;

    LocalDate finishedDate;

    @JsonIgnore
    @ManyToOne(cascade = {REFRESH,DETACH,PERSIST,MERGE})
    Company company;

    @ManyToMany(mappedBy = "course", cascade = {PERSIST, MERGE, REMOVE})
    List<Group> group = new ArrayList<>();

    @OneToOne(mappedBy = "course",cascade ={PERSIST, MERGE, REMOVE} )
    Teacher teacher;
}
