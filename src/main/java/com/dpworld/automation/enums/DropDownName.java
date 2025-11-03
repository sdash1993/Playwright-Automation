package com.dpworld.automation.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public enum DropDownName {
    COUNTRY("Country"),
    UNLOCODE("UN/LOCODE"),
    CURRENCY_CODE("Currency Code"),
    CONSIGNER("Consigner"),
    CONSIGNEE("Consignee"),
    TRANSPORT_MODE("Transport Mode"), // Consolidation page as well

    CARGO_TYPE("Cargo Type"),
    ACTIVITY_TYPE("Activity Type"),

    SERVICE_MODE("Service Mode"), //Consoldiation Orders
    ENTRY_DETAIL("Entry Detail"),

    INCO_TERM("Inco Term"),
    ORIGIN("Origin"),
    DESTINATION("Destination"),

    ORDER_NUMBER("Order Number"),
    ORIGIN_PORT("Origin Port"),
    DESTINATION_PORT("Destination Port"),
    GOODS_VALUE_CURRENCY("Goods Value Currency"),

    INSURANCE_VALUE_CURRENCY("Insurance Value Currency"),
    TYPE("Type"),

    CONSOLIDATION_TYPE("Consolidation Type"),
    FREIGHT_OVERSEAS_CURRENCY("Freight Overseas Currency"),

    INNERS_UNIT("Inners Unit"),
    SHIPMENT_TYPE("Shipment Type"),
    @JsonIgnoreProperties
    VESSEL_NAME_OR_FLIGHT("VesselName/ Flight"),
    @JsonIgnoreProperties
    SHIPPING_LINE("Shipping Line"),
    PAYMENT("Payment"),
    GROSS_WEIGHT_UNITS("Gross Weight Unit"),
    STATUS("Status"),

    STATE("State"),
    CITY("City"),
    CHARGES_APPLY("Charges Apply"),

    NET_WEIGHT_UNIT("Net Weight Unit"),

    RELEASE_TYPE("Release Type"),

    CREATED_BY("Created By"),

    ASSIGNED_TO("AssignedTo"),

    AIRWAY_BILL_DIMS("Airway Bill Dims"),

    HOUSE_BILL_TYPE("House Bill Type"),

    ON_BOARD("On Board"),

    PARTY("Party"),

    DELIVERY_MODE("Delivery Mode"),

    EXPORTER_STMT("Exporter Stmt"),
    SERVICE_TYPE("Service Type"),

    SHIPPER_CODPM("Shipper Codpm"),
    CONTRACTOR("Contractor"),
    SCREENING_STATUS("Screening Status"),

    PHASE("Phase"),

    SPOT_RATE_TYPE("Spot Rate Type"),
    EFREIGHT_STATUS("Efreight Status"),
    SALES_AGENT("Sales Agent"),
    JOB_STATUS("Job Status"),
    CLIENT("Client"),

    PACKS_UNIT("Packs Unit"),

    ROUTE_TYPE("Route Type"),
    FULL_NAME("Full Name"),
    AIRCRAFT_TYPE("Aircraft Type"),
    ETD("ETD"),
    ETA("ETA"),
    PAYMENT_TERMS("Payment Terms"),
    WAREHOUSE("Warehouse"),
    BONDED_WAREHOUSE("Bonded Warehouse"),
    SIZE("Size");


    private final String dropdown;

    DropDownName(String dropdown) {
        this.dropdown = dropdown;
    }

    public String getDropdown() {
        return this.dropdown;
    }

}
