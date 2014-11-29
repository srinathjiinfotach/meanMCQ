package meanMCQ.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class McqQuestion {
	@Id
	@GeneratedValue
	private Long id;

	public String content;

    @OneToMany(mappedBy = "mcqQuestion", cascade = CascadeType.ALL)
    public Set<McqChoice> mcqChoices = new HashSet<McqChoice>();

	public McqQuestion() {
	}

    public McqQuestion(String content) {
        this.content = content;
    }

    public String getContent() {
		return content;
	}

	public Set<McqChoice> getMcqChoices() {
		return mcqChoices;
	}

    public void setMcqChoices(Set<McqChoice> mcqChoices) {
        this.mcqChoices = mcqChoices;
    }

    public Long getId() {
		return id;
	}
}
