package cc;

import com.huajuaner.annotation.DoWhiteList;
import com.huajuaner.aspect.WhiteListJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lan ChengJian, 2024/9/14
 */
@RestController
public class WhitelistController {

    /**
     * 通过：http://localhost:8566/api/queryUserInfoByWhitelist?userId=111
     * 拦截：http://localhost:8566/api/queryUserInfoByWhitelist?userId=222222
     */
    @DoWhiteList(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")
    @RequestMapping(path = "/api/queryUserInfoByWhitelist", method = RequestMethod.GET)
    public String queryUserInfoByWhitelist(@RequestParam String userId) {

        System.out.println("查询用户信息，userId：" + userId);
        return "查询用户信息，userId：" + userId;
    }
}
