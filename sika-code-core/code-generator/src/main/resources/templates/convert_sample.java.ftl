package ${package.Convert};

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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
    /**
     * <p>
     * 将实体对象转化为DTO对象
     * </p>
     *
     * @param ${table.entityName?uncap_first} : 实体对象
     * @return ${table.classBodyName?uncap_first}DTO : 数据传输对象
     * @author ${author}
     * @date ${cfg.date}
     */
    @Override
    @Mappings({@Mapping(source = "id", target = "${table.classBodyName?uncap_first}Id")})
    ${table.classBodyName}DTO convertToDTO(${table.entityName} ${table.entityName?uncap_first});

    /**
     * <p>
     * 将DTO对象转化为实体对象
     * </p>
     *
     * @param ${table.classBodyName?uncap_first}DTO : 数据传输对象
     * @return ${table.entityName?uncap_first} : 实体对象
     * @author ${author}
     * @date ${cfg.date}
     */
    @Override
    @Mappings({@Mapping(source = "${table.classBodyName?uncap_first}Id", target = "id")})
    ${table.entityName} convertToEntity(${table.classBodyName}DTO ${table.classBodyName?uncap_first}DTO);

}
