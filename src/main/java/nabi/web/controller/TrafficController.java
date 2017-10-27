package nabi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nabi.web.dto.BusBookDTO;
import nabi.web.dto.BusDTO;
import nabi.web.dto.StationDTO;
import nabi.web.service.TrafficService;

@Controller
@RequestMapping("/traffic")
public class TrafficController {
	@Autowired
	TrafficService trafficService;
	
	@RequestMapping("/test")
	@ResponseBody
	public void test(){
		trafficService.fileSetup();
	}
	
	@RequestMapping("/busInfo")
	@ResponseBody
	public List<StationDTO> busInfo(String routeId){
		List<StationDTO> stationList = trafficService.searchBus(routeId);
		System.out.println(stationList);
		return stationList;
	}
	@RequestMapping("/searchStation")
	@ResponseBody
	public List<BusDTO> searchStation(String stationId, String email){
		List<BusDTO> busList = trafficService.searchStation(stationId, email);
		System.out.println(busList);
		return busList;
	}
	
	@Scheduled(cron="0 0 5 * * *")
	public void updateInfo(){
		trafficService.fileSetup();
	}
	
	@RequestMapping("/selectBookBus")
	@ResponseBody
	public List<BusDTO> selectBusBook(String email){
		List<BusDTO> busList = trafficService.selectBookBus(email);
		System.out.println(busList);
		return busList;
	}
	@RequestMapping("/insertBusBook")
	@ResponseBody
	public int insertBusBook(BusBookDTO dto){
		System.out.println(dto);
		return 
				trafficService.insertBookBus(dto);
	}
	
	@RequestMapping("/deleteBusBook")
	@ResponseBody
	public int deleteBusBook(BusBookDTO dto){
		System.out.println(dto);
		return trafficService.deleteBookBus(dto);
	}
	
}
