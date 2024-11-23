package com.PharmEZ.PharmEZback.home.controller;

import com.PharmEZ.PharmEZback.medicine.entity.Medicine;
import com.PharmEZ.PharmEZback.medicine.repository.MedicineRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * api 데이터 로딩
 *
 *
 */
@Controller
@RequestMapping("/api")
public class HomeController {

    private final MedicineRepository medicineRepository;

    public HomeController(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @GetMapping("")
    public String home() {
        return "medicine_home";
    }

    @PostMapping("")
    public String medicineSave(@RequestParam("numOfRows") String numOfRows, Model model) {
        String result = "";

        try {
            String requestNumOfRows = numOfRows;
            String serviceKey = URLEncoder.encode("bxIEfobo/fivORnHnRmrhawXwYMeO4idG1G2w+P/e6GCo4p6e46YKeRxUoGO7u7lWt2zc2KWODYNAPEZ8LvJDQ==", "UTF-8");
            URL url = new URL("https://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?" +
                    "serviceKey=" + serviceKey +
                    "&numOfRows=" + URLEncoder.encode(requestNumOfRows, "UTF-8") +
                    "&type=json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject body = (JSONObject) jsonObject.get("body");

            if (body != null) {
                JSONArray itemsArr = (JSONArray) body.get("items");

                if (itemsArr != null) {
                    for (Object item : itemsArr) {
                        JSONObject tmp = (JSONObject) item;
                        Long itemSeq = tmp.get("itemSeq") != null ? Long.parseLong((String) tmp.get("itemSeq")) : null;
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
                        medicineRepository.save(newMedicine);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/api"; // 폼 제출 후 리다이렉션
    }
}
