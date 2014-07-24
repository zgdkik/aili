package org.hbhk.maikkr.user.share.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Entity
@Tabel("t_mkk_comment")
public class Comment extends BaseInfo {

	private static final long serialVersionUID = 3846844061131232345L;

	@Column("blogId")
	private String blogId; // 博客id
	@Column("commentConcent")
	private String commentConcent;// 评论内容
	@Column("commentUser")
	private String commentUser;// 评论人

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getCommentConcent() {
		return commentConcent;
	}

	public void setCommentConcent(String commentConcent) {
		this.commentConcent = commentConcent;
	}

	public String getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}

}
