package com.dpworld.automation.utils;

import com.dpworld.automation.browser.BrowserManager;
import com.dpworld.automation.exceptions.AutomationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIUtil {
    private static APIUtil apiUtilInstance;
    private final Playwright playwright;
    private final ObjectMapper objectMapper;
    public APIUtil() {
        this.playwright = Playwright.create();
        this.objectMapper = new ObjectMapper();
    }

    public APIResponse sendAPIRequest(String baseURL, String endpoint, String method, Map<String, String> headers, Object requestBody) {
        String requestBodyString = null;
        if (requestBody != null) {
            try {
                requestBodyString = objectMapper.writeValueAsString(requestBody);
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid request body: " + e.getMessage());
            }
        }
        APIRequestContext apiRequestContext = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(baseURL)
                .setExtraHTTPHeaders(headers));

        APIResponse response = null;
        try {
            switch (method.toUpperCase()) {
                case "GET":
                    if(requestBody != null) {
                        try {
                            HashMap<String,String> queryParams = (HashMap<String, String>) requestBody;
                            RequestOptions requestOptions = RequestOptions.create();
                            for (Map.Entry<String,String> entry : queryParams.entrySet()) {
                                requestOptions.setQueryParam( entry.getKey(),entry.getValue());
                            }
                            response = apiRequestContext.get(endpoint, requestOptions);
                        } catch (Exception e){
                            throw new AutomationException("Please send query parameters as HashMap");
                        }
                    } else {
                        response = apiRequestContext.get(endpoint);
                    }
                    break;
                case "POST":
                    response = apiRequestContext.post(endpoint, RequestOptions.create().setData(requestBody));
                    break;
                case "PUT":
                    response = apiRequestContext.put(endpoint, RequestOptions.create().setData(requestBody));
                    break;
                case "DELETE":
                    response = apiRequestContext.delete(endpoint);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            }
        } catch (PlaywrightException e) {
            throw new RuntimeException("Failed to send API request: " + e.getMessage() + e.getStackTrace());
        }

        LogUtils.info("API request details:");
        LogUtils.info("Method: " + method.toUpperCase());
        LogUtils.info("Endpoint: " + endpoint);
        LogUtils.info("Headers: " + headers);
        if (requestBody != null) {
            LogUtils.info("Request body: " + requestBodyString);
        }
        LogUtils.info("Response code: " + response.status());
        LogUtils.info("Response body: " + response.text());
        return response;
    }

    public void validateResponse(APIResponse response, int expectedStatusCode, String expectedResponseBody) {
        // Validate the status code
        if (response.status() != expectedStatusCode) {
            throw new RuntimeException(String.format("Expected status code %d, but got %d", expectedStatusCode, response.status()));
        }

        // Validate the response body if it's not null
        if (expectedResponseBody != null) {
            try {
                // Parse the expected response body into a JSON object
                JsonNode expectedJson = new ObjectMapper().readTree(expectedResponseBody);

                // Parse the actual response body into a JSON object
                JsonNode actualJson = new ObjectMapper().readTree(response.text());

                // Compare the expected and actual JSON objects
                if (!expectedJson.equals(actualJson)) {
                    throw new RuntimeException(String.format("Expected response body to be '%s', but got '%s'", expectedResponseBody, response.text()));
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid expected response body: " + e.getMessage());
            }
        }
    }

    public void validateResponse(APIResponse response, int expectedStatusCode, Object expectedResponseBody, List<String> ignoreFields, Map<String, Object> verifyFields) {
        if (response.status() != expectedStatusCode) {
            throw new RuntimeException(String.format("Expected status code %d, but got %d", expectedStatusCode, response.status()));
        }

        if (expectedResponseBody != null) {
            ObjectNode expectedResponseBodyJson = objectMapper.valueToTree(expectedResponseBody);
            String expectedResponseBodyString = null;
            String actualResponseBodyString = response.text();

            // Ignore fields in expected response body
            if (ignoreFields != null && !ignoreFields.isEmpty()) {
                for (String ignoreField : ignoreFields) {
                    expectedResponseBodyJson = expectedResponseBodyJson.without(ignoreField);
                }
            }

            // Verify fields in expected response body
            if (verifyFields != null && !verifyFields.isEmpty()) {
                for (Map.Entry<String, Object> verifyField : verifyFields.entrySet()) {
                    String fieldName = verifyField.getKey();
                    Object expectedFieldValue = verifyField.getValue();

                    JsonNode actualFieldValue = expectedResponseBodyJson.get(fieldName);
                    if (actualFieldValue == null || !actualFieldValue.equals(expectedFieldValue)) {
                        throw new RuntimeException(String.format("Expected '%s' field in response body to be '%s', but got '%s'", fieldName, expectedFieldValue, actualFieldValue));
                    }
                }
            }

            try {
                expectedResponseBodyString = objectMapper.writeValueAsString(expectedResponseBodyJson);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Invalid expected response body: " + e.getMessage());
            }

            if (!expectedResponseBodyString.equals(actualResponseBodyString)) {
                throw new RuntimeException(String.format("Expected response body to be '%s', but got '%s'", expectedResponseBodyString, actualResponseBodyString));
            }
        }
    }

    public APIResponse sendAPIRequestWithPostMultiPart(String baseURL, String endpoint, Map<String, String> headers, Object requestBody) {
        String requestBodyString = null;
        if (requestBody != null) {
            try {
                requestBodyString = objectMapper.writeValueAsString(requestBody);
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid request body: " + e.getMessage());
            }
        }
        APIRequestContext apiRequestContext = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(baseURL)
                .setExtraHTTPHeaders(headers));

        APIResponse response = null;
        String filePath = "./src/test/resources/RunnerPricingAutomationMAWB.xlsx";
        Path path = Paths.get(filePath);
        FormData formData = FormData.create().set("main_file",path)
                .set("emails","mohammed.suhail@dpworld.com")
                .set("file_type","RUNNER_PRICING")
                .set("tenant_id","69");
        response =  apiRequestContext.post(endpoint, RequestOptions.create().setMultipart(formData));
        LogUtils.info("API request details:");
        LogUtils.info("Endpoint: " + endpoint);
        LogUtils.info("Headers: " + headers);
        if (requestBody != null) {
            LogUtils.info("Request body: " + requestBodyString);
        }
        LogUtils.info("Response code: " + response.status());
        LogUtils.info("Response body: " + response.text());
        return response;
    }

}
