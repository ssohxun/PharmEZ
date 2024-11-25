package com.PharmEZ.PharmEZback.home.controller;

import com.PharmEZ.PharmEZback.medicine.entity.Medicine;
import com.PharmEZ.PharmEZback.medicine.repository.MedicineRepository;
import com.PharmEZ.PharmEZback.pharmacy.entity.Pharmacy;
import com.PharmEZ.PharmEZback.pharmacy.repository.PharmacyRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * api 데이터 로딩
 *
 * @author sylee
 */
@Controller
@RequestMapping("/api")
public class HomeController {

    private final MedicineRepository medicineRepository;

    private final PharmacyRepository pharmacyRepository;

    public HomeController(MedicineRepository medicineRepository, PharmacyRepository pharmacyRepository) {
        this.medicineRepository = medicineRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    @GetMapping("")
    public String home() {
        return "home";
    }

    @PostMapping("")
    public String medicineSave(String numOfRows, int pageNo) {
        String result = "";

        try {
            int rowsPerPage = Math.min(Integer.parseInt(numOfRows), 100); // 최대 100
            String serviceKey = "bxIEfobo/fivORnHnRmrhawXwYMeO4idG1G2w+P/e6GCo4p6e46YKeRxUoGO7u7lWt2zc2KWODYNAPEZ8LvJDQ==";

            for (int pageCount = 1; pageCount <= pageNo; pageCount++) {
                String requestUrl = "https://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"
                        + "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8")
                        + "&numOfRows=" + rowsPerPage
                        + "&pageNo=" + pageCount
                        + "&type=json";

//                System.out.println("Requesting URL: " + requestUrl);

                // API 요청 및 응답 처리
                BufferedReader bf = new BufferedReader(new InputStreamReader(new URL(requestUrl).openStream(), "UTF-8"));
                result = bf.readLine();

<<<<<<< HEAD
                // JSON 파싱
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                JSONObject body = (JSONObject) jsonObject.get("body");

                if (body != null) {
                    JSONArray itemsArr = (JSONArray) body.get("items");

                    if (itemsArr != null && !itemsArr.isEmpty()) {
                        for (Object item : itemsArr) {
                            JSONObject tmp = (JSONObject) item;
                            Long itemSeq = tmp.get("itemSeq") != null ? Long.parseLong((String) tmp.get("itemSeq")) : null;

                        if (itemSeq != null) {  // itemSeq가 null이 아니면 저장
    
                            Medicine newMedicine = new Medicine(
                                        itemSeq,
                                        (String) tmp.get("entpName"),
                                        (String) tmp.get("itemName"),
                                        (String) tmp.get("efcyQesitm"),
                                        (String) tmp.get("useMethodQesitm"),
                                        (String) tmp.get("atpnWarnQesitm"),
                                        (String) tmp.get("atpnQesitm"),
                                        (String) tmp.get("intrcQesitm"),
                                        (String) tmp.get("seQesitm"),
                                        (String) tmp.get("depositMethodQesitm"),
                                        (String) tmp.get("itemImage")
                                );
                            // 중복 체크 후 저장
                            if (!medicineRepository.existsById(itemSeq)) {
                                     medicineRepository.save(newMedicine);
                            } else {
                                System.out.println("Medicine ID " + itemSeq + "는 이미 존재하기 때문에, 저장을 건너뜁니다.");
                            }
                        }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/api";
    }

    /**
     * xml 태그를 추출하기 위한 메소드
     *
     * author @sylee
     **/
    public static String getTagValue(String tag, Element eElement){
        String result = " ";
        try {
            NodeList nodeList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
            result = nodeList.item(0).getTextContent();
//        System.out.println(result);
            return result;
        }
        catch (Exception e){
            return null;
        }
    }

    @PostMapping("/pharmacy")
    public String pharmacySave(String numOfRows2, int pageNo2) {
        try {
            int rowsPerPage = Math.min(Integer.parseInt(numOfRows2), 100);
            // URL 생성
            String apiKey = URLEncoder.encode("bxIEfobo/fivORnHnRmrhawXwYMeO4idG1G2w+P/e6GCo4p6e46YKeRxUoGO7u7lWt2zc2KWODYNAPEZ8LvJDQ==","UTF-8");
            for (int pageCount = 1; pageCount <= pageNo2; pageCount++) {
                String requestUrl = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown"
                        + "?serviceKey=" + apiKey
                        + "&numOfRows=" + rowsPerPage
                        + "&pageNo=" + pageCount;

//            System.out.println("Request URL: " + requestUrl);

                // XML 파싱
                URL url = new URL(requestUrl);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(url.openStream());

                // items 태그 접근
                NodeList itemsList = doc.getElementsByTagName("items");
                if (itemsList.getLength() > 0) {
                    Node itemsNode = itemsList.item(0); // 첫 번째 <items> 태그
                    if (itemsNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element itemsElement = (Element) itemsNode;

                        // 하위 item 태그
                        NodeList pharmacyItems = itemsElement.getElementsByTagName("item");
                        System.out.println("Pharmacy items count: " + pharmacyItems.getLength());

                        for (int i = 0; i < pharmacyItems.getLength(); i++) {
                            Node nNode = pharmacyItems.item(i);
                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) nNode;

                                Long id = Long.parseLong(getTagValue("rnum", eElement));
                                Integer frontZipCode = Integer.parseInt(getTagValue("postCdn1", eElement).trim());
                                Integer backZipCode = Integer.parseInt(getTagValue("postCdn2", eElement).trim());
                                Double latitude = Double.parseDouble(getTagValue("wgs84Lat", eElement));
                                Double longitude = Double.parseDouble(getTagValue("wgs84Lon", eElement));

                                Pharmacy newPharmacy = new Pharmacy(
                                        id,
                                        getTagValue("dutyAddr", eElement),
                                        getTagValue("dutyName", eElement),
                                        getTagValue("dutyTel1", eElement),
                                        getTagValue("dutyTime1s", eElement),
                                        getTagValue("dutyTime1c", eElement),
                                        getTagValue("dutyTime2s", eElement),
                                        getTagValue("dutyTime2c", eElement),
                                        getTagValue("dutyTime3s", eElement),
                                        getTagValue("dutyTime3c", eElement),
                                        getTagValue("dutyTime4s", eElement),
                                        getTagValue("dutyTime4c", eElement),
                                        getTagValue("dutyTime5s", eElement),
                                        getTagValue("dutyTime5c", eElement),
                                        getTagValue("dutyTime6s", eElement),
                                        getTagValue("dutyTime6c", eElement),
                                        getTagValue("dutyTime7s", eElement),
                                        getTagValue("dutyTime7c", eElement),
                                        getTagValue("dutyTime8s", eElement),
                                        getTagValue("dutyTime8c", eElement),
                                        frontZipCode,
                                        backZipCode,
                                        latitude,
                                        longitude
                                );

                                // 중복 확인 후 저장 로직
                                if (!pharmacyRepository.existsById(id)) {
                                    pharmacyRepository.save(newPharmacy);
                                } else {
                                    System.out.println("Pharmacy ID " + id + "는 이미 존재하기 때문에, 저장을 건너뜁니다.");
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("item tag 없음.");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/api";
    }
}
