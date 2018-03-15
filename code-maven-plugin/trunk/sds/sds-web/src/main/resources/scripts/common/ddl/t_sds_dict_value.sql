-- 数据字典key-value表
DROP TABLE IF EXISTS "public"."t_sds_dict_value";
CREATE TABLE "public"."t_sds_dict_value" (
"key" varchar(50) COLLATE "default",
"value" varchar(50) COLLATE "default",
"dict_code" varchar(50) COLLATE "default",
"order_no" int4,
"remark" varchar(255) COLLATE "default",
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"id" varchar(50) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE);

ALTER TABLE "public"."t_sds_dict_value" ADD PRIMARY KEY ("id");
