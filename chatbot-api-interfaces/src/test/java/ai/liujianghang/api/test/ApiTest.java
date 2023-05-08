package ai.liujianghang.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建请求方法对象
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/48411118851818/topics?scope=unanswered_questions&count=20");
        // 添加头信息
        get.addHeader("cookie", "个人cookie信息");
        get.addHeader("Content-Type", "application/json;charset=utf8");
        // 发送请求，返回HttpResponse响应对象
        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer_question() throws IOException {
        // 创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 针对问题 创建一个Request对象
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/412884248251548/answer");
        post.addHeader("cookie", "个人cookie信息");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"让我来为你解决这个问题！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";
        // 回答的实体类
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        // 执行
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://api.openai.com/vi/completions");
        post.addHeader("Content-Type", "application/json;charset=utf8");
        post.addHeader("Authorization","Bearer YourKey");
        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        // 回答的实体类
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        // 执行
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
