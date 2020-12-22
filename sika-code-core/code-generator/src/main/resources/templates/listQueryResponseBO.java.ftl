package ${package.ListQueryResponseBO};

import ${package.DTO}.${table.classBodyName}DTO;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.domain.${table.classBodyName}Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * <p>
 * ${table.comment!} 普通查询响应类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@Accessors(chain = true)
public class ${table.classBodyName}ListQueryResponseBO implements BaseStandardResponseBO<List<${table.classBodyName}DTO>>, ${table.classBodyName}Domain {
    /**
    * 返回给页面的响应对象
    */
    private List<${table.classBodyName}ListQueryResponse> queryResponse;

    @Override
    public void build(List<${table.classBodyName}DTO> queryResponse) {
        this.queryResponse = convert().convertToListQueryResponses(queryResponse);
    }

    /**
     * <p>
     * 响应类 : 封装响应数据
     * </p>
     *
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public static class ${table.classBodyName}ListQueryResponse extends ${table.classBodyName}DTO {

    }
}