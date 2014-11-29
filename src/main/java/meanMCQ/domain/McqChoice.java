package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class McqChoice {
    @Id
    @GeneratedValue
    private Long id;

    private String content;
    @JsonIgnore
    private boolean answer;

    @JsonIgnore
    @ManyToOne
    private McqQuestion mcqQuestion;

	public McqChoice() {
    }

    public McqChoice(String content, boolean answer) {
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

	public void setMcqQuestion(McqQuestion mcqQuestion) {
		this.mcqQuestion = mcqQuestion;
	}
    
}
