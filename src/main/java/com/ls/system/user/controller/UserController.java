package com.ls.system.user.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ls.system.user.entity.User;
import com.ls.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: chenjun
 * @Date 2020年01月03日 13:42
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

@Autowired
    private UserService userService;

        @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String dologin(@RequestParam("usercode") String usercode, @RequestParam("userpassword") String userpassword, HttpServletRequest httpServletRequest){
        if (usercode != null && userpassword != null){
            User user = userService.getUerByUserNameandPwd(usercode, userpassword);
            if (user != null){
                httpServletRequest.getSession().setAttribute("userSession", user);
                return "frame";
            }else{
                return "error";
            }
        }
        return "error";
    }


    @RequestMapping("/userlist")
    public String userlist(Map<String, Object> map,@RequestParam("username") String queryname, @RequestParam(value = "pageNum", defaultValue = "1")
            int pageNum,Model model) {
       PageHelper.startPage(pageNum, 4);
        List<User> userList = userService.selectAllUser();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
           if (userList != null && userList.size() > 0){
               model.addAttribute("userList", userList);
            map.put("totalCount", pageInfo.getTotal());
            map.put("currentPageNo", pageInfo.getPageNum());
            map.put("totalPageCount", pageInfo.getPages());
            return "/user/userlist";
        }
        return "error";
    }

//
//    @RequestMapping(method = RequestMethod.GET, value = "/list")
//    public String list(Model model) {
//        List<User> userList = userService.selectAllUser(new HashMap<>());
//        model.addAttribute("cshipDateList", cShipDataList);
//        return "/user/userlist";
//    }
//
//
//    @RequestMapping(method = RequestMethod.POST, value = "/list")
//    @ResponseBody
//    public Page list(Model model, HttpServletRequest request, Page page) {
//        Map<String, Object> params = super.getWebParams(request);
//        return shipContractService.getListForPage(params, page);
//    }



//    @RequestMapping("/selectuserByName")
//    public String userlist1(@RequestParam("queryname") String queryname, @RequestParam(value = "pageNum", defaultValue = "1")
//            int pageNum,Model model) {
//        List<User> userList = userService.selectUserByUserName(queryname);
//        PageInfo<User> pageInfo = new PageInfo<User>(userList);
//        if (userList != null && userList.size() > 0){
//            model.addAttribute("userList", userList);
//            return "/user/userlist";
//        }
//        return "error";
//    }

//    @RequestMapping(value="/login", method = RequestMethod.GET)
//    public String login() {
//        return "login";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String loginValidate(HttpSession session, Model model, @ModelAttribute User user) {
//        List<User> list = new ArrayList<User>();
//        User record  = new User();
//        record.setUsername(user.getUsername());
//        list = userService.selectSelective(record);
//        if (list.size() == 0) {
//            model.addAttribute("status", 1);
//        } else {
//            record.setUserpassword(Encryption.MD5(user.getUserpassword()));
//            list = userService.selectSelective(record);
//            if (list.size() == 0) {
//                model.addAttribute("status", 2);
//            }
//            record = list.get(0);
//            session.setAttribute("User", record);
//            model.addAttribute("status", 0);
//        }
//
//        return "login";
//    }

//    @RequestMapping(value="/register",method = RequestMethod.GET)
//    public String register(){
//        return "register";
//    }
//
//    @RequestMapping(value="/user/register",method = RequestMethod.POST)
//    public String addUser(@ModelAttribute User user, Model model){
//
//        User record=new User();
//        record.setUsername(user.getUsername());
//        List<User> list=userService.selectSelective(record);
//        if(list.size()==0){
//            user.setCreationdate(new Date());
//            user.setUserpassword(Encryption.MD5(user.getUserpassword()));
//            if(userService.insert(user)==1){
//                model.addAttribute("status",0);
//            }else{
//                model.addAttribute("status",1);
//            }
//        }else{
//            model.addAttribute("status",2);
//        }
//
//        return "register";
//    }
//
//    @RequestMapping(value="/userInfo", method = RequestMethod.GET)
//    public String userInfo(Model model, HttpSession session) {
//        User user = (User) session.getAttribute("userinfo");
//        if(user != null){
//            model.addAttribute("user", user);
//        }
//        return "userInfo";
//    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        //session.removeAttribute("user");
        return "login";
    }


}
