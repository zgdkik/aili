package com.feisuo.sds.common.share.request.push;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "message")
public class PushMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217395597475442369L;

	/** 是哪个用户要发的推送 */
	@JsonProperty("UserId")
	private String userId;

	/** 用户类型 */
	@JsonProperty("UserType")
	private int userType;

	/** 优先级升序，0-10，必填 */
	@JsonProperty("Priority")
	private int priority;
	
	@JsonProperty("Groups")
	private List<PushTargetGroup> groups;

	/** 关联Id,用于多条消息进行关联,非必填 */
	@JsonProperty("RelationId")
	private String relationId;

	/** 内容Id(类型为订单是为订单id，类型为消息为消息id),必填 */
	@JsonProperty("ContentId")
	private String contentId;

	/** 推送内容 */
	@JsonProperty("Content")
	private String content;

	/** 消息类型（1：订单，2：消息），必填 */
	@JsonProperty("ContentType")
	private int contentType;

	/** 用于标识消息来源，可为空 */
	@JsonProperty("Source")
	private String source;

	/** 客户端Ip，可为空 */
	@JsonProperty("ClientIp")
	private String clientIp;


	/** 订单类型 */
	@JsonProperty("Extensions")
	private Map<String, String> extensions;

	/** 用于显示消息的标题 */
	@JsonProperty("Title")
	private String title;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public List<PushTargetGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<PushTargetGroup> groups) {
		this.groups = groups;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Map<String, String> getExtensions() {
		return extensions;
	}

	public void setExtensions(Map<String, String> extensions) {
		this.extensions = extensions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	

}
