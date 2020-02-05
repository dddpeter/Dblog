package club.javafan.blog.web.controller.admin;


import club.javafan.blog.common.Result.ResponseResult;
import club.javafan.blog.domain.AdminUser;
import club.javafan.blog.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.util.Objects.isNull;

/**
 * @author 不会敲代码的小白(博客)
 * @date 2019/12/25 22:15
 * @desc 后台管理
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private LinkService linkService;
    @Resource
    private TagService tagService;
    @Resource
    private CommentService commentService;


    @GetMapping({"/login"})
    public ModelAndView login() {
        return new ModelAndView("admin/login");
    }

    @GetMapping({"/test"})
    public ModelAndView test() {
        return new ModelAndView("admin/test");
    }


    @GetMapping({"", "/", "/index", "/index.html"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("admin/index");
        modelAndView.addObject("path", "index");
        modelAndView.addObject("categoryCount", categoryService.getTotalCategories());
        modelAndView.addObject("blogCount", blogService.getTotalBlogs());
        modelAndView.addObject("linkCount", linkService.getTotalLinks());
        modelAndView.addObject("tagCount", tagService.getTotalTags());
        modelAndView.addObject("commentCount", commentService.getTotalComments());
        modelAndView.addObject("path", "index");
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@RequestParam("userName") String userName,
                              @RequestParam("password") String password,
                              @RequestParam("verifyCode") String verifyCode,
                              HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("admin/login");
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空！");
            return modelAndView;
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空！");
            return modelAndView;
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        System.out.println(kaptchaCode);
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误！");
            return modelAndView;
        }
        AdminUser adminUser = adminUserService.login(userName, password);
        if (isNull(adminUser)) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            return new ModelAndView("redirect:/admin/index");
        }
        session.setAttribute("errorMsg", "登录失败！");
        return modelAndView;

    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/login");
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (isNull(adminUser)) {
            return modelAndView;
        }
        modelAndView.setViewName("admin/profile");
        modelAndView.addObject("path", "profile");
        modelAndView.addObject("loginUserName", adminUser.getLoginUserName());
        modelAndView.addObject("nickName", adminUser.getNickName());
        return modelAndView;
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public ResponseResult passwordUpdate(HttpServletRequest request,
                                 @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return ResponseResult.failResult("参数不能为空");
        }
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        ResponseResult responseResult = adminUserService.updatePassword(loginUserId,
                originalPassword, newPassword);
        if (responseResult.isSuccess()) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return responseResult;
        }
        return ResponseResult.failResult("修改失败！");
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public ResponseResult nameUpdate(HttpServletRequest request,
                             @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName) {
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName) || isNull(loginUserId)) {
            return ResponseResult.failResult("参数不能为空");
        }
        ResponseResult responseResult = adminUserService.updateName(loginUserId, loginUserName, nickName);
        return responseResult;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return new ModelAndView("admin/login");
    }
}
