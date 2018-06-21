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
package springduino.mvc.services;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import serialduino.drivers.ComLinkDevice;
import serialduino.drivers.DummyLinkDevice;
import serialduino.drivers.LinkDevice;
import serialduino.microcontrollers.ArduinoController;

/**
 *
 * @author Emanuele Paiano
 */
@Service
public class ArduinoServiceImpl implements ArduinoService{
    
    @Autowired
    @Qualifier(value="ArduinoUsb")
    private ArduinoController arduino;
    
    private final String usbPort="/dev/ttyUSB1";
    
    private final int speed=ComLinkDevice.BAUDRATE_9600;
    
    @PostConstruct
    public void init(){
        arduino.init();              
    }
 
    @Override
    public boolean ledOn(){
        boolean success=false;
        success=arduino.exec(new String[]{"0","0","0","1"});
        return success;
    }
    
    @Override
    public boolean ledOff(){
        boolean success=arduino.exec(new String[]{"0","0","0","0"});
        return success;
    }
    
    @Bean(name="ArduinoUsb")
    public ArduinoController monitor(){
        LinkDevice port=new ComLinkDevice(ComLinkDevice.getPortByName(this.usbPort), speed);
        
        //LinkDevice port=new ComLinkDevice(ComLinkDevice.getPorts()[0], speed);
        
        //LinkDevice port=new DummyLinkDevice();
        
        return new ArduinoController(port);
    }
}
