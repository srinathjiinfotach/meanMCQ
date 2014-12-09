package meanMCQ.dto;

import meanMCQ.domain.Question;

/**
 * Created by red on 12/8/14.
 */
public class QuestionAnswerDto {
    public Question question;

    public QuestionAnswerDto(Question question) {
        this.question = question;
    }
}
