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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import nabi.web.dto.BusDTO;
import nabi.web.util.Constants;

@Service
@Transactional
public class TrafficServiceImpl implements TrafficService {
	/**
	 * 버퍼 사이즈
	 */
	final static int BUFF_SIZE = 1024;

	@Override
	public List<BusDTO> searchStation(String stationId) {
		StringBuilder sb = new StringBuilder("route");
		Calendar cal = Calendar.getInstance();
		sb.append(cal.get(Calendar.YEAR));
		sb.append(String.format("%02d", cal.get(Calendar.MONTH) + 1));
		sb.append(String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)));
		fileMake(sb + ".txt");
		return arrivalBusList(stationId, sb + ".txt");
	}

	public List<BusDTO> arrivalBusList(String stationId, String fileName) {
		List<BusDTO> busList = new ArrayList<>();
		List<String> busRouteIdList = new ArrayList<>();
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
					busRouteIdList.add(routeId);
					bus.setStationId(getTagValue("stationId", eElement));
					busList.add(bus);
				}
			}
			Map<String, List<String>> map = fileRead(fileName, busRouteIdList);
			List<String> busNameList = map.get("name");
			List<String> busTypeList = map.get("type");
			List<String> firstList = map.get("first");
			List<String> lastList = map.get("last");
			for (int i = 0; i < busNameList.size(); i++) {
				BusDTO bus = busList.get(i);
				bus.setBusName(busNameList.get(i));
				bus.setBusType(busTypeList.get(i));
				bus.setFirstStation(firstList.get(i));
				bus.setLastStation(lastList.get(i));
				
			}
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

	public Map<String, List<String>> fileRead(String fileName, List<String> busRouteList) {
		Map<String, List<String>> map = new HashMap<>();
		List<String> busNameList = null;
		List<String> busTypeList = null;
		List<String> firstList = null;
		List<String> lastList = null;
		try { // 예외 처리는 기본으로 해 줘야 한다
				// 파일에서 스트림을 통해 주르륵 읽어들인다
			BufferedReader in = new BufferedReader(new FileReader(Constants.ROUTE_FOLDER + "\\" + fileName));
			busNameList = new ArrayList<>();
			busTypeList = new ArrayList<>();
			firstList = new ArrayList<>();
			lastList = new ArrayList<>();
			String s;
			while ((s = in.readLine()) != null) {
				String[] split = s.split("\\^");
				for (int i = 0; i < busRouteList.size(); i++) {
					for (int j = 0; j < split.length; j++) {
						String[] busInfo = split[j].split("\\|");
						if (busRouteList.get(i).equals(busInfo[0])) {
							busNameList.add(busInfo[1]);
							firstList.add(busInfo[4]);
							lastList.add(busInfo[7]);
							switch (busInfo[2]) {
							case "13":
							case "23":
								busTypeList.add("G");
								break;
							case "11":
							case "12":
							case "14":
								busTypeList.add("R");
								break;
							default:
								busTypeList.add("B");
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
		map.put("name", busNameList);
		map.put("type", busTypeList);
		map.put("first", firstList);
		map.put("last", lastList);
		return map;
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
		OutputStream outStream = null;
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
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream(downloadDir + "\\" + localFileName) ,"UTF-8");
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
}
