/**
 * Copyright 2017 Emanuele Paiano
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */

package serialduino.drivers;

import java.util.logging.Logger;

/**
 * SerialDuino generic serial link interface
 * @author Emanuele Paiano 
 * https://emanuelepaiano.github.io
 * */
public interface LinkDevice {
	
	/**
	 * write string to link port
	 * @param data output string to arduino
	 * @return bytes sent number
	 * */
	public int write(String data);
	
	/**
	 * read string from link port
	 * @param bytes bytes number to read
	 * @return string read
	 * */
	public String read(int bytes);
	
	/**
	 * read string from link port
	 * @return string read
	 * */
	public String read();
	
	
	/**
	 * open connection on link port
	 * @return true if success false otherwise
	 * */
	public boolean open();
	
	/**
	 * close connection on link port
	 * @return true if success false otherwise
	 * */
	public boolean close();
	
	/**
	 * @return true if port is connected and ready to write/read data, false otherwise
	 * */
	public boolean isReady();
	
	/**
	 * @return true if there are incoming bytes, false otherwise
	 * */
	public boolean bufferAvailable();
	
	/**
	 * @return device port
	 * */
	public Object getDevice();
	
	/**
	 * to log events
	 * */
	final static Logger logger=Logger.getLogger(LinkDevice.class.getName());
	
}
