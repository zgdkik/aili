package org.hbhk.maikkr.core.server.event1;

import org.springframework.context.ApplicationEvent;

public class BlogHitsEvent extends ApplicationEvent {

	private static final long serialVersionUID = -9081512685323297050L;

	public BlogHitsEvent(Object obj) {
		super(obj);

	}

	public void updateBlogHit() {
		System.out.println("asdasd");
	}

}
