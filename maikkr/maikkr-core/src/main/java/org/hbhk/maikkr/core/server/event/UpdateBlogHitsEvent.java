package org.hbhk.maikkr.core.server.event;

import org.hbhk.maikkr.user.server.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class UpdateBlogHitsEvent extends ApplicationEvent {

	private static final long serialVersionUID = -9081512685323297050L;
	@Autowired
	private IBlogService blogService;
	private String blogId;

	public UpdateBlogHitsEvent(String blogId) {
		super(blogId);
		this.blogId = blogId;
		blogService.updateBlogHit(blogId);
	}

	public String getBlogId() {
		return blogId;
	}

}
