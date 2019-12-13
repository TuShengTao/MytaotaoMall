package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2019/12/11
 *
 * @author Tu ShengTao
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper userMapper;
    @Override
    public TaotaoResult getAddress(Long id) {
        TbUser user=userMapper.selectByPrimaryKey(id);
        // 设置密码为空
        user.setPassword("");
        return TaotaoResult.ok(user);
    }
}
