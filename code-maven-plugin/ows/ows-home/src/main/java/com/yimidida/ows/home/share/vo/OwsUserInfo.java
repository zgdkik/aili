package com.yimidida.ows.home.share.vo;

import java.util.List;

import com.yimidida.ows.home.share.entity.OwsUser;
import com.yimidida.ows.home.share.entity.UserPeople;

public class OwsUserInfo {
	private OwsUser user;
	private List<UserPeople> people;
	public OwsUser getUser() {
		return user;
	}
	public void setUser(OwsUser user) {
		this.user = user;
	}
	public List<UserPeople> getPeople() {
		return people;
	}
	public void setPeople(List<UserPeople> people) {
		this.people = people;
	}
	
}
