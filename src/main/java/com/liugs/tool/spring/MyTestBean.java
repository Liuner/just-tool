package com.liugs.tool.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName MyTestBean
 * @Description
 * @Author liugs
 * @Date 2020/8/3 17:07:57
 */
public class MyTestBean {
	private String name = "TEST_BEAN";

	private Long postProcessFlag = 1L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @PostConstruct 注释作为初始化回调函数的一个替代
	 * @PreDestroy 注释作为销毁回调函数的一个替代
	 */
	@PostConstruct
	public void init(){
		System.out.println("Bean is going through init.");
	}
	@PreDestroy
	public void destroy(){
		System.out.println("Bean will destroy now.");
	}
	public Long getPostProcessFlag() {
		return postProcessFlag;
	}
}