package cc;

import cc.dao.IUserDao;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lan ChengJian, 2024/9/18
 */
@RestController
public class MybatisController {

    @Autowired
    private IUserDao userDao;

    /**
     * http://localhost:8566/queryUserInfoById
     */
    @GetMapping("/queryUserInfoById")
    public void queryUserInfoById() {
        User user = userDao.queryUserInfoById(1L);
        System.out.println("测试结果：" + JSON.toJSONString(user));
    }
}
