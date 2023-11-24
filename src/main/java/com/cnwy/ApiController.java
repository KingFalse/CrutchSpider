package com.cnwy;

import com.alibaba.fastjson2.JSON;
import com.cnwy.views.bind.BindField;
import com.cnwy.views.bind.BindView;
import com.cnwy.views.detail.DetailView;
import com.cnwy.views.list.LinkView;
import com.cnwy.views.result.CrawlerResult;
import com.cnwy.views.result.ResultView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    @PostMapping("/api/add/cacheLinks")
    public String addLinks(@RequestParam String traceID, @RequestBody List<Map<String, String>> cacheLinks) throws JsonProcessingException {
        System.err.println("收到链接列表:" + cacheLinks.size());
        LinkView.cacheLinks.put(traceID, cacheLinks);
        return "OK";
    }

    @PostMapping("/api/add/cacheContext")
    public String cacheContext(@RequestParam String context, @RequestParam String traceID, @RequestParam String xPath) {
        DetailView.cacheContext.put(traceID, context);
        DetailView.cacheXpath.put(traceID, xPath);
        return "OK";
    }

    @PostMapping("/api/add/cacheResult")
    public String cacheResult(@RequestBody CrawlerResult crawlerResult) {
//        System.err.println("收到对象:" + JSON.toJSONString(crawlerResult));
        ResultView.cacheResult.put(crawlerResult.traceID, crawlerResult);
        System.err.println("---" + ResultView.cacheResult.size());
        System.err.println("---" + ResultView.cacheResult.get(crawlerResult.traceID));

        return "OK";
    }

    @GetMapping("/api/list/fields")
    public String listFields(@RequestParam String traceID) {
        List<BindField> fields = BindView.cacheContext.get(traceID);
        return JSON.toJSONString(fields);
    }

}
