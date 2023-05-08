package ai.liujianghang.api.domain.zsxq.model.res;

/**
 * 回答问题回来后后 返回的实体类 告诉是否成功就行了
 */
public class AnswerRes {
    private boolean succeeded;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }
}
