package org.masteryourself.tutorial.redis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.domain.User;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.service.UserService;
import org.masteryourself.tutorial.redis.utils.RedisConstants;
import org.masteryourself.tutorial.redis.utils.RegexUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : UserServiceMemImpl
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/19 7:21 PM
 */
@Slf4j
@Service
public class UserServiceMemImpl implements UserService {

    private static final List<User> USER_DB = new CopyOnWriteArrayList<>();

    static {
        USER_DB.add(new User(1L, "张三", "17621208646"));
        USER_DB.add(new User(2L, "李四", "17621208647"));
        USER_DB.add(new User(3L, "王五", "17621208648"));
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCode(String phone) {
        // 1. 校验手机号码
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机号格式错误！");
        }
        // 2. 符合，生成验证码
        String code = RandomUtil.randomNumbers(6);
        // 3. 保存验证码
        stringRedisTemplate.opsForValue().set(RedisConstants.USER_CODE_KEY + phone, code, RedisConstants.USER_CODE_TTL, TimeUnit.MINUTES);
        // 4. 发送验证码
        log.info("发送短信验证码成功，验证码：{}", code);
        return Result.ok();
    }

    @Override
    public Result login(String phone, String code) {
        // 1. 校验手机号码
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机号格式错误！");
        }
        // 2. 从 redis 获取验证码并校验
        String cacheCode = stringRedisTemplate.opsForValue().get(RedisConstants.USER_CODE_KEY + phone);
        if (cacheCode == null || !cacheCode.equals(code)) {
            return Result.fail("验证码错误");
        }
        // 3. 查询用户信息
        User user = USER_DB.stream().filter(user1 -> user1.getPhone().equals(phone)).findFirst().orElse(null);
        if (user == null) {
            // 4. 说明不存在首次注册
            user = this.create(phone);
            log.info("新注册用户 {}", user);
        } else {
            log.info("从数据库中查询到用户 {}", user);
        }
        // 5. 保存用户信息到 redis 中, 表示已经完成登录
        String token = UUID.randomUUID().toString(true);
        String tokenKey = RedisConstants.USER_TOKEN_KEY + token;
        // 需要全部转成 string
        stringRedisTemplate.opsForHash().putAll(tokenKey,
                BeanUtil.beanToMap(user, new HashMap<>(), CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString())));
        stringRedisTemplate.expire(tokenKey, 30, TimeUnit.MINUTES);
        // 6. 返回 token
        return Result.ok(token);
    }

    private User create(String phone) {
        User user = new User();
        User maxUser = USER_DB.stream().max(Comparator.comparingLong(User::getId)).orElse(null);
        if (maxUser == null) {
            user.setId(1L);
        } else {
            user.setId((maxUser.getId() + 1));
        }
        user.setPhone(phone);
        user.setName("user_" + RandomUtil.randomString(10));
        return user;
    }

}
