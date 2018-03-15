DROP TABLE IF EXISTS "public"."t_sds_role_function";
CREATE TABLE "public"."t_sds_role_function" (
"role_code" varchar(50) COLLATE "default",
"function_code" varchar(50) COLLATE "default",
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(20) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"id" varchar(50) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)
;
ALTER TABLE "public"."t_sds_role_function" ADD PRIMARY KEY ("id");
