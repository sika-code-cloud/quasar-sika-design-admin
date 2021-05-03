# quasar-sika-design-admin
[English](./README.md) | 简体中文

<h1 align="center">Quasar Sika Design Admin</h1>
<div align="center">
An out-of-box UI solution for enterprise applications as a Vue boilerplate. based on  <a href="http://www.quasarchs.com/" target="_blank"> Quasar </a>
</div>

<div align="center">

[![License](https://img.shields.io/npm/l/package.json.svg?style=flat)](https://github.com/dq-open-cloud/quasar-sika-design/blob/main/LICENSE)
[![Release](https://img.shields.io/badge/release-v1-blue)](https://github.com/dq-open-cloud/quasar-sika-design/releases)

</div>

- 预览: http://quasar.admin.sikacode.com/
- <a href="https://github.com/dq-open-cloud/quasar-sika-design-admin" target="_blank"> Github </a>
- <a href="https://gitee.com/sikadai/quasar-sika-design-admin" target="_blank"> Gitee </a>
- 交流QQ群：327424532
- 微信公众号：sikacode 开源社区
- 首页: 待完善
- 文档: 待完善
- 更新日志: 待完善
- 常见问题: 待完善

## 项目介绍
- quasar-sika-design-admin是一套企业级中后台管理系统解决方案,Quasar Sika Design Admin 是一个企业级中后台前端 / 设计解决方案，中后台管理模板，我们秉承 Ant Design 的设计价值观，致力于在设计规范和基础组件的基础上，继续向上构建，提炼出典型模板 / 业务组件 / 配套设计资源，进一步提升企业级中后台产品设计研发过程中的『用户』和『设计者』的体验

## 项目初衷
起于 Sika Design，不止于 Sika Design，每一个细节都是极致体验
愿景：开源改变世界，Sika Design Admin 让世界没有难写的代码。

## 项目特色
### 前端
- 优雅美观：基于 Ant Design 体系精心设计
- 常见设计模式：开源改变世界，Sika Design 让世界没有难写的代码
- 最新技术栈：使用 Quasar&Vue&echarts 等前端前沿技术开发
- 响应式：针对不同屏幕大小设计
- 主题：可配置的主题满足多样化的品牌诉求
- 最佳实践：良好的工程实践助你持续产出高质量代码
### 后端
- 优雅、简洁、规范而不失个性
- 抽象基础组件
- 约束代码规范
- **特色**的领域驱动设计实践【上下文对象context+executor执行者】
- 完整的代码生成器-新模块基础功能零开发

## 组织结构

```
- quasar-sika-design-admin
  - quasar-sika-design // 前端-基于vue+quasar构建
  - quasar-sika-design-server // 后端服务-基于springboot+mybatis
    - doc // 文档说明
      - sql // sql脚本
    - quasar-sika-design-server-common // 基础公共模块;包括业务的constant+dto+query以及baseDTO+baseSrvice等等
    - quasar-sika-design-server-core // 核心业务模块;包括业务的service，核心领域实现逻辑等等
    - quasar-sika-design-server-ataaccess // 数据库访问层
    - quasar-sika-design-server-generator // 代码生成器模块【无须开发】
    - quasar-sika-design-server-web // controller层
    - sika-code-cor // 核心公共组件，包括且不限于缓存组件、代码生成器组件、公共组件、数据访问、分布式锁、脚手架规范
      - cache // 缓存组件
      - code-generator // 代码生成器组件
      - common // 公共组件
      - databasse // 数据库访问组件
      - hutool-starter // hutool基础集成
      - lock // 分布式组件
      - standard-footer // 标准脚手架组件
 ```

## 技术选型
### 后端技术
| 技术 | 官网 | 备注 |
| --- | --- | --- |
| Spring Framework | http://projects.spring.io/spring-framework/ | 容器 |
| spring-boot-dependencies | https://spring.io/projects/spring-boot/ | 不解释 |
| Apache Shiro | http://shiro.apache.org/ | 安全框架 |
| MyBatis | http://www.mybatis.org/mybatis-3/zh/index.html | ORM框架 |
| MyBatisPlus | https://mp.baomidou.com/ | ORM增强框架 |
| Mybatis-plus-boot-starter | https://mp.baomidou.com/ | ORM增强框架 |
| Mybatis-Plus-Generator | https://baomidou.gitee.io/mybatis-plus-doc/#/generate-code/ | ORM增强框架 |
| HikariCP | https://github.com/brettwooldridge/HikariCP/ | 数据库连接池 |
| ShardingSphere | https://shardingsphere.apache.org/ | 分库分表组件 |
| Redis | https://redis.io/ | 分布式缓存数据库 |
| commons-collections | http://commons.apache.org/proper/commons-collections/ | 集合工具组件 |
| Log4J | http://logging.apache.org/log4j/1.2/ | 日志组件 |
| FastJson | https://mvnrepository.com/artifact/com.alibaba/fastjson/ | JSON序列化和反序列化组件 |
| Lombok | https://www.projectlombok.org/ | 简化JAVA代码组件 |
| Hutool | http://hutool.mydoc.io/ | 符合国人习惯的工具组件 |
| MapStruct | http://mapstruct.org/ | 实体转化组件 |

### 前端技术
| 技术 | 官网 | 备注 |
| --- | --- | --- |
| Vue | https://cn.vuejs.org/ | 渐进式JavaScript 框架 |
| Quasar | http://www.quasarchs.com/ | 基于Vue实现的前端UI框架 |
| Echarts | https://echarts.apache.org/zh/index.html/ | 基于 JavaScript 的开源可视化图表库 |
| Lodashi | https://www.lodashjs.com/ | 一致性、模块化、高性能的 JavaScript 实用工具库 |

Overview
----

基于 [Quasar](http://www.quasarchs.com/quasar-cli/installation/#Introduction) 实现的 [Quasar Sika Design_Admin](http://quasar.admin.sikacode.com/) 

PC端示例
----
<div style="float:left;border:solid 1px #000;margin:2px;">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/dashboard_analysis.png" width="49%" height="160px">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/dashboard_workplace.png" width="49%" height="160px">
</div>

***

<div style="float:left;border:solid 1px #000;margin:2px;">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/dashboard_monitor.png" width="32%" height="120px">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/dashboard_workplace.png" width="32%" height="120px">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/account_settings.png" width="32%" height="120px">
</div>

***

<div style="float:left;border:solid 1px #000;margin:2px;">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/account_settings.png" width="32%">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/form_advanced-form.png" width="32%">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/list_goodsList.png" width="32%">
</div>

***

<div style="float:left;border:solid 1px #000;margin:2px;">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/list_goodsList.png" width="32%">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/profile_advanced.png" width="32%">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/pc/right_lan.png" width="32%">
</div>

mobile端示例
----
<div style="float:left;border:solid 1px #000;margin:2px;">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/mobile/center1.png" width="24%">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/mobile/center2.png" width="24%">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/mobile/monitor2.png" width="24%" >
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/mobile/center.png" width="24%"  >
</div>

***

<div style="border:solid 1px #000;margin:2px; text-align: center">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/mobile/dashboard_workplace1.png" width="24%">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/mobile/account_settings.png" width="24%" >
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/mobile/account_settings.png" width="24%">
    <img src="https://gitee.com/sikadai/sika-material/raw/master/quasar-sika-design/case/imgs/mobile/list_goods-list.png" width="24%">
</div>

服务端代码示例
----

#### Controller 由代码生成器生成

```
package com.quasar.sika.design.server.business.menu.controller;
import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.menu.service.MenuService;
import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;
import com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:13
 */
@RestController(value = "menuController")
@RequestMapping("menu")
public class MenuController extends BaseStandardController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody MenuDTO menuDto) {
        return super.success(menuService.save(menuDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<MenuDTO> menuDtos) {
        return super.success(menuService.saveForBatch(menuDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody MenuDTO menuDto) {
        return super.success(menuService.updateById(menuDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody MenuQuery menuQuery) {
        return super.success(menuService.page(menuQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody MenuQuery menuQuery) {
        return super.success(menuService.find(menuQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody MenuQuery menuQuery) {
        return super.success(menuService.list(menuQuery));
    }
}

```
#### Service 由代码生成器生成
```
package com.quasar.sika.design.server.business.menu.service;
import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;
import com.sika.code.standard.base.service.BaseStandardService;
import java.util.List;
/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:09
 */
public interface MenuService extends BaseStandardService<MenuDTO> {

}

```

#### ServiceImpl 由代码生成器生成
```
package com.quasar.sika.design.server.business.menu.service.impl;
import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.quasar.sika.design.server.business.menu.convert.MenuConvert;
import com.quasar.sika.design.server.business.menu.entity.MenuEntity;
import com.quasar.sika.design.server.business.menu.mapper.MenuMapper;
import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;
import com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery;
import com.quasar.sika.design.server.business.menu.service.MenuService;
import com.quasar.sika.design.server.business.rolemenu.service.RoleMenuService;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:10
 */
@Service(value = "menuService")
public class MenuServiceImpl extends BaseStandardServiceImpl<MenuMapper, MenuEntity, MenuDTO> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    protected BaseConvert<MenuEntity, MenuDTO> convert() {
        return MenuConvert.INSTANCE;
    }
}

```

#### Mapper 由代码生成器生成
```
package com.quasar.sika.design.server.business.menu.mapper;

import com.quasar.sika.design.server.business.menu.entity.MenuEntity;
import org.springframework.stereotype.Repository;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:08
 */
@Repository
public interface MenuMapper extends BaseStandardMapper<MenuEntity> {

}

```
#### Xml 由代码生成器生成
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.quasar.sika.design.server.business.menu.mapper.MenuMapper">

    <!-- 通用查询映射结果 -->
    <resultMap id="ResultMap" type="com.quasar.sika.design.server.business.menu.entity.MenuEntity">
        <result column="id" property="id" />
        <result column="create_by" property="createBy" />
        <result column="update_by" property="updateBy" />
        <result column="create_date" property="createDate" />
        <result column="update_date" property="updateDate" />
        <result column="version" property="version" />
        <result column="available" property="available" />
        <result column="is_deleted" property="isDeleted" />
        <result column="remark" property="remark" />
        <result column="menu_name" property="menuName" />
        <result column="parent_id" property="parentId" />
        <result column="order_num" property="orderNum" />
        <result column="url" property="url" />
        <result column="target" property="target" />
        <result column="menu_type" property="menuType" />
        <result column="visible" property="visible" />
        <result column="perms" property="perms" />
        <result column="icon" property="icon" />
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="columnList">
        id, create_by, update_by, create_date, update_date, version, available, is_deleted, remark, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon
    </sql>

    <!-- 根据查询条件获取列表信息 -->
    <select id="listByQuery" resultMap="ResultMap" parameterType="com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery" >
        SELECT <include refid="columnList" />
        FROM sika_menu
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
    </select>

    <!-- 根据查询条件获取Id列表信息 -->
    <select id="listIdByQuery" resultType="java.lang.Long" parameterType="com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery" >
        SELECT id
        FROM sika_menu
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
    </select>

    <!-- 根据查询条件获取实体信息 -->
    <select id="findByQuery" resultMap="ResultMap" parameterType="com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery" >
        SELECT <include refid="columnList" />
        FROM sika_menu
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
        LIMIT 1
    </select>

    <!-- 根据查询条件获取表id -->
    <select id="findIdByQuery" resultType="java.lang.Long" parameterType="com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery" >
        SELECT id
        FROM sika_menu
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
        LIMIT 1
    </select>

    <!-- 根据查询条件获取分页信息 -->
    <select id="pageByQuery" resultMap="ResultMap" parameterType="com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery" >
        SELECT <include refid="columnList" />
        FROM sika_menu
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
        <include refid="order_by_sql"/>
    </select>

    <!-- 根据查询条件获取总数量信息 -->
    <select id="totalCountByQuery" resultType="Integer" parameterType="com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery" >
        SELECT count(*)
        FROM sika_menu
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
    </select>

    <!-- 根据查询条件SQL -->
    <sql id="query_sql" >
        <if test="query.id != null">AND id = #{query.id}</if>
        <if test="query.menuId != null">AND id = #{query.menuId}</if>
        <if test="query.menuName != null">AND menu_name = #{query.menuName}</if>
        <if test="query.parentId != null">AND parent_id = #{query.parentId}</if>
        <if test="query.orderNum != null">AND order_num = #{query.orderNum}</if>
        <if test="query.url != null">AND url = #{query.url}</if>
        <if test="query.target != null">AND target = #{query.target}</if>
        <if test="query.menuType != null">AND menu_type = #{query.menuType}</if>
        <if test="query.visible != null">AND visible = #{query.visible}</if>
        <if test="query.perms != null">AND perms = #{query.perms}</if>
        <if test="query.icon != null">AND icon = #{query.icon}</if>
        <if test="query.ids != null and query.ids.size() > 0">
            AND id in
            <foreach item="item" collection="query.ids" separator="," open="(" close=")" index="">
                #{item}
            </foreach>
        </if>
    </sql>

    <!-- 排序的sql -->
    <sql id="order_by_sql">
        <if test="query.sortColumn != null and query.sortType != null" >
            ORDER BY
            <include refid="order_by_column_sql"/>
            <include refid="order_by_type_sql"/>
        </if>
    </sql>

    <!-- 排序列名的sql -->
    <sql id="order_by_column_sql">
        <choose>
            <when test="query.sortColumn == 'menuId'">
                id
            </when>
            <otherwise>
                id
            </otherwise>
        </choose>
    </sql>

    <!-- 排序类型的sql -->
    <sql id="order_by_type_sql">
        <choose>
            <when test="query.sortType == 'DESC'">
                DESC
            </when>
            <otherwise>
                ASC
            </otherwise>
        </choose>
    </sql>
</mapper>

```

#### Context【领域上下文对象】
```
package com.quasar.sika.design.server.common.auth.context;

import com.quasar.sika.design.server.common.auth.executor.AuthRegisterRequestExecutor;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.mail.context.CheckMailCodeContext;
import com.quasar.sika.design.server.common.mail.pojo.request.CheckMailRequest;
import com.quasar.sika.design.server.common.shiro.util.SHA256Util;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author daiqi
 * @create 2021-01-09 18:02
 */
@Data
@Accessors(chain = true)
public class AuthRegisterContext extends BaseStandardContext {
    private AuthRegisterRequest registerRequest;
    private CheckMailRequest checkMailRequest;
    protected CaptchaCheckRequest captchaCheckRequest;
    private CheckMailCodeContext checkMailCodeContext;
    private Boolean bindOauthUser;

    @Override
    public void initCustomer() {
        checkMailCodeContext = new CheckMailCodeContext().setRequest(checkMailRequest);
        registerRequest.setPassword(SHA256Util.sha256(registerRequest));
    }

    @Override
    protected Class<? extends BaseStandardExecutor> buildExecutorClass() {
        return AuthRegisterRequestExecutor.class;
    }
}

```
#### Executor领域执行者对象【按领域划分】
```
package com.quasar.sika.design.server.common.auth.executor;

import cn.hutool.core.util.BooleanUtil;
import com.quasar.sika.design.server.common.auth.context.AuthRegisterContext;
import com.quasar.sika.design.server.common.auth.domain.AuthDomain;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.exception.BusinessException;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author daiqi
 * @create 2021-01-09 18:02
 */
@Data
@Accessors(chain = true)
public class AuthRegisterRequestExecutor extends BaseStandardExecutor<AuthRegisterContext> implements AuthDomain {
    @Override
    protected void executeBefore() {
        verify();
    }

    protected void verify() {
        // 图片验证码校验
        captchaService().checkCaptchaVerifyCode(context.getCaptchaCheckRequest());
        // 邮箱验证码校验
        executorManager().execute(context.getCheckMailCodeContext());
        // 校验用户名
        AuthRegisterRequest registerRequest = context.getRegisterRequest();
        authService().checkRegisterUsername(registerRequest);
        // 校验邮箱
        authService().checkRegisterEmail(registerRequest);
        // 校验手机号
        authService().checkRegisterPhone(registerRequest);
    }

    @Override
    protected ServiceResult doExecute() {
        AuthRegisterRequest registerRequest = context.getRegisterRequest();
        boolean saveSuccess = userService().save(registerRequest);
        if (BooleanUtil.isFalse(saveSuccess)) {
            throw new BusinessException("保存失败,请校验注册参数");
        }
        return ServiceResult.newInstanceOfSucResult(AuthResponse.success(registerRequest));
    }

    @Override
    protected void executeAfter() {
        // 自动登录
        AuthRegisterRequest registerRequest = context.getRegisterRequest();
        AuthLoginRequest request = new AuthLoginRequest(registerRequest.getUsername(), registerRequest.getPassword());
        request.setEncryptedPassword(true);
        if (BooleanUtil.isTrue(context.getBindOauthUser())) {
            authService().bindOauthUser(request);
        } else {
            authService().login(request);
        }
        // 移除缓存
        captchaService().removeCaptchaVerifyCode(context.getCaptchaCheckRequest());
        mailService().removeMailCode(context.getCheckMailRequest());
    }
}

```

前端环境和依赖
----
- node
- yarn
- webpack
- eslint
- @vue/cli ~3
- [Quasar](https://github.com/quasarframework/quasar) - Quasar Of Vue 实现

> 请注意，我们强烈建议本项目使用 [Yarn](https://yarnpkg.com/) 包管理工具，这样可以与本项目演示站所加载完全相同的依赖版本 (yarn.lock) 。由于我们没有对依赖进行强制的版本控制，采用非 yarn 包管理进行引入时，可能由于 Pro 所依赖的库已经升级版本而引入了新版本所导致的问题。作者可能会由于时间问题无法及时排查而导致您采用本项目作为基项目而出现问题。



项目下载和运行
----

- 拉取项目代码
```bash
git clone https://github.com/dq-open-cloud/quasar-sika-design.git
cd quasar-sika-design
```

- 安装依赖
```
yarn install
```

- 开发模式运行
```
quasar dev
```

- 编译项目
```
quasar build
```

- Lints and fixes files
```
yarn run lint
```

文档待完善

启动步骤
- 找到QuasarSikaDesignServerApplication直接运行
- 前端quasar-dev启动运行即可

其他说明
----

- **关于 Issue 反馈 (重要!重要!重要!) 请在开 *Issue* 前，先阅读该内容：[Issue / PR 编写建议](https://github.com/vueComponent/ant-design-vue-pro/issues/90)** 

- 项目使用的 [quasar-cli](http://www.quasarchs.com/quasar-cli/installation/#Introduction), 请确保你所使用的 quasar-cli 是新版，并且已经学习 cli 官方文档使用教程

- 关闭 Eslint (不推荐) 移除 `package.json` 中 `eslintConfig` 整个节点代码, `vue.config.js` 下的 `lintOnSave` 值改为 `false`

- **用于生产环境，请使用 `release` 版本代码，使用 master 代码出现的任何问题需要你自行解决**

- **后端提供的`Mysql`和`Redis`环境属于线上测试环境，内测阶段请大家一定不要随意增删字段**

## 浏览器兼容

- Chrome for Android >= 87
- Firefox for Android >= 83
- Android >= 81
- Chrome >= 77
- Edge >= 84
- Firefox >= 74
- iOS >= 10.3
- Opera >= 68
- Safari >= 11

| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="IE / Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE / Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/opera/opera_48x48.png" alt="Opera" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Opera |
| --- | --- | --- | --- | --- |
| IE10, Edge | last 2 versions | last 2 versions | last 2 versions | last 2 versions |


## Contributors

This project exists thanks to all the people who contribute. 
<a href="https://github.com/vueComponent/ant-design-vue-pro/graphs/contributors"><img src="https://opencollective.com/ant-design-pro-vue/contributors.svg?width=890&button=false" /></a>

### Customize the configuration
See [Configuring quasar.conf.js](https://quasar.dev/quasar-cli/quasar-conf-js).

