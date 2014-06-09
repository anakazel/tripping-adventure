package com.composition;

import com.composition.model.Operation;
import com.composition.proxy.ProxyServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * Main entry point of the rest2wsdl application
 */
public final class Deployer {
    /**
     * The port on which Tomcat is listening, default 9090
     */
    private static int port = 9090;
    /**
     * The web context name, default is rest2wsdl
     */
    public static String context = "rest2wsdl";
    public static List<Operation> OPERATIONS;

    private Deployer() {}

    private static Deployer instance = new Deployer();

    public static Deployer getInstance() {
        return instance;
    }

    private CountDownLatch shutDownSignal = new CountDownLatch(1);

    private int run() throws LifecycleException, InterruptedException {
        System.out.println("Starting embedded Tomcat...");
        final Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        final File base = new File(System.getProperty("java.io.tmpdir"));
        final Context rootCtx = tomcat.addContext("/" + context, base.getAbsolutePath());
        final ProxyServlet servlet = new ProxyServlet();
        Tomcat.addServlet(rootCtx, "BaseServlet", servlet);
        rootCtx.addServletMapping("/*", "BaseServlet");
        tomcat.start();
        shutDownSignal.await();
        return 0;
    }

    public static void main(String[] args) throws LifecycleException, InterruptedException {
        port = Integer.parseInt(args[0]);
        context = args[1];
        final Properties propFile = new Properties();
        try {
//            final InputStream input = new FileInputStream(args[2]);
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            final InputStream input = classloader.getResourceAsStream("soap.operations.properties");
            propFile.load(input);
            final int count = Integer.parseInt(propFile.getProperty("operations"));
            OPERATIONS = new ArrayList<>();
            System.out.println("Loading operations...");
            for(int i = 1; i <= count; ++i){
                final Operation o = new Operation();
                o.setBaseUrl(propFile.getProperty("operation" + i + ".baseUrl"));
                o.setLocation(propFile.getProperty("operation" + i + ".location"));
                o.setHttpMethod(propFile.getProperty("operation" + i + ".httpMethod"));
                o.setRequestContentType(propFile.getProperty("operation" + i + ".request.contentType"));
                o.setResponseContentType(propFile.getProperty("operation" + i + ".response.contentType"));
                final List<String> params = new ArrayList<>();
                params.add(propFile.getProperty("operation" + i + ".params"));
                o.setParams(params);
                OPERATIONS.add(o);
                System.out.println(o);
            }
        } catch (IOException e) {
            System.out.println("I/O error.");
        }
        System.exit(getInstance().run());
        //TODO ADD USAGE [port] [context name] [path to config file]
    }
}
