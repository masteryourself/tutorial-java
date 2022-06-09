package org.masteryourself.tutorial.redis.cases.service;

import org.masteryourself.tutorial.redis.cases.Blog;
import org.masteryourself.tutorial.redis.cases.dto.Result;

/**
 * <p>description : BlogService
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.1.1
 * @date : 2022/6/7 6:52 PM
 */
public interface BlogService {

    Result create(Blog blog);

    Result like(Long blogId);

    Result list(Long blogId);
}
