package club.javafan.blog.web.controller.common;

import club.javafan.blog.common.util.PageQueryUtil;
import club.javafan.blog.domain.Blog;
import club.javafan.blog.domain.BlogCategory;
import club.javafan.blog.repository.BlogCategoryMapper;
import club.javafan.blog.repository.BlogMapper;
import club.javafan.blog.web.vo.SiteMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author lijinde
 * @create 2022/07/01 08:23
 **/
@Controller
public class SiteMapController {

    @Resource
    private BlogMapper blogMapper;
    @Resource
    BlogCategoryMapper blogCategoryMapper;
    public static String BEGIN_DOC = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";

    final static String END_DOC = "</urlset>";
    final static String CHANGEFREQ_DAILY = "daily";
    public final static String CHANGEFREQ_ALWAYS = "always";
    final static String SITE_BASE_URL = "https://blog.dddpeter.top/";

    @GetMapping(value = "/sitemap.xml", produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public void getSiteMap(HttpServletResponse response) throws IOException {
        StringBuffer sb = new StringBuffer();
        //拼接开始部分
        sb.append(BEGIN_DOC);
        //拼接网站首页地址
        sb.append(new SiteMap(SITE_BASE_URL));
        // index resume page
        sb.append(new SiteMap(SITE_BASE_URL + "about"));
        // index categry pages
        List<BlogCategory> categries = blogCategoryMapper.findCategoryList(null);
        for(BlogCategory category:categries){
            sb.append(new SiteMap(SITE_BASE_URL + "category/" + URLEncoder.encode(category.getCategoryName(), Charset.forName("UTF-8")),category.getCreateTime(), CHANGEFREQ_DAILY, "0.8"));
        }
        // 所有博客的索引
        List<Blog> list=  blogMapper.findBlogList(new PageQueryUtil());
        for (Blog blog : list) {
            sb.append(new SiteMap(SITE_BASE_URL + "blog/" + blog.getBlogId() ,blog.getCreateTime(), CHANGEFREQ_DAILY, "0.8"));
        }
        //拼接结尾
        sb.append(END_DOC);
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        Writer writer = response.getWriter();

        writer.append(sb.toString());
    }
}
