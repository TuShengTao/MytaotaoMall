package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;


/**
 * @author: tushengtao
 * @date: 2019/12/3
 * @Description: 用户管理Service
 * @param:
 * @return:
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_DOMAIN_BASE_USRL}")
	public String SSO_DOMAIN_BASE_USRL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	@Value("${USER_ADDRESS_URL}")
	public String USER_ADDRESS_URL;
	@Value("${REST_BASE_URL}")
	public String REST_BASE_URL; 
	/**
	 * @author: tushengtao
	 * @date: 2019/12/11
	 * @Description: 
	 * @param: 
	 * @return: 
	 */
	@Override
	public TbUser getUserByToken(String token) {
		try {
			//调用sso系统的服务，根据token取用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			//把json转换成TaotaoREsult
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @author: tushengtao
	 * @date: 2019/12/11
	 * @Description:
	 * @param: [id]
	 * @return: com.taotao.pojo.TbUser
	 */
	@Override
	public TbUser selectAddress(Long id) {
		// 获取用户地址
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL +USER_ADDRESS_URL + id);
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
