package com.composition.utils;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 * Custom namespace prefix mapper for preferred types
 * @author alexg
 */
public final class CustomNamespacePrefixMapper extends NamespacePrefixMapper{
    private static final String WSDL_NAMESPACE_URI = "http://schemas.xmlsoap.org/wsdl/";
    private static final String HTTP_NAMESPACE_URI = "http://schemas.xmlsoap.org/wsdl/http/";
    private static final String MIME_NAMESPACE_URI = "http://schemas.xmlsoap.org/wsdl/mime/";

    @Override
    public String getPreferredPrefix(String arg0, String arg1, boolean b) {
        if(arg0.equals(WSDL_NAMESPACE_URI)){
            return "wsdl";
        }
        if(arg0.equals(HTTP_NAMESPACE_URI)){
            return "http";
        }
        if(arg0.equals(MIME_NAMESPACE_URI)){
            return "mime";
        }
        return "ns1";
    }
}
