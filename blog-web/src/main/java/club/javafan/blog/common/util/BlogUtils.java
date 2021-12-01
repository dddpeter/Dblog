package club.javafan.blog.common.util;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.misc.Extension;

import java.net.URI;
import java.util.Arrays;

/**
 * @author dddpeter(博客)
 * @date 2019/12/28 20:52
 * @desc 博客工具类
 */
public class BlogUtils {

    /**
     * 获取URI
     *
     * @param uri
     * @return
     * @throws Exception
     */
    public static URI getHost(URI uri) throws Exception {
        URI effectiveURI = new URI(uri.getScheme(), uri.getUserInfo()
                , uri.getHost(), uri.getPort(), null, null, null);
        return effectiveURI;
    }

    /**
     *
     *
     */

    public static String markDownToHtml(String content){
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[] { TablesExtension.create()}));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(content);
        String html = renderer.render(document);
        return html;
    }

}