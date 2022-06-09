-- 1. 参数列表
local goodsId = ARGV[1]
local userId = ARGV[2]
local orderId = ARGV[3]

-- 2. 数据 key
local stockKey = 'seckill:stock:' .. goodsId
local orderKey = 'seckill:purchased:' .. goodsId

-- 3. 脚本业务
-- 3.1 判断库存是否充足
if (tonumber(redis.call('get', stockKey)) <= 0) then
    -- 库存不足, 返回 1
    return 1
end
-- 3.2 判断用户是否下过单
if (redis.call('sismember', orderKey, userId) == 1) then
    -- 用户已经下过单
    return 2;
end
-- 3.3 扣减库存和保存购买用户
redis.call('incrby', stockKey, '-1')
redis.call('sadd', orderKey, userId)
-- 0 表示下单成功
redis.call('rpush', 'topic:order', '{userId:' .. userId .. ',goodsId:' .. goodsId .. ', orderId :' .. orderId .. '}')
return 0