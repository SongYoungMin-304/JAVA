package com.example.java.callByValue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SingletonController {

    @Autowired
    SingleService singleService;

    @Autowired
    ProtoService protoService;

    @RequestMapping("/test")
    public String test() throws Exception{

        for(int a = 0; a < 100; a++){
            Thread.sleep(1000);
            log.info("a체크 " + a);
        }

        log.info("주소 체크 싱글" + singleService.toString());
        log.info("주소 체크 프로토" + protoService.toString());
        singleService.print();
        return "songyoungmin";
    }

}
