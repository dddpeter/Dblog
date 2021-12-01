package club.javafan.blog.client;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonToolClient {
    final  String OSCHINA_RSS_URL = "https://www.oschina.net/news/rss";
    final  String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36 Edg/96.0.1054.34";
    public String getOsChinaRss() throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(OSCHINA_RSS_URL);
        httpGet.setHeader("User-Agent",USER_AGENT);
        HttpResponse response = client.execute(httpGet);
        if(response.getStatusLine().getStatusCode() == 200){
            return EntityUtils.toString(response.getEntity());
        }
        else{
            throw new Exception("fail");
        }

    }
}
