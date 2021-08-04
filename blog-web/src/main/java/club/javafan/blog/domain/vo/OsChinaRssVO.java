package club.javafan.blog.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OsChinaRssVO {
   String title;
    String  link;
    Date pubDate;
    String description;
    String guid;
    String category;
}
