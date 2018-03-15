struct User{
	1:i64 id,
	2:string name
}

service UserService{
	void show(1:User user)
}