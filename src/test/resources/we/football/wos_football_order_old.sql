CREATE TABLE `football_order_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tenant_code` varchar(32) NOT NULL DEFAULT '' COMMENT '租户code',
  `member_id` bigint(20) DEFAULT '0' COMMENT '用户id',
  `member_type` tinyint(4) DEFAULT '0' COMMENT '用户类型：1-注册游客、2-注册用户、3-充值用户、4-测试用户',
  `member_account` varchar(50) DEFAULT '' COMMENT '用户账号',
  `member_nickname` varchar(50) DEFAULT '' COMMENT '用户昵称',
  `times` int(11) DEFAULT '0' COMMENT '投注倍数',
  `bet_amount` decimal(18,3) DEFAULT '0.000' COMMENT '订单总金额',
  `max_win_amount` decimal(18,3) DEFAULT '0.000' COMMENT '订单最大可中金额',
  `pass_type` varchar(255) DEFAULT '0' COMMENT '过关方式。如：3*1（3串一）多个逗号分隔 。存储pass_type_tag',
  `bunch_type` tinyint(4) DEFAULT '0' COMMENT '过关类型（1：胜平负；2：比分；3：进球数；4：半全场；5：混合过关）',
  `status` tinyint(4) DEFAULT '0' COMMENT '订单状态（1:待扣款 2:待开奖 3:已中奖待加款 5:未中奖 6:已中奖  7:撤单待加款 10:已撤单 11:处理失败 12:扣款失败 20:异常订单）',
  `status_remark` varchar(255) DEFAULT NULL COMMENT '状态描述，主要用于出错之后错误记录',
  `bet_count` int(11) DEFAULT '1' COMMENT '总投注数',
  `match_count` int(11) DEFAULT '1' COMMENT '总选择赛事场数',
  `win_count` int(11) DEFAULT '1' COMMENT '总中奖注数',
  `win_amount` decimal(18,3) DEFAULT '0.000' COMMENT '总中奖额度',
  `wait_amount` decimal(18,3) DEFAULT '0.000' COMMENT '待加减款奖额度，中间状态，此时status=3|7等状态',
  `profit_amount` decimal(18,3) DEFAULT '0.000' COMMENT '总盈利额度',
  `bets_point` tinyint(4) DEFAULT '1' COMMENT '购彩点（1：快速购彩）',
  `single_play` tinyint(4) DEFAULT '0' COMMENT '是否是单场赛事（0：否；1：是）',
  `order_done` tinyint(4) DEFAULT '0' COMMENT '订单是否已完结（0：否；1：是，所有子订单已处理完）',
  `client_type` varchar(8) DEFAULT NULL COMMENT '客户端类型：Android；iOS；H5；WEB',
  `idempotent` varchar(128) DEFAULT '' COMMENT '下注 幂等参数',
  `deduct_batch_number` bigint(20) DEFAULT '0' COMMENT '扣款批次号， 下注时通知用户扣款的批号编号',
  `award_batch_number` bigint(20) DEFAULT '0' COMMENT '中奖 通知派奖批次号',
  `cancel_batch_number` varchar(32) DEFAULT '' COMMENT '撤单批次号',
  `bunch2` int(11) DEFAULT '0' COMMENT '2串1总注数',
  `bunch3` int(11) DEFAULT '0' COMMENT '3串1总注数',
  `bunch4` int(11) DEFAULT '0' COMMENT '4串1总注数',
  `bunch5` int(11) DEFAULT '0' COMMENT '5串1总注数',
  `bunch6` int(11) DEFAULT '0' COMMENT '6串1总注数',
  `bunch7` int(11) DEFAULT '0' COMMENT '7串1总注数',
  `bunch8` int(11) DEFAULT '0' COMMENT '8串1总注数',
  `delete` tinyint(4) DEFAULT '0' COMMENT '是否删除（0：否；1：删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_01` (`tenant_code`,`member_id`,`status`,`delete`) USING BTREE,
  KEY `idx_02` (`tenant_code`,`delete`) USING BTREE,
  KEY `idx_03` (`tenant_code`,`order_done`,`delete`) USING BTREE,
  KEY `idx_04` (`order_done`,`delete`) USING BTREE,
  KEY `idx_05` (`delete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7995549690502913 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='足彩投注主订单';



CREATE TABLE `football_order_record_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tenant_code` varchar(32) NOT NULL DEFAULT '' COMMENT '租户code',
  `order_id` bigint(20) DEFAULT '0' COMMENT '主订单id',
  `competition_id` bigint(20) DEFAULT '0' COMMENT '赛事id',
  `match_id` bigint(20) DEFAULT '0' COMMENT '比赛id',
  `match_number` varchar(32) DEFAULT '' COMMENT '赛事编号',
  `match_time` datetime DEFAULT NULL COMMENT '开赛时间',
  `home_team_id` bigint(20) DEFAULT '0' COMMENT '主队id',
  `away_team_id` bigint(20) DEFAULT '0' COMMENT '客队id',
  `member_id` bigint(20) DEFAULT '0' COMMENT '用户id',
  `member_type` tinyint(4) DEFAULT '0' COMMENT '用户类型：1-注册游客、2-注册用户、3-充值用户、4-测试用户',
  `member_account` varchar(50) DEFAULT '' COMMENT '用户账号',
  `member_nickname` varchar(50) DEFAULT '' COMMENT '用户昵称',
  `bet_content` varchar(512) DEFAULT NULL COMMENT '单场赛事所有投注内容（格式：play_tag@赔率，多个逗号分隔）',
  `win_content` varchar(512) DEFAULT NULL COMMENT '单场赛事所有中奖玩法（格式：play_tag@赔率，多个逗号分隔）',
  `bet_result` varchar(256) DEFAULT '' COMMENT '赛果',
  `bets_point` int(11) DEFAULT '1' COMMENT '购彩点（1：快速购彩）',
  `status` tinyint(4) DEFAULT '0' COMMENT '订单状态（2:待开奖 5:未中奖 6:已中奖  10:已撤单 11:处理失败 12:扣款失败 20:异常订单）',
  `pos_gall` tinyint(4) DEFAULT '0' COMMENT '是否为定位胆（0：否；1：是）',
  `bunch_type` tinyint(4) DEFAULT '0' COMMENT '过关类型（1：胜平负；2：比分；3：进球数；4：半全场；5：混合过关）',
  `single_play` tinyint(4) DEFAULT '0' COMMENT '是否是单场赛事（0：否；1：是）',
  `client_type` varchar(8) DEFAULT NULL COMMENT '客户端类型：Android；iOS；H5；WEB',
  `delete` tinyint(4) DEFAULT '0' COMMENT '是否删除（0：否；1：删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_01` (`tenant_code`,`order_id`,`status`,`delete`) USING BTREE,
  KEY `idx_02` (`match_id`,`status`,`delete`) USING BTREE,
  KEY `idx_03` (`tenant_code`,`match_id`,`member_id`,`status`) USING BTREE,
  KEY `idx_04` (`tenant_code`,`status`,`delete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7995549690502915 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='足彩投注子订单表';




-- old

/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : wos_football_order

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 12/11/2020 21:37:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for football_dic_sys
-- ----------------------------
DROP TABLE IF EXISTS `football_dic_sys`;
CREATE TABLE `football_dic_sys`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '租户code',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '字典key',
  `parent_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '父级key',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '名称',
  `value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
  `status` tinyint(4) DEFAULT 1 COMMENT '状态：1-启用；0-停用',
  `sort` int(11) DEFAULT 0 COMMENT '排序，值越大越靠前',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operator_id` bigint(20) DEFAULT 0 COMMENT '创建人id',
  `operator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建人名称',
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_operator_id` bigint(20) DEFAULT 0 COMMENT '更新人',
  `update_operator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新人名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_01`(`tenant_code`, `code`, `status`) USING BTREE,
  INDEX `idx_02`(`tenant_code`, `parent_code`, `status`) USING BTREE,
  INDEX `idx_03`(`tenant_code`, `status`) USING BTREE,
  INDEX `idx_04`(`tenant_code`, `code`, `status`, `sort`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '足球购彩系统字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for football_order
-- ----------------------------
DROP TABLE IF EXISTS `football_order`;
CREATE TABLE `football_order`  (
  `id` bigint(20) NOT NULL,
  `tenant_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '租户code',
  `member_id` bigint(20) DEFAULT 0 COMMENT '用户id',
  `member_type` tinyint(4) DEFAULT 0 COMMENT '用户类型：1-注册游客、2-注册用户、3-充值用户、4-测试用户',
  `member_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户账号',
  `member_nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户昵称',
  `times` int(11) DEFAULT 0 COMMENT '投注倍数',
  `bet_amount` decimal(18, 3) DEFAULT 0.000 COMMENT '订单总金额',
  `max_win_amount` decimal(18, 3) DEFAULT 0.000 COMMENT '订单最大可中金额',
  `pass_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '过关方式。如：3*1（3串一）多个逗号分隔 ',
  `bunch_type` tinyint(4) DEFAULT 0 COMMENT '过关类型（1：胜平负；2：比分；3：进球数；4：半全场；5：混合过关）',
  `status` tinyint(4) DEFAULT 0 COMMENT '订单状态（1:待扣款 2:待开奖 3:开奖中 5:未中奖 6:已中奖  7:打和 10:已撤单 11:处理失败 12:扣款失败 20:异常订单）',
  `bet_count` int(11) DEFAULT 1 COMMENT '总投注数',
  `win_count` int(11) DEFAULT 1 COMMENT '总中奖注数',
  `win_amount` decimal(18, 3) DEFAULT 0.000 COMMENT '总中奖额度',
  `profit_amount` decimal(18, 3) DEFAULT 0.000 COMMENT '总盈利额度',
  `bets_point` tinyint(4) DEFAULT 1 COMMENT '购彩点（1：快速购彩）',
  `single_play` tinyint(4) DEFAULT 0 COMMENT '是否是单场赛事（0：否；1：是）',
  `order_done` tinyint(4) DEFAULT 0 COMMENT '订单是否已完结（0：否；1：是，所有子订单已处理完）',
  `client_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户端类型：Android；iOS；H5；WEB',
  `idempotent` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '下注 幂等参数',
  `bunch2` int(11) DEFAULT 0 COMMENT '2串1总注数',
  `bunch3` int(11) DEFAULT 0 COMMENT '3串1总注数',
  `bunch4` int(11) DEFAULT 0 COMMENT '4串1总注数',
  `bunch5` int(11) DEFAULT 0 COMMENT '5串1总注数',
  `bunch6` int(11) DEFAULT 0 COMMENT '6串1总注数',
  `bunch7` int(11) DEFAULT 0 COMMENT '7串1总注数',
  `bunch8` int(11) DEFAULT 0 COMMENT '8串1总注数',
  `delete` tinyint(4) DEFAULT 0 COMMENT '是否删除（0：否；1：删除）',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_01`(`tenant_code`, `member_id`, `delete`) USING BTREE,
  INDEX `idx_02`(`tenant_code`, `member_id`, `delete`) USING BTREE,
  INDEX `idx_03`(`tenant_code`, `delete`) USING BTREE,
  INDEX `idx_04`(`tenant_code`, `delete`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '足彩投注主订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for football_order_record
-- ----------------------------
DROP TABLE IF EXISTS `football_order_record`;
CREATE TABLE `football_order_record`  (
  `id` bigint(20) NOT NULL,
  `tenant_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '租户code',
  `order_id` bigint(20) DEFAULT 0 COMMENT '主订单id',
  `order_match_id` bigint(20) DEFAULT 0 COMMENT '用户同一场赛事的订单标识',
  `competition_id` bigint(20) DEFAULT 0 COMMENT '赛事id',
  `competition_name_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '赛事中文名称',
  `competition_name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '赛事英文名称',
  `competition_name_other` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '赛事其它语言名称，存i18n的key',
  `match_id` bigint(20) DEFAULT 0 COMMENT '比赛id',
  `match_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '赛事编号',
  `match_time` datetime(0) DEFAULT NULL COMMENT '开赛时间',
  `home_team_id` bigint(20) DEFAULT 0 COMMENT '主队id',
  `home_team_name_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '主队中文名',
  `home_team_name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '主队英文名',
  `home_team_name_other` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '主队其它语言名称，存i18n的key',
  `away_team_id` bigint(20) DEFAULT 0 COMMENT '客队id',
  `away_team_name_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '客队中文名',
  `away_team_name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '客队英文名',
  `away_team_name_other` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '客队其它语言名称，存i18n的key',
  `member_id` bigint(20) DEFAULT 0 COMMENT '用户id',
  `member_type` tinyint(4) DEFAULT 0 COMMENT '用户类型：1-注册游客、2-注册用户、3-充值用户、4-测试用户',
  `member_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户账号',
  `member_nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户昵称',
  `times` int(11) DEFAULT 0 COMMENT '投注倍数',
  `play_id` bigint(20) DEFAULT NULL COMMENT '二级玩法id',
  `bet_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '投注内容（格式：play_tag_id@赔率）',
  `bet_result` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '赛果',
  `bets_point` int(11) DEFAULT 1 COMMENT '购彩点（1：快速购彩）',
  `pass_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '过关方式。如：3*1（3串一）多个逗号分隔 ',
  `status` tinyint(4) DEFAULT 0 COMMENT '订单状态（1:待扣款 2:待开奖 3:开奖中 5:未中奖 6:已中奖  7:打和 10:已撤单 11:处理失败 12:扣款失败 20:异常订单）',
  `pos_gall` tinyint(4) DEFAULT 0 COMMENT '是否为定位胆（0：否；1：是）',
  `bunch_type` tinyint(4) DEFAULT 0 COMMENT '过关类型（1：胜平负；2：比分；3：进球数；4：半全场；5：混合过关）',
  `single_play` tinyint(4) DEFAULT 0 COMMENT '是否是单场赛事（0：否；1：是）',
  `order_done` tinyint(4) DEFAULT 0 COMMENT '订单是否已完结（0：否；1：是，该订单所属的赛事订单已处理完）',
  `client_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户端类型：Android；iOS；H5；WEB',
  `delete` tinyint(4) DEFAULT 0 COMMENT '是否删除（0：否；1：删除）',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_01`(`tenant_code`, `competition_id`, `match_id`, `member_id`, `status`) USING BTREE,
  INDEX `idx_02`(`tenant_code`, `competition_id`, `match_id`, `member_id`, `status`) USING BTREE,
  INDEX `idx_03`(`tenant_code`, `match_id`, `member_id`, `status`) USING BTREE,
  INDEX `idx_04`(`tenant_code`, `member_id`, `status`) USING BTREE,
  INDEX `idx_05`(`tenant_code`, `match_id`, `member_id`, `status`) USING BTREE,
  INDEX `idx_06`(`tenant_code`, `member_id`, `status`) USING BTREE,
  INDEX `idx_07`(`tenant_code`, `competition_id`, `member_id`, `status`) USING BTREE,
  INDEX `idx_08`(`tenant_code`, `order_id`, `competition_id`, `match_id`, `member_id`, `status`) USING BTREE,
  INDEX `idx_09`(`tenant_code`, `order_id`, `order_match_id`, `status`, `delete`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '足彩投注子订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for football_play
-- ----------------------------
DROP TABLE IF EXISTS `football_play`;
CREATE TABLE `football_play`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '玩法赔率名称',
  `category_id` bigint(20) DEFAULT 0 COMMENT '一级玩法大类id',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '一级玩法大类名称',
  `play_id` bigint(20) DEFAULT 0 COMMENT '二级玩法id',
  `play_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '二级玩法大类名称',
  `play_tag_id` bigint(20) DEFAULT 0 COMMENT '玩法规则Tag编号（由category_id+play_id+id）',
  `sort` int(11) DEFAULT 1 COMMENT '排序',
  `delete` tinyint(4) DEFAULT 0 COMMENT '是否删除（0：否；1：是）',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_01`(`category_id`, `play_id`, `delete`) USING BTREE,
  INDEX `idx_02`(`category_id`, `delete`) USING BTREE,
  INDEX `idx_03`(`play_tag_id`, `delete`) USING BTREE,
  INDEX `idx_04`(`delete`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '足球玩法表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for football_play_odds
-- ----------------------------
DROP TABLE IF EXISTS `football_play_odds`;
CREATE TABLE `football_play_odds`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '赔率id',
  `tenant_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '租户code',
  `match_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '所属比赛id',
  `match_name_zh` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '赛事名称，例中超，英超，意甲，世界杯',
  `match_name_en` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '赛事英文名称',
  `short_name_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '赛事中文短名称',
  `short_name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '赛事英文短名称',
  `short_home_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '主队简称/中文',
  `short_home_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '主队简称/英文',
  `short_away_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '客队简称/中文',
  `short_away_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '客队简称/英文',
  `issue_num` bigint(20) DEFAULT NULL COMMENT '序号, 标识周六032',
  `match_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '比赛时间',
  `sell_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '1,1,2,2,2\",  // 销售状态，胜平负,让球,比分,进球数,半全场',
  `play_tag_id` bigint(20) NOT NULL COMMENT '玩法编号id',
  `play_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '二级玩法id',
  `play_name_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '二级玩法名称/中文',
  `play_name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '二级玩法名称/英文',
  `play_tag_name_zh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '玩法编号名称/中文',
  `play_tag_name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '玩法编号名称/英文',
  `current_odds` decimal(14, 3) NOT NULL DEFAULT 0.000 COMMENT '当前赔率值',
  `change_odds` decimal(14, 3) NOT NULL DEFAULT 0.000 COMMENT '赛事变赔',
  `single_play` tinyint(4) DEFAULT 0 COMMENT '是否是单场赛事（0：否；1：是）',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `delete` tinyint(4) DEFAULT 0 COMMENT '是否删除（0：否；1：是）',
  `operator_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '操作人id',
  `operator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作员/中文',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_match_id`(`tenant_code`, `match_id`) USING BTREE,
  INDEX `idx_play_tag_id`(`tenant_code`, `play_tag_id`) USING BTREE,
  INDEX `idx_play_id`(`tenant_code`, `play_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '足球玩法赔率表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for football_play_odds_log
-- ----------------------------
DROP TABLE IF EXISTS `football_play_odds_log`;
CREATE TABLE `football_play_odds_log`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tenant_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '租户编码',
  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '操作类型， 1：足球  2：篮球',
  `match_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属比赛id',
  `latest_odds_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '实时赔率id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作内容(修改赔率集合字符串)',
  `channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '渠道来源, 如：IOS,ANDROID,H5,WEB-PC,WEB-PHONE',
  `operator_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '操作员ID',
  `operator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作员名称',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tenant_code`(`tenant_code`) USING BTREE,
  INDEX `idx_type`(`tenant_code`, `type`) USING BTREE,
  INDEX `idx_match_id`(`tenant_code`, `match_id`) USING BTREE,
  INDEX `idx_operator_id`(`tenant_code`, `operator_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '变赔赔率更新日志表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
