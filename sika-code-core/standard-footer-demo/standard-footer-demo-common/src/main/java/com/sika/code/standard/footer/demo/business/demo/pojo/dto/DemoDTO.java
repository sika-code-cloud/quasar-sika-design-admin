package com.sika.code.standard.footer.demo.business.demo.pojo.dto;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 示例表
 * </p>
 *
 * @author daiqi
 * @since 2020-12-22 12:07:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DemoDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long demoId;
    /**
     * 名字
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 优先级
     */
    private Integer priority;

}
