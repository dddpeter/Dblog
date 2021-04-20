package club.javafan.blog.repository;

import club.javafan.blog.domain.BlogImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BlogImageMapper extends BaseMapper<BlogImage> {

}
