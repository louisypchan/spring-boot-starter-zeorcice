package com.zd.ice.server.impl;

import com.zeroc.Ice.Current;
import com.zeroc.IceBox.*;

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
public class DefaultServiceManager implements ServiceManager {


    @Override
    public Map<String, String> getSliceChecksums(Current current) {
        return SliceChecksums.checksums;
    }

    @Override
    public void startService(String service, Current current) throws AlreadyStartedException, NoSuchServiceException {

    }

    @Override
    public void stopService(String service, Current current) throws AlreadyStoppedException, NoSuchServiceException {

    }

    @Override
    public void addObserver(ServiceObserverPrx observer, Current current) {

    }

    @Override
    public void shutdown(Current current) {

    }
}
