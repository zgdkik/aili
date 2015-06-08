package org.hbhk.aili.boot.share.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 * 
 */
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 5009300140634580156L;
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
