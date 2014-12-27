package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Choice {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String content;
    @JsonIgnore
    @NotNull
    private boolean answer;

    @JsonIgnore
    @ManyToOne
    private Question question;

    public Choice() {
    }

    public Choice(String content, boolean answer) {
        this.content = content;
        this.answer = answer;
    }

    public boolean isAnswer() {
        return answer;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
