package com.dpworld.automation.enums;

public enum SettingsTabs {

    SETTINGS("Settings"),
    EMAIL_SETTINGS("Email Settings"),
    QUOTE_SETTINGS("Quote Settings"),
    SHIPMENT_SETTINGS("Shipment Settings"),
    BOOKING_SETTINGS("Booking Settings"),
    INTEGRATION_SETTINGS("Integration Settings"),
    INVOICE_SETTINGS("Invoice Settings"),
    ORGANIZATION_SETTINGS("Organization Settings"),
    CONSOLIDATION_SETTINGS("Consolidation Settings"),
    CURRENCY_SETTINGS("Currency Settings"),
    DOCUMENT_SETTINGS("Document Settings"),
    TRANSPORT_INSTRUCTION_SETTINGS("Transport Instruction Settings"),
    PORTAL_SETTINGS("Portal Settings"),
    BL_LOCK_SETTINGS("BL Lock Settings"),
    GL_INTEGRATION("GL Integration"),
    RECEIVABLES("Receivables"),
    PAYABLES("Payables"),
    TARIFF_SETTINGS("Tariff Settings"),
    DASHBOARD_SETTINGS("Dashboard Settings"),
    CLEARANCE_SETTINGS("Clearance Settings"),
    SEQUENCE_CONFIGURATION("Sequence Configuration"),
    AWB_LOCK_SETTINGS("AWB Lock Settings"),
    EVENT_SETTINGS("Event Settings"),
    CREDIT_LIMIT_CONFIGURATION("Credit Limit Configuration"),
    DEFAULT_CHARGES("Default Charges"),
    CSR_SETTINGS("CSR Settings"),
    CRM_SETTINGS("CRM Settings");


    String settingsTab;

    SettingsTabs(String settingsTab) {
        this.settingsTab = settingsTab;
    }

    public String getSettingsTab(){
        return settingsTab;
    }
}
