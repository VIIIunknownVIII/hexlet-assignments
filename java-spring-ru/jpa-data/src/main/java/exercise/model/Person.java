package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Table(name = "people")
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
}
// END