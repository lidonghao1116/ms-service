package com.jiacer.modules.clientInterface.controller;

import com.jiacer.modules.common.utils.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping
public class BaseController {

    @RequestMapping
    public String welcome(){
        return "hello world!";
    }

    @RequestMapping(value = "/api")
    public String api() throws IOException, URISyntaxException {

        String api  = FileUtil.readSourceAsString("api.json");
        return api;
    }
}
