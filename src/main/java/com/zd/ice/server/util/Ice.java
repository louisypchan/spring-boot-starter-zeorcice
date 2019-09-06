package com.zd.ice.server.util;

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
public class Ice {
    /**
     * see https://doc.zeroc.com/ice/3.7/property-reference/miscellaneous-ice-properties for details
     */
    public final static String BackgroundLocatorCacheUpdates = "Ice.BackgroundLocatorCacheUpdates";
    public final static String BatchAutoFlushSize = "Ice.BatchAutoFlushSize";
    public final static String CacheMessageBuffers = "Ice.CacheMessageBuffers";
    public final static String MessageSizeMax = "Ice.MessageSizeMax";
    public final static String WarnConnections = "Ice.Warn.Connections";
    public final static String PrintAdapterReady = "Ice.PrintAdapterReady";
    public final static String PrintProcessId = "Ice.PrintProcessId";
    public final static String OverrideTimeout = "Ice.Override.Timeout";
    public final static String TCP_BACKLOG = "Ice.TCP.Backlog";
    public final static String TCP_RCVSIZE = "Ice.TCP.RcvSize"; //513920
    public final static String TCP_SNDSIZE = "Ice.TCP.SndSize"; //513920
}
