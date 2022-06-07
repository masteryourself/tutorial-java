package org.masteryourself.tutorial.redis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.masteryourself.tutorial.redis.domain.Blog;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>description : BlogMapper
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/7 6:59 PM
 */
public interface BlogMapper extends Mapper<Blog> {

    @Update("update blog set liked = liked + 1 where id = #{blogId}")
    int liked(@Param("blogId") Long blogId);

    @Update("update blog set liked = liked - 1 where id = #{blogId}")
    int unliked(@Param("blogId") Long blogId);
}
