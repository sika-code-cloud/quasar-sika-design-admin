package com.quasar.sika.design.server.business.post.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.post.service.PostService;
import com.quasar.sika.design.server.business.post.pojo.dto.PostDTO;
import com.quasar.sika.design.server.business.post.pojo.query.PostQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 岗位信息表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:48
 */
@RestController(value = "postController")
@RequestMapping("post")
public class PostController extends BaseStandardController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody PostDTO postDto) {
        return super.success(postService.save(postDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<PostDTO> postDtos) {
        return super.success(postService.saveForBatch(postDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody PostDTO postDto) {
        return super.success(postService.updateById(postDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody PostQuery postQuery) {
        return super.success(postService.page(postQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody PostQuery postQuery) {
        return super.success(postService.find(postQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody PostQuery postQuery) {
        return super.success(postService.list(postQuery));
    }
}
