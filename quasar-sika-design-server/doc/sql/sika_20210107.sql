-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sika_dept;
create table sika_dept
(
    id          bigint(20)                    not null auto_increment comment '部门id',
    parent_id   bigint(20)                             default 0 comment '父部门id',
    ancestors   varchar(50)                            default '' comment '祖级列表',
    dept_name   varchar(30)                            default '' comment '部门名称',
    order_num   int(4)                                 default 0 comment '显示顺序',
    leader      varchar(20)                            default null comment '负责人',
    phone       varchar(11)                            default null comment '联系电话',
    email       varchar(50)                            default null comment '邮箱',
    create_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    KEY idx_update_date (update_date),
    primary key (id)
) engine = innodb
  auto_increment = 200 comment = '部门表';


-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sika_user;
CREATE TABLE sika_user
(
    id          bigint(20) unsigned                              NOT NULL AUTO_INCREMENT COMMENT 'ID',
    create_by   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                                          NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                                          NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                                          NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    username    varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '用户名',
    password    varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '密码',
    oauthPwd    varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '授权密码',
    nickName    varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '用户昵称',
    sex         int(11)                                          NOT NULL DEFAULT '0' COMMENT '性别【1：男，2：女，0：未知】',
    phone       varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '手机号',
    email       varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '邮箱',
    avatar      varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '头像',
    token       varchar(63) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT 'token',
    type        int(11)                                          NOT NULL DEFAULT '1' COMMENT '用户类型：1：游客，2：系统用户',
    PRIMARY KEY (id),
    KEY idx_username (username),
    KEY idx_email (email),
    KEY idx_phone (phone),
    KEY idx_token (token)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='用户表';

-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
drop table if exists sika_post;
create table sika_post
(
    id          bigint(20)                    not null auto_increment comment '岗位ID',
    post_code   varchar(64)                   not null comment '岗位编码',
    post_name   varchar(50)                   not null comment '岗位名称',
    post_sort   int(4)                        not null comment '显示顺序',
    create_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    KEY idx_update_date (update_date),
    primary key (id)
) engine = innodb comment = '岗位信息表';


-- ----------------------------
-- 4、角色信息表
-- ----------------------------
drop table if exists sika_role;
create table sika_role
(
    id          bigint(20)                    not null auto_increment comment '角色ID',
    role_name   varchar(30)                   not null comment '角色名称',
    role_key    varchar(100)                  not null comment '角色权限字符串',
    role_sort   int(4)                        not null comment '显示顺序',
    data_scope  char(1)                                default '1' comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    create_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    KEY idx_update_date (update_date),
    primary key (id)
) engine = innodb
  auto_increment = 100 comment = '角色信息表';


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
drop table if exists sika_menu;
create table sika_menu
(
    id          bigint(20)                    not null auto_increment comment '菜单ID',
    menu_name   varchar(50)                   not null comment '菜单名称',
    parent_id   bigint(20)                             default 0 comment '父菜单ID',
    order_num   int(4)                                 default 0 comment '显示顺序',
    url         varchar(200)                           default '#' comment '请求地址',
    target      varchar(20)                            default '' comment '打开方式（menuItem页签 menuBlank新窗口）',
    menu_type   char(1)                                default '' comment '菜单类型（M目录 C菜单 F按钮）',
    visible     char(1)                                default 0 comment '菜单状态（0显示 1隐藏）',
    perms       varchar(100)                           default null comment '权限标识',
    icon        varchar(100)                           default '#' comment '菜单图标',
    create_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    KEY idx_update_date (update_date),
    primary key (id)
) engine = innodb
  auto_increment = 2000 comment = '菜单权限表';

-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sika_user_role;
create table sika_user_role
(
    id          bigint(20)                    not null auto_increment comment '主键',
    user_id     bigint(20)                    not null comment '用户ID',
    role_id     bigint(20)                    not null comment '角色ID',
    create_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id)
) engine = innodb comment = '用户和角色关联表';

-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sika_role_menu;
create table sika_role_menu
(
    id          bigint(20)                    not null auto_increment comment '主键',
    role_id     bigint(20)                    not null comment '角色ID',
    menu_id     bigint(20)                    not null comment '菜单ID',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id)
) engine = innodb comment = '角色和菜单关联表';


-- ----------------------------
-- 8、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sika_role_dept;
create table sika_role_dept
(
    id          bigint(20)                    not null auto_increment comment '主键',
    role_id     bigint(20)                    not null comment '角色ID',
    dept_id     bigint(20)                    not null comment '部门ID',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id)
) engine = innodb comment = '角色和部门关联表';


-- ----------------------------
-- 9、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sika_user_post;
create table sika_user_post
(
    id          bigint(20)                    not null auto_increment comment '主键',
    user_id     bigint(20)                    not null comment '用户ID',
    post_id     bigint(20)                    not null comment '岗位ID',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id)
) engine = innodb comment = '用户与岗位关联表';



