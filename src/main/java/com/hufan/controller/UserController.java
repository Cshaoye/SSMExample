package com.hufan.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hufan.pojo.User;
import com.hufan.service.IUserService;
import com.hufan.ucenter.client.Client;
import com.hufan.utils.XMLHelper;

/**
 * 
 * @author hufan
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static Log log = LogFactory.getLog(UserController.class);
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/index")
	public String indexPage() {
		log.info("enter the index page");
		return "/user/index";
	}

	@RequestMapping(value = "/login")
	public String loginPage() {
		log.info("enter the login page");
		return "/user/login";
	}

	@RequestMapping(value = "/register")
	public String registerPage() {
		log.info("enter the register page");
		return "/user/register";
	}

	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean result = true;
		String jsvalue = "";
		//userService.getUserByUserName(user);
		if (result) {
			Client discuzClient4DIS = new Client();
			String loginResult = discuzClient4DIS.uc_user_login(username, password);// 根据账户密码获取用户相关信息
			log.info("loginresult-->" + loginResult);
			LinkedList<String> userInfo = XMLHelper.uc_unserialize(loginResult);
			int uid = Integer.valueOf(userInfo.get(0));// 获取用户的ID
			log.info("uid-->" + uid);
			String ucsynlogin = discuzClient4DIS.uc_user_synlogin(uid);// 根据ID进行同步登陆
			try {
				// 把$ucsynlogin输出到页面上就行了.这样就实现了同步登陆,实际上就是一个script标签可以进行跨域的功能
				// 直接访问论坛就有用户信息了
				response.setCharacterEncoding("UTF-8");
				// 同步Cookie信息
				response.addHeader(
						"P3P",
						" CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
				Cookie auth = new Cookie(" auth ", discuzClient4DIS.uc_authcode(
						password + "\t" + uid, "ENCODE"));
				// 设置本地 Discuz 登录的cookie信息，cookie存活时间
				auth.setMaxAge(31536000);
				auth.setDomain(" localhost ");// 设置本地cookie
				response.addCookie(auth);
				Cookie userCookie = new Cookie("loginuser", username);
				response.addCookie(userCookie);
				// 把返回过来的 js 文件直接输出在页面上，然后跳转到论坛网站首页就已经是登录之后的
				response.getWriter().print(ucsynlogin);
				jsvalue = ucsynlogin;
				model.setViewName("/user/success");
				model.addObject("jsvalue", jsvalue);
				return model;
			} catch (IOException e) {
				log.info("同步失败!", e);
			}
			model.setViewName("/user/success");
			model.addObject("jsvalue", jsvalue);
			return model;
		}
		model.setViewName("/user/fail");
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "doRegister")
	public ModelAndView doRegister(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email) {
		boolean flag = false;
		ModelAndView model = new ModelAndView();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		int result = 1;
		// userService.addUser(user);
		if (result > 0) {
			Client discuzClient4DIS = new Client();
			/**
			 * -1 : 用户名不合法 -2 : 包含不允许注册的词语 -3 : 用户名已经存在 -4 : email 格式有误 -5 :
			 * email 不允许注册 -6 : 该 email 已经被注册 >1 : 表示成功，数值为 UID
			 */
			String u_result = discuzClient4DIS.uc_user_register(username, password,
					email);
			log.info("the u_result:" + u_result);
			int uid = Integer.parseInt(u_result);

			if (uid > 0) {
				flag = true;
			}
		}
		if (flag) {
			model.setViewName("/user/register");
			model.addObject("isOk", "yes");
		} else {
			model.setViewName("/user/register");
			model.addObject("isOk", "no");
		}
		return model;
	}

	// uc.uc_user_synlogout();
	@ResponseBody
	@RequestMapping("logout")
	public String doLogout() {
		Client discuzClient4DIS = new Client();
		discuzClient4DIS.uc_user_synlogout();
		return null;
	}

	@RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
	public ModelAndView gainUserById(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getUserById(id);
		if (user != null) {
			modelAndView.addObject("user", user);
			modelAndView.setViewName("/user/message");
		} else {
			modelAndView.setViewName("/user/index");
		}
		return modelAndView;
	}
}
