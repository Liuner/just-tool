package com.liugs.tool.service.controller;

import com.liugs.tool.ability.ExportDataService;
import com.liugs.tool.ability.ValidateService;
import com.liugs.tool.ability.bo.ExportDataServiceReqBo;
import com.liugs.tool.ability.bo.ValidateServiceReqBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ToolTestController
 * @Description test controller
 * @Author liugs
 * @Date 2021/2/27 18:12:34
 */
@RestController
@RequestMapping("tool/test")
public class ToolTestController {

    @Autowired
    private ExportDataService exportDataService;

    @RequestMapping(value = "/exportData", method = RequestMethod.POST)
    @ResponseBody
    public Object exportData(ExportDataServiceReqBo reqBo) {
        return exportDataService.exportData(reqBo);
    }

    @Autowired
    private ValidateService validateService;

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    @ResponseBody
    public Object validate(@RequestBody ValidateServiceReqBo reqBo) {
        return validateService.validate(reqBo);
    }
}
