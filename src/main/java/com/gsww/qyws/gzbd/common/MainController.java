package com.gsww.qyws.gzbd.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/yqjk/")
public class MainController extends BaseController {
    @RequestMapping(value = "/index")
    public ModelAndView swapMonitor(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/yqjk/index");
        modelAndView.addObject("xzqh","62");
        return   modelAndView;
    }
}
