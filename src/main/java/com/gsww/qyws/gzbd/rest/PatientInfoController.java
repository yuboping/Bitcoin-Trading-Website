package com.gsww.qyws.gzbd.rest;

import com.gsww.qyws.gzbd.common.BaseController;
import com.gsww.qyws.gzbd.entity.ResultEntity;
import com.gsww.qyws.gzbd.service.PatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wangjian
 * @Date 2020/1/27 上午 01:46
 */
@Controller
@RequestMapping("patientInfo")
public class PatientInfoController extends BaseController {

	@Autowired
	private PatientInfoService patientInfoService;

	/**
	 * 患者详细信息
	 * @param areaCode
	 * @param patientState 患者状态
	 * @param isDtLj 当天累计
	 * @param isdoctor 是否医务人员
	 * @param zzState 危重、死亡、治愈
	 * @param isJc 是否解除
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView queryPatientInfo(String areaCode, String patientState,String isDtLj,String isdoctor,String zzState,String isJc){
		List<Map<String, Object>> result = new ArrayList<>();
		try{
			//疑似病历
			if ("1".equals(patientState)){
				result = patientInfoService.querySuspectPatient(areaCode);
				//确诊病历
			}else if ("2".equals(patientState)){
				result = patientInfoService.queryConfirmPatient(areaCode,isDtLj,isdoctor);
				//死亡病历
			}else if ("3".equals(patientState)){
				result = patientInfoService.queryDeathPatient(areaCode);
				//治愈病历
			}else if ("4".equals(patientState)){
				result = patientInfoService.queryCurePatient(areaCode);
				//确诊病历重症、危重症、死亡、
			}else if("5".equals(patientState)){
				result = patientInfoService.queryConfirmZzAndWzzPatient(areaCode,isDtLj,zzState,isdoctor);
			}else if("6".equals(patientState)){
				result = patientInfoService.querySuspectOtherPatient(areaCode,isDtLj,isdoctor,isJc);
			}
		}catch (Exception e){

		}
		//
		//ModelAndView modelAndView = new ModelAndView("/patient/patient_info_list");

		ModelAndView modelAndView = new ModelAndView("/patient/patient_list");
		modelAndView.addObject("result",result);
		return modelAndView;
	}


	/**
	 * 患者详细信息
	 * @param areaCode
	 * @param isqzbl 患者状态
	 * @param isDtLj 当天累计
	 * @param isdoctor 是否医务人员
	 * @param zzState  病人状态 危重、死亡、治愈
	 * @param isJc 是否解除
	 * @return
	 */
	@RequestMapping("/listNew")
	public ModelAndView queryPatientInfoNew(String areaCode, String isqzbl,String isDtLj,String isdoctor,String zzState,String isJc){
		List<Map<String, Object>> result = new ArrayList<>();
		try{

		}catch (Exception e){

		}
		//
		ModelAndView modelAndView = new ModelAndView("/patient/patient_list");
		modelAndView.addObject("result",result);
		return modelAndView;
	}

	/**
	 * 患者护理信息
	 */
	@RequestMapping("/queryPatientInfoTw")
	@ResponseBody
	public ResultEntity queryPatientInfoTw(String orgCode, String sfzh){
		List<Map<String, Object>> result = new ArrayList<>();
		//封装返回数据
		ResultEntity resultEntity = ResultEntity.ok("获取成功");
		try{
			List<Map<String, Object>> queryPatientInfoTw = patientInfoService.queryPatientInfoTw(orgCode,sfzh);
			resultEntity.put("queryPatientInfoTw", queryPatientInfoTw);
		}catch (Exception e){
			resultEntity = ResultEntity.error("获取失败");
		}
		return resultEntity;
	}

	/**
	 * 患者血压信息
	 */
	@RequestMapping("/queryPatientInfoXy")
	@ResponseBody
	public ResultEntity queryPatientInfoXy(String orgCode, String sfzh){
		List<Map<String, Object>> result = new ArrayList<>();
		//封装返回数据
		ResultEntity resultEntity = ResultEntity.ok("获取成功");
		try{
			List<Map<String, Object>> queryPatientInfoXy = patientInfoService.queryPatientInfoXy(orgCode,sfzh);
			resultEntity.put("queryPatientInfoXy", queryPatientInfoXy);
		}catch (Exception e){
			resultEntity = ResultEntity.error("获取失败");
		}
		return resultEntity;
	}

	/**
	 * 患者详情信息
	 */
	@RequestMapping("/queryPatientInfoXq")
	@ResponseBody
	public ResultEntity queryPatientInfoXqs(String orgCode, String sfzh){
		List<Map<String, Object>> result = new ArrayList<>();
		//封装返回数据
		ResultEntity resultEntity = ResultEntity.ok("获取成功");
		try{
			List<Map<String, Object>> queryPatientInfoXq = patientInfoService.queryPatientInfoXq(orgCode,sfzh);
			resultEntity.put("queryPatientInfoXq", queryPatientInfoXq);
		}catch (Exception e){
			resultEntity = ResultEntity.error("获取失败");
		}
		return resultEntity;
	}

	/**
	 * 患者详情信息
	 */
	@RequestMapping("/queryPatientInfo")
	public ModelAndView queryPatientInfoXq(String orgCode, String sfzh){

		ModelAndView modelAndView = new ModelAndView("/patient/patient_view");
		modelAndView.addObject("sfzh",sfzh);
		modelAndView.addObject("orgCode",orgCode);
		return modelAndView;
	}

	/**
	 * 患者详情信息
	 */
	@RequestMapping(value ="/patientInfoList", method = RequestMethod.POST)
	@ResponseBody
	public ResultEntity patientInfoList(HttpServletRequest request, HttpServletResponse response){
		Map params = getParameterMap(request);
		List<Map<String, Object>> result = new ArrayList<>();
		//封装返回数据
		ResultEntity resultEntity = ResultEntity.ok("获取成功");
		try{
			Map<String, Object> patientInfoList = patientInfoService.getPatientInfoList(params);
			resultEntity.put("patientInfoList", patientInfoList);
		}catch (Exception e){
			resultEntity = ResultEntity.error("获取失败");
		}
		return resultEntity;
	}

}
