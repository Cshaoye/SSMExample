package com.hufan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author hufan
 * 
 */
@Controller
@RequestMapping(value = "/discuz")
public class DiscuzController {
	@RequestMapping(value = "/index")
	public String indexPage() {
		return "/discuz/index";
	}
}
