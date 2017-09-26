package nabi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nabi.web.dto.SpendDTO;
import nabi.web.service.SpendService;
import nabi.web.util.Constants;

@Controller
@RequestMapping("/spend")
public class SpendController {
	@Autowired
	SpendService spendService;
	/**
	 * ���� ���� ����
	 * @param request
	 * @param dto
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public int insertSpending(HttpServletRequest request, SpendDTO dto){
		int monthNum = Integer.parseInt(request.getParameter("monthNum"));
		dto.setMonth(Constants.MONTH[monthNum]);
		System.out.println(dto);
		return spendService.insertSpending(dto);
	}
	/**
	 * ���� ���� ����
	 * @param request
	 * @param dto
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public int deleteSpending(HttpServletRequest request, SpendDTO dto){
		int monthNum = Integer.parseInt(request.getParameter("monthNum"));
		dto.setMonth(Constants.MONTH[monthNum]);
		System.out.println(dto);
		return spendService.deleteSpending(dto);
	}
	/**
	 * ���� ��ǥ ����/����
	 * @param request
	 * @param dto
	 * @return
	 */
	@RequestMapping("/insertGoal")
	@ResponseBody
	public int insertSpendingGoal(HttpServletRequest request, SpendDTO dto){
		int monthNum = Integer.parseInt(request.getParameter("monthNum"));
		dto.setMonth(Constants.MONTH[monthNum]);
		System.out.println(dto);
		return spendService.insertGoal(dto);
	}
	
	/**
	 * ������ �ҷ�����
	 * @param request
	 * @param dto
	 * @return
	 */
	@RequestMapping("/select")
	@ResponseBody
	public Map<String, Object> selectSpending(HttpServletRequest request, SpendDTO dto){
		int monthNum = Integer.parseInt(request.getParameter("monthNum"));
		dto.setMonth(Constants.MONTH[monthNum]);
		
		Map<String, Object> result = new HashMap<>();
		result.put("spendList", spendService.selectSpending(dto));
		
		result.put("spendGoal", spendService.selectGoal(dto));
		 
		
		return result;
	}
	
}
