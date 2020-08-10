package com.liugs.tool.spring;

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

	public void init(){
		System.out.println("Bean is going through init.");
	}
	public void destroy(){
		System.out.println("Bean will destroy now.");
	}
	public Long getPostProcessFlag() {
		return postProcessFlag;
	}
}