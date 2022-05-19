package com.itran.fgoc.server.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itran.fgoc.common.mybatisplus.FgocPage;
import com.itran.fgoc.common.mybatisplus.PageParam;
import com.itran.fgoc.server.test.entity.param.demo.DemoPageParam;
import com.itran.fgoc.server.test.entity.param.demo.DemoSaveUpdateParam;
import com.itran.fgoc.server.test.entity.po.Demo;
import com.itran.fgoc.server.test.entity.vo.demo.DemoVO;
import com.itran.fgoc.server.test.entity.vo.demo.DemoPageVO;

/**
 * @author chun
 * @date 2020/9/4
 * @description
 */
public interface DemoService extends IService<Demo> {

    /**
     * 保存数据
     *
     * @param param
     */
    void save(DemoSaveUpdateParam param);

    /**
     * 修改数据
     *
     * @param param
     * @param id
     */
    void update(DemoSaveUpdateParam param, Long id);

    /**
     * 分页查询
     *
     * @param page
     * @param param
     * @return
     */
    FgocPage<DemoPageVO> demoPage(PageParam page, DemoPageParam param);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    DemoVO demo(Long id);


    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    void deleteById(Long id);
}
