package com.liugs.tool.async;

import com.liugs.tool.ability.bo.JustSyncDataServiceReqBo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName JustAsyncDataService
 * @Description 数据同步 异步
 * @Author liugs
 * @Date 2021/6/15 15:28:45
 */
@Component
public class JustAsyncDataService {

    @Async("ToolAsyncExecutor")
    public void async(JustSyncDataServiceReqBo reqBo) {

    }


}
