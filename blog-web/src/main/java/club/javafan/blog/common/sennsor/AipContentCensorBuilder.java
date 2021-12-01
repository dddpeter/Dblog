package club.javafan.blog.common.sennsor;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

/**
 * 文本审核单例
 *
 * @author dddpeter
 * @createDate 2020/2/2
 */
@Component(value = "aipContentCensorBuilder")
public class AipContentCensorBuilder {
    private static final Logger logger = LoggerFactory.getLogger(AipContentCensorBuilder.class);
    @Resource
    AipContentCensor baiduAipContentCensor;

    /**
     *  敏感词结果
     */
    public static class SensorResult{
        private String desc;
        private Integer code;

        public String getDesc() {
            return desc;
        }

        public SensorResult setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Integer getCode() {
            return code;
        }

        public SensorResult setCode(Integer code) {
            this.code = code;
            return this;
        }

        public SensorResult failResult(String desc){
            SensorResult sensorResult = new SensorResult();
            sensorResult.setCode(INTEGER_ONE);
            sensorResult.setDesc(desc);
            return sensorResult;
        }

        public SensorResult successResult(String desc) {
            SensorResult sensorResult = new SensorResult();
            sensorResult.setCode(INTEGER_ZERO);
            sensorResult.setDesc(desc);
            return sensorResult;
        }
    }

    /**
     * 文本敏感词校验
     * @param text
     * @return
     */
    public  SensorResult judgeText(String text){
        JSONObject jsonObject = baiduAipContentCensor.textCensorUserDefined(text);
        logger.info("AipContentCensorBuilder text: {},info: {}",text,jsonObject);
        //0表示非违禁，1表示违禁，2表示建议人工复审
        Integer conclusionType = jsonObject.getInt("conclusionType");
        if (Objects.isNull(jsonObject) || Objects.isNull(conclusionType) || conclusionType.intValue() == INTEGER_ZERO){
            return new SensorResult().failResult("需要人工审核");
        }
        else{
            return new SensorResult().successResult("合规！");
        }


    }
}
