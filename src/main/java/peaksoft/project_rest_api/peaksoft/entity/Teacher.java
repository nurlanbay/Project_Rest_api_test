package peaksoft.project_rest_api.peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @Column(name = "firs_name", nullable = false)
     String firstName;

    @Column(name = "last_name", nullable = false)
     String lastName;

    @Column(name = "email", nullable = false)
    String email;

    @JsonIgnore
    @OneToOne(cascade = ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    Course course;

}
