package club.javafan.blog.web.controller.blog;

import club.javafan.blog.service.CategoryService;
import club.javafan.blog.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class MyOpenSourceController {
    @Resource
    private ConfigService configService;
    @Resource
    private CategoryService categoryService;

    @GetMapping({"/opensource"})
    public ModelAndView opensource() throws Exception {

        ModelAndView modelAndView = new ModelAndView("blog/amaze/opensource");
        modelAndView.addObject("configurations", configService.getAllConfigs());
        modelAndView.addObject("categories", categoryService.getAllCategories());
        return modelAndView;
    }
}