-- ----------------------------
-- 10、操作日志记录
-- ----------------------------
drop table if exists sika_oper_log;
create table sika_oper_log
(
    id             bigint(20)                    not null auto_increment comment '日志主键',
    title          varchar(50)                            default '' comment '模块标题',
    business_type  int(2)                                 default 0 comment '业务类型（0其它 1新增 2修改 3删除）',
    method         varchar(100)                           default '' comment '方法名称',
    request_method varchar(10)                            default '' comment '请求方式',
    operator_type  int(1)                                 default 0 comment '操作类别（0其它 1后台用户 2手机端用户）',
    oper_name      varchar(50)                            default '' comment '操作人员',
    dept_name      varchar(50)                            default '' comment '部门名称',
    oper_url       varchar(255)                           default '' comment '请求URL',
    oper_ip        varchar(50)                            default '' comment '主机地址',
    oper_location  varchar(255)                           default '' comment '操作地点',
    oper_param     varchar(2000)                          default '' comment '请求参数',
    json_result    varchar(2000)                          default '' comment '返回参数',
    status         int(1)                                 default 0 comment '操作状态（0正常 1异常）',
    error_msg      varchar(2000)                          default '' comment '错误消息',
    oper_time      datetime comment '操作时间',
    create_by      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date    datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date    datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version        int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available      int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted     int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark         varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id)
) engine = innodb
  auto_increment = 100 comment = '操作日志记录';


-- ----------------------------
-- 11、字典类型表
-- ----------------------------
drop table if exists sika_dict_type;
create table sika_dict_type
(
    id          bigint(20)                    not null auto_increment comment '字典主键',
    dict_name   varchar(100)                           default '' comment '字典名称',
    dict_type   varchar(100)                           default '' comment '字典类型',
    create_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id),
    unique (dict_type)
) engine = innodb
  auto_increment = 100 comment = '字典类型表';

-- ----------------------------
-- 12、字典数据表
-- ----------------------------
drop table if exists sika_dict_data;
create table sika_dict_data
(
    id          bigint(20)                    not null auto_increment comment '字典编码',
    dict_sort   int(4)                                 default 0 comment '字典排序',
    dict_label  varchar(100)                           default '' comment '字典标签',
    dict_value  varchar(100)                           default '' comment '字典键值',
    dict_type   varchar(100)                           default '' comment '字典类型',
    css_class   varchar(100)                           default null comment '样式属性（其他样式扩展）',
    list_class  varchar(100)                           default null comment '表格回显样式',
    is_default  char(1)                                default 'N' comment '是否默认（Y是 N否）',
    create_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by   varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version     int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available   int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted  int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id)
) engine = innodb
  auto_increment = 100 comment = '字典数据表';


-- ----------------------------
-- 13、参数配置表
-- ----------------------------
drop table if exists sika_config;
create table sika_config
(
    id           int(5)                        not null auto_increment comment '参数主键',
    config_name  varchar(100)                           default '' comment '参数名称',
    config_key   varchar(100)                           default '' comment '参数键名',
    config_value varchar(500)                           default '' comment '参数键值',
    config_type  char(1)                                default 'N' comment '系统内置（Y是 N否）',
    create_by    varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by    varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date  datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date  datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version      int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available    int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted   int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark       varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id)
) engine = innodb
  auto_increment = 100 comment = '参数配置表';


-- ----------------------------
-- 14、系统访问记录
-- ----------------------------
drop table if exists sika_logininfor;
create table sika_logininfor
(
    id             bigint(20)                    not null auto_increment comment '访问ID',
    login_name     varchar(50)                            default '' comment '登录账号',
    ipaddr         varchar(50)                            default '' comment '登录IP地址',
    login_location varchar(255)                           default '' comment '登录地点',
    browser        varchar(50)                            default '' comment '浏览器类型',
    os             varchar(50)                            default '' comment '操作系统',
    status         char(1)                                default '0' comment '登录状态（0成功 1失败）',
    msg            varchar(255)                           default '' comment '提示消息',
    login_time     datetime comment '访问时间',
    create_by      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date    datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date    datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version        int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available      int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted     int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark         varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id)
) engine = innodb
  auto_increment = 100 comment = '系统访问记录';


-- ----------------------------
-- 15、在线用户记录
-- ----------------------------
drop table if exists sika_user_online;
create table sika_user_online
(
    id               bigint(20)                    not null auto_increment comment '访问ID',
    sessionId        varchar(50)                            default '' comment '用户会话id',
    login_name       varchar(50)                            default '' comment '登录账号',
    dept_name        varchar(50)                            default '' comment '部门名称',
    ipaddr           varchar(50)                            default '' comment '登录IP地址',
    login_location   varchar(255)                           default '' comment '登录地点',
    browser          varchar(50)                            default '' comment '浏览器类型',
    os               varchar(50)                            default '' comment '操作系统',
    status           varchar(10)                            default '' comment '在线状态on_line在线off_line离线',
    start_timestamp  datetime comment 'session创建时间',
    last_access_time datetime comment 'session最后访问时间',
    expire_time      int(5)                                 default 0 comment '超时时间，单位为分钟',
    create_by        varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by        varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date      datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date      datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version          int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available        int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted       int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark           varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id),
    unique key (sessionId)
) engine = innodb comment = '在线用户记录';


-- ----------------------------
-- 18、通知公告表
-- ----------------------------
drop table if exists sika_notice;
create table sika_notice
(
    id             int(4)                        not null auto_increment comment '公告ID',
    notice_title   varchar(50)                   not null comment '公告标题',
    notice_type    char(1)                       not null comment '公告类型（1通知 2公告）',
    notice_content varchar(2000)                          default null comment '公告内容',
    status         char(1)                                default '0' comment '公告状态（0正常 1关闭）',
    create_by      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    update_by      varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    create_date    datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_date    datetime                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    version        int(11)                       NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    available      int(11)                       NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    is_deleted     int(11)                       NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    remark         varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    primary key (id)
) engine = innodb
  auto_increment = 10 comment = '通知公告表';
