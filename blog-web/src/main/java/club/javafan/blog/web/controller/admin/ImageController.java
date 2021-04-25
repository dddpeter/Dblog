package club.javafan.blog.web.controller.admin;

import club.javafan.blog.common.result.ResponseResult;
import club.javafan.blog.common.util.PageQueryUtil;
import club.javafan.blog.common.util.PageResult;
import club.javafan.blog.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/admin")
public class ImageController {
    @Resource
    ImageService imageService;
    @GetMapping("/images/list")
    @ResponseBody
    public ResponseResult list(@RequestParam(required = true) Integer page, @RequestParam(required = true) Integer limit) {
        if (isNull(page) || isNull(limit)) {
            return ResponseResult.failResult("参数错误！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(page,limit);
        PageResult blogImagePage = imageService.getBlogImagePage(pageUtil);
        return ResponseResult.successResult().setData(blogImagePage);
    }
    @GetMapping("/images")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("admin/images");
        modelAndView.addObject("path", "images");
        return modelAndView;
    }
}
