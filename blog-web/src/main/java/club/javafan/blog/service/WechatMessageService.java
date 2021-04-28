package club.javafan.blog.service;

import javax.servlet.http.HttpServletRequest;

public interface WechatMessageService {
    /**
     * 微信公众号处理
     * @param request
     * @return
     */
    String newMessageRequest(HttpServletRequest request);

}
