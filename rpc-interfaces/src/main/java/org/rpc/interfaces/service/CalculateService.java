package org.rpc.interfaces.service;
/**
 *
 * 生产者端服务接口(以数值计算为例)
 *
 * @author CaiXiangning 
 * @date May 31, 2017 
 * @email caixiangning@gmail.com
 */
public interface CalculateService {

	/**
	 * 求和运算
	 * @param param1
	 * @param param2
	 * @return
	 */
	public int add(int param1, int param2);
	
	/**
	 * 减法运算
	 * @param param1
	 * @param param2
	 * @return
	 */
	public int sub(int param1, int param2);
}
