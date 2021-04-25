package club.javafan.blog.repository;

import club.javafan.blog.common.util.PageQueryUtil;

public class BlogImageSqlProvider {

    public String selectImages(PageQueryUtil pageUtil) {
        StringBuilder sql = new StringBuilder("select id,image_path,image_url,created_time,modified_time from tb_images where 1=1 order by created_time desc");
        if(pageUtil!=null && pageUtil.getLimit()!=0){
            sql.append(" limit ");
            sql.append((pageUtil.getPage()-1) * pageUtil.getLimit());
            sql.append(",");
            sql.append(pageUtil.getLimit());
        }
        return sql.toString();

    }
}
