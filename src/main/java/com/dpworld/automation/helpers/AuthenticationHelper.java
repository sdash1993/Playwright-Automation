package com.dpworld.automation.helpers;

import com.dpworld.automation.data.login.LoginData;
import com.dpworld.automation.endpoints.ApiEndpoints;
import com.dpworld.automation.utils.APIUtil;
import com.microsoft.playwright.APIResponse;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.dpworld.automation.config.ConfigurationManager.configuration;


public class AuthenticationHelper {
    public static APIResponse generateToken(String username, String password){
        Map<String , String> map =  new HashMap<>();
        map.put("Accept", "*/*");
        map.put("Content-Type", "application/json; charset=UTF-8");
        String tokenPayload = "{\"UserName\": \""+username+"\",\"Password\": \""+password+"\"}";
        return new APIUtil().sendAPIRequest(configuration().baseUrl(), ApiEndpoints.GENERATE_TOKEN, "POST", map, tokenPayload);
    }

    public static Map<String, String> getAuthHeaders(LoginData loginData) {
        APIResponse response = generateToken(loginData.getUserName(), loginData.getPassword());
        JSONObject jsonObject = new JSONObject(response.text());
        String token = "Bearer " + jsonObject.get("token").toString();
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("Authorization", token);
        String ASPNetAuth= response.headersArray().stream().filter(x->x.value.contains("AspNetAuth")).findAny().get().value;
        String AKSCookie = response.headersArray().stream().filter(x->x.value.contains("AKSCookie")).findAny().get().value;

        headers.put("Cookie", ASPNetAuth.substring(0, ASPNetAuth.indexOf(";")) + ";" + AKSCookie.substring(0, AKSCookie.indexOf(";")));
        return headers;
    }

    public static Map<String, String> getNpmAuthHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-api-key-v2", "b50c6d45-38c5-4eca-9d61-205e56bc2b3d");
        return headers;
    }
}
