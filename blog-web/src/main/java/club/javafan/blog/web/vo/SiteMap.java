package club.javafan.blog.web.vo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author lijinde
 * @create 2022/07/01 08:19
 **/
public class SiteMap {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ZoneId defaultZoneId = ZoneId.systemDefault();

    private String loc;
    /**
     * 最后更新时间 yyyy-MM-dd
     */
    private Date lastmod;
    /**
     * 更新速度 always hourly daily weekly monthly yearly never
     */
    private String changefreq;
    /**
     * 权重 1.0 0.9 0.8
     */
    private String priority;


    @Override
    /** 重写 toString 适应xml格式 */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<url>");
        sb.append("<loc>" + loc + "</loc>");
        sb.append("<lastmod>" + sdf.format(lastmod) + "</lastmod>");
        sb.append("<changefreq>" + changefreq + "</changefreq>");
        sb.append("<priority>" + priority + "</priority>");
        sb.append("</url>");
        return sb.toString();
    }

    public SiteMap() {
    }

    public SiteMap(String loc) {
        this.loc = loc;
        this.lastmod = Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
        this.changefreq = "always";
        this.priority = "1.0";
    }

    public SiteMap(String loc, Date lastModified, String changefreq, String priority) {
        this.loc = loc;
        this.lastmod = lastModified;
        this.changefreq = changefreq;
        this.priority = priority;
    }
}
