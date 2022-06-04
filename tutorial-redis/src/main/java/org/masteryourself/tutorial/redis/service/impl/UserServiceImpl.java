package org.masteryourself.tutorial.redis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.domain.User;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.mapper.UserMapper;
import org.masteryourself.tutorial.redis.service.UserService;
import org.masteryourself.tutorial.redis.utils.RedisConstants;
import org.masteryourself.tutorial.redis.utils.RegexUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : UserServiceImpl
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/19 7:21 PM
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

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
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone", phone);
        User user = userMapper.selectOneByExample(example);
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
        user.setPhone(phone);
        user.setName("user_" + RandomUtil.randomString(10));
        userMapper.insertSelective(user);
        return user;
    }

}
