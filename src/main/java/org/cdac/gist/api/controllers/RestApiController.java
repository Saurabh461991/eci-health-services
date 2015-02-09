package org.cdac.gist.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kamalp on 27-01-2015.
 */

@RestController("/api/**")
public class RestApiController {

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public Object api() {
        return "version 1";
    }
}