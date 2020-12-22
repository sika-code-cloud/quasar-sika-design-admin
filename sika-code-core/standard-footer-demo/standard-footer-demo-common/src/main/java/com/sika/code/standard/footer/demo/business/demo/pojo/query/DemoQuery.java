package com.sika.code.standard.footer.demo.business.demo.pojo.query;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.sika.code.standard.base.pojo.query.BaseStandardQuery;

/**
 * <p>
 * 示例表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-22 12:07:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DemoQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long demoId;
    /**
     * 名字
     */
    protected String name;
    /**
     * 年龄
     */
    protected Integer age;
    /**
     * 用户昵称
     */
    protected String nickname;
    /**
     * 优先级
     */
    protected Integer priority;

}

