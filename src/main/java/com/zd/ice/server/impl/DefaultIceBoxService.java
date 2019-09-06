package com.zd.ice.server.impl;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import com.zeroc.IceBox.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class DefaultIceBoxService implements Service, Object {

    private final Logger logger = LoggerFactory.getLogger(DefaultIceBoxService.class);

    private ObjectAdapter objectAdapter;

    @Override
    public void start(String name, Communicator communicator, String[] args) {
        logger.info("Service name : " + name);
        objectAdapter = communicator.createObjectAdapter(name);
        objectAdapter.add(this, Util.stringToIdentity(name));
        objectAdapter.activate();
    }

    @Override
    public void stop() {
        if(objectAdapter != null){
            objectAdapter.destroy();
        }
    }
}
