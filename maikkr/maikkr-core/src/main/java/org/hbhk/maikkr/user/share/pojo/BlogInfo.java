package org.hbhk.maikkr.user.share.pojo;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.Entity;
import org.hbhk.aili.orm.server.annotation.Tabel;
import org.hbhk.aili.orm.share.model.BaseInfo;

@Entity
@Tabel("t_mkk_blog")
public class BlogInfo extends BaseInfo {
	private static final long serialVersionUID = -2269793723241795275L;
	@Column("blogId")
	private String blogId;// 微博Id
	@Column("blogUser")
	private String blogUser;// 微博用户
	@Column("blogTitle")
	private String blogTitle;// 微博标题
	@Column("blogContent")
	private String blogContent;// 微博内容
	@Column("blogCollect")
	private Integer blogCollect;// 收藏次数
	@Column("blogForward")
	private Integer blogForward;// 转发次数
	@Column("blogReview")
	private Integer blogReview;// 评论次数
	@Column("blogHit")
	private Integer blogHit;// 热度次数
	@Column("blogLink")
	private String blogLink;// 图片url
	//主题url
	@Column("blogUrl")
	private String blogUrl;
	private String userHeadImg;
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getBlogUser() {
		return blogUser;
	}
	public void setBlogUser(String blogUser) {
		this.blogUser = blogUser;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public Integer getBlogCollect() {
		return blogCollect;
	}
	public void setBlogCollect(Integer blogCollect) {
		this.blogCollect = blogCollect;
	}
	public Integer getBlogForward() {
		return blogForward;
	}
	public void setBlogForward(Integer blogForward) {
		this.blogForward = blogForward;
	}
	public Integer getBlogReview() {
		return blogReview;
	}
	public void setBlogReview(Integer blogReview) {
		this.blogReview = blogReview;
	}
	public Integer getBlogHit() {
		return blogHit;
	}
	public void setBlogHit(Integer blogHit) {
		this.blogHit = blogHit;
	}
	public String getBlogLink() {
		return blogLink;
	}
	public void setBlogLink(String blogLink) {
		this.blogLink = blogLink;
	}
	public String getBlogUrl() {
		return blogUrl;
	}
	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
	public String getUserHeadImg() {
		return userHeadImg;
	}
	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
