package com.example.java.callByValue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SingleService {

    public void print(){
        log.info("songPrint");
    }

}
