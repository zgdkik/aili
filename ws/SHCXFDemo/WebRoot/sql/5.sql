 select * from t_dar_login
 
 
 
 create  table  t_user0 (
         id  number  primary  key,
        email  varchar2(20) ,
        nickname varchar2(20) ,
        password varchar2(20) ,
        user_integral varchar2(20)  ,
        is_email_verify varchar2(20) ,
        email_verify_code varchar2(20)  ,
        last_login_time varchar2(20)  ,
        last_login_ip  varchar2(20) 
       
        )
        
        insert into t_user0
        values
          (1,
           'jdbc@163.com',
           'hbhk',
           '135246',
           '1',
           '0',
           'hbhk8795213213',
           '2012-10-01 10:12:12',
           '127.0.0.1')



CREATE TABLE article (
    id number  primary  key ,
    title varchar2(100)  ,
    author varchar2(32)  ,
    pubDate  timestamp  ,
    content   varchar2(100) 
)

drop table article

create  sequence  hibernate_sequence
select  *from  article
