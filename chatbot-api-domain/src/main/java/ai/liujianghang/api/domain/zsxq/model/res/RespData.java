package ai.liujianghang.api.domain.zsxq.model.res;

import ai.liujianghang.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * 请求问题返回的结果
 */
public class RespData {
    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }
}
