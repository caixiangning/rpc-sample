package org.rpc.provider.service.impl;

import org.rpc.interfaces.service.CalculateService;
import org.springframework.stereotype.Component;

/**
 *
 * 生产者端服务接口(以数值计算为例)
 * (实际开发中生产者服务接口以及实现类是分开的，本示例即是如此)
 *
 * @author CaiXiangning 
 * @date May 31, 2017 
 * @email caixiangning@gmail.com
 */
@Component("calculateService")
public class CalculateServiceImpl implements CalculateService{
	/**
	 * 求和运算
	 * @param param1
	 * @param param2
	 * @return
	 */
	public int add(int param1, int param2) {
		// TODO Auto-generated method stub
		return param1 + param2;
	}

	/**
	 * 减法运算
	 * @param param1
	 * @param param2
	 * @return
	 */
	public int sub(int param1, int param2) {
		// TODO Auto-generated method stub
		return param1 - param2;
	}
}
