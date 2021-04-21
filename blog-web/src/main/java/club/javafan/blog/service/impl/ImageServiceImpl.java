package club.javafan.blog.service.impl;

import club.javafan.blog.common.util.PageQueryUtil;
import club.javafan.blog.common.util.PageResult;
import club.javafan.blog.domain.BlogImage;
import club.javafan.blog.repository.BlogImageMapper;
import club.javafan.blog.service.ImageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageServiceImpl  implements ImageService {
    @Resource
    BlogImageMapper blogImageMapper;
    @Override
    public PageResult getBlogImagePage(PageQueryUtil pageUtil) {
        LambdaQueryWrapper<BlogImage> queryWrapper  =new LambdaQueryWrapper();
        Page<BlogImage> userPage = new Page<>(pageUtil.getPage() , pageUtil.size());
        IPage<BlogImage> userIPage = blogImageMapper.selectPage(userPage , queryWrapper);
        List<BlogImage> images = userIPage.getRecords();
        int total = blogImageMapper.selectCount(queryWrapper).intValue();
        PageResult pageResult = new PageResult(images, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
