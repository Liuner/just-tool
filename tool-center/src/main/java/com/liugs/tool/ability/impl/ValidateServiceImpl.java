package com.liugs.tool.ability.impl;

import com.liugs.tool.ability.ValidateService;
import com.liugs.tool.ability.bo.ValidateServiceReqBo;
import com.liugs.tool.ability.bo.ValidateServiceRspBo;
import com.liugs.tool.dao.TestMapper;
import com.liugs.tool.dao.po.TestPo;
import com.liugs.tool.util.ToolRspUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ValidateServiceImpl
 * @Description 验证服务 实现类
 * @Author liugs
 * @Date 2021/3/3 14:40:41
 */
@Slf4j
@Service("validateService")
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public ValidateServiceRspBo validate(ValidateServiceReqBo reqBo) {
        log.debug("验证服务开始，入参：{}", reqBo);
        ValidateServiceRspBo retBo = ToolRspUtil.getSuccessRspBo(ValidateServiceRspBo.class);

        TestPo po = testMapper.selectByName(reqBo.getName());
        if (null == po) {
            log.debug("未查询到匹配的记录");
            retBo.setRespDesc("未查询到匹配的记录");
        }
        BeanUtils.copyProperties(po, retBo);
        log.debug("查询完成，出参：{}", retBo);
        return retBo;
    }
}
