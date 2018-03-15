package org.hbhk.aili.client.core.core.context;

import java.util.List;
import java.util.Set;

public interface IUser {

	@Deprecated
	List<IRole> getRoles();

	@Deprecated
	void setRoles(List<IRole> roles);
	String getId();

	void setId(String id);

	Set<String> getRoleids();

	Set<String> queryAccessUris();

	void setRoleids(Set<String> roleids);

	void setUserName(String userName);

	String getUserName();
}
