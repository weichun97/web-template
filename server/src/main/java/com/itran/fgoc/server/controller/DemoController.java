package com.itran.fgoc.server.controller;

import com.itran.fgoc.common.core.annotation.RepeatSubmit;
import com.itran.fgoc.common.core.api.Response;
import com.itran.fgoc.common.mybatisplus.FgocPage;
import com.itran.fgoc.common.mybatisplus.PageParam;
import com.itran.fgoc.server.entity.param.demo.DemoPageParam;
import com.itran.fgoc.server.entity.param.demo.DemoSaveUpdateParam;
import com.itran.fgoc.server.entity.vo.demo.DemoPageVO;
import com.itran.fgoc.server.entity.vo.demo.DemoVO;
import com.itran.fgoc.server.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * @author chun
 * @date 2020/9/4
 * @description demo
 */
@RequestMapping("demo")
@RestController
@Api(value = "DemoController", tags = "demo")
public class DemoController {

    @Autowired
    private DemoService service;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取")
    public Response<DemoVO> demo(@PathVariable Long id) {
        return Response.success(service.demo(id));
    }

    @RepeatSubmit(10)
    @GetMapping("page")
    @ApiOperation(value = "分页查询")
    public Response<FgocPage<DemoPageVO>> demoPage(@Valid PageParam page, @Valid DemoPageParam param) {
        return Response.success(service.demoPage(page, param));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public Response save(@Valid @RequestBody DemoSaveUpdateParam param) {
        service.save(param);
        return Response.success();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "修改")
    public Response update(@Valid @RequestBody DemoSaveUpdateParam param, @PathVariable Long id) {
        service.update(param, id);
        return Response.success();
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除")
    public Response deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return Response.success();

    }
}
