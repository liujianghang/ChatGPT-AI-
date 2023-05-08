package ai.liujianghang.api.domain.zsxq.model.req;

/**
 * 请求问答接口信息
 * 数据就是reqData 仅仅就是包装了一下
 */
public class AnswerReq {
    private ReqData reqData;

    public AnswerReq(ReqData reqData) {
        this.reqData = reqData;
    }

    public ReqData getReqData() {
        return reqData;
    }

    public void setReqData(ReqData reqData) {
        this.reqData = reqData;
    }
}
