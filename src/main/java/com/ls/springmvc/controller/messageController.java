package com.ls.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: chenjun
 * @Date 2020年01月03日 11:11
 * @Description:
 */
@Controller
//@Controller标识一个Spring类是Spring MVC controller处理器, 也就是加了@Controller，这个文件就会被spring认为是处理请求的
public class messageController {

//    接着开始写函数，@RequestMapping() 里面写链接，@RequestMapping() 注解可以在控制器类的级别和/或其中的方法的级别上使用。
    @RequestMapping("message/go")
    public String goTest(){
        return "reach";
    }


//    @PathVariable可以将 URL 中占位符参数绑定到控制器处理方法的入参中:URL 中的 {xxx} 占位符可以通过，@PathVariable("xxx") 绑定到操作方法的入参中。
    @RequestMapping("/message/detail/data={uname}")
    public String getUserDetail(@PathVariable("uname") String data, Model model){
        model.addAttribute("data",data);
        return "detail";
    }


    @RequestMapping(value = "/message/report1",method = RequestMethod.GET)
    public String reportGet(@RequestParam("begin") String begin,
                            @RequestParam("end") String end, Model model){
        model.addAttribute("begin",begin);
        model.addAttribute("end",end);
        model.addAttribute("formType","GET");
        return "report";
    }
}
