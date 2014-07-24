package org.hbhk.maikkr.user.share.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Entity
@Tabel("t_mkk_blog")
public class Blog extends BaseInfo {
	private static final long serialVersionUID = -2269793723241795275L;
	@Column("blogId")
	private int blogId;// 微博Id
	@Column("blogUser")
	private String blogUser;// 微博用户
	@Column("blogContent")
	private String blogContent;// 微博内容
	@Column("blogCollect")
	private int blogCollect;// 收藏次数
	@Column("blogForward")
	private int blogForward;// 转发次数
	@Column("blogReview")
	private int blogReview;// 评论次数
	@Column("blogLink")
	private String blogLink;// 图片url

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogUser() {
		return blogUser;
	}

	public void setBlogUser(String blogUser) {
		this.blogUser = blogUser;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public int getBlogCollect() {
		return blogCollect;
	}

	public void setBlogCollect(int blogCollect) {
		this.blogCollect = blogCollect;
	}

	public int getBlogForward() {
		return blogForward;
	}

	public void setBlogForward(int blogForward) {
		this.blogForward = blogForward;
	}

	public int getBlogReview() {
		return blogReview;
	}

	public void setBlogReview(int blogReview) {
		this.blogReview = blogReview;
	}

	public String getBlogLink() {
		return blogLink;
	}

	public void setBlogLink(String blogLink) {
		this.blogLink = blogLink;
	}

}