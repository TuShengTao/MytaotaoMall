package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);

	TbUser selectAddress(Long id);
}
