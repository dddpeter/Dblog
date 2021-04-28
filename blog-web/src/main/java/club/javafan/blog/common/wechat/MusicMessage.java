package club.javafan.blog.common.wechat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhoumin
 * @create 2018-07-11 10:19
 */
@Setter
@Getter
public class MusicMessage extends BaseWechatMessage {
    /**
     * 音乐
     */
    private Music Music;

}
