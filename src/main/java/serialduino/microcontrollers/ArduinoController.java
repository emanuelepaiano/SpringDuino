/*
 * Copyright 2018 Emanuele Paiano.
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
package serialduino.microcontrollers;

import serialduino.arduino.ArduinoSerialMonitor;
import serialduino.drivers.LinkDevice;
import serialduino.settings.DefaultArduinoSerialMonitor;

/**
 *
 * @author Emanuele Paiano
 */
public class ArduinoController implements HardwareController {

    protected final ArduinoSerialMonitor arduino;
    
    public ArduinoController(LinkDevice port){
        this.arduino=new ArduinoSerialMonitor(port);
    }
    
    public void finalize(){
        this.arduino.close();
    }
    
    @Override
    public synchronized boolean init() {
        return arduino.open();
    }

    public synchronized ArduinoSerialMonitor getArduino() {
        return arduino;
    }

    @Override
    public synchronized boolean disconnect() {
        return arduino.close();
    }


    @Override
    public synchronized String waitAndGetResponse() {
        String resp = "";

        while (!arduino.bufferAvailable()) {
            try {
                Thread.sleep(5);

            } catch (InterruptedException e) {
            }
        }

        arduino.setEndChar("\r\n");

        resp = arduino.receive();

        arduino.setEndChar(DefaultArduinoSerialMonitor.END_WITH);

        return resp;
    }

    @Override
    public synchronized boolean exec(String[] param) {
        if (param.length < 4) {
            return false;
        } else {
            String opcode = param[0];
            if (arduino.isReady()) {
                arduino.send(opcode + "," + param[1] + "," + param[2] + ", " + param[3]);
            }

            return waitAndGetResponse().contains("250 OK");
        }

    }

}
