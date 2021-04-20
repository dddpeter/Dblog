package club.javafan.blog.web.common;

import club.javafan.blog.common.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler(Exception e){
      log.error("发生异常",e);
        if(e instanceof MissingServletRequestParameterException){
            return ResponseResult.failResult("缺少必需参数："+((MissingServletRequestParameterException) e).getParameterName());
        }
        return ResponseResult.failResult("发生异常：" + e.getMessage());
    }
}
