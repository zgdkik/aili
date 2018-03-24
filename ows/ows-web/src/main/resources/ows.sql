/*
Navicat MySQL Data Transfer

Source Server         : hbhk
Source Server Version : 50173
Source Host           : 139.196.180.16:3306
Source Database       : ows

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-09-14 10:25:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_html
-- ----------------------------
DROP TABLE IF EXISTS `tb_html`;
CREATE TABLE `tb_html` (
  `html_id` varchar(50) NOT NULL,
  `html_content` text,
  `html_backup` text,
  `menu_id` varchar(50) NOT NULL,
  `change_date` datetime NOT NULL,
  `change_user` varchar(50) NOT NULL,
  `comp_Code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`html_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_html
-- ----------------------------
INSERT INTO `tb_html` VALUES ('158be463bfb54b1f96b62fd89428861c', '<p><span style=\"font-size:16px\"><span style=\"font-family:arial,helvetica,sans-serif\"><span style=\"color:rgb(68, 68, 68)\">&nbsp; &nbsp; </span></span></span><span style=\"font-size:14px\"><span style=\"font-family:arial,helvetica,sans-serif\"><span style=\"color:rgb(68, 68, 68)\">&nbsp; &nbsp;</span><span style=\"color:rgb(68, 68, 68)\">我公司成立于1999年，是湖北省内知名物流企业，总部设在武汉市东西湖台商投资区，主营华中、华东及华南地区的仓储、运输、包装及市内配送等业务，目前营业网点100多个，网络覆盖湖北、湖南、广州、上海等省，并逐步扩张。现有经营场地8万余平方米，标准化库房3万余平方米，员工1400余人，可调配车辆300余台。年吞吐量达130余万吨。已与东风汽车、金牛管业、亚太味业、百事可乐、诺贝尔瓷砖等国内外众多知名企业建立了牢固的合作伙伴关系。<br />\n&nbsp; &nbsp; &nbsp; 本公司在业务迅猛发展的同时狠抓质量管理，已通过了ISO：9001－2000国际认证，形成了以各岗位工作考核标准化为内容的质量保障体系，做到了安全正点、优质服务、结算准确、到货及时，成为众多企业拓展营销网络、降低物流成本的开路先锋，得到了广泛的赞誉。2012年12月，公司通过中国物流采购联合会AAAA评审，被授予AAAA级企业。2014年2月被中国道路运输协会授予&ldquo;中国道路运输领袖品牌&rdquo;。<br />\n&nbsp; &nbsp; &nbsp; 21世纪是信息倍速传播的时代。2005年12月，《大道物流信息管理系统》正式投入使用，已从1.0版升级到5.0版，实现了货物流、资金流、信息流的有机结合与高效运转，客户可登陆公司网<a href=\"http://www.ddwl.com.cn/\" style=\"color: rgb(68, 68, 68); text-decoration: none;\">www.ddwl.com.cn</a>对货物信息及代收款的收付等情况进行适时跟踪，公司信息化系统与中国建设银行系统成功对接，实现了网上支付货款，大大降低了资金安全风险，提高了服务质量。<br />\n&nbsp; &nbsp; &nbsp; 从2011年开始，公司加快了信息化、机械化、网络化的步伐。目前，公司实行理货扫描、库区作业基本实现机械化，大大提高工作效率；营业网点全面实现电脑开单，所有货物信息可实现适时查询。网点建设的迅速铺开已在湖北省内基本形成了一个密集的无缝隙配送网络，并逐步向华东、华中、华南地区辐射扩张。公司在工作及生活区全面推行6S管理，并在员工中倡导低碳、环保的生活理念；个性化的文化活动蓬勃开展，营运速度全面提升。2011年湖北省交通运输厅物流发展局将公司评为&ldquo;物流示范企业&rdquo;；由武汉企业家协会、武汉企业联合会与武汉出版集团联合主办的《天下汉商》杂志，还以《左手信息，右手文化》为题，专题报道了我公司的发展之路；由中国物流与采购联合会主办的《现代物流报》以《文化底蕴铸就品牌》对我公司进行了连续报道。&nbsp;&nbsp;&nbsp;&nbsp;<br />\n&nbsp; &nbsp; &nbsp;&nbsp;</span>2015年公司进入了新一轮高速发展时期，我们不断推陈出新、积极变革，我们将始终秉承&ldquo;优化物流服务、提升产业价值、愉悦每位客户&rdquo;的使命，向着&ldquo;中部第一、全国十强&rdquo;的目标全速前进！</span></span></p>\n', null, 'zjdd_gsjj', '2016-09-08 17:17:58', 'admin', 'yimidida');
INSERT INTO `tb_html` VALUES ('1917348064b443f8954a8b4b62e76e3b', '<p>■干线运输班车化：定时定班，货发全国。</p>\n\n<p>我公司主要采用公路运输，可为您提供湖北、广东、湖南、上海全境运输服务。湖北省内各线及长沙、岳阳专线每日发车，次日到货；上海两日内到货；广州专线三日内到货。&nbsp;</p>\n', null, 'ddwl_cpfw_gxys', '2016-09-08 19:25:24', 'admin', 'yimidida');
INSERT INTO `tb_html` VALUES ('38c7acd7abf3471395d9e549d12f449c', '<p>最安全、最快捷的代收货款业务</p>\n\n<p>■　三日付款：我公司确认收到货款后三个工作日内将货款支付到您指定的贵宾卡内。</p>\n\n<p>■　代收货款手续费标准：代收金额200元以下的，按2元/票收取手续费；代收金额在201&mdash;3000元的，按4元/单收取，代收金额在3001元以上，按0.15%收取，每单手续费40元封顶。</p>\n', null, 'ddwl_cpfw_dshk', '2016-09-08 17:51:05', 'admin', 'yimidida');
INSERT INTO `tb_html` VALUES ('3d1054650dd14a56bb8c4ca55d0f22af', '<div style=\"color: rgb(68, 68, 68); font-family: 宋体, Verdana, Arial; font-size: 12px; line-height: 26.4px;\">■仓储服务：配套齐全、安全放心。</div>\n\n<div style=\"color: rgb(68, 68, 68); font-family: 宋体, Verdana, Arial; font-size: 12px; line-height: 26.4px;\">我公司仓库有2万余平方米，物流园内各项装卸设备齐全，可集仓储、装卸、运输、配送于一体。24小时有人巡查，安全放心。</div>\n', null, 'ddwl_cpfw_ccfw', '2016-09-09 15:50:45', 'admin', 'yimidida');
INSERT INTO `tb_html` VALUES ('564f9797babc4b18b9bac5f4db61175a', '<div style=\"color: rgb(68, 68, 68); font-family: 宋体, Verdana, Arial; font-size: 12px; line-height: 26.4px;\">■市场活动</div>\n\n<div style=\"color: rgb(68, 68, 68); font-family: 宋体, Verdana, Arial; font-size: 12px; line-height: 26.4px;\">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;根据客户需求和公司业务发展，我公司决定于6月18日在武汉市江夏区纸坊武昌大道（兴邦华庭）对面设立营业部。</div>\n\n<div style=\"color: rgb(68, 68, 68); font-family: 宋体, Verdana, Arial; font-size: 12px; line-height: 26.4px;\">截止目前，我公司已在湖北、湖南、广东、上海等地设立60余家分公司，在武汉市内设有20余家营业部，以&ldquo;专线直达、天天</div>\n\n<div style=\"color: rgb(68, 68, 68); font-family: 宋体, Verdana, Arial; font-size: 12px; line-height: 26.4px;\">发车&rdquo;的营运模式服务广大客户。同时我公司已全面实行到货短信通知和语音通知，总部及武汉各营业部一律通过银行转账支付</div>\n\n<div style=\"color: rgb(68, 68, 68); font-family: 宋体, Verdana, Arial; font-size: 12px; line-height: 26.4px;\">代收款，安全便捷。</div>\n', null, 'ddwl_cpfw_scfw', '2016-09-08 17:54:32', 'admin', 'yimidida');
INSERT INTO `tb_html` VALUES ('c82ad31c55864d568ce1cbe70425dabc', '<p><span style=\"color:#6633ff\"><strong>企业愿景</strong></span></p>\n\n<p>中部第一 全国十强</p>\n\n<hr />\n<p><span style=\"color:#6633ff\"><strong>大道精神</strong></span><br />\n知难而进 永创新高</p>\n\n<hr />\n<p><span style=\"color:#6633ff\"><strong>服务宗旨</strong></span></p>\n\n<p>安全 快捷 优质</p>\n\n<hr />\n<p><span style=\"color:#6633ff\"><strong>文化理念</strong></span></p>\n\n<p>管理：我是一切的根源 服务：让客户与我们的每次合作都成为愉快的记忆 工作：有成果就有报酬 无成果就是耻辱 执行：不是做了 而是做到 更要做好 学习：知识转化成能力 能力转化成生产力 创新：其实只需要改变一点点 善恶：小爱大恶 奖罚：奖得心动 罚得心痛 成长：使我痛苦者必使我强大 晋升：屡败屡战 敢于PK 精英：能将任何负能量转化为正能量的人</p>\n\n<p>&nbsp;</p>\n', null, 'zjdd_qyln', '2016-09-08 17:30:24', 'admin', 'yimidida');
INSERT INTO `tb_html` VALUES ('ddd34d1e83c643b5bb9471128ab42e14', '<div style=\"color: rgb(68, 68, 68); font-family: 宋体, Verdana, Arial; font-size: 12px; line-height: 26.4px;\">■信息服务：全程跟踪，适时查询。</div>\n\n<p><span style=\"color:rgb(68, 68, 68); font-family:宋体,verdana,arial; font-size:12px\">当您在我公司发运货物后，可在本公司网站上输入运单号和发（收）货方电话号码，即可查询代收款、回单等相关信息。</span></p>\n\n<p><span style=\"color:rgb(68, 68, 68); font-family:宋体,verdana,arial; font-size:12px\">此项服务可帮助您及时了解货物装车、发车、到货、提货、代收金额等相关信息。贵宾卡客户与公司签订网上查询协议后，</span></p>\n\n<p><span style=\"color:rgb(68, 68, 68); font-family:宋体,verdana,arial; font-size:12px\">可登陆本公司网站查询转帐情况，打印详细转帐清单。</span></p>\n', null, 'ddwl_cpfw_xxfw', '2016-09-08 17:51:44', 'admin', 'yimidida');
INSERT INTO `tb_html` VALUES ('f2a378c5e4fc494c98fc0fd0ece533f8', '<p>■配送服务：送货上门，服务贴心。</p>\n\n<p>上门取货：距总部及营业部10km范围内等不禁行路段，货量达到1.5T或5m&sup3;免费上门取货，当天14：00后将根据车辆情况再作临时安排；送货上门：市内（外）送货视距离远近收取相应的配送服务费，提供送货服务。</p>\n', null, 'ddwl_cpfw_psfw', '2016-09-08 17:55:19', 'admin', 'yimidida');
INSERT INTO `tb_html` VALUES ('f3e2d8eb229e49e9821d8e5c15a6c14b', '<p>■保价运输：快速理赔，全程无忧。</p>\n\n<p><span style=\"color:rgb(68, 68, 68); font-family:宋体,verdana,arial; font-size:12px\">当您发货时，只需申明货物价值，缴纳一定比例的保价费，货物的运输风险将全部转降至我公司，当您的货物发现损坏或丢失时，我们将按货物的实际损失全额赔偿。</span></p>\n', null, 'ddwl_cpfw_bjys', '2016-09-09 17:16:04', 'admin', 'yimidida');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `menu_id` varchar(20) NOT NULL,
  `menu_name` varchar(50) NOT NULL,
  `menu_parentid` varchar(20) NOT NULL,
  `menu_viewurl` varchar(200) DEFAULT NULL,
  `menu_backurl` varchar(200) DEFAULT NULL,
  `menu_icon1` varchar(50) DEFAULT NULL,
  `menu_icon2` varchar(50) DEFAULT NULL,
  `menu_status` int(11) NOT NULL,
  `menu_sort` int(11) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_user` int(11) NOT NULL,
  `change_date` datetime DEFAULT NULL,
  `change_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` varchar(50) NOT NULL,
  `notice_title` varchar(200) DEFAULT NULL COMMENT '标题',
  `notice_summary` varchar(2000) DEFAULT NULL COMMENT '摘要',
  `notice_type` int(11) DEFAULT NULL COMMENT '类型',
  `notice_img` varchar(100) DEFAULT NULL COMMENT '图片',
  `notice_content` text COMMENT '内容',
  `notice_status` int(11) DEFAULT NULL COMMENT '状态',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建用户',
  `change_date` datetime DEFAULT NULL,
  `change_user` varchar(50) DEFAULT NULL,
  `notice_running` int(11) DEFAULT NULL COMMENT '消息是否进行',
  `comp_code` varchar(50) DEFAULT NULL COMMENT '新闻动态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES ('1', '1', '1', '1', '1', '1', '1', '2016-09-08 17:54:28', '1', '2016-09-09 17:54:38', '1', '1', '1');
INSERT INTO `tb_notice` VALUES ('2', '2', '2', '2', '2', '2', '2', '2016-09-08 17:55:05', '2', '2016-09-09 17:55:08', '2', '2', '2');
INSERT INTO `tb_notice` VALUES ('2a6415f37c834fd1b007610513a30d3c', '11', '11111', '0', null, '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('3f8bace477a146a09a6c7b47fcbdced7', '', null, '0', '', '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('4410d987bcf8471cbbd4f1c8c2145564', '111', '2121', '0', '', '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('524076de56bd46efb96da40d11e3acf7', '555555', '555555', '0', '', '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('689cc9d0431546bca083c102ac63d96b', '2222222', '22222', '0', '', '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('ad7a86a2b29147e88916576b7992c21c', '66666', null, '0', '', '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('b5d8165998904c34bb154c7afda061a5', '', '', '0', null, '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('b69cd5f4aea94db0aa05699715fef7e5', '555', '55555', '0', '', '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('bd4f5195e9d5408c8232b10c6b4a02a3', '1212', '1221', '0', null, '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('e876510619c24853b7168e219ab6bd8c', '44', '444', '0', null, '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('eb4b62e96e424360a8fb464a40b2530f', '2121', '21212', '0', null, '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('efc5e0c47f4243cb939d3b3fb761247a', '77', '777', '0', '', '', '1', null, null, null, null, '0', null);
INSERT INTO `tb_notice` VALUES ('fb8abc074b8f493fb0349eae120c867c', '33', '3333', '0', null, '', '1', null, null, null, null, '0', null);

-- ----------------------------
-- Table structure for tb_ows_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_ows_user`;
CREATE TABLE `tb_ows_user` (
  `id` varchar(50) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `compCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_ows_user
-- ----------------------------
INSERT INTO `tb_ows_user` VALUES ('1', '13020205713', '123456', '2016-09-10 14:52:39', '2016-09-10 14:52:42', '1', 'ddwl');

-- ----------------------------
-- Table structure for tb_recruit
-- ----------------------------
DROP TABLE IF EXISTS `tb_recruit`;
CREATE TABLE `tb_recruit` (
  `recruit_id` varchar(50) NOT NULL,
  `recruit_title` varchar(200) DEFAULT NULL,
  `recruit_area` varchar(200) DEFAULT NULL,
  `recruit_category` varchar(200) DEFAULT NULL,
  `recruit_mail` varchar(200) DEFAULT NULL,
  `recruit_count` varchar(20) DEFAULT NULL,
  `recruit_job` varchar(2000) DEFAULT NULL,
  `recruit_requirement` varchar(2000) DEFAULT NULL,
  `recruit_welfare` varchar(2000) DEFAULT NULL,
  `recruit_contact` varchar(300) DEFAULT NULL,
  `recruit_begindate` datetime DEFAULT NULL,
  `recruit_enddate` datetime DEFAULT NULL,
  `recruit_status` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `change_date` datetime DEFAULT NULL,
  `change_user` varchar(50) DEFAULT NULL,
  `compCode` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_recruit
-- ----------------------------
INSERT INTO `tb_recruit` VALUES ('e747021b3db340bfaea8a4b59fc7317c', '456', '789', '1', '1', '1', '1', '1', '1', '1', '2016-09-16 00:00:00', '2016-09-16 00:00:00', '1', '2016-09-09 15:30:56', null, '2016-09-09 18:15:21', 'admin', 'ddwl');
INSERT INTO `tb_recruit` VALUES ('80a1ec78f4d14ccf8d904c45599c7fc8', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2016-09-09 00:00:00', '2016-09-09 00:00:00', '1', '2016-09-09 15:31:06', null, '2016-09-09 16:39:02', 'admin', 'ddwl');
INSERT INTO `tb_recruit` VALUES ('46dc4524536846348655fccd7a2b56f5', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, null, '1', '2016-09-09 15:31:27', null, '2016-09-09 15:31:27', 'admin', 'ddwl');
INSERT INTO `tb_recruit` VALUES ('ace9323efdc1456caf5693c01cecc06c', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, null, '1', '2016-09-09 15:33:56', null, '2016-09-09 15:33:56', 'admin', 'ddwl');
INSERT INTO `tb_recruit` VALUES ('bc1f6bcb25f8473cb5b07bd2084c08cb', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, null, '1', '2016-09-09 15:38:07', null, '2016-09-09 15:38:07', 'admin', 'ddwl');
INSERT INTO `tb_recruit` VALUES ('5994b2be20164b5982bf8229ccc5d138', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, null, '1', '2016-09-09 15:40:20', null, '2016-09-09 15:40:20', 'admin', 'ddwl');
INSERT INTO `tb_recruit` VALUES ('5df54e9eae704b14af42ccdc0aac28a9', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, null, '1', '2016-09-09 15:40:35', null, '2016-09-09 15:40:35', 'admin', 'ddwl');
INSERT INTO `tb_recruit` VALUES ('3f410b0806564fd5a0a0c0f904d3fb5e', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, null, '1', '2016-09-09 15:43:38', null, '2016-09-09 15:43:38', 'admin', 'ddwl');
INSERT INTO `tb_recruit` VALUES ('dc8a7610c39c489997435541f1a85f8a', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, null, '1', '2016-09-09 15:52:15', null, '2016-09-09 15:52:15', 'admin', 'ddwl');
INSERT INTO `tb_recruit` VALUES ('4de04196443a4c7ead3ae5c45a3e9ec9', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, null, '1', '2016-09-09 15:52:38', null, '2016-09-09 15:52:38', 'admin', 'ddwl');

-- ----------------------------
-- Table structure for t_auth_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_privilege`;
CREATE TABLE `t_auth_privilege` (
  `id` varchar(50) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `gwUrl` varchar(255) DEFAULT NULL,
  `parent_code` varchar(50) DEFAULT NULL,
  `display_order` varchar(255) DEFAULT NULL,
  `checkable` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `leaf` varchar(255) DEFAULT NULL,
  `icon_cls` varchar(255) DEFAULT NULL,
  `cls` varchar(255) DEFAULT NULL,
  `descp` varchar(2000) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `shoutcut_keys` varchar(50) DEFAULT NULL,
  `module_type` varchar(50) DEFAULT NULL,
  `app_type` varchar(50) DEFAULT NULL,
  `compCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_privilege
-- ----------------------------
INSERT INTO `t_auth_privilege` VALUES ('007729a4f1c84753b83b564ee179fea7', 'ddwl_cpfw_ccfw', '仓储服务', '/ddwl/introduce?roleCode=ddwl_cpfw_ccfw', '/ddwlGw/productService?roleCode=ddwl_cpfw_ccfw', 'cpfw', '6', null, 'MENU', null, '', null, '', 'admin', '2016-09-08 17:40:55', 'admin', '2016-09-08 19:31:30', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('2326705a1d9b486da97b1f7027218d6d', 'ddwl_cpfw_psfw', '配送服务', '/ddwl/introduce?roleCode=ddwl_cpfw_psfw', '/ddwlGw/productService?roleCode=ddwl_cpfw_psfw', 'cpfw', '4', null, 'MENU', null, '', null, '', 'admin', '2016-09-08 17:38:49', 'admin', '2016-09-08 19:31:40', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('28362de9c22a431280ad9ae2b42b4a22', 'zjdd_gsjj', '公司简介', '/ddwl/introduce?roleCode=zjdd_gsjj', '/ddwl/introduce?roleCode=zjdd_gsjj', 'ddwl_zjdd', '1', null, 'MENU', null, '', null, '', 'admin', '2016-09-08 11:44:40', 'admin', '2016-09-08 16:31:07', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('3c2391dca726473f979b8e952b2cc59b', 'ddwl_zjdd', '走进大道', 'ddwl_zjdd', null, 'platform', '1', null, 'WORKBENCH', null, '', null, '', 'admin', '2016-09-08 11:43:15', null, null, '1', '', 'WORKBENCH', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('3e0d3526b11946dc82cdbe0da26ae611', 'zjdd_qyln', '企业理念', '/ddwl/introduce?roleCode=zjdd_qyln', '/ddwlGw/introduce?roleCode=zjdd_qyln', 'ddwl_zjdd', '2', null, 'MENU', null, '', null, '', 'admin', '2016-09-08 11:47:10', 'admin', '2016-09-08 17:21:29', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('3f426e162c6240f487bd089101ecf181', 'ddwl_xwzx_gsxw', '公司新闻', '/ddwl/noticeManage?type=2', '/ddwlGw/notice?type=2', 'ddwl_xwzx', '2', null, 'MENU', null, '', null, '', 'admin', '2016-09-09 10:39:54', null, null, '1', '', 'MENU', 'WEB', null);
INSERT INTO `t_auth_privilege` VALUES ('41312950-2765-43d0-9478-9576b15df9a7', '12', '菜单管理', '/privilege/privilegelist', null, '1', '2', null, 'MENU', null, null, null, null, null, null, null, null, '1', null, 'MENU', 'common', null);
INSERT INTO `t_auth_privilege` VALUES ('4249ffd3-a537-4046-a96f-8ff3781ebdd4', '0', '应用系统', '/', null, '', '1', null, 'Y', null, null, null, null, null, null, null, null, '1', null, 'Root', 'common', null);
INSERT INTO `t_auth_privilege` VALUES ('556b94cd-2083-4246-bdf2-6ebc7c354ce5', '21', '数据字典', '/dict/showDictIndex', null, '2', '1', null, 'MENU', null, null, null, null, null, null, null, null, '1', null, 'MENU', 'common', null);
INSERT INTO `t_auth_privilege` VALUES ('5a3c36ae-8f8b-4db3-abdc-391aa6cfb27c', '22', '系统参数', '/common/systemParameterList', null, '2', '2', null, 'MENU', null, null, null, null, null, null, null, null, '1', null, 'MENU', 'common', null);
INSERT INTO `t_auth_privilege` VALUES ('71423e11da734041ab5804f8ab687f8e', 'platform', '平台系统', '/platform', null, '0', '1', null, 'Root', null, null, null, null, null, null, null, null, '1', null, 'Root', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('74f39a33-d346-461d-9293-786745df26b9', '2', '系统配置', '/sysconfig', null, 'platform', '7', null, 'Y', null, null, null, null, null, null, null, null, '1', null, 'WORKBENCH', 'common', null);
INSERT INTO `t_auth_privilege` VALUES ('78c6d367-637c-4222-914a-c99d551d5477', '14', '用户管理', '/user/userlist', null, '1', '4', null, 'MENU', null, null, null, null, null, null, null, null, '1', null, 'MENU', 'common', null);
INSERT INTO `t_auth_privilege` VALUES ('84fc3d48-5e67-4b1c-9d1c-1c283dd6649b', '1', '权限管理', '/auth', null, 'platform', '8', null, 'MENU', null, null, null, null, null, null, null, null, '1', null, 'WORKBENCH', 'common', null);
INSERT INTO `t_auth_privilege` VALUES ('8d60668e2e2c4dbbb1310d0fe7e4089b', 'ddwl_xwzx_tzgg', '通知公告', '/ddwl/noticeManage?type=1', '/ddwlGw/notice?type=1', 'ddwl_xwzx', '1', null, 'MENU', null, '', null, '', 'admin', '2016-09-09 10:13:07', 'admin', '2016-09-09 10:34:04', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('9d4ecb1b2b8340388b0dd6ff718fc165', 'ddwk_ddzp_yxyg', '优秀员工', '/ddwl/excellentPeopleManage', '/ddwlGw/excellentPeople', 'ddwl_ddzp', '1', null, 'MENU', null, '', null, '', 'admin', '2016-09-09 11:31:49', 'admin', '2016-09-09 11:33:10', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('aec5b79918894d2a941fc36a5de2ab7a', 'ddwl_cpfw_bjys', '保价运输', '/ddwl/introduce?roleCode=ddwl_cpfw_bjys', '/ddwlGw/productService?roleCode=ddwl_cpfw_bjys', 'cpfw', '3', null, 'MENU', null, '', null, '', 'admin', '2016-09-08 17:38:04', 'admin', '2016-09-08 19:31:47', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('af760a4d0ff5488cbbe2c44ebe9f1628', 'ddwl_xwzx', '新闻资讯', 'ddwl_xwzx', 'ddwl_xwzx', 'platform', '5', null, 'WORKBENCH', null, '', null, '', 'admin', '2016-09-09 10:03:32', null, null, '1', '', 'WORKBENCH', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('c70ed2afecd0480bb29fb283a918732f', 'ddwl_ddzp', '大道招聘', 'ddwl_ddzp', 'ddwl_ddzp', 'platform', '6', null, 'WORKBENCH', null, '', null, '', 'admin', '2016-09-09 11:28:04', null, null, '1', '', 'WORKBENCH', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('ce416c9edfd44dacb708d2f7efd74904', 'ddwl_cpfw_gxys', '干线运输', '/ddwl/introduce?roleCode=ddwl_cpfw_gxys', '/ddwlGw/productService?roleCode=ddwl_cpfw_gxys', 'cpfw', '1', null, 'MENU', null, '', null, '', 'admin', '2016-09-08 17:48:35', 'admin', '2016-09-08 19:31:55', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('d5f0a64d59cd4571a000c479cee9c0c7', 'ddwl_cpfw_scfw', '市场活动', '/ddwl/introduce?roleCode=ddwl_cpfw_scfw', '/ddwlGw/productService?roleCode=ddwl_cpfw_scfw', 'cpfw', '7', null, 'MENU', null, '', null, '', 'admin', '2016-09-08 17:42:24', 'admin', '2016-09-08 19:32:05', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('d86282b4ef8e4ae69416d76ab40f41a8', 'map-area', '区域维护', '/areaMap/area', null, '4', '3', null, 'MENU', null, null, null, null, null, null, null, null, '1', null, 'MENU', 'map', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('d8b70859-7576-4103-9093-3dbe0537f6a7', '11', '角色管理', '/role/roleList', null, '1', '1', null, 'N', null, null, null, null, null, null, null, null, '1', null, 'MENU', 'common', '');
INSERT INTO `t_auth_privilege` VALUES ('ec6049b1f5dd459eada3dbf6e75892ce', 'ddwl_ddzp_zpxx', '招聘信息', '/ddwl/recruitManage', '/ddwlGw/recruit', 'ddwl_ddzp', '2', null, 'MENU', null, '', null, '', 'admin', '2016-09-09 11:29:21', 'admin', '2016-09-09 11:33:18', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('f6e8e29c1f9449a3a910f0ff612e96ad', 'ddwl_cpfw_xxfw', '信息服务', '/ddwl/introduce?roleCode=ddwl_cpfw_xxfw', '/ddwlGw/productService?roleCode=ddwl_cpfw_xxfw', 'cpfw', '5', null, 'MENU', null, '', null, '', 'admin', '2016-09-08 17:39:57', 'admin', '2016-09-08 19:32:12', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('f9107bfda09d4039ba75a4cafe44f67f', 'ddwl_cpfw_dshk', '代收货款', '/ddwl/introduce?roleCode=ddwl_cpfw_dshk', ' /ddwlGw/productService?roleCode=ddwl_cpfw_dshk', 'cpfw', '2', null, 'MENU', null, '', null, '', 'admin', '2016-09-08 17:36:50', 'admin', '2016-09-08 19:32:20', '1', '', 'MENU', 'WEB', 'ddwl');
INSERT INTO `t_auth_privilege` VALUES ('fb553d30decc4fbdbf4a8049c6f460e9', 'cpfw', '产品服务', 'cpfw', null, 'platform', '2', null, 'WORKBENCH', null, '', null, '', 'admin', '2016-09-08 16:03:23', null, null, '1', '', 'WORKBENCH', 'WEB', 'ddwl');

-- ----------------------------
-- Table structure for t_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role`;
CREATE TABLE `t_auth_role` (
  `id` varchar(50) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_code` varchar(50) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `dept_code` varchar(50) DEFAULT NULL,
  `role_type` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `comp_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_role
-- ----------------------------
INSERT INTO `t_auth_role` VALUES ('1', null, null, null, null, 'admin', 'admin', null, null, null, '1', null);

-- ----------------------------
-- Table structure for t_auth_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role_privilege`;
CREATE TABLE `t_auth_role_privilege` (
  `ROLE_CODE` varchar(50) DEFAULT NULL,
  `PRIVILEGE_CODE` varchar(50) DEFAULT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `UPDATE_USER` varchar(20) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `ID` varchar(50) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_role_privilege
-- ----------------------------
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_ddzp', null, null, null, '1', '01681336eca24dbcaf66db664b8ea14a', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_xwzx_tzgg', null, null, null, '1', '0deba52dc46b4bd99477c7c40dacdf41', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '1', null, null, null, '1', '1e85ab66cb5e45be81845188977c1798', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_cpfw_xxfw', null, null, null, '1', '2da8787b4831454aa57fb62a1a99e10e', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_cpfw_ccfw', null, null, null, '1', '2fec48da25ab4616bf89dd7cbf4c9447', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_cpfw_psfw', null, null, null, '1', '45346f9c97304fb7baa8f1b1edf64b06', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'platform', null, null, null, '1', '682953b5cea54135a33ca75260ce42ca', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_cpfw_dshk', null, null, null, '1', '70a8290ab0124d348272e943dc93d96e', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '22', null, null, null, '1', '7887777b510246a6942a59c289ee3f2d', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'zjdd_qyln', null, null, null, '1', '7bd87b41e1084957a0e3b32f022b250f', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_zjdd', null, null, null, '1', '7dfafd8d5e1a4cbaa20c71289a150f66', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '14', null, null, null, '1', '7ef059cd2a4c4d7b967021e7cf1e03ae', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '0', null, null, null, '1', '8604563affe643308f735e7d592f6ecf', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'zjdd_gsjj', null, null, null, '1', '94649f5743134a6bb3d802393dcb08d1', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_cpfw_gxys', null, null, null, '1', '997dafd36afb4c65bce61bab0905e95a', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_xwzx_gsxw', null, null, null, '1', 'a04d4f7ee598436b909e904f42a5966b', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_xwzx', null, null, null, '1', 'aa12cfe8f3ff47808ed01c41cfc63ab3', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_ddzp_zpxx', null, null, null, '1', 'c09710db0ced4084b85e97bbcfc903dd', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwk_ddzp_yxyg', null, null, null, '1', 'c4725fc5e7b34802b98229f71eeb01b6', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_cpfw_bjys', null, null, null, '1', 'd6c2ae9403e84a19adde0d1b60d14be2', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'ddwl_cpfw_scfw', null, null, null, '1', 'd8adea95c5f449e2a4add21c86ac1ba0', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '21', null, null, null, '1', 'd91ccc9e3d9445d6932b1bf9ee2e4f8d', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '11', null, null, null, '1', 'e17d9e9cc5ea4cc6b26a01e3236a930c', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', 'cpfw', null, null, null, '1', 'e205728ba9074d5e818b7d7eb9c7524a', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '2', null, null, null, '1', 'ed2e5f229c674a2c923f5e7c4a94807c', '2016-09-09 11:32:03');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '12', null, null, null, '1', 'fb436f134a0e4c5f826f380201707d56', '2016-09-09 11:32:03');

-- ----------------------------
-- Table structure for t_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_user`;
CREATE TABLE `t_auth_user` (
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `id` varchar(255) NOT NULL DEFAULT '',
  `create_time` timestamp NULL DEFAULT NULL,
  `comp_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_user
-- ----------------------------
INSERT INTO `t_auth_user` VALUES ('admin', 'VUCMO1MKgCFF6D7zkR78bQ==', 'admin', 'admin', '2016-09-06 16:37:22', '1', '1', '2016-09-06 16:37:26', 'yimidida');

-- ----------------------------
-- Table structure for t_auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_user_role`;
CREATE TABLE `t_auth_user_role` (
  `ID` varchar(40) NOT NULL,
  `USER_CODE` varchar(40) DEFAULT NULL,
  `ROLE_CODE` varchar(40) DEFAULT NULL,
  `CREATE_USER` varchar(40) DEFAULT NULL,
  `UPDATE_USER` varchar(40) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_user_role
-- ----------------------------
INSERT INTO `t_auth_user_role` VALUES ('1', 'admin', 'admin', null, null, null, null, '1');

-- ----------------------------
-- Table structure for t_common_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_common_dict`;
CREATE TABLE `t_common_dict` (
  `DICT_CODE` varchar(50) DEFAULT NULL,
  `DICT_NAME` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `UPDATE_USER` varchar(255) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `ID` varchar(50) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `PARENT_DICT_CODE` varchar(50) DEFAULT NULL,
  `IS_LEAF` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_common_dict
-- ----------------------------
INSERT INTO `t_common_dict` VALUES ('BUS_TYPE', '业务类型', null, null, null, null, '1', '', null, '0', '0');
INSERT INTO `t_common_dict` VALUES ('PRODICT_TYPE_CODE', '产品类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('module_type', '权限类型', null, null, null, null, '1', '', null, 'COMMON_DICT', '1');
INSERT INTO `t_common_dict` VALUES ('SETT_OP_CODE', '结算操作编码', null, null, null, null, '0', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('BIZ_TYPE_CODE', '业务类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('YES_NO', '是否', null, null, null, null, '1', '', null, 'COMMON_DICT', '1');
INSERT INTO `t_common_dict` VALUES ('SETTMENT_TYPE', '结算方式', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('R_D_TYPE', '收到类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('GOODS_CHARGE_BANKACCOUNT_TYPE', '银行类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('PRINT_TYPE', '补印类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('DISPATCH_LOG_OPER_TYPE_CODE', '分派日志类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('INTER_COMPLAINTS_PROCESS_STATE', '投诉处理状态', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('PAYMENT_TYPE', '付款方式', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('GOODS_CHARGE_PAYMENT_TYPE', '代收货款转款状态', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('GOODS_CHARGE_OPER_TYPE', '代收货款操作状态', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('PROCESS_STATE', '问题件处理状态', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('rule', '规则引擎', null, null, null, null, '1', '', null, '0', '0');
INSERT INTO `t_common_dict` VALUES ('condition-type', '条件类型', null, null, null, null, '1', '', null, 'rule', '1');
INSERT INTO `t_common_dict` VALUES ('system_type', '系统类型', null, null, null, null, '0', '', null, 'COMMON_DICT', '1');
INSERT INTO `t_common_dict` VALUES ('POST_TYPE', '职位类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('ABNORMAL_WRAP_CONDITION', '外包装情况', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('subscribe-status', '预约状态', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('0', '数据字典总分类', null, null, null, null, '1', '', null, '', '0');
INSERT INTO `t_common_dict` VALUES ('COMMON_DICT', '通用类型', null, null, null, null, '1', '', null, '0', '0');
INSERT INTO `t_common_dict` VALUES ('FREEZED_FLAG', '冻结标示', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('SETT_FLAG', '是否结算', null, null, null, null, '0', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('ACCOUNT_END_WITH', '账号后缀', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('COMPLANTS_TYPE', '投诉类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('INTER_COMPLAINTS_RESULT_OPER_CODE', '投诉处理类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('INTER_COMPLAINTS_RESULT_COMP_CODE', '处罚公司类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('COMPLANTS_TYPE_REF', '投诉类别', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('system_type', '系统类型', null, null, null, null, '1', '', null, 'COMMON_DICT', '1');
INSERT INTO `t_common_dict` VALUES ('vehicle-type', '车辆类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('return-order', '退单原因', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('BAR_OP_TYPE', '结算操作编码', null, null, null, null, '0', '', null, 'BUS_TYPE', '0');
INSERT INTO `t_common_dict` VALUES ('01', '上门收件', null, null, null, null, '0', '', null, 'BAR_OP_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('SERVICE_TYPE', '服务方式', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('BAR_INPUT_TYPE_CODE', '巴枪操作类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');
INSERT INTO `t_common_dict` VALUES ('return-waybill', '退单类型', null, null, null, null, '1', '', null, 'BUS_TYPE', '1');

-- ----------------------------
-- Table structure for t_common_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `t_common_dict_value`;
CREATE TABLE `t_common_dict_value` (
  `DICT_KEY` varchar(50) DEFAULT NULL,
  `DICT_VALUE` varchar(50) DEFAULT NULL,
  `DICT_CODE` varchar(50) DEFAULT NULL,
  `ORDER_NO` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `UPDATE_USER` varchar(50) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `ID` varchar(50) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `PARAMONE` varchar(50) DEFAULT NULL,
  `PARAMTWO` varchar(50) DEFAULT NULL,
  `PARAMTHREE` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_common_dict_value
-- ----------------------------
INSERT INTO `t_common_dict_value` VALUES ('3', '回单付', 'PAYMENT_TYPE', '3', null, null, null, null, '1', '480c59908c744c23ac7df1d65ed641e2', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '代收扣运', 'SETTMENT_TYPE', '2', null, null, null, null, '1', '8c02a01f634b49c984bd380f6d937697', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('3', '月结', 'SETTMENT_TYPE', '3', null, null, null, null, '1', '46cb50ea59da4c669e070b8727503ee6', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('14', '超期', 'GOODS_CHARGE_OPER_TYPE', '6', null, null, null, null, '1', '1bb8a07c8208430f88f71e762e86ab53', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('74', '超期审核', 'GOODS_CHARGE_OPER_TYPE', '7', null, null, null, null, '1', 'a0687ff790ee40968d106edb070d30f1', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('0', '自动输入', 'BAR_INPUT_TYPE_CODE', '0', null, null, null, null, '1', '4ad1b9fec7744286a158ac1325439174', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '手动输入', 'BAR_INPUT_TYPE_CODE', '1', null, null, null, null, '1', '2ea0bc7485a04160b55b42680fd9967d', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '手工配载', 'BAR_INPUT_TYPE_CODE', '2', null, null, null, null, '1', '3c99eb463c54407087448106ab17c4a0', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('5', '污染', 'COMPLANTS_TYPE', '5', null, null, null, null, '1', 'c26af70b5b30478f8525b9ac507b5dc1', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('7', '异常违规', 'COMPLANTS_TYPE', '7', null, null, null, null, '1', '3c12284a00954ff38a29093b79e22a33', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '处罚', 'INTER_COMPLAINTS_RESULT_OPER_CODE', '1', null, null, null, null, '1', 'd84656086b5d4a82932228b5e28ec8cf', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '省区', 'INTER_COMPLAINTS_RESULT_COMP_CODE', '1', null, null, null, null, '1', '16bec0110c98433fb6a4e801178a01c0', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('113', '发件遗失', 'COMPLANTS_TYPE_REF', '14', null, null, null, null, '1', '7baf5a25ae794b52a8065e354771a531', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('115', '派送遗失', 'COMPLANTS_TYPE_REF', '16', null, null, null, null, '1', '97fc98c339b14294bdd2e9551c1488ff', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('117', '分拨破损', 'COMPLANTS_TYPE_REF', '18', null, null, null, null, '1', '83ead19786324b3b8d5c7919f015f915', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '受理投诉', 'INTER_COMPLAINTS_PROCESS_STATE', '2', null, null, null, null, '1', 'db8bc895d8c444e7b8762e658aef415c', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('3', '处理完成', 'INTER_COMPLAINTS_PROCESS_STATE', '3', null, null, null, null, '1', '05dc3368c06a429eb09bdfb15469b1d5', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('line', '时效管理', 'system_type', '7', null, null, 'admin', '2016-09-06 18:38:25', '0', 'f6bc6e1f600949eaaedafbc99b2efa6a', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('条件类型', 'condition-type', 'rule', '1', null, null, null, null, '0', 'f7934fab11384fc9aeb839edb94041a1', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '待处理', 'PROCESS_STATE', '1', null, null, null, null, '1', '4f6dea8ef1384be5a367c4752d9dac33', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('3', '已处理', 'PROCESS_STATE', '3', null, null, null, null, '1', 'deac6bd0e0144da9b4df0d0483d66664', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('>', '大于', 'condition-type', '1', null, null, null, null, '1', '65a440d24a9943ddb2fdd76811800ca3', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('profession', '专业通道', 'POST_TYPE', '2', null, null, null, null, '1', 'e4a058201eec4c2199140818e3661585', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('44', '超期申请', 'GOODS_CHARGE_OPER_TYPE', '8', null, null, null, null, '1', 'e82542aa12e54798a93f472b75896478', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('72', '登记审核', 'GOODS_CHARGE_OPER_TYPE', '9', null, null, null, null, '1', 'da249d75f3074808b89782049477890c', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('43', '正式挂失', 'GOODS_CHARGE_OPER_TYPE', '11', null, null, null, null, '1', '56573db1fd3347838ff75ad659fb98c4', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('73', '解挂失', 'GOODS_CHARGE_OPER_TYPE', '12', null, null, null, null, '1', 'a671b4447a32493e9ef13bf437d9a696', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '寄放单据联', 'PRINT_TYPE', '1', null, null, null, null, '1', 'c6d3cdce63144252baa6af0e69679fe3', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('3', '条码补印', 'PRINT_TYPE', '3', null, null, null, null, '1', '36644000a7994dfaa2640f385ed7fe94', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('5', '预交款打印日志(派件)', 'PRINT_TYPE', '5', null, null, null, null, '1', 'e470c0869b2249a083c364ab4d4268ed', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('10', '取消发款', 'GOODS_CHARGE_OPER_TYPE', '21', null, null, null, null, '1', 'ea0b6145e10c499b9a5f253fc0ece4ee', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('40', '转账确认', 'GOODS_CHARGE_OPER_TYPE', '22', null, null, null, null, '1', '401b0658b6cd461e9a1da2e3bbe8f95f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('15', '代收款删除', 'GOODS_CHARGE_OPER_TYPE', '23', null, null, null, null, '1', 'f7bea171de8d4a8da756d737f2567197', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('16', '代收款取消锁定', 'GOODS_CHARGE_OPER_TYPE', '24', null, null, null, null, '1', 'aa33dc12557941358e50b17255582fdf', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('0', '未处理', 'PROCESS_STATE', '0', null, null, null, null, '1', 'f9a3bd02a6b24cbe8cf2c3e1e78dc28e', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '处理中', 'PROCESS_STATE', '2', null, null, null, null, '1', 'ec1c0d91cca444d79d6b081257605e02', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('4', '删除/撤销', 'PROCESS_STATE', '4', null, null, null, null, '1', '5d51ed59b5fb424bb376add2f54f0988', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('119', '多方破损', 'COMPLANTS_TYPE_REF', '20', null, null, null, null, '1', 'f240688255514638b67edba4950a50ef', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('121', '网点短少', 'COMPLANTS_TYPE_REF', '22', null, null, null, null, '1', '11ed031f7d3b4a96b38fae5af64096e1', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('124', '网点污染', 'COMPLANTS_TYPE_REF', '25', null, null, null, null, '1', '1390df84d1304e29a9732de3964e96b3', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('126', '回单延误', 'COMPLANTS_TYPE_REF', '27', null, null, null, null, '1', '3e7d481dcb2e42139d4e472c7e66e6ba', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('129', '禁运品', 'COMPLANTS_TYPE_REF', '30', null, null, null, null, '1', 'aa2f9ec701894a6690088f2168ae983e', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '已转款', 'GOODS_CHARGE_PAYMENT_TYPE', '2', null, null, null, null, '1', '2fd8924377fa4a0abca35caf53d6f6b6', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('0', '无', 'ABNORMAL_WRAP_CONDITION', '0', null, null, null, null, '1', 'a2da3ac174854332b92390c058bc013f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '完好', 'ABNORMAL_WRAP_CONDITION', '1', null, null, null, null, '1', '64a4c6afc0924d9ab7cfc714d1f9bd11', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '破损', 'ABNORMAL_WRAP_CONDITION', '2', null, null, null, null, '1', '50f125c9433e4acb9bcb22fc6ae0f015', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('4', '潮湿', 'ABNORMAL_WRAP_CONDITION', '4', null, null, null, null, '1', 'c7f9eff91fe5416cba1ffd9b80467fbb', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('-1', '撤销投诉', 'INTER_COMPLAINTS_PROCESS_STATE', '20', null, null, null, null, '1', '937cf48290774e498667e9db47b983dc', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('8', '驳回投诉', 'INTER_COMPLAINTS_PROCESS_STATE', '8', null, null, null, null, '1', '0ab54f9239ea41d2a27586576dbbd11b', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('客户临时改约', '客户临时改约', 'return-order', '3', null, null, null, null, '1', '92b1877b3aae42c9ad81e77261e8d591', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('客户拒收', '客户拒收', 'return-order', '5', null, null, null, null, '1', 'fd40772f8c314391a97c0d33d63aeb07', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('6.8', '6.8', 'vehicle-type', '2', null, null, null, null, '1', '75668409f8994f6692f62fa2c572339b', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('甩厢车型', '甩厢车型', 'vehicle-type', '5', null, null, null, null, '1', '4035b3285e56497fa22b3fb8c7bb6779', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('待处理', '待处理', 'subscribe-status', '1', null, null, null, null, '1', 'faacbb59936e4bb3a9ad76e773d24754', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('自提', '自提', 'subscribe-status', '2', null, null, null, null, '1', 'd8db4ec6f31b4740b67b50a99b9df308', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('拒收', '拒收', 'subscribe-status', '3', null, null, null, null, '1', 'c7510bcb037a4befb0bf7c1ee4e9b1ae', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('电话无人接听', '电话无人接听', 'subscribe-status', '4', null, null, null, null, '1', '726250c821564feca0b8a3a89f99dd58', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('电话停机', '电话停机', 'subscribe-status', '5', null, null, null, null, '1', 'a77bd162fee7440b838daedc2e84bd6a', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('电话关机', '电话关机', 'subscribe-status', '6', null, null, null, null, '1', '49a828dabbe345b4baf90efbe78bdbdd', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('需要回复', '需要回复', 'subscribe-status', '7', null, null, null, null, '1', 'c785b5bd33e341ff93bbe18ccc94cda0', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('无人签收', '无人签收', 'subscribe-status', '8', null, null, null, null, '1', '6ec3428d5efe423cbe2a80017e3986d4', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '省际件', 'BIZ_TYPE_CODE', '2', null, null, null, null, '1', '2c244c4bbb7d49428645d3e7e8d24758', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('0', '未冻结', 'FREEZED_FLAG', '1', null, null, null, null, '1', '144f9cb2d4154ce9a359f5f690410e6a', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '是', 'YES_NO', '1', null, null, null, null, '1', '9a9d26816021444397a419b90df8d442', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('BI', '报表', 'system_type', '4', null, null, null, null, '0', '33c0197240b040aab29a549c505f800c', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('common', '通用', 'system_type', '5', null, null, null, null, '0', 'bff7d23eaa6b4526abe0bc402353f8a2', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('0', '无操作', 'GOODS_CHARGE_OPER_TYPE', '1', null, null, null, null, '1', '0c51850139024ef99eeb8dcf20d5151b', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('12', '登记', 'GOODS_CHARGE_OPER_TYPE', '2', null, null, null, null, '1', '27c898138f044d7b8892685c6dc4b2ae', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('42', '取消登记', 'GOODS_CHARGE_OPER_TYPE', '3', null, null, null, null, '1', 'a0f39be3d954414ab5c511afc620294a', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('11', '冻结', 'GOODS_CHARGE_OPER_TYPE', '4', null, null, null, null, '1', '34b1655b2b7645f7b73eee5a0b11e0e4', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('41', '解冻', 'GOODS_CHARGE_OPER_TYPE', '5', null, null, null, null, '1', 'f5c2cea121f940e6a020c85d6a33c471', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('.YM', '平台结算账户', 'ACCOUNT_END_WITH', '1', null, null, null, null, '1', '78060d2a548e48768aebe12df7a043fa', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('.GL', '资金总账', 'ACCOUNT_END_WITH', '2', null, null, null, null, '1', '78f595197ea647af80de3ce0603866ca', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('.LA', '企业结算账户', 'ACCOUNT_END_WITH', '3', null, null, null, null, '1', '52ee42d1b78a425983de8a3751c2081a', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '延误', 'COMPLANTS_TYPE', '1', null, null, null, null, '1', '62583858bad84dd5a5565272f46763df', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '遗失', 'COMPLANTS_TYPE', '2', null, null, null, null, '1', '6e044c4336514cf486f99ea00371b2e1', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('3', '破损', 'COMPLANTS_TYPE', '3', null, null, null, null, '1', '0450e31f59a044d4866a8d4a397ebd00', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('4', '短少', 'COMPLANTS_TYPE', '4', null, null, null, null, '1', '5a882fd495ae4358bb95a9f0c5a6a69c', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('6', '回单问题', 'COMPLANTS_TYPE', '6', null, null, null, null, '1', '7715a4707ef14597ad560ad5b161ecb9', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '获赔', 'INTER_COMPLAINTS_RESULT_OPER_CODE', '2', null, null, null, null, '1', '218857b8ecde448796e7e9c4979b38ce', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '干线运输商', 'INTER_COMPLAINTS_RESULT_COMP_CODE', '2', null, null, null, null, '1', 'a0b253305679434d905dee2b9729a4fa', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('110', '分拨延误', 'COMPLANTS_TYPE_REF', '11', null, null, null, null, '1', 'd3af3ad38e064cc1836d5cded9f4518f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('111', '网点延误', 'COMPLANTS_TYPE_REF', '12', null, null, null, null, '1', '5d57ff7231e94ab6b6c1d862b889475f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('112', '干线延误', 'COMPLANTS_TYPE_REF', '13', null, null, null, null, '1', '280f8c3655714d569ebecc26faaaa122', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('114', '中转遗失', 'COMPLANTS_TYPE_REF', '15', null, null, null, null, '1', '509641a874314baea4e9e791cec309ea', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('116', '多方遗失', 'COMPLANTS_TYPE_REF', '17', null, null, null, null, '1', '06ff6cf5b8aa4246be70c1d6d95bf986', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('118', '网点破损', 'COMPLANTS_TYPE_REF', '19', null, null, null, null, '1', '7f620af49d3145c0b3461574eccda97d', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('120', '分拨短少', 'COMPLANTS_TYPE_REF', '21', null, null, null, null, '1', 'fc37c744b84f409c9e11b9d292c1776b', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('122', '多方短少', 'COMPLANTS_TYPE_REF', '23', null, null, null, null, '1', '4b46c85a802c4cce89a6d4f5175111db', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('123', '分拨污染', 'COMPLANTS_TYPE_REF', '24', null, null, null, null, '1', '98e194abeb5045458b42e5fbba57134e', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('125', '多方污染', 'COMPLANTS_TYPE_REF', '26', null, null, null, null, '1', 'eb15d2232edb498ea068adf705460b31', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('127', '回单遗失', 'COMPLANTS_TYPE_REF', '28', null, null, null, null, '1', '866e977b25fd49b39be42e64f471666e', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('128', '无效回单', 'COMPLANTS_TYPE_REF', '29', null, null, null, null, '1', '470a6369679d4d8f9295afdcf50cfc1b', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('130', '差重差方', 'COMPLANTS_TYPE_REF', '31', null, null, null, null, '1', '3b4a3b8482734107a5f8445cbea3965e', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('131', '乱收费', 'COMPLANTS_TYPE_REF', '32', null, null, null, null, '1', 'e7adc618a2de44c180008f30ea0e3eb9', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('132', '虚假信息', 'COMPLANTS_TYPE_REF', '33', null, null, null, null, '1', 'c53a149deda44fca9fd53202c810a7f2', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('WEB', '平台', 'system_type', '1', null, null, null, null, '1', '3cf66c2975354a931a6bfcd82bb427904', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('GUI', 'GUI', 'system_type', '2', null, null, 'admin', '2016-09-06 18:38:29', '0', 'a4074cd7510a4eb2b1841ab3c23f382d0', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('NONE', 'NONE', 'system_type', '3', null, null, 'admin', '2016-09-06 18:38:32', '0', '828ba486511c4db99662b308c3ddf8598', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('map', '网点地图', 'system_type', '6', null, null, 'admin', '2016-09-06 18:38:36', '0', '4c4ba3c9752e462c39510c5ba10e39b1f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('BI', '报表', 'system_type', '4', null, null, 'admin', '2016-09-06 18:38:39', '0', '33c0197240b040aab29a5449c505f800c', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('common', '通用', 'system_type', '5', null, null, 'admin', '2016-09-06 18:38:42', '0', 'bff7d23eaa6b4526a5be0bc402353f8a2', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('货单不一（少装、漏装、串货）', '货单不一（少装、漏装、串货）', 'return-order', '1', null, null, null, null, '1', 'faf436f3ba694ba884547ed9f1deb553', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('货损货差', '货损货差', 'return-order', '2', null, null, null, null, '1', 'c53ff39bd00b4229bf2adc7d5bf44cfb', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('其他', '其他', 'return-order', '6', null, null, null, null, '1', '15030dd6e4ed430c94acc649aeffa327', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('4.2', '4.2', 'vehicle-type', '1', null, null, null, null, '1', 'c40507ebc76b4e40813a36b367862f65', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('小依维柯', '小依维柯', 'vehicle-type', '3', null, null, null, null, '1', 'a50f63ece5704404a1e45f877c64913e', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('大依维柯', '大依维柯', 'vehicle-type', '4', null, null, null, null, '1', 'ca44420457924053af74a1d9734905df', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('Root', 'Root', 'module_type', '1', null, null, 'admin', '2016-09-06 18:37:59', '0', '53be5bcc8f4f474bb3a128053ea62e9a', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('WORKBENCH', 'WORKBENCH', 'module_type', '3', null, null, null, null, '1', '6ea4b83a2afa4f9490584d6a77533205', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('MENU', '菜单', 'module_type', '4', null, null, null, null, '1', '1ea719a56fa24e84bad72e641948509b', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('PANEL', 'PANEL', 'module_type', '6', null, null, 'admin', '2016-09-06 18:38:57', '0', 'f8591e78de7440288827c0cf0c8c7876', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('WIDGET', '按钮', 'module_type', '7', null, null, null, null, '1', 'a136bcd37fc64b0d8bdd7496c249c73c', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('15', '交收件联', 'BAR_OP_TYPE', '6', null, null, null, null, '0', '69a989f1bfb84447aa6c535d3e6920c1', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('19', '收件且装车', 'BAR_OP_TYPE', '10', null, null, null, null, '0', '50449a42a8d9462aaa9ade1a41d22b11', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('35', '卸车且派件出仓', 'BAR_OP_TYPE', '25', null, null, null, null, '0', 'd0d2853cba414d40a3b0a298bcfcc496', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '系统触发派', 'SETT_OP_CODE', '2', null, null, null, null, '0', '4faa2b41ea4c4101bf4010be1e847ec3', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('4', '红冲', 'SETT_OP_CODE', '4', null, null, null, null, '0', 'c83a2547c321456da6ce045ca93966e9', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('6', '取消交款', 'SETT_OP_CODE', '6', null, null, null, null, '0', 'a6247b779d7c471a8a43db016e0b9f71', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('30', '装车', 'SETT_OP_CODE', '7', null, null, null, null, '0', 'bdc1cc3963524817aa442540ad8384cc', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('32', '卸车', 'SETT_OP_CODE', '8', null, null, null, null, '0', '001f47f1bcfa4e68a31180d3bb496e44', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '到付', 'PAYMENT_TYPE', '2', null, null, null, null, '1', 'b97c73a7c8284fb6ae6dfb876fa7d83f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('4', '两边付', 'PAYMENT_TYPE', '4', null, null, null, null, '1', '5840217665c64cc9884e302e43fdec2e', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('RECEIVE', '收件', 'R_D_TYPE', '1', null, null, null, null, '1', 'c103d48ef784423fa652e3f294fc18e4', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('DISPATH', '派件', 'R_D_TYPE', '2', null, null, null, null, '1', '2dbe2bf88c234b1f893745d998a126d4', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('13', '临时挂失', 'GOODS_CHARGE_OPER_TYPE', '10', null, null, null, null, '1', '433b0cc4fc7142c09f106e9157ce5e5b', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '工商银行', 'GOODS_CHARGE_BANKACCOUNT_TYPE', '1', null, null, null, null, '1', '2a79f6730fe846e08e066184e49cf535', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('S4', '门到港', 'SERVICE_TYPE', '4', null, null, null, null, '1', 'dbefdd92c45a417296f99c04679ac31c', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '取消分派', 'DISPATCH_LOG_OPER_TYPE_CODE', '2', null, null, null, null, '1', '5ee5474b55284ee48de0f9dbbcdf88a0', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('4', '取消交款', 'DISPATCH_LOG_OPER_TYPE_CODE', '4', null, null, null, null, '1', '773318cb60db4fcc8c341a537a0268b4', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('6', '欠款自提', 'DISPATCH_LOG_OPER_TYPE_CODE', '6', null, null, null, null, '1', 'e7a422a8b54e419b95c2160153999c9f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('8', '欠款交款', 'DISPATCH_LOG_OPER_TYPE_CODE', '8', null, null, null, null, '1', 'fbe3984ba842413a83fa8a5820ce4de3', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('9', '取消欠款交账', 'DISPATCH_LOG_OPER_TYPE_CODE', '9', null, null, null, null, '1', '8d01498dad4443639dff2fdb7037cdd3', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('11', '欠款分派', 'DISPATCH_LOG_OPER_TYPE_CODE', '11', null, null, null, null, '1', '12ec2e2341094dac86fc1d0b74e9acc3', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('3', '污染', 'ABNORMAL_WRAP_CONDITION', '3', null, null, null, null, '1', '0ba7b312d4184464977c245ac8c1974d', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('5', '申诉', 'INTER_COMPLAINTS_PROCESS_STATE', '5', null, null, null, null, '1', 'fdb2ed6e77054b33bdc4408a0daabcb0', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('货单不一（少装、漏装、串货）', '货单不一（少装、漏装、串货）', 'return-waybill', '1', null, null, null, null, '1', '01ad5eaee3ab46da96ac7ee4336ab2af', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('货损货差', '货损货差', 'return-waybill', '2', null, null, null, null, '1', 'b61d516841b94ca79954dea14fcd4598', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('26', '装件入包', 'BAR_OPER_TYPE_CODE', '17', null, null, null, null, '1', 'c0d9f423ff274a048744b67f06b56f9a', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('28', '快件解包笼袋', 'BAR_OPER_TYPE_CODE', '19', null, null, null, null, '1', '3b03127e966240b4bc7ae0f30fc2db15', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('31', '从车中删除', 'BAR_OPER_TYPE_CODE', '22', null, null, null, null, '1', '88080aab2e494598b834f93fcf39d214', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('32', '快件卸车', 'BAR_OPER_TYPE_CODE', '23', null, null, null, null, '1', '6172230bab7445fd95e1144e2f821fc6', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('35', '卸车且派件出仓', 'BAR_OPER_TYPE_CODE', '26', null, null, null, null, '1', '51820a441f4d4c09871c39c74801c57f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '分派', 'DISPATCH_LOG_OPER_TYPE_CODE', '1', null, null, null, null, '1', 'b8a8853b574d437dabd497615ccb971d', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('客户临时改约', '客户临时改约', 'return-waybill', '3', null, null, null, null, '1', '6025f003ddaf4ab588d99d043f1161f3', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('送货不及时', '送货不及时', 'return-waybill', '4', null, null, null, null, '1', 'a7f97e7bf7ce41bf9d921c586acffc38', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('客户拒收', '客户拒收', 'return-waybill', '5', null, null, null, null, '1', 'c761e078eecf4deab20a13871c29ab60', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('其他', '其他', 'return-waybill', '6', null, null, null, null, '1', '1f8e4a84ed2642938fa9cb79b45e1d98', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('WEB', '平台', 'system_type', '1', null, null, null, null, '0', '3cf66c2975354a93a6bfcd82bb427904', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('GUI', 'GUI', 'system_type', '2', null, null, null, null, '0', 'a4074cd7510a4eb2b841ab3c23f382d0', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('NONE', 'NONE', 'system_type', '3', null, null, null, null, '0', '828ba486511c4db9966b308c3ddf8598', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('App', 'App', 'module_type', '2', null, null, 'admin', '2016-09-06 18:38:12', '0', 'a72841d0fb7048b88570d06b7a54ff80', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('BUNDLE', 'BUNDLE', 'module_type', '5', null, null, 'admin', '2016-09-06 18:38:53', '0', 'cbd0dab7183946a2a916fc7107e92478', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('NONE', 'NONE', 'module_type', '8', null, null, 'admin', '2016-09-06 18:38:07', '0', '80ba77164c0e44a2a2fd4afd0e1cf274', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '系统触发收', 'SETT_OP_CODE', '1', null, null, null, null, '0', 'ea8001a7dda54f67b3f43ee9a9428ffe', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('3', '审核', 'SETT_OP_CODE', '3', null, null, null, null, '0', '9c5112c9404e4a4b9114010a98961367', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('5', '派件交款确认', 'SETT_OP_CODE', '5', null, null, null, null, '0', '6cf2543117ee4df699dce2f5a02f7a9f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '省内件', 'BIZ_TYPE_CODE', '1', null, null, null, null, '1', '659a4dbe251f4c9e97d51d63788f5d65', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '已冻结', 'FREEZED_FLAG', '2', null, null, null, null, '1', 'd87b485eff4349e09f7ad1ac465d1ed9', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '已解冻', 'FREEZED_FLAG', '3', null, null, null, null, '1', '465bf520186b47c38f6cca72cbdd594e', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('0', '否', 'YES_NO', '2', null, null, null, null, '1', '8639940a779f467a8b490f922a760a14', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('S1', '门到门', 'SERVICE_TYPE', '1', null, null, null, null, '1', '7d46ab31735448d697574771791ba54d', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('S2', '港到门', 'SERVICE_TYPE', '2', null, null, null, null, '1', '9fcf2958f73148708fb90622d65d56fa', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '现结', 'SETTMENT_TYPE', '1', null, null, null, null, '1', 'a6af079de0984c3b97a02c6a0a1c1d8d', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('map', '网点地图', 'system_type', '6', null, null, null, null, '0', '4c4ba3c9752e462c9510c5ba10e39b1f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '农业银行', 'GOODS_CHARGE_BANKACCOUNT_TYPE', '2', null, null, null, null, '1', 'fbfeb6673e524f008e45df443b9f6632', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('2', '收方单据联', 'PRINT_TYPE', '2', null, null, null, null, '1', '4ab551178c83477fa91433b3b7c50525', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('4', '交款明细补印', 'PRINT_TYPE', '4', null, null, null, null, '1', '7fb45b193e014c5eb2c5888d0631d73f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('3', '交款', 'DISPATCH_LOG_OPER_TYPE_CODE', '3', null, null, null, null, '1', '72e9f38f853548448dee4625ed013064', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('5', '自提', 'DISPATCH_LOG_OPER_TYPE_CODE', '5', null, null, null, null, '1', '7185fe97dd874574a4f18b8819f82854', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('7', '取消欠款自提', 'DISPATCH_LOG_OPER_TYPE_CODE', '7', null, null, null, null, '1', '958db65234194ff8bce36c77879d27a7', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('10', '原返货', 'DISPATCH_LOG_OPER_TYPE_CODE', '10', null, null, null, null, '1', 'f0a9a4effe544c3fa757f587bbb889e9', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('5', '总部审核完成', 'PROCESS_STATE', '5', null, null, null, null, '1', '1475e4b4bbe7487d9c446daf2a9e67c3', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('6', '待总部审核', 'PROCESS_STATE', '6', null, null, null, null, '1', '1d313b7701de49659a36b09abf241610', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('0', '未转款', 'GOODS_CHARGE_PAYMENT_TYPE', '1', null, null, null, null, '1', '63bc3fffc9f944408cc015ca20cf1149', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('0', '新增投诉', 'INTER_COMPLAINTS_PROCESS_STATE', '0', null, null, null, null, '1', '3eb90433f9784c088340960875cdb4fb', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '提交投诉', 'INTER_COMPLAINTS_PROCESS_STATE', '1', null, null, null, null, '1', 'f039ca15e4c6417abf0f168877bf858d', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('4', '完成', 'INTER_COMPLAINTS_PROCESS_STATE', '4', null, null, null, null, '1', '835d80f93f8c41cdbd03ec94e23c2502', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('10', '申诉完成', 'INTER_COMPLAINTS_PROCESS_STATE', '10', null, null, null, null, '1', '7fa87d52b8bd45eabe564949fecb0890', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('9', '兴业银行', 'GOODS_CHARGE_BANKACCOUNT_TYPE', '9', null, null, null, null, '1', '46a6fbd1860c491abcf1ea56ccc6b32a', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('10', '邮政银行1', 'GOODS_CHARGE_BANKACCOUNT_TYPE', '10', null, null, null, null, '1', '0268dec485e345d6995d47b6514df849', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('阿斯达', '阿斯达', 'system_type', '76', null, null, null, null, '0', '66b3fdd28ab241b49890d4769166dde2', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('asdasd', 'asda', 'system_type', '32', null, null, null, null, '0', 'd887605ff1ad4bf199f03b7a569c7825', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('oms', '订单管理', 'module_type', '9', null, null, 'admin', '2016-09-06 18:38:18', '0', '01e8d020858f4e70ad9082f0b7bb8554', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('送货不及时', '送货不及时', 'return-order', '4', null, null, null, null, '1', '7c341ad1100b495cb5fea24902800a5e', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('manage', '管理通道', 'POST_TYPE', '1', null, null, null, null, '1', '4a2a122aaef944369871e71d374b83cf', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('01', '上门收件', 'BAR_OP_TYPE', '1', null, null, null, null, '0', 'eb761f2239b64a8dbdf1579f46f1ea37', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('11', '收件入仓;', 'BAR_OP_TYPE', '2', null, null, null, null, '0', '0b20809da29443ce94d46bed624ca843', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('12', '派件出仓', 'BAR_OP_TYPE', '3', null, null, null, null, '0', 'ce1b0ba58ed24b8fbdaaa595ce46a540', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('13', '二程接驳收件', 'BAR_OP_TYPE', '4', null, null, null, null, '0', '4796e50f60cc41d68968309901ae0e3f', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('14', '二程接驳派件', 'BAR_OP_TYPE', '5', null, null, null, null, '0', 'd1a8abd6767341fb85d57c3f71ccf91b', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('16', '交派件联', 'BAR_OP_TYPE', '7', null, null, null, null, '0', '9ccba937c0aa467b85467f51f35a9b71', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('17', '滞留件入仓', 'BAR_OP_TYPE', '8', null, null, null, null, '0', 'f5f7d5139264459aa8049b461d610931', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('18', '滞留件出仓', 'BAR_OP_TYPE', '9', null, null, null, null, '0', '807d07ea5bd94d6bbbfbc3066ad9501d', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('20', '中转发车', 'BAR_OP_TYPE', '11', null, null, null, null, '0', '8354a0726f4a4d61af67f29d6aa5bf19', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('21', '中转到车', 'BAR_OP_TYPE', '12', null, null, null, null, '0', 'f50cf680aacb4e57928bbef58a2ebce4', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('22', '中转落货', 'BAR_OP_TYPE', '13', null, null, null, null, '0', 'ed3df2c6cbe54652a5073887a29509cb', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('23', '过车操作', 'BAR_OP_TYPE', '14', null, null, null, null, '0', '9e7141ecbee64604a16c3105376307af', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('24', '中转滞留', 'BAR_OP_TYPE', '15', null, null, null, null, '0', 'fab86505d753467b859aa6a40c499465', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('25', '中转批量滞留', 'BAR_OP_TYPE', '16', null, null, null, null, '0', '73bc80880bc24d1aa754a1d27e87b149', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('26', '装件入包', 'BAR_OP_TYPE', '17', null, null, null, null, '0', 'b9dac2b834f64f468c09c04b1ef36ad9', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('27', '整车滞留', 'BAR_OP_TYPE', '18', null, null, null, null, '0', 'beeacaf5a3c94e2db28852370020147c', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('28', '快件解包笼袋', 'BAR_OP_TYPE', '19', null, null, null, null, '0', 'a7cc7bef6c9640cea5934ce2d605163a', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('29', '快件装笼', 'BAR_OP_TYPE', '20', null, null, null, null, '0', '0fd421aeb7ad4d72939d16b2baab2a94', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('30', '快件装车', 'BAR_OP_TYPE', '21', null, null, null, null, '0', '9615d184d1764c6c82be885fe140b1af', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('31', '从车中删除', 'BAR_OP_TYPE', '22', null, null, null, null, '0', 'e811fac0551d4439816cdb3dbde0d6cb', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('32', '快件卸车', 'BAR_OP_TYPE', '23', null, null, null, null, '0', '0a616216a2634df4b3c3b3189ee2fdb0', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('33', '封车', 'BAR_OP_TYPE', '24', null, null, null, null, '0', '626d20aff284464aa56c3e553c4f41e8', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('34', '解封车', 'BAR_OP_TYPE', '26', null, null, null, null, '0', 'b6c03d61ba8e433782b8a650264972b4', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('S3', '港到港', 'SERVICE_TYPE', '3', null, null, null, null, '1', '6c59dfba38054726a181d4cf7ea84aed', null, null, null, null);
INSERT INTO `t_common_dict_value` VALUES ('1', '寄付', 'PAYMENT_TYPE', '1', null, null, null, null, '1', '1e3eaa9057e94ed3ac092d6e9a6cc0bb', null, null, null, null);

-- ----------------------------
-- Table structure for t_system_parameter
-- ----------------------------
DROP TABLE IF EXISTS `t_system_parameter`;
CREATE TABLE `t_system_parameter` (
  `id` varchar(50) NOT NULL,
  `sys_key` varchar(50) DEFAULT NULL,
  `sys_value` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login_log`;
CREATE TABLE `t_user_login_log` (
  `user_code` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `login_method` varchar(50) DEFAULT NULL,
  `id` varchar(50) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_login_log
-- ----------------------------
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '00e577060ba640828ee31d4909f0a983', '2016-09-09 15:37:44');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '01531e4b2a6b42cfa6a3a1b467c8ee05', '2016-09-08 17:25:31');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '020ba2ea77bd4ae5929340ece88c6139', '2016-09-08 10:47:23');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '02fcabfa54e94040966466cc5aea3bc4', '2016-09-09 09:55:52');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '0358d45f6d3d4e3389c014f6e336e265', '2016-09-08 11:38:02');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '045e3fb5471447f3aa1180d9c7959479', '2016-09-09 16:23:19');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '090ca0bf68224fe09e302d98041411b4', '2016-09-08 11:56:06');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '09d3030c978e4e058acfded0a87544ac', '2016-09-10 09:59:54');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '0a514dad711c4ffe9e134032722f59ee', '2016-09-09 15:30:38');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '0b0e98045a7148f08cf09ac01933caac', '2016-09-10 18:05:40');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '0b4423e591c14a37a0802b9e8d0dc0ea', '2016-09-09 19:13:06');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '0b935db13f894429aa52fc9a5375f2bf', '2016-09-09 10:02:07');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '0c238b69466b470ba87e27db8ee6da39', '2016-09-10 18:05:40');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '0e3a7fb7fc774a0c9408a80fd2a75da6', '2016-09-08 15:19:36');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '0e89e0dab5eb4f30b9c384735b2d01f1', '2016-09-09 10:52:27');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '10f0a8f8361e4ab1970a83c8870f8e4d', '2016-09-08 17:20:40');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '170068c5ebaf4f548af044ba072106df', '2016-09-10 15:34:32');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '1845935fbf53453cb2e10a0bb835ae29', '2016-09-08 11:22:24');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '18d8dc63fc9c43cd8461fd28bd21dfd4', '2016-09-09 10:15:42');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '1ac54b378d4d4ec994dbd9e970b14ebb', '2016-09-09 18:15:10');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '1be3aedd87d540e39cfe8b1b79ef9911', '2016-09-09 09:59:05');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '1dcd30e13d434e4e81e0f73e8f08f770', '2016-09-09 11:22:32');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '1f58098937e442f8b18056dada2e329c', '2016-09-10 14:38:56');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '2114d5b03c9148b98fed8b989c8142e0', '2016-09-08 11:18:30');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '249e5bc3754f4b9885d89824ef03a16d', '2016-09-08 15:22:18');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '25d0854e652840719d873caca84ce110', '2016-09-08 15:17:38');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '25dbb304f7584ce382229106e379ea7d', '2016-09-09 13:54:25');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '26f2aa79f09a419890fdf0c6810e6e65', '2016-09-10 11:07:44');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '2b633371d09a429faa305923e129cac4', '2016-09-09 21:00:53');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '2c818b81d8eb45b68d12f86126a2d4bb', '2016-09-08 15:41:41');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '2c868251982843b5ba2eef271ccf75e7', '2016-09-10 15:54:12');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '2d42801be9f5436bacd770a477f82757', '2016-09-08 10:36:40');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '3274e56368394f668c014f50209b3e47', '2016-09-09 17:11:09');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '3327162366fb40219691c52312bae605', '2016-09-08 11:16:01');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '36fa8e96de2c42acb974eb1253a6bb21', '2016-09-09 15:21:32');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '37032695470b4b549bb570164f6194e4', '2016-09-08 11:31:04');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '373fb5a48acb4d0e92083726118944f1', '2016-09-09 10:47:50');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '38de7482ca1442988981b96a9c59af73', '2016-09-10 11:18:47');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '39c14e67dc354797bc30c1518072f3ac', '2016-09-08 17:23:09');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '3a50246bd2af43c8aafe40af22abe6d3', '2016-09-10 10:07:41');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '3d43fef2d6114857af01a7fd610b55b8', '2016-09-09 09:25:12');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '3d72ee5129234cffa6ff89c879b484ad', '2016-09-12 08:47:36');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '3df53f57b3bf4ed3975131cfa2f9c345', '2016-09-08 11:40:14');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '43e2b9b6b2804a38b4c82bee9b5d7478', '2016-09-08 14:49:36');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '49888b96089b4e0ba7080971d435d683', '2016-09-12 10:29:25');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '4aeae92cf3a040b8ab2f54ccdf331594', '2016-09-09 18:01:16');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '4cf3b1d8f6744aefb2ef8dc08feca1b2', '2016-09-08 15:07:06');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '4dbd8f26aafb452fbdc4717de29dcc30', '2016-09-08 11:21:10');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '57bf22b592ec4a09b0b6d2107a173acf', '2016-09-09 12:03:17');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '5892a52694854c30b87d818b96dc7050', '2016-09-09 15:25:41');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '5aa1ace979954e36882b46e1bc519df9', '2016-09-08 10:32:37');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '5dc05b2332154a4591423eb8f0cce2c6', '2016-09-08 14:51:58');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '614ca056334e4e7d932fd2503e1237cc', '2016-09-10 08:48:20');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '64c813bbeed14e2c8c831d5a238561c4', '2016-09-09 16:26:28');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '66ee4060f4164970be91a8ab9cf3d39e', '2016-09-08 17:11:33');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '67953876b67b4bb883ea9e7c242533cc', '2016-09-08 11:47:57');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '69d4e083c35b4a7b92c011a992523f3e', '2016-09-08 11:35:49');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '6b6ba2b732904d15bb7338bf0cf7864f', '2016-09-08 16:29:18');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '6cbdbf72f8d3488e82c13bf7d325946f', '2016-09-09 10:43:58');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '6e49ae67f9184130a58c1aee84447d18', '2016-09-10 15:29:24');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '6f22ed9ac2144f248769d44bba3c3d1a', '2016-09-12 10:03:40');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '7428b19ab8794f32919aea946358f0b1', '2016-09-08 17:43:22');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '75539ec8376b4b3f8863410f6f386226', '2016-09-09 15:33:31');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '768c68c63f1b458cbf008bd46c2628ae', '2016-09-10 09:51:38');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '7f33ef5010cf4ced9c32de9546266716', '2016-09-09 16:08:31');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '7f6fa84b838c4755a3a021bd8925d442', '2016-09-08 14:33:58');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '843adb6d7d8a4c76adfcab5cace945b0', '2016-09-09 15:11:26');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '855ce2b541bf4edeb8b33255e17e4da1', '2016-09-10 14:13:21');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '865e6f95ecf84404882d083050b74daf', '2016-09-08 19:08:21');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '9450dd70d558435caa1b1eeaea6b12ca', '2016-09-10 17:26:26');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '9a0f8caa04af49daaff64821c561cd09', '2016-09-09 20:42:41');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', '9cacaa5edd954045b5df11643c4fd0f7', '2016-09-08 14:43:50');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '9defa1851be1494ba4fad56f9c8d47d8', '2016-09-09 13:50:47');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', '9f2402e830c8412cb25ff4bb082703d0', '2016-09-09 09:58:29');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'a3ec9f29443e45d0a22c5715565a71ec', '2016-09-12 10:06:24');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'a5033dc5989146ceb3684166fecd118b', '2016-09-08 15:03:29');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'a59e7141d32a4e70b8520c833e5f49d1', '2016-09-10 18:11:16');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'a71f361b06ea40a2994588d1d90e6b90', '2016-09-09 15:40:16');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'a744bc82577c4eef92dff2d977224a00', '2016-09-09 16:11:32');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'a937a611de40419091b28fdf31b7165b', '2016-09-09 15:56:26');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'ab27bc8b5862430890593e2eed99290f', '2016-09-10 17:54:38');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'ad39865e6cdc4473886c1c770b481620', '2016-09-09 09:52:44');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'aed22468260f425f931761f592b6a190', '2016-09-08 17:50:43');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'b0a3956751e348ef801cb0effdc40ce7', '2016-09-10 14:10:07');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'bae1d0ef1a8242fb84adb3f2427c257a', '2016-09-10 10:54:04');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'bbb2fee7b04d4e8d818e13068b49dd73', '2016-09-09 15:50:35');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'bc3d8e12ac8144778418c5dc3dfc6192', '2016-09-08 10:44:30');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'bd8762f6178a49ecac11fc03f60130a9', '2016-09-08 19:30:45');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'be94cb0ab45844c3bbdf091b480086a6', '2016-09-09 09:09:33');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'bf8a544de5de4ccfb15f5f69bccc208f', '2016-09-08 14:53:58');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'c576ff2cfe5247598fc4f3d019f52ab8', '2016-09-08 19:25:04');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'c781d9632b3a4271893c52557eb0ca45', '2016-09-08 16:22:35');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'c80d1f5c8a7b4a3ea001840e65b44667', '2016-09-10 18:08:18');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'c8b2088e10b2455e8a59a38af1b7ec20', '2016-09-09 13:49:53');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'ca2f27133dcd432188aad141856bdf08', '2016-09-09 20:44:42');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'ca855f4a20aa46babf1463a66042fd85', '2016-09-10 09:55:56');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'cb4f52cc2abb4dbeaceed3f470dc861a', '2016-09-09 17:01:29');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'cd03dfedc0714620b418fa679d62e6bd', '2016-09-10 17:23:19');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'ce4479093e4a45408df5907febccd498', '2016-09-09 16:05:24');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'd17fe92cd0a64297bc977cba821dd9f1', '2016-09-08 15:01:22');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'd5c378cb5829449080a5ab0b86ff6e4c', '2016-09-09 10:07:30');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'd71a59775abe4c88a3d127a31e332761', '2016-09-08 17:17:23');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'd7f28e79e43543c190bf75e670be11bf', '2016-09-10 18:08:58');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'd80103efc51044eeb9c89df689efb067', '2016-09-10 18:25:00');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'dae375f5823642e3905de3ac3f35b792', '2016-09-10 10:03:44');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'dba84be8c25646e788ebaa4a71f24f61', '2016-09-10 09:36:24');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'dde32582a3474c699be84d5b5ce7ab9b', '2016-09-10 13:59:11');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'e18a624e1f6e4cb5b9e5a4728108d9f4', '2016-09-09 10:46:22');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'e24aacdcbd424a1598fffd37fd83fa83', '2016-09-09 14:14:58');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'e4dfbfc7d5694b4f911c1ab3f7aad5c4', '2016-09-09 09:59:56');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'e92316ae0bda468098a713a3a110b865', '2016-09-09 19:10:41');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'e9a9ea833fe04f7e8b95e690d9e7edd9', '2016-09-09 11:32:37');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'eab609820b5a492da6acb8cdb36a4584', '2016-09-09 16:48:58');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'ec8318b90a7946aaa0587c56370b0f5b', '2016-09-08 11:14:48');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'ef224a98cbfc4abd9b03eb65137e8ecf', '2016-09-08 11:05:54');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.61', 'pc', 'fbbba282591540939e37cbe9d23a7c0f', '2016-09-08 17:31:29');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'fdb0865c0c614a36b48fb47472f0c5bd', '2016-09-09 17:55:25');
INSERT INTO `t_user_login_log` VALUES ('admin@yimidida', '192.168.10.72', 'pc', 'ff70956fdd08443eba732fc23212d6c2', '2016-09-09 14:40:26');
