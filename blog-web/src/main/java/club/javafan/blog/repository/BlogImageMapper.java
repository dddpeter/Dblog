package club.javafan.blog.repository;

import club.javafan.blog.common.annotation.Slave;
import club.javafan.blog.common.util.PageQueryUtil;
import club.javafan.blog.domain.BlogImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogImageMapper extends BaseMapper<BlogImage> {
    @Slave
    @SelectProvider(type = BlogImageSqlProvider.class, method = "selectImages")
    List<BlogImage> findImageList(PageQueryUtil pageUtil);

    @Slave
    @Select("select count(1) from tb_images")
    int getTotalImages(PageQueryUtil pageUtil);
}
