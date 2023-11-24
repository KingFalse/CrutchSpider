package com.cnwy;

import com.alibaba.fastjson2.JSON;
import com.cnwy.views.bind.BindField;
import com.cnwy.views.bind.BindView;
import com.cnwy.views.detail.DetailView;
import com.cnwy.views.link.LinkView;
import com.cnwy.views.link.SamplePerson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.util.JSONArrayUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @PostMapping("/api/add/links")
    public String addLinks(@RequestBody List<SamplePerson> ss) throws JsonProcessingException {
        System.err.println("收到链接列表:" + ss.size());
        LinkView.ss.clear();
        LinkView.ss.addAll(ss);
        return "OK";
    }

    @PostMapping("/api/add/cacheContext")
    public String cacheContext(@RequestParam String context, @RequestParam String traceID,@RequestParam String xPath) {
        DetailView.cacheContext.put(traceID, context);
        DetailView.cacheXpath.put(traceID, xPath);
        return "OK";
    }
    @GetMapping("/api/list/fields")
    public String listFields(@RequestParam String traceID) {
        List<BindField> fields = BindView.cacheContext.get(traceID);
        return JSON.toJSONString(fields);
    }

}
