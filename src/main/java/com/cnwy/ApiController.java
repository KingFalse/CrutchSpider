package com.cnwy;

import com.cnwy.views.link.LinkView;
import com.cnwy.views.link.SamplePerson;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
