package club.javafan.blog.web.controller.admin;

import club.javafan.blog.common.result.ResponseResult;
import club.javafan.blog.common.util.BlogUtils;
import club.javafan.blog.domain.BlogImage;
import club.javafan.blog.repository.BlogImageMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author 敲代码的长腿毛欧巴(博客)
 * @date 2019/12/28 21:40
 * @desc 上传
 */
@Controller
@RequestMapping("/admin")
public class UploadController {
    @Value("${file.file-path}")
    private String FILE_PATH;
    @Resource
    BlogImageMapper blogImageMapper;

    @PostMapping({"/upload/file"})
    @ResponseBody
    public ResponseResult upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        //创建文件
        File destFile = new File(newFileName);
        String fileUrl = BlogUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName;
        String location = System.getProperty("user.dir").replaceAll("\\\\", "/") + FILE_PATH;
        BlogImage blogImage = BlogImage
                .builder()
                .imagePath(location + newFileName)
                .imageUrl(fileUrl)
                .build();
        blogImageMapper.insert(blogImage);
        try {
            file.transferTo(destFile);
            ResponseResult resultSuccess = ResponseResult.successResult("成功！");
            resultSuccess.setData(fileUrl);
            return resultSuccess;
        } catch (IOException e) {
            return ResponseResult.failResult("文件上传失败！");
        }
    }
}
