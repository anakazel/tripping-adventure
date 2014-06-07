package com.composition;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.util.concurrent.CountDownLatch;

/**
 * Main entry point of the rest2wsdl application
 */
public class Deployer {

    private Deployer() {}

    private static Deployer instance = new Deployer();

    public static Deployer getInstance() {
        return instance;
    }

    public CountDownLatch shutDownSignal = new CountDownLatch(1);

    private int run() throws LifecycleException, InterruptedException {
        System.out.println("Starting embedded Tomcat...");
        final Tomcat tomcat = new Tomcat();
        tomcat.setPort(9090);
        final File base = new File(System.getProperty("java.io.tmpdir"));
        final Context rootCtx = tomcat.addContext("/rest2wsdl", base.getAbsolutePath());
        final BaseServlet servlet = new BaseServlet();
        Tomcat.addServlet(rootCtx, "BaseServlet", servlet);
        rootCtx.addServletMapping("/*", "BaseServlet");
        tomcat.start();
        System.out.println("Done.");
        shutDownSignal.await();
        return 0;
    }

    public static void main(String[] args) throws LifecycleException, InterruptedException {
        System.exit(getInstance().run());
    }
}
