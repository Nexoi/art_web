package com.seeu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seeu.utils.DateFormatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;
import java.util.Date;

@Controller
public class PageController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DateFormatterService dateFormatterService;

    @GetMapping("/web/{id}.html")
    public String webPage(@PathVariable String id, Model model) {
        boolean isNum = id.matches("[0-9]+");
        if (!isNum) return "404";
        ResponseEntity<String> webPage = restTemplate.getForEntity("http://api.vvaryun.com/api/v1/webpage/" + id, String.class);
        JSONObject object = JSON.parseObject(webPage.getBody());
        WebPage page = object.getJSONObject("data").toJavaObject(WebPage.class);
        Date time = page.getCreateTime();
        model.addAttribute("pg", page);
        model.addAttribute("time", dateFormatterService.getyyyyMMdd_X().format(time));
        return "temppage";
    }
}
