package club.javafan.blog.web;

import com.qcloud.scf.runtime.AbstractSpringHandler;

public class BlogHandler extends AbstractSpringHandler {
    @Override
    public void startApp() {
        // 修改为项目的入口主函数
        BlogApplication.main(new String[]{""});
    }
}
