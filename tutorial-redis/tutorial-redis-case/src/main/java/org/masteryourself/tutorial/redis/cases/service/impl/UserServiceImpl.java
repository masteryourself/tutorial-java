package org.masteryourself.tutorial.redis.cases.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.cases.domain.User;
import org.masteryourself.tutorial.redis.cases.dto.Result;
import org.masteryourself.tutorial.redis.cases.mapper.UserMapper;
import org.masteryourself.tutorial.redis.cases.service.UserService;
import org.masteryourself.tutorial.redis.cases.utils.RedisConstants;
import org.masteryourself.tutorial.redis.cases.utils.RegexUtils;
import org.masteryourself.tutorial.redis.cases.utils.UserHolder;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
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
                        .setIgnoreProperties("ctime", "mtime")
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString())));
        stringRedisTemplate.expire(tokenKey, 30, TimeUnit.MINUTES);
        // 6. 返回 token
        return Result.ok(token);
    }

    @Override
    public List<User> findByIds(List<Long> userIds) {
        String userIdStr = StrUtil.join(",", userIds);
        return userMapper.findByIds(userIdStr);
    }

    @Override
    public Result sign() {
        // 1. 获取当前登录用户
        Long userId = UserHolder.getUser().getId();
        // 2. 获取当前日期
        LocalDateTime now = LocalDateTime.now();
        // 3. 拼接 key
        String key = RedisConstants.USER_SIGN_KEY + now.format(DateTimeFormatter.ofPattern("yyyyMM")) + ":" + userId;
        // 4. 获取今天是本月的第几天, 下标是从 1 开始
        int dayOfMonth = now.getDayOfMonth();
        Boolean signFlag = stringRedisTemplate.opsForValue().getBit(key, dayOfMonth - 1);
        if (Boolean.TRUE.equals(signFlag)) {
            return Result.ok("当天已经签到");
        }
        // 5. 向 BitMap 中设置值表示完成签到
        stringRedisTemplate.opsForValue().setBit(key, dayOfMonth - 1, true);
        return Result.ok("签到成功");
    }

    @Override
    public Result signCount() {
        // 1. 获取当前登录用户
        Long userId = UserHolder.getUser().getId();
        // 2. 获取当前日期
        LocalDateTime now = LocalDateTime.now();
        // 3. 拼接 key
        String key = RedisConstants.USER_SIGN_KEY + now.format(DateTimeFormatter.ofPattern("yyyyMM")) + ":" + userId;
        // 4. 获取今天是本月的第几天, 下标是从 1 开始
        int dayOfMonth = now.getDayOfMonth();
        // 5.获取本月截止今天为止的所有的签到记录
        List<Long> result = stringRedisTemplate.opsForValue().bitField(
                key,
                BitFieldSubCommands.create()
                        .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth)).valueAt(0)
        );
        if (CollectionUtil.isEmpty(result) || result.get(0) == null) {
            // 没有任何签到结果
            return Result.ok(0);
        }
        Long signNum = result.get(0);
        // 6. 循环遍历
        int count = 0;
        while (true) {
            // 与 1 做与运算，得到数字的最后一个 bit 位, 如果这个 bit 为 0, 则表示签到中断
            if ((signNum & 1) == 0) {
                break;
            } else {
                // 不为 0 表示签到
                count++;
            }
            // 把数字右移一位，抛弃最后一个 bit 位，继续下一个 bit 位
            signNum = signNum >> 1;
        }
        return Result.ok(count);
    }

    private User create(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setName("user_" + RandomUtil.randomString(10));
        userMapper.insertSelective(user);
        return user;
    }

}
