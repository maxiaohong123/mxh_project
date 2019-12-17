package com.mxh.pdc.service.rest.clients;

import com.mxh.pdc.annotation.RestClient2018;
import com.mxh.pdc.xiaomage.annotation.RestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Maxiaohong on 2019-12-01.
 */
//@RestClient(name="${pjc.rest.name}")
@RestClient2018(name="${pjc.rest.name}")
public interface UserRestService {
//    @GetMapping("/user/say")//pdc-->pjc
    @RequestMapping("/user/say")
    public String say(@RequestParam("name") String name);
}
