package com.hufan.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

/**
 * 
 * @author hufan
 * 
 */
@Controller
@RequestMapping(value = "/weibo")
public class WeiBoController {

	private static Log log = LogFactory.getLog(WeiBoController.class);

	@RequestMapping(value = "/sina")
	public String indexPage() {
		log.info("enter the index page");
		return "/weibo/sina";
	}

	/**
	 * 让用户跳转到微博登录的界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sina/login", method = RequestMethod.GET)
	public String redirectToWeiBo() {
		System.out.println("enter the sina login....");
		Oauth oauth = new Oauth();
		String sina_login_url = null;
		try {
			sina_login_url = oauth.authorize("code");
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return "redirect:" + sina_login_url;
	}

	@RequestMapping(value = "/sina/result", method = RequestMethod.GET)
	public ModelAndView loginSinaWeiboAction(@RequestParam String code)
			throws WeiboException {
		ModelAndView modelAndView = new ModelAndView();
		String url = "/weibo/result";
		Oauth oauth = new Oauth();
		AccessToken accessToken = oauth.getAccessTokenByCode(code);
		String uid = accessToken.getUid();
		Users users = new Users(accessToken.getAccessToken());
		User user = users.showUserById(uid);
		System.out.println(user);
		modelAndView.setViewName(url);
		modelAndView.addObject("user", user);
		// return "redirect:" + url;
		return modelAndView;
	}
}