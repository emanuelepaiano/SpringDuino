/*
 * Copyright 2018 Emanuele Paiano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package serialduino.drivers;

/**
 * This is fake device for debug. It prints messages on console
 * @author Emanuele Paiano
 */
public class DummyLinkDevice implements LinkDevice{

    private boolean connected=true;
    
    
    @Override
    public int write(String data) {
        if(!connected)
            return -1;
        System.out.println("write(): "+data);
        return data.length();
    }

    @Override
    public String read(int bytes) {
        if(!connected)
            return "read(): not connected";
        System.out.println("read(): "+bytes);
        return "read(): "+bytes;
    }

    @Override
    public String read() {
        if(!connected)
            return "read(): not connected";
        System.out.println("read()");
        return "read()";
    }

    @Override
    public boolean open() {
       System.out.println("open(): "+connected);
       this.connected=true;
       return connected; 
    }

    @Override
    public boolean close() {
        System.out.println("open(): "+connected);
       this.connected=false;
       return connected; 
    }

    @Override
    public boolean isReady() {
       System.out.println("isReady(): "+connected);
       this.connected=true;
       return connected; 
    }

    @Override
    public boolean bufferAvailable() {
        return this.connected;
    }

    @Override
    public Object getDevice() {
        return null;
    }
    
}
