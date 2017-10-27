package nabi.web.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import nabi.web.dao.TrafficDAO;
import nabi.web.dto.BusBookDTO;
import nabi.web.dto.BusDTO;
import nabi.web.dto.StationDTO;
import nabi.web.util.Constants;

@Service
@Transactional
public class TrafficServiceImpl implements TrafficService {
	@Autowired
	TrafficDAO trafficDAO;
	/**
	 * 버퍼 사이즈
	 */
	final static int BUFF_SIZE = 1024;

	@Override
	public List<BusDTO> searchStation(String stationId, String email) {
		StringBuilder sb = new StringBuilder("route");
		Calendar cal = Calendar.getInstance();
		sb.append(cal.get(Calendar.YEAR));
		sb.append(String.format("%02d", cal.get(Calendar.MONTH) + 1));
		sb.append(String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)));
		fileMake(sb + ".txt");
		List<BusDTO> busList = arrivalBusList(stationId, sb + ".txt");
		List<BusBookDTO> busBookDTOList= trafficDAO.selectBookBus(email);
		for(int i=0; i<busBookDTOList.size(); i++){
			BusBookDTO busBookDTO = busBookDTOList.get(i);
			for(int j=0; j<busList.size(); j++){
				BusDTO busDTO = busList.get(j);
				if(busBookDTO.getRouteId().equals(busDTO.getBusName())){
					busDTO.setBook(true);
				}
			}
		}
		return busList;
	}

	public void setBusCurrentStation(List<StationDTO> stationList, String routeId){
		URL url;
		Document doc;
		try {
			url = new URL(
					Constants.BUS_SEARCH_URL + "serviceKey=" + Constants.BUS_AUTH_KEY + "&routeId=" + routeId);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); // XML문서 빌더 객체를 생성
			doc = db.parse(new InputSource(url.openStream())); // XML문서를 파싱한다.
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("busLocationList");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String stationId = getTagValue("stationId", eElement);
					for(int i=0; i<stationList.size(); i++){
						StationDTO station = stationList.get(i);
						if(station.getStationId().equals(stationId)){
							station.setBus(true);
							station.setPlateNo(getTagValue("plateNo", eElement));
						}
					}
			
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<BusDTO> arrivalBusList(String stationId, String fileName) {
		List<BusDTO> busList = new ArrayList<>();
		URL url;
		Document doc;
		try {
			url = new URL(
					Constants.STATION_SEARCH_URL + "serviceKey=" + Constants.BUS_AUTH_KEY + "&stationId=" + stationId);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); // XML문서 빌더 객체를 생성
			doc = db.parse(new InputSource(url.openStream())); // XML문서를 파싱한다.
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("busArrivalList");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					BusDTO bus = new BusDTO();
					bus.setLocationNoOne(getTagValue("locationNo1", eElement));
					bus.setLocationNoTwo(getTagValue("locationNo2", eElement));
					bus.setPlateNoOne(getTagValue("plateNo1", eElement));
					bus.setPlateNoTwo(getTagValue("plateNo2", eElement));
					bus.setPredictTimeOne(getTagValue("predictTime1", eElement));
					bus.setPredictTimeTwo(getTagValue("predictTime2", eElement));
					bus.setStaOrder(getTagValue("staOrder", eElement));
					String routeId = getTagValue("routeId", eElement);
					bus.setRouteId(routeId);
					bus.setStationId(getTagValue("stationId", eElement));
					busList.add(bus);
				}
			}
			fileRead(fileName, busList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return busList;
	}

	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return "";
		else
			return nValue.getNodeValue();
	}

	public List<StationDTO> stationFileRead(String fileName, String routeId) {
		List<StationDTO> stationList = new ArrayList<>();
		try { // 예외 처리는 기본으로 해 줘야 한다
				// 파일에서 스트림을 통해 주르륵 읽어들인다
			BufferedReader in = new BufferedReader(new FileReader(Constants.ROUTE_FOLDER + "\\" + fileName));

			String s;
			while ((s = in.readLine()) != null) {
				String[] split = s.split("\\^");
				for (int i = 0; i < split.length; i++) {
					String[] busInfo = split[i].split("\\|");
					if (busInfo[0].equals(routeId)) {
						stationList.add(new StationDTO(routeId, busInfo[1], busInfo[5], "", Integer.parseInt(busInfo[3]), false));
					}
				}
			}

			in.close();
		} catch (IOException e) {
			System.err.println(e);
		}

		return stationList;
	}

	public void fileRead(String fileName, List<BusDTO> busList) {

		try { // 예외 처리는 기본으로 해 줘야 한다
				// 파일에서 스트림을 통해 주르륵 읽어들인다
			BufferedReader in = new BufferedReader(new FileReader(Constants.ROUTE_FOLDER + "\\" + fileName));

			String s;
			while ((s = in.readLine()) != null) {
				String[] split = s.split("\\^");
				for (int i = 0; i < busList.size(); i++) {
					BusDTO busDTO = busList.get(i);
					for (int j = 0; j < split.length; j++) {
						String[] busInfo = split[j].split("\\|");
						if (busDTO.getRouteId().equals(busInfo[0])) {
							busDTO.setBusName(busInfo[1]);
							busDTO.setFirstStation(busInfo[4]);
							busDTO.setLastStation(busInfo[7]);
							busDTO.setUpFirstTime(busInfo[9]);
							busDTO.setUpLastTime(busInfo[10]);
							busDTO.setDownFirstTime(busInfo[11]);
							busDTO.setDownLastTime(busInfo[12]);
							busDTO.setPeekAlloc(busInfo[13]);
							busDTO.setnPeekAlloc(busInfo[14]);
							switch (busInfo[2]) {
							case "13":
							case "23":
								busDTO.setBusType("G");
								break;
							case "11":
							case "12":
							case "14":
								busDTO.setBusType("R");
								break;
							default:
								busDTO.setBusType("B");
								break;
							}
							break;
						}
					}
				}
			}
			in.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void fileMake(String fileName) {

		File folder = new File(Constants.ROUTE_FOLDER);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File(Constants.ROUTE_FOLDER + "\\" + fileName);
		if (!file.isFile() || file.length() == 0) {
			fileUrlDownload(Constants.ROUTE_URL + fileName, Constants.ROUTE_FOLDER);
		}
	}

	/**
	 * fileAddress에서 파일을 읽어, 다운로드 디렉토리에 다운로드
	 * 
	 * @param fileAddress
	 * @param localFileName
	 * @param downloadDir
	 */
	public void fileUrlReadAndDownload(String fileAddress, String localFileName, String downloadDir) {
		URLConnection uCon = null;
		InputStream is = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			System.out.println("-------Download Start------");
			URL Url;
			char[] buf;
			int byteRead;
			int byteWritten = 0;
			Url = new URL(fileAddress);

			uCon = Url.openConnection();
			is = uCon.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "MS949");
			OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream(downloadDir + "\\" + localFileName),
					"MS949");
			bw = new BufferedWriter(osr);
			br = new BufferedReader(isr);

			buf = new char[BUFF_SIZE];
			while ((byteRead = br.read(buf)) != -1) {
				bw.write(buf, 0, byteRead);
			}
			bw.flush();
			System.out.println("Download Successfully.");
			System.out.println("File name : " + localFileName);
			System.out.println("of bytes  : " + byteWritten);
			System.out.println("-------Download End--------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param fileAddress
	 * @param downloadDir
	 */
	public void fileUrlDownload(String fileAddress, String downloadDir) {
		int slashIndex = fileAddress.lastIndexOf('?');
		int periodIndex = fileAddress.lastIndexOf('.');
		// 파일 어드레스에서 마지막에 있는 파일이름을 취득
		String fileName = fileAddress.substring(slashIndex + 1);
		if (periodIndex >= 1 && slashIndex >= 0 && slashIndex < fileAddress.length() - 1) {
			fileUrlReadAndDownload(fileAddress, fileName, downloadDir);
		} else {
			System.err.println("path or file name NG.");
		}
	}

	@Override
	public List<StationDTO> searchBus(String routeId) {
		StringBuilder sb = new StringBuilder("routestation");
		Calendar cal = Calendar.getInstance();
		sb.append(cal.get(Calendar.YEAR));
		sb.append(String.format("%02d", cal.get(Calendar.MONTH) + 1));
		sb.append(String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)));
		String fileName = sb+".txt";
		fileMake(fileName);
		List<StationDTO> list = stationFileRead(fileName, routeId);
		setBusCurrentStation(list, routeId);
		return list;
	}

	@Override
	public void fileSetup() {
		// TODO Auto-generated method stub
		File folder = new File(Constants.ROUTE_FOLDER);
		File[] files = folder.listFiles();
		for (File file : files) {
			file.delete();
		}
		StringBuilder routeSb = new StringBuilder("route");
		StringBuilder routeStationSb = new StringBuilder("routestation");
		Calendar cal = Calendar.getInstance();

		String timeInfo = cal.get(Calendar.YEAR) + String.format("%02d", cal.get(Calendar.MONTH) + 1)
				+ String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
		routeSb.append(timeInfo);
		routeStationSb.append(timeInfo);

		fileMake(routeSb + ".txt");
		fileMake(routeStationSb + ".txt");

	}

	@Override
	public List<BusBookDTO> selectBookBus(String email) {
		return trafficDAO.selectBookBus(email);
	}

	@Override
	public int insertBookBus(BusBookDTO dto) {
		return trafficDAO.insertBookBus(dto);
	}

	@Override
	public int deleteBookBus(BusBookDTO dto) {
		return trafficDAO.deleteBookBus(dto);
	}
}
