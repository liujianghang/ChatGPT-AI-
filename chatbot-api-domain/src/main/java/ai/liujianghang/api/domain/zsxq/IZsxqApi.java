package ai.liujianghang.api.domain.zsxq;

import ai.liujianghang.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import org.slf4j.Logger;

import java.io.IOException;

public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionTopicId(String groupId, String cookie) throws IOException;
    boolean answer(String groupId,String cookie,String topicId,String text, boolean silenced) throws IOException;
}
