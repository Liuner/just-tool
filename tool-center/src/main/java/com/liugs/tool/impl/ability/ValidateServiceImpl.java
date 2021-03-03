package com.liugs.tool.impl.ability;

import com.liugs.tool.ability.ValidateService;
import com.liugs.tool.ability.bo.ValidateServiceReqBo;
import com.liugs.tool.ability.bo.ValidateServiceRspBo;
import com.liugs.tool.base.constants.ToolRespConstants;
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
    public ValidateServiceRspBo validate(ValidateServiceReqBo reqBo) {
        log.debug("验证服务开始，入参：{}", reqBo);
        ValidateServiceRspBo retBo = new ValidateServiceRspBo();

        TestPo po = testMapper.selectByName(reqBo.getName());
        BeanUtils.copyProperties(po, retBo);
        retBo.setRespCode(ToolRespConstants.SUCCESS_CODE);
        retBo.setRespDesc(ToolRespConstants.SUCCESS_DESC);
        log.debug("查询完成，出参：{}", retBo);
        return retBo;
    }
}
