package com.jerubaal.keyval;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeyValueController {

    private KeyValueService kvSvc;

    public KeyValueController(KeyValueService kvSvc) {
        this.kvSvc = kvSvc;
    }

    @GetMapping
    public String lookup(@RequestParam("key") String key) {
        var maybeValue = kvSvc.get(key);
        return maybeValue.orElse("not found");
    }
}
