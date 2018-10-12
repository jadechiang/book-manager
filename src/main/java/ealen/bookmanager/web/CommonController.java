package ealen.bookmanager.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-05.
 */
@Controller
public class CommonController {

    @RequestMapping(value = "/frame.html", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "test")
    public String test() {
        return "register";
    }
}
