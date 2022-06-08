package com.itran.fgoc.server.maps;

import com.itran.fgoc.server.entity.param.demo.DemoSaveUpdateParam;
import com.itran.fgoc.server.entity.po.Demo;
import com.itran.fgoc.server.entity.vo.demo.DemoPageVO;
import com.itran.fgoc.server.entity.vo.demo.DemoVO;

import org.mapstruct.Mapper;
import java.util.List;

/**
 * @author chun
 * @date 2020/8/12 16:16
 */
@Mapper(componentModel = "spring")
public interface DemoMaps {

    /**
     * saveUpdateParam转换po
     *
     * @param param the param
     * @return demo
     */
    Demo saveUpdateParamToPo(DemoSaveUpdateParam param);

    /**
     * Po to vo demo vo.
     *
     * @param demo the demo
     * @return the demo vo
     */
    DemoVO poToVo(Demo demo);
}
