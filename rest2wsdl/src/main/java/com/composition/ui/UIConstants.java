package com.composition.ui;

/**
 * @author alexg
 */
public enum UIConstants {

    ; // INSTANCE

    /**
     * The supported HTTP methods by the application
     */
    static String [] HTTP_METHODS = {"GET", "PUT", "POST", "DELETE"};

    /**
     * The supported HTTP Content Types by the application
     */
    static final String [] REQUEST_TYPES = {"text/xml", "application/json"};

    static final int SERVICE_NAME_COLUMN_IDX = 0;
    static final int BASE_URL_COLUMN_IDX = 1;
    static final int LOCATION_URL_COLUMN_IDX = 2;
    static final int NAME_COLUMN_IDX = 3;
    static final int HTTP_METHOD_COLUMN_IDX = 4;
    static final int REQUEST_CONTENT_TYPE_COLUMN_IDX = 5;
    static final int RESPONSE_CONTENT_TYPE_COLUMN_IDX = 6;
    static final int PARAMS_COLUMN_IDX = 6;

}
