package club.javafan.blog.client;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.XMLBody;

public interface CommonToolClient {
    @Get(value = "https://www.oschina.net/news/rss",dataType = "xml")
    String getOsChinaRss();
}
