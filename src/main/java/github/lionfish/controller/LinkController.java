package github.lionfish.controller;

import github.lionfish.service.ISLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("link")
public class LinkController {

    @Autowired
    ISLinkService isLinkService;

    @RequestMapping("convert")
    public String convert(HttpServletRequest request) {
        String link = request.getParameter("link");
        String timestamp = request.getParameter("timestamp");
        String expired = request.getParameter("expired");
        return isLinkService.encode(link,timestamp,expired);
    }

}
