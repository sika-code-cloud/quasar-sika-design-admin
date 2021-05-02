package ${package.Convert};

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ${package.Entity}.${entity};
import ${package.DTO}.${table.classBodyName}DTO;

/**
 * <p>
 * ${table.comment!} 转换类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${table.classBodyName}Convert extends BaseConvert<${table.entityName}, ${table.classBodyName}DTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    ${table.classBodyName}Convert INSTANCE = Mappers.getMapper(${table.classBodyName}Convert.class);

}
