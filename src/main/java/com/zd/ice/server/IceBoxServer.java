package com.zd.ice.server;

import com.zd.ice.server.config.IceBoxProperties;
import com.zd.ice.server.impl.DefaultServiceManager;
import com.zd.ice.server.impl.LoggerI;
import com.zd.ice.server.util.Ice;
import com.zeroc.Ice.Properties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.Map;

/****************************************************************************
 Copyright (c) 2019 Louis Y P Chen.
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 ****************************************************************************/
public class IceBoxServer extends com.zeroc.Ice.Application{

    private final Logger logger = LoggerFactory.getLogger(IceBoxServer.class);

    /**
     *  Default batch flush size
     */
    private final static String DefaultBatchAutoFlushSize = "1024"; //KB
    /**
     * Max message size, 1024 KB by default
     */
    private final static String DefaultMessageSizeMax = "1024"; //KB


    private DefaultServiceManager defaultServiceManager;
    private final ApplicationArguments applicationArguments;
    private final ApplicationContext applicationContext;

    private com.zeroc.Ice.InitializationData initData = null;

    private String instanceName = null;

    public IceBoxServer(ApplicationContext applicationContext, ApplicationArguments applicationArguments) {
        this.initData = new com.zeroc.Ice.InitializationData();
        this.applicationContext = applicationContext;
        this.applicationArguments = applicationArguments;
    }

    private void buildDefault(Properties properties) {
        Assert.notNull(properties, "properties must not be null");
        properties.setProperty(Ice.BackgroundLocatorCacheUpdates, "1");
        properties.setProperty(Ice.BatchAutoFlushSize, DefaultBatchAutoFlushSize);
        properties.setProperty(Ice.MessageSizeMax, DefaultMessageSizeMax);
        properties.setProperty(Ice.WarnConnections, "1");
        properties.setProperty(Ice.PrintAdapterReady, "1");
        properties.setProperty(Ice.PrintProcessId, "1");
        properties.setProperty(Ice.OverrideTimeout, "5000");
        properties.setProperty("Ice.Trace.ThreadPool", "1");
        properties.setProperty("IceBox.Trace.ServiceObserver", "1");
        properties.setProperty("Ice.Admin.DelayCreation", "1");
    }


    public IceBoxServer prepare(IceBoxProperties iceBoxProperties) {
        logger.info("prepare icebox according to properties");
        //use customer logger
        com.zeroc.Ice.Util.setProcessLogger(new LoggerI("spring-boot-starter-zeorcice by louisypchan"));
        initData.properties = com.zeroc.Ice.Util.createProperties();
        this.buildDefault(initData.properties);
        if (StringUtils.isNotBlank(iceBoxProperties.getPrintServicesReady())){
            initData.properties.setProperty("IceBox.PrintServicesReady", iceBoxProperties.getPrintServicesReady());
        }
        //load orders
        if(StringUtils.isNotBlank(iceBoxProperties.getLoadOrder())){
            initData.properties.setProperty("IceBox.LoadOrder", iceBoxProperties.getLoadOrder());
        }
        initData.properties.setProperty("IceBox.InheritProperties", iceBoxProperties.getInheritProperties());
        if (iceBoxProperties.getServices() != null){
            for (IceBoxProperties.Service service: iceBoxProperties.getServices()) {
                String entry = service.getEntry();
                String endpoints = service.getEndpoints();
                if (StringUtils.isNotBlank(entry)){
                    initData.properties.setProperty(String.format("IceBox.Service.%s", service.getName()), entry);
                }
                if(StringUtils.isNotBlank(endpoints)){
                    initData.properties.setProperty(String.format("%s.Endpoints", service.getName()), endpoints);
                }
                if (StringUtils.isNotBlank(service.getUseSharedCommunicator())) {
                    initData.properties.setProperty(String.format("IceBox.UseSharedCommunicator.%s", service.getName()), service.getUseSharedCommunicator());
                }
            }
        }
        //Miscellaneous
        if(iceBoxProperties.getMiscellaneous() != null){
            for (Map.Entry<String, String> entry : iceBoxProperties.getMiscellaneous().entrySet()){
                initData.properties.setProperty(entry.getKey(), entry.getValue());
            }
        }

        String appName = String.format("IceBox.Server.%s", String.valueOf(System.currentTimeMillis()));
        if(StringUtils.isNotBlank(iceBoxProperties.getName())){
            appName = iceBoxProperties.getName();
        }

        for(Map.Entry<String, String> entry : initData.properties.getPropertiesForPrefix("").entrySet()){
            logger.info(entry.getKey() + " : " + entry.getValue());
        }

        this.instanceName = appName;
        return this;
    }

    public IceBoxServer serve() {
        System.exit(this.main(this.instanceName, this.applicationArguments.getSourceArgs(), this.initData));
        return this;
    }

    @Override
    public int run(String[] args) {
        logger.info("start to initiate icebox server");
        this.defaultServiceManager = new DefaultServiceManager(applicationContext, communicator(), applicationArguments.getSourceArgs());
        return this.defaultServiceManager.run();
    }
}
