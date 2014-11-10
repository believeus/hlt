package com.hlt2008.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Example extends TbaseEntity{

	private static final long serialVersionUID = 3206958391548032662L;

	/** 标题*/
	private String title;
	
	/** 图片链接 */
	private String path;

	/** 内容*/
	private String content;

	/** 获取图片链接*/
	public String getPath() {
		return path;
	}

	/** 设置图片链接*/
	public void setPath(String path) {
		this.path = path;
	}

	/** 获取标题*/
	public String getTitle() {
		return title;
	}

	/** 设置标题 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 获取内容 */
	public String getContent() {
		return content;
	}

	/** 设置内容 */
	public void setContent(String content) {
		this.content = content;
	}

}
