package com.yoon.unleash;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unleash")
@RequiredArgsConstructor
public class Controller {

    private final SyncUserService syncUserService;

    @PutMapping("/sync/{userId}")
    public void sync(@PathVariable String userId){
        syncUserService.sync(userId);
    }

}
