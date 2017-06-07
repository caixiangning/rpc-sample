package org.rpc.core.container;

/**
 *
 * 抽象web容器
 *
 * @author CaiXiangning
 * @date May 31, 2017
 * @email caixiangning@gmail.com
 */
public abstract class Container {
	/**
	 * 容器是否启动的状态位 volatile关键字的作用是强制从公共堆栈中取得变量的值，而不是从线程私有数据栈中取得变量的值
	 */
	public static volatile boolean isStart = false;

	/**
	 * 启动容器
	 */
	public abstract void start();

	/**
	 * 容器对象自身的引用
	 */
	public static volatile Container container = null;
}
