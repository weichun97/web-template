package com.itran.fgoc.server.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itran.fgoc.common.core.api.SqlCode;
import com.itran.fgoc.common.core.exception.ApiException;
import com.itran.fgoc.common.mybatisplus.FgocPage;
import com.itran.fgoc.common.mybatisplus.PageParam;
import com.itran.fgoc.server.test.entity.param.demo.DemoPageParam;
import com.itran.fgoc.server.test.entity.param.demo.DemoSaveUpdateParam;
import com.itran.fgoc.server.test.entity.po.Demo;
import com.itran.fgoc.server.test.entity.vo.demo.DemoVO;
import com.itran.fgoc.server.test.entity.vo.demo.DemoPageVO;
import com.itran.fgoc.server.test.mapper.DemoMapper;
import com.itran.fgoc.server.test.maps.DemoMaps;
import com.itran.fgoc.server.test.service.DemoService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author chun
 * @date 2020/9/4
 * @description
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

    @Resource
    private DemoMaps maps;

    @Override
    public void save(DemoSaveUpdateParam param) {
        Demo demo = maps.saveUpdateParamToPo(param);
        demo.setCreateTime(new Date());
        int i = baseMapper.insert(demo);
        if (i < 1) {
            throw new ApiException(SqlCode.INSERT_ERROR);
        }
    }

    @Override
    public void update(DemoSaveUpdateParam param, Long id) {
        Demo demo = maps.saveUpdateParamToPo(param);
        demo.setId(id);
        updateById(demo);
    }

    @Override
    public FgocPage<DemoPageVO> demoPage(PageParam page, DemoPageParam param) {
        return baseMapper.demoPage(FgocPage.getPage(page), param);
    }

    @Nullable
    @Override
    public DemoVO demo(Long id) {
        Demo demo = this.getById(id);
        return maps.poToVo(demo);
    }

    @Override
    public void deleteById(Long id) {
        removeById(id);
    }
}
