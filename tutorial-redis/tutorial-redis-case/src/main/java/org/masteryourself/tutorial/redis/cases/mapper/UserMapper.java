package org.masteryourself.tutorial.redis.cases.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.masteryourself.tutorial.redis.cases.domain.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>description : UserMapper
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/4 1:52 PM
 */
public interface UserMapper extends Mapper<User> {

    @Select("select * from user where id in (${userIdStr}) order by field(id, ${userIdStr})")
    List<User> findByIds(@Param("userIdStr") String userIdStr);
}
