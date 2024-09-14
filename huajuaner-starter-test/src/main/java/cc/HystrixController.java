package cc;

import com.huajuaner.annotaion.DoHystrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lan ChengJian, 2024/9/14
 */
@RestController
public class HystrixController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 测试：http://localhost:8566/api/queryUserInfoByHystrix?userId=aaa
     */
    @DoHystrix(timeoutValue = 350, returnJson = "{\"code\":\"1111\",\"info\":\"调用方法超过350毫秒，熔断返回！\"}")
    @RequestMapping(path = "/api/queryUserInfoByHystrix", method = RequestMethod.GET)
    public String queryUserInfoByHystrix(@RequestParam String userId) throws InterruptedException {
        logger.info("查询用户信息，userId：{}", userId);
        Thread.sleep(1000);
        return "查询成功";
    }
}
