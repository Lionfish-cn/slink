package github.lionfish.controller;

import github.lionfish.service.ISLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AController {

    @Autowired
    ISLinkService isLinkService;

    @RequestMapping("/a/{hash}")
    public String a(@PathVariable String hash) {
        return "redirect:" + isLinkService.lookupFullLink(hash);
    }
}
