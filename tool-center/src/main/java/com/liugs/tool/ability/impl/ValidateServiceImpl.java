package com.liugs.tool.ability.impl;

import com.liugs.tool.ability.ValidateService;
import com.liugs.tool.ability.bo.ValidateServiceReqBo;
import com.liugs.tool.ability.bo.ValidateServiceRspBaseBo;
import com.liugs.tool.constants.RespConstants;
import com.liugs.tool.dao.TestMapper;
import com.liugs.tool.dao.po.TestPo;
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
    public ValidateServiceRspBaseBo validate(ValidateServiceReqBo reqBo) {
        log.debug("验证服务开始，入参：{}", reqBo);
        ValidateServiceRspBaseBo retBo = new ValidateServiceRspBaseBo();
        retBo.setRespCode(RespConstants.RESP_CODE_SUCCESS);
        retBo.setRespDesc(RespConstants.RESP_CODE_SUCCESS);

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
