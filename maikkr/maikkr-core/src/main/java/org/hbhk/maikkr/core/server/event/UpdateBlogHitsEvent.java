package org.hbhk.maikkr.core.server.event;

import org.hbhk.aili.core.server.web.WebApplicationContextHolder;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.springframework.context.ApplicationEvent;

public class UpdateBlogHitsEvent extends ApplicationEvent {

	private static final long serialVersionUID = -9081512685323297050L;
	private IBlogService blogService;
	private String blogId;

	public UpdateBlogHitsEvent(String blogId) {
		super(blogId);
		blogService = (IBlogService) WebApplicationContextHolder.getWebApplicationContext().getBean("blogService");
		this.blogId = blogId;

	}

	public void updateBlogHit() {
		blogService.updateBlogHit(this.blogId);
	}

	public String getBlogId() {
		return blogId;
	}

}
