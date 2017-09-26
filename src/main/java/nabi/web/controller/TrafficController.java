package nabi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nabi.web.dto.BusDTO;
import nabi.web.service.TrafficService;

@Controller
@RequestMapping("/traffic")
public class TrafficController {
	@Autowired
	TrafficService trafficService;
	
	@RequestMapping("/searchStation")
	@ResponseBody
	public List<BusDTO> searchStation(String stationId){
		trafficService.searchStation(stationId);
		return null;
	}
}
