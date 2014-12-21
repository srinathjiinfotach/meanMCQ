package meanMCQ.dto;

import java.util.Collection;
import java.util.Set;

/**
 * Created by red on 12/7/14.
 */
public class AnswerDto {
    public Collection<Long> choiceIds;

    public AnswerDto(Collection<Long> choiceIds) {
        this.choiceIds = choiceIds;
    }
}
