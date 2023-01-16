package club.javafan.blog.common.qquserinfo;


import club.javafan.blog.domain.vo.QQUserInfoVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author dddpeter
 * @date 2020/3/4
 */
@Service("qqUserInfo")
public class QQUserInfo {
    /**
     * 请求前缀
     */
    private static final String REQUEST_URL = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=";
    @Resource
    ObjectMapper objectMapper;

    /**
     * 获取qq的个人信息
     *
     * @param qq 号
     * @return QQUserInfoVO qq个人信息
     */

    public QQUserInfoVO getQQUserInfo(String qq) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet httpGet = new HttpGet(REQUEST_URL + qq);
            QQUserInfoVO qqUserInfoVO = new QQUserInfoVO();
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            if (Objects.isNull(responseEntity)) {
                return null;
            }
            String userData = EntityUtils.toString(responseEntity, "gbk").trim();
            if (StringUtils.isNotEmpty(userData)) {
                int from = userData.indexOf("(");
                int to = userData.lastIndexOf(")");
                String json = userData.substring(from + 1, to);
                JsonNode qqJsonNode = objectMapper.readTree(json).get(qq);
                //String headUrl =  qqJsonNode.get(0).textValue();
                // https://q.qlogo.cn/g?b=qq&nk=11111&s=100
                qqUserInfoVO.setHeadImage("https://q.qlogo.cn/g?b=qq&nk="+qq+"&s=100");
                qqUserInfoVO.setNickName(qqJsonNode.get(6).textValue());
                qqUserInfoVO.setQNumber(qq);
                qqUserInfoVO.setQEmail(qq + "@qq.com");
                return qqUserInfoVO;
            }
        } finally {
            if (Objects.nonNull(httpClient)) {
                httpClient.close();
            }
        }
        return null;
    }

}
