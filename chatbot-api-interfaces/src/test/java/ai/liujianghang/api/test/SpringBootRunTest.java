package ai.liujianghang.api.test;

import ai.liujianghang.api.ApiApplication;
import ai.liujianghang.api.domain.ai.IOpenAI;
import ai.liujianghang.api.domain.zsxq.IZsxqApi;


import ai.liujianghang.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import ai.liujianghang.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class SpringBootRunTest {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);
    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Autowired
    private IZsxqApi zsxqApi;
    @Autowired
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionTopicId(groupId, cookie);
        logger.info("测试结果:{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        if (topics != null) {
            for (Topics topic : topics) {
                String topicId = topic.getTopic_id();
                String text = topic.getQuestion().getText();
                logger.info("topicID:{} text：{}", topicId, text);
                // 回答问题
                zsxqApi.answer(groupId, cookie, topicId, text, false);
            }
        }
    }

    @Test
    public void test_openAi() throws IOException {
        String response = openAI.doChatGPT("帮我写一个冒泡排序");
        logger.info("测试结果:{}", response);
    }
}
