-- 数据字典类型表
DROP TABLE IF EXISTS "public"."t_sds_dict";
CREATE TABLE "public"."t_sds_dict" (
"dict_code" varchar(50) COLLATE "default",
"dict_name" varchar(255) COLLATE "default",
"remark" varchar(255) COLLATE "default",
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(255) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"id" varchar(50) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)
;

ALTER TABLE "public"."t_sds_dict" ADD PRIMARY KEY ("id");
