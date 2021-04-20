package club.javafan.blog.service;

import club.javafan.blog.common.util.PageQueryUtil;
import club.javafan.blog.common.util.PageResult;

public interface ImageService {
    PageResult getBlogImagePage(PageQueryUtil pageUtil);
}
