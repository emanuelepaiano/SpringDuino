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
package serialduino.microcontrollers;

/**
 *
 * @author Emanuele Paiano
 */
public interface HardwareController {
    
    /**
     * Initialize Device
     */
    public boolean init();	

    /**
     * Disconnect device
     * */
    public boolean disconnect();
    
    /**
     * Blocking operation. Wait a response on serial port
     * */
    public String waitAndGetResponse();
	
	
    /**
     * Run single command string to Arduino
     * @return true if success, false otherwise
     * */
    public boolean exec(String[] params);
    
}
