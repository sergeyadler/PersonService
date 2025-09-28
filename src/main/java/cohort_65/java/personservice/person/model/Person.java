package cohort_65.java.personservice.person.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(schema = "public", name="people")

public class Person {
    @Id
    Integer id;
    @Setter
    String name;
    LocalDate birthDate;
    @Setter
    @Embedded
    Address address;
}
