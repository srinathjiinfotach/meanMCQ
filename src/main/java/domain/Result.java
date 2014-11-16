package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Result {
    // local attributes
    @Id
    @GeneratedValue
    private Long id;

    private double marks;

    // foreign attributes
    @JsonIgnore
    @ManyToOne
    private PupilAccount pupil;

    @JsonIgnore
    @ManyToOne
    private Test test;

    // getter methods
    public Long getId() {
        return id;
    }

    // constructors
    public Result() {
    }

}
