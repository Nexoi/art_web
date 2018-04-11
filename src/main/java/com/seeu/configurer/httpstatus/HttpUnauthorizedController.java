package com.seeu.configurer.httpstatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpUnauthorizedController {

    @RequestMapping("/unauthorized")
    public ResponseEntity unauthorizedPage() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(("无权访问 [unauthorized]"));
    }
}
