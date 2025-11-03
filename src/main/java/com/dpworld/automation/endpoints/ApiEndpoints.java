package com.dpworld.automation.endpoints;

public class ApiEndpoints {

    public static final String UPDATE_TENANT_SETTINGS = "/services/Default/TenantSettings/Update";
    public static final String GENERATE_TOKEN = "/Api/Account/GenerateToken";
    public static final String RETRIEVE_TENANT_SETTINGS = "/services/Default/Tenants/Retrieve";
    public static final String CREATE_ORGANIZATIONS = "services/Default/Organizations/Create";
    public static final String CREATE_CONSOLIDATION = "services/Default/Consolidation/Create";
    public static final String CREATE_SHIPMENT = "services/Default/Shipments/Create";
    public static final String SEARCH_ORGANIZATIONS = "Services/Default/Organizations/List";
    public static final String RETRIEVE_ORGANIZATIONS = "Services/Default/Organizations/Retrieve";
    public static final String DELETE_ORGANIZATIONS = "services/Default/Organizations/Delete";
    public static final String UPDATE_ORGANIZATIONS = "services/Default/Organizations/Update";
    public static final String UPDATE_COMPANY_SETTINGS = "/services/Default/CompanySettings/Update";
    public static final String RETRIEVE_COMPANY_DETAILS = "/services/Default/Companies/Retrieve";
    public static final String RETRIEVE_SHIPMENT = "/services/Default/Shipments/Retrieve";
    public static final String RETRIEVE_CONSOLIDATION = "/services/Default/Consolidation/Retrieve";
    public static final String RETRIEVE_UN_LOCATIONS = "/services/Default/UnLocations/Retrieve";
    public static final String RETRIEVE_MAWB_STOCKS = "/services/Default/MawbStocks/Retrieve";

    public static final String RATE_UPLOAD_API_NPM = "/v3/rates/upload";

    public static final String UPLOAD_PROGRESS_NPM = "/rates/progress";

    public static final String CREATE_PAYMENT = "/services/Default/PaymentDetails/Create";
    public static final String CREATE_CONTAINER = "/services/Default/CommonContainers/Create";
    public static final String LIST_ROLES = "/Services/Administration/Role/List";
    public static final String CREATE_ROLES = "/services/Administration/Role/Create";
    public static final String UPDATE_ROLE_PERMISSIONS = "/services/Administration/RolePermission/Update";
    public static final String DELETE_ROLE = "/services/Administration/Role/Delete";
}
