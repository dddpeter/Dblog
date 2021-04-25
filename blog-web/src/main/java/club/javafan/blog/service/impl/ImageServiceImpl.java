package club.javafan.blog.service.impl;

import club.javafan.blog.common.util.PageQueryUtil;
import club.javafan.blog.common.util.PageResult;
import club.javafan.blog.domain.BlogImage;
import club.javafan.blog.repository.BlogImageMapper;
import club.javafan.blog.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageServiceImpl  implements ImageService {
    @Resource
    BlogImageMapper blogImageMapper;
    @Override
    public PageResult getBlogImagePage(PageQueryUtil pageUtil) {
        int total = blogImageMapper.getTotalImages(pageUtil);
        List<BlogImage> images = blogImageMapper.findImageList(pageUtil);
        PageResult pageResult = new PageResult(images, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
