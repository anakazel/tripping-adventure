package com.composition;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.util.concurrent.CountDownLatch;

public class Main {
    // basic singleton
    private Main() {}

    private static Main instance = new Main();

    public static Main getInstance() {
        return instance;
    }

    public CountDownLatch shutDownSignal = new CountDownLatch(1);

    private int run() throws LifecycleException, InterruptedException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9090);
        File base = new File(System.getProperty("java.io.tmpdir"));
        Context rootCtx = tomcat.addContext("/rest2wsdl", base.getAbsolutePath());
        BaseServlet servlet = new BaseServlet();
        Tomcat.addServlet(rootCtx, "BaseServlet", servlet);
        rootCtx.addServletMapping("/*", "BaseServlet");
        tomcat.start();
        shutDownSignal.await();
        return 0;
    }

    public static void main(String[] args) throws LifecycleException, InterruptedException {
        System.exit(getInstance().run());
    }
}
