package meanMCQ.dto;

import java.util.Set;

/**
 * Created by red on 12/7/14.
 */
public class AnswerDto {
    public Set<Long> choiceIds;

    public AnswerDto(Set<Long> choiceIds) {
        this.choiceIds = choiceIds;
    }
}
