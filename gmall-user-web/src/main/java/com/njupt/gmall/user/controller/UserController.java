package com.njupt.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.njupt.gmall.bean.UmsMember;
import com.njupt.gmall.bean.UmsMemberReceiveAddress;
import com.njupt.gmall.service.UmsMemberReceiveAddressService;
import com.njupt.gmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhaokun
 * @create 2020-05-11 20:35
 */
@Controller
public class UserController {

    @Reference
    UserService userService;

    @Reference
    UmsMemberReceiveAddressService umsMemberReceiveAddressService;

    @RequestMapping("checkUsername")
    @ResponseBody
    public String checkUsername(HttpServletRequest request){
        String username = request.getParameter("username");
        String usernameResult = userService.checkUsername(username);
        return usernameResult;
    }

    @RequestMapping("checkPhone")
    @ResponseBody
    public String checkPhone(HttpServletRequest request){
        String phone = request.getParameter("phone");
        String phoneResult = userService.checkPhone(phone);
        return phoneResult;
    }

    @RequestMapping("index")
    public ModelAndView index(String username, String password, String phone){
        ModelAndView mv = null;
        if(username != null){
            UmsMember umsMember = new UmsMember();
            umsMember.setUsername(username);
            umsMember.setPassword(password);
            umsMember.setPhone(phone);
            userService.register(umsMember);
            String url = "redirect:http://order.ikwin.net:8085/gmall-passport-web/index?ReturnUrl=http://106.12.154.17:8083/gmall-search-web/index";
            mv = new ModelAndView(url);
        }else{
            mv = new ModelAndView("index");
        }
        return mv;
    }

    @RequestMapping("getUmsMemberAddress")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getUmsMemberReceiveAddressById(String memberId){
       return umsMemberReceiveAddressService.getUmsMemberReceiveAddressById(memberId);
    }

}
