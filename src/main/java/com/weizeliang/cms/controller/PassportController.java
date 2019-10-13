package com.weizeliang.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weizeliang.cms.domain.User;
import com.weizeliang.cms.service.UserService;
import com.weizeliang.cms.util.CMSContant;
import com.weizeliang.cms.util.CMSException;
import com.weizeliang.cms.vo.UserVO;

/**
 * 
 * @ClassName: PassportController
 * @Description: 登录注册入口
 * @author: weizeliang
 * @date: 2019年9月18日 上午10:08:10
 */
@RequestMapping("passport")
@Controller
public class PassportController {

	@Resource
	private UserService userService;

	/**
	 * 注册
	 * 
	 * @Title: reg
	 * @Description: TODO
	 * @return
	 * @return: String
	 * 
	 *          VO = VIEW OBJECT 视图对象
	 */

	@PostMapping("reg")
	public String reg(Model model, @Valid UserVO userVO, BindingResult result,RedirectAttributes redirectAttributes) {

		try {
			// 校验是否有不合法的注册信息r,如果有则回到注册页面,进行消息提示
			if (result.hasErrors()) {

				return "/passport/reg";
			}

			userService.insertSelective(userVO);

			//注册成功,把用户名携带到登录页面
			redirectAttributes.addFlashAttribute("username", userVO.getUsername());
			return "redirect:/passport/login";// 注册成功跳转到登录页面

		} catch (CMSException e) {
			e.printStackTrace();
			// 捕获自定义异常
			model.addAttribute("error", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error","系统异常.请于管理员联系");

		}

		return "passport/reg";//注册失败,回到注册页面
	}
	

	/**
	 * 去注册
	 * 
	 * @Title: reg
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg() {

		return "passport/reg";
	}
	/**
	 * 去登录
	 * @Title: login 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login() {
		
		return "passport/login";
	}
	
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录
	 * @return
	 * @return: String
	 */
	@PostMapping("login")
	public String login(User user,HttpServletRequest request) {
		
		try {
			
			User user2 = userService.login(user);
			
			//登录成功,存session
			HttpSession session = request.getSession();
		
			//根据角色进入对应的页面
			if(CMSContant.USER_ROLE.equals(user2.getRole())) {//普通用户
				session.setAttribute("user", user2);
				return "redirect:/my";
				
			}
			
			session.setAttribute("admin", user2);//管理员的session
			return "redirect:/admin";//管理员
			
			
		} catch (CMSException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error","系统异常");
		}
		return "passport/login";//登录失败
		
	}
	
	/**
	 * 
	 * @Title: logout 
	 * @Description: 注销
	 * @return
	 * @return: String
	 */
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 session.invalidate();
		 
		 return "redirect:/passport/login";
	}
}
