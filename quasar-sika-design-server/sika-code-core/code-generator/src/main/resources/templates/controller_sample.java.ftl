package ${package.Controller};


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import ${table.modulePackage}.business.${package.ModuleName}.service.${table.classBodyName}Service;
import ${package.DTO}.${table.classBodyName}DTO;
import ${package.Query}.${table.classBodyName}Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@RestController(value = "${table.controllerName?uncap_first}")
@RequestMapping("${table.nameRemovePrefix}")
public class ${table.controllerName} extends BaseStandardController {
    @Autowired
    private ${table.classBodyName}Service ${table.classBodyName?uncap_first}Service;

    @RequestMapping(value = "save")
    public Result save(@RequestBody ${table.classBodyName}DTO ${table.classBodyName?uncap_first}Dto) {
        return super.success(${table.classBodyName?uncap_first}Service.save(${table.classBodyName?uncap_first}Dto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<${table.classBodyName}DTO> ${table.classBodyName?uncap_first}Dtos) {
        return super.success(${table.classBodyName?uncap_first}Service.saveForBatch(${table.classBodyName?uncap_first}Dtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody ${table.classBodyName}DTO ${table.classBodyName?uncap_first}Dto) {
        return super.success(${table.classBodyName?uncap_first}Service.updateById(${table.classBodyName?uncap_first}Dto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody ${table.classBodyName}Query ${table.classBodyName?uncap_first}Query) {
        return super.success(${table.classBodyName?uncap_first}Service.page(${table.classBodyName?uncap_first}Query));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody ${table.classBodyName}Query ${table.classBodyName?uncap_first}Query) {
        return super.success(${table.classBodyName?uncap_first}Service.find(${table.classBodyName?uncap_first}Query));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody ${table.classBodyName}Query ${table.classBodyName?uncap_first}Query) {
        return super.success(${table.classBodyName?uncap_first}Service.list(${table.classBodyName?uncap_first}Query));
    }
}
