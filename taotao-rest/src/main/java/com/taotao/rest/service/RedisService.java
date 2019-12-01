package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created on 2019/11/5
 *
 * @author Tu ShengTao
 * Description:
 */
public interface RedisService {
    TaotaoResult syncContent(long contentCid);
}
