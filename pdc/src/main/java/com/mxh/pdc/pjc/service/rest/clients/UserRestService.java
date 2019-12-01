package com.mxh.pdc.pjc.service.rest.clients;

import com.mxh.pdc.pjc.annotation.RestClient2018;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Maxiaohong on 2019-12-01.
 */
@RestClient2018(name="pjcmxh-local")
public interface UserRestService {
    @RequestMapping("/user/say")//pdc-->pjc
    public String say(@RequestParam String name);
}
