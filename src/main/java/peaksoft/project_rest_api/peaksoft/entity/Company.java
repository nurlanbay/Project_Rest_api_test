package peaksoft.project_rest_api.peaksoft.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "company_name", nullable = false)
    String companyName;

    @Column(name = "located_country")
    String locatedCountry;

    @OneToMany(mappedBy = "company",
            cascade = {PERSIST,REMOVE,MERGE,DETACH},
            fetch = FetchType.EAGER)

    @ToString.Exclude
    Set<Course> course;

    public void addCourse(Course course) {
        this.course.add(course);
    }

}
