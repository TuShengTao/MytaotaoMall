package com.taotao.rest.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * Created on 2019/11/3
 *
 * @author Tu ShengTao
 * Description:首页 门户portal 内容管理 如广告
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private TbContentMapper contentMapper;
    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContenList(long contentId) {

        //添加Redis缓存不能影响业务逻辑

        //从缓存中取内容
        try {
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentId + "");
            if (!StringUtils.isBlank(result)) {
                //把字符串转换成list
                List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
                return resultList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据内容分类id从MySQL数据库查询内容列表
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(contentId);
        //执行查询
        List<TbContent> list=contentMapper.selectByExample(example);

        //向缓存中添加内容
        try {
            //把list转换成字符串
            String cacheString = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentId + "", cacheString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
