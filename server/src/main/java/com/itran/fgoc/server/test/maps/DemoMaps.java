package com.itran.fgoc.server.test.maps;

import com.itran.fgoc.server.test.entity.param.demo.DemoSaveUpdateParam;
import com.itran.fgoc.server.test.entity.po.Demo;
import com.itran.fgoc.server.test.entity.vo.demo.DemoPageVO;
import com.itran.fgoc.server.test.entity.vo.demo.DemoVO;

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
