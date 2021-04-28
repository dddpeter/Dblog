package club.javafan.blog.web.controller.wechat;

import club.javafan.blog.common.wechat.WechatUtil;
import club.javafan.blog.service.WechatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Resource
    WechatMessageService messageService;

    @RequestMapping(value = "index" ,method = {RequestMethod.GET})
    public void checkWeXin(HttpServletRequest request, HttpServletResponse response) {
        log.info("-------------------------微信接口校验--------");
        PrintWriter out = null;
        try {
            //设置编码，不然接收到的消息乱码
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String signature = request.getParameter("signature");//微信加密签名
            String timestamp = request.getParameter("timestamp");//时间戳
            String nonce = request.getParameter("nonce");//随机数
            String echostr = request.getParameter("echostr");//随机字符串
            out = response.getWriter();
            if (WechatUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            log.error("微信校验失败", e);
        } finally {
            out.close();
        }
    }
    @RequestMapping(value = "index",method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("后端服务错误：", e);
        }
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
//        String respMessage = weixinPost(request);
        String respMessage = messageService.newMessageRequest(request);

        // 响应消息
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(respMessage);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            out.close();
        }
    }
}
