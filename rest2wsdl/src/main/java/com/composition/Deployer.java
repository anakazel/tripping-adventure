package com.composition;

import com.composition.model.Operation;
import com.composition.proxy.ProxyServlet;
import com.composition.utils.WSDLUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Main entry point of the rest2wsdl application
 */
public final class Deployer {
    /**
     * The port on which Tomcat is listening, default 9090
     */
    public static int port = 9090;
    /**
     * The web context name, default is rest2wsdl
     */
    public static String context = "rest2wsdl";
    public static List<Operation> OPERATIONS;

    public static void startServerAndCreateWSDL() throws InterruptedException, JAXBException {
        WSDLUtils.generateWSDL();
        new Thread(){
            public void run(){
                final Server server = new Server(port);
                final ServletHandler handler = new ServletHandler();
                server.setHandler(handler);
                handler.addServletWithMapping(ProxyServlet.class, "/" + context + "/*");
                try {
                    server.start();
                    System.out.println("Server started.");
                    server.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}