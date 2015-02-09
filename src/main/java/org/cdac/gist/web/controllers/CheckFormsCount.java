package org.cdac.gist.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sgalande on 2/6/15.
 */
@Controller
public class CheckFormsCount {

    @Autowired

    @RequestMapping(value = "/formscount", method = RequestMethod.GET)
    public String checkFormsCount() {

        return "formscount";
    }
}
