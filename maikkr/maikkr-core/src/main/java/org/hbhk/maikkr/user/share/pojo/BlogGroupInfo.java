package org.hbhk.maikkr.user.share.pojo;

import java.io.Serializable;
import java.util.Date;

public class BlogGroupInfo implements Serializable {

	private static final long serialVersionUID = -1345673403325225202L;
	private String id ;
	private String commentUser;
	private String commentConcent;
	private Date createTime;
	private String blogTitle;
	private String blogUser;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}
	public String getCommentConcent() {
		return commentConcent;
	}
	public void setCommentConcent(String commentConcent) {
		this.commentConcent = commentConcent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogUser() {
		return blogUser;
	}
	public void setBlogUser(String blogUser) {
		this.blogUser = blogUser;
	}
	
	
	
}
