
#!/bin/bash
#created by liuyi(刀戈) on 2015-11-16
#安装包存放目录
software=/work/soft/redis/software
#redis的安装二进制目录 /work/soft/redis/bin/redis-*
redis=/work/soft/redis
#redis 配置目录
conf=/work/soft/redis/conf
#redis 日志目录
logs=/work/soft/redis/logs
redisData=/work/soft/redis/data
redisSrc=$software/redis-stable/src
#######dir tree##########
#--redis
#	--bin
#		--redis-*
#	--conf 
#		--redis-common.conf
#		--xxxx.conf
#检查目录
if [ ! -e $software ]; then
mkdir -p $software
fi
if [ ! -d $redis ];then
mkdir -p $redis
fi
if [ ! -d $conf ];then
mkdir -p $conf
fi
if [ ! -d $logs ];then
mkdir $logs
fi
if [ ! -d $redisData ];then
mkdir $redisData
fi
cd $software
#============================install libunwind 1.1================================
function libunwind(){
cd $software
if [ ! -f "libunwind-1.1.tar.gz" ]; then
wget http://ftp.yzu.edu.tw/nongnu/libunwind/libunwind-1.1.tar.gz
fi
tar xf libunwind-1.1.tar.gz
cd libunwind-1.1
./configure && make && make install
}
if [ ! -f "/usr/local/lib/libunwind.so" ];then
libunwind
fi
#===========================indysll gperftools ================================
function gperftools(){
cd $software
if [ ! -f "gperftools-2.4.tar.gz" ];then
echo "download software gperftools-2.4.tar.gz"
curl -sSL https://github.com/gperftools/gperftools/releases/download/gperftools-2.4/gperftools-2.4.tar.gz -o gperftools-2.4.tar.gz
fi
if [ ! -f "gperftools-2.4.tar.gz" ];then
echo "download gperftools-2.4.tar.gz faild! on github "
exit 1
fi
tar xf gperftools-2.4.tar.gz
cd gperftools-2.4
./configure && make && make install
echo "usr/local/lib" > /etc/ld.so.conf.d/usr_local_lib.conf
/sbin/ldconfig 
}
if [ ! -f "/usr/local/lib/libtcmalloc.so" ];then
gperftools
fi
#==============================install tcl===================================
function tcl(){
cd $software
if [ ! -f "tcl8.6.4-src.tar.gz" ];then
echo " download tcl....."
wget http://downloads.sourceforge.net/tcl/tcl8.6.4-src.tar.gz
fi
if [ ! -f "tcl8.6.4-src.tar.gz" ];then
echo "download tcl faild...."
fi
tar xf tcl8.6.4-src.tar.gz
cd tcl8.6.4/unix/
./configure --prefix=/usr --without-tzdata --mandir=/usr/share/man $([ $(uname -m) = x86_64 ] && echo --enable-64bit)
make && sed -e "s@^\(TCL_SRC_DIR='\).*@\1/usr/include'@" -e "/TCL_B/s@='\(-L\)\?.*unix@='\1/usr/lib@" -i tclConfig.sh
make install && make install-private-headers && ln -v -sf tclsh8.6 /usr/bin/tclsh && chmod -v 755 /usr/lib/libtcl8.6.so
}
if [ ! -f "/usr/lib/libtcl8.6.so" ];then
tcl
fi
#==================================install rybu===========================
if [ ! -f "/usr/bin/ruby" ];then
yum -y install ruby
fi
if [ ! -d "/usr/local/share/gems/gems/redis-3.2.1" ];then
gem sources --add https://ruby.taobao.org/ --remove https://rubygems.org/
gem install redis
fi
#===================================install redis ============================
function redis(){
cd $software
if [ ! -f "redis-stable.tar.gz" ];then
wget http://download.redis.io/releases/redis-stable.tar.gz
fi
if [ ! -f "redis-stable.tar.gz" ];then
echo "download redis faild .."
fi
tar xf redis-stable.tar.gz
cd redis-stable
make PREFIX=$redis USE_TCMALLOC=yes FORCE_LIBC_MALLOC=yes install
echo " install redis success!! redis install to $redis"
}
if [ ! -f "$redis/bin/redis-server" ];then
redis
fi
#+===================================== redis commog file====================================
function common_file(){
echo -e daemonize yes > $conf/redis-common.conf
echo -e "cluster-enabled yes" >> $conf/redis-common.conf
echo -e cluster-node-timeout 15000 >> $conf/redis-common.conf
echo -e cluster-migration-barrier 1 >> $conf/redis-common.conf
echo -e tcp-keepalive 0 >> $conf/redis-common.conf
echo -e loglevel notice >> $conf/redis-common.conf
echo -e dir $redisData >> $conf/redis-common.conf
echo -e slave-serve-stale-data yes >> $conf/redis-common.conf
echo -e slave-read-only yes >> $conf/redis-common.conf
echo -e repl-disable-tcp-nodelay yes >> $conf/redis-common.conf
echo -e slave-priority 100 >> $conf/redis-common.conf
echo -e appendonly yes >> $conf/redis-common.conf
echo -e appendfsync everysec >> $conf/redis-common.conf
echo -e no-appendfsync-on-rewrite yes >> $conf/redis-common.conf
echo -e auto-aof-rewrite-min-size 64mb >> $conf/redis-common.conf
echo -e lua-time-limit 5000 >> $conf/redis-common.conf
echo -e slowlog-log-slower-than 10000 >> $conf/redis-common.conf
echo -e slowlog-max-len 128 >> $conf/redis-common.conf
echo -e hash-max-ziplist-entries 512 >> $conf/redis-common.conf
echo -e hash-max-ziplist-value 64 >> $conf/redis-common.conf
echo -e list-max-ziplist-entries 512 >> $conf/redis-common.conf
echo -e list-max-ziplist-value 64 >> $conf/redis-common.conf
echo -e set-max-intset-entries 512 >> $conf/redis-common.conf
echo -e zset-max-ziplist-entries 128 >> $conf/redis-common.conf
echo -e zset-max-ziplist-value 64 >> $conf/redis-common.conf
echo -e activerehashing yes >> $conf/redis-common.conf
echo -e client-output-buffer-limit normal 0 0 0 >> $conf/redis-common.conf
echo -e client-output-buffer-limit slave 256mb 64mb 60 >> $conf/redis-common.conf
echo -e client-output-buffer-limit pubsub 32mb 8mb 60 >> $conf/redis-common.conf
echo -e aof-rewrite-incremental-fsync yes >> $conf/redis-common.conf
}
if [ ! -f "$conf/redis-common.conf" ];then
common_file
fi
#+===================================== redis node file====================================
function node_file(){
for no in $(seq -w 7001 7006)
do
echo -e include $conf/redis-common.conf > "$conf/$no.conf"
echo -e port $no >> "$conf/$no.conf"
echo -e logfile $logs/$no.log >> "$conf/$no.conf"
echo -e maxmemory 400m >> "$conf/$no.conf"
echo -e maxmemory-policy allkeys-lru >> "$conf/$no.conf"
echo -e appendfilename appendonly-$no.aof >> "$conf/$no.conf"
echo -e dbfilename dump-$no.rdb >> "$conf/$no.conf"
echo -e cluster-config-file node-$no.conf >> "$conf/$no.conf"
echo -e auto-aof-rewrite-percentage 80-100 >> "$conf/$no.conf"
done
}
if [ ! -f "$conf/7001.conf" ];then
node_file
fi
function started(){
for no in $(seq -w 7001 7006) 
do
echo " $redis/bin/redis-server $conf/$no.conf"
$redis/bin/redis-server $conf/$no.conf
done
}
function stoped(){
for pid in $(ps -ef | grep redis | grep 700*| awk '{print $2}')
do
kill $pid
done
}
function init(){
echo "init redis cluster "
$redisSrc/redis-trib.rb create --replicas 1 127.0.0.1:7006 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005

}
function usafe(){
echo -e "Usafe $0 palse in :"
echo -e "[started] started redis cluster"
echo -e "[stoped] stop all redis cluster"
echo -e "[rcf] reset $redis/redis-common.conf"
echo -e "[nodefile] reset all node conf"
echo -e "[init] init redis cluser"
}
case $1 in
started) started;;
stoped) stoped;;
rcf) common_file;;
nodefile) node_file;;
init) init;;
*) usafe;;
esac

#####run 
#chmod +x xxx.sh
#sh xxx.sh started  -- >脚本会自动的安装redis的相关依赖软件  安装完成之后会进行redis的server的启动
#sh xxx.sh init 初始化集群
 
 
