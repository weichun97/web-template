package com.itran.fgoc.server.test.mapper;

import com.itran.fgoc.common.mybatisplus.FgocPage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.itran.fgoc.server.test.entity.param.demo.DemoPageParam;
import com.itran.fgoc.server.test.entity.po.Demo;
import com.itran.fgoc.server.test.entity.vo.demo.DemoPageVO;

/**
 * @author chun
 * @date 2020/8/12 15:51
 */
@Mapper
public interface DemoMapper extends BaseMapper<Demo> {

    FgocPage<DemoPageVO> demoPage(FgocPage fgocPage, DemoPageParam param);
}
