package club.javafan.blog.common.wechat;

public class TextMessage  extends  BaseWechatMessage{
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }

}
