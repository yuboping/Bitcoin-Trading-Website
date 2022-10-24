package com.gsww.qyws.gzbd.common;

import com.gsww.auth.UscSdk;
import com.gsww.auth.entity.AuthResult;
import com.gsww.auth.entity.SysUserSession;
import com.gsww.auth.util.ReadProperties;
import com.gsww.qyws.gzbd.entity.HisFpjcXqyyzh;
import com.gsww.qyws.gzbd.service.LoginService;
import com.gsww.qyws.gzbd.utils.Base64Util;
import com.gsww.qyws.gzbd.utils.StringHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/login/")
public class LoginController extends BaseController{
	@Autowired
	private LoginService loginService;
		@RequestMapping("/jumpLogin")
		public String loginMain(Model model, HttpServletRequest request) {
			request.getSession().removeAttribute("userName");
			return "/login";
		}

		@PostMapping("/loginToSystem")
		public ModelAndView loginToSystem(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
			String username = (String) request.getParameter("userName");
			String passWord = (String) request.getParameter("passWord");
			Map<String, Object> resMap = new HashMap<String, Object>();
			ModelAndView modelAndView = new ModelAndView("/yqjk/index");
			String tokenString = "CSGLYY";//MYGHXXXT//FYJKXXXT//YPZBCGXT//QYRKXT//JKDASJK//质控平台 SJZKXT;  交换平台  SJJHYZLGLPT;  empi EMPIGLXT;
			//if (sessionKaptcha.equals(inputKaptcha)) {
				// 验证码正确，检查用户名和密码
				try {
					String userName = Base64Util.decodeData(username);
					AuthResult result= UscSdk.auth(userName,passWord,tokenString);
					if(result==null){
						resMap.put("ret", "4");
						resMap.put("msg", "认证接口错误！");
					}else{
						if(result.getResultCode().equals("0")){//成功
							SysUserSession sysUserSession=result.getSysUserSession();
							request.getSession().setAttribute("sysUserSession", sysUserSession);
							request.getSession().setAttribute("token", result.getToken());
							request.getSession().setAttribute("userName", sysUserSession.getUserName());
							request.getSession().setAttribute("sysMenuJson", result.getSysMenuJson());
							request.getSession().setAttribute("xzqh",sysUserSession.getXzqhCode());
							request.getSession().setAttribute("sysOperMap", result.getSysOper());
							modelAndView.addObject("xzqh",sysUserSession.getXzqhCode());
							resMap.put("ret", "0");
							resMap.put("msg", "登录成功！");
						}else{
							resMap.put("ret", "3");
							resMap.put("msg", result.getResultMsg());
						}
					}
				} catch (Exception e) {
					resMap.put("ret", "4");
					resMap.put("msg", "系统错误！");
				}
			if(resMap.get("ret").equals("0")){
				return modelAndView;
			}else{
				String script = "alert('"+resMap.get("msg")+"');window.location.href='jumpLogin';";
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("<script language=\"javascript\">");
				response.getWriter().write(script + "\n");
				response.getWriter().write("</script>");
				return null;
			}
			
		}
	 
		/**
		 * 从综合管理过来
		 * @param request
		 * @param response
		 * @param attr
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/sso", method = RequestMethod.POST)
		public ModelAndView sso(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) throws Exception {
			String token=request.getParameter("token");
			String appCode=request.getParameter("appCode");
			try {
				AuthResult result= UscSdk.resolveResult(token);
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html;charset=UTF-8");
				if(result==null){
					response.getWriter().write("<script type='text/javascript'>alert('单点认证接口错误！');window.close();</script>");
				}else{
					if(result.getResultCode().equals("0")){//成功
						SysUserSession sysUserSession = result.getSysUserSession();
						//SysUserSession sysUserSessionss = (SysUserSession) request.getSession().getAttribute("sysUserSession");
						///SysUserSession sysUserSessionInfo = (SysUserSession) request.getAttribute("sysUserSession");
						if(sysUserSession != null) {
							request.getSession().setAttribute("sysUserSession", sysUserSession);
							request.getSession().setAttribute("token", result.getToken());
							request.getSession().setAttribute("userName", sysUserSession.getUserName());
							request.getSession().setAttribute("sysMenuJson", result.getSysMenuJson());
							request.getSession().setAttribute("xzqh", sysUserSession.getXzqhCode());
							request.getSession().setAttribute("sysOperMap", result.getSysOper());

							attr.addFlashAttribute("idCard", request.getParameter("idCard"));
							ModelAndView modelAndView = new ModelAndView("/analysis/main_index");
                            modelAndView.addObject("xzqh",sysUserSession.getXzqhCode());
							return modelAndView;

						}
					}else{
						response.getWriter().write("<script type='text/javascript'>alert('"+result.getResultMsg()+"');window.close();</script>");
					}
				}
			} catch (Exception e) {
				response.getWriter().write("<script type='text/javascript'>alert('系统错误！');window.close();</script>");
			}
			return null;
		}


	/**
	 * 疾病预防单点
	 * @param request
	 * @param response
	 * @return mav
	 */
	@RequestMapping(value = "/yqjkSso", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String enName = (String) request.getParameter("enName");
		String enPwd = (String) request.getParameter("enPwd");
		String loginUrl = (String) request.getParameter("loginUrl");
		String areaCode = (String) request.getParameter("areaCode");
		String remoteAddr = request.getRemoteAddr();
		String userName="";
		String authKey="";
		String yzm = StringHelper.toString(request.getParameter("enYzm"));
		Map<String, Object> resMap = new HashMap<String, Object>();
		SysUserSession sysUserSession = null;
		String isFl = "";
		try{
			//用户名、密码 解密
			userName = Base64Util.decodeData(enName);
			authKey = Base64Util.decodeData(enPwd);
			//判断账号是否正确
			isFl = loginService.getLoginUserByAccountAndPsw(userName, authKey);
		} catch (Exception e) {
			resMap.put("ret", "4");
			resMap.put("msg", "异常");
		} finally {
			if("1".equals(isFl)){
				ModelAndView modelAndView = new ModelAndView("/yqjk/index");
				request.getSession().setAttribute("userName", areaCode);
				modelAndView.addObject("areaCode",areaCode);
				request.getSession().setAttribute("userName", areaCode);
				return modelAndView;
			}else {
				ModelAndView modelAndView = new ModelAndView("");
				return modelAndView;
			}
		}
	}

}
