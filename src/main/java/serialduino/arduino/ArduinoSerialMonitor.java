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

package serialduino.arduino;

import serialduino.drivers.LinkDevice;
import serialduino.settings.DefaultArduinoSerialMonitor;

/**
 * SerialDuino. Arduino serial monitor
 * @author Emanuele Paiano 
 * https://emanuelepaiano.github.io
 * @see DefaultArduinoSerialMonitor
 * */
public class ArduinoSerialMonitor {
	
	/**
	 * End char(s) while sending data
	 **/
	private String end_with=DefaultArduinoSerialMonitor.END_WITH;
	
	
	
	/**
	 * New Line & Carriage Return value
	 * */
	public static String NLCR_END="\n\r";
	
	
	/**
	 * Carriage Return value
	 * */
	public static String CR_END="\r";
	
	
	/**
	 * New Line value
	 * */
	public static String NL_END="\n";
	
	/**
	 * No new line or carriage return value
	 * */
	public static String NONE_END="";
	
	
	/**
	 * porta di comunicazione
	 * */
	private LinkDevice port;
	
	
	/**
	 *  Constructor 
	 *  @param port LinkDevice object (see package org.serialduino.drivers)
	 *   */
	public ArduinoSerialMonitor(LinkDevice port)
	{
		this.port=port;
	}
	
	
	/**
	 * Send data with ENDCHAR (see setEndChar() method)
	 * @param data string to send
	 * @return sent bytes number if success, -1 otherwise
	 * */
	public int send(String data)
	{
		if (port.isReady()){
				this.port.write(data+end_with);
				return data.length();
		}
		return -1;
	}
	
	
	/**
	 * receive a single char from Arduino
	 * @return single char
	 * 
	 * */
	public String receiveChar()
	{	
		String tmp=null;
		if (port.isReady())
		{
			if (bufferAvailable())	
					tmp=port.read(1);
			
		}
		return tmp;
	}
	
	
	
	/**
	 * equivalent to receive('\n').
	 * @return string ends with '\n'
	 * 
	 * */
	public String receiveString()
	{	
		return receiveUntil('\n');
	}
	
	/**
	 * receive strings from Arduino using single char for termination
	 * @param ch termination char (i.e. '\n')
	 * @return string sent from arduino
	 * 
	 * */
	public String receiveUntil(char ch)
	{	
		String tmp=null;
		
		if (port.isReady() && bufferAvailable())
		{
			tmp="";
			while(bufferAvailable()){
				String current=receiveChar();
				tmp+=current;
				if(current.toCharArray()[0]==ch)
					break;
			}
		}
		return tmp;
	}
	
	/**
	 * receive all data(s) from input buffer
	 * @return string sent from arduino
	 * 
	 * */
	public String receive()
	{	
		String tmp=null;
		
		if (port.isReady() && bufferAvailable())
		{
			tmp="";
			while(bufferAvailable()){
				String current=receiveChar();
				tmp+=current;
			}
		}
		return tmp;
	}
	
	
	/**
	 * Open connection on port and wait 2 seconds for Arduino Boot
	 * @return true if success, false otherwise
	 * @see DefaultArduinoSerialMonitor.ARDUINO_BOOT
	 * */
	public boolean open()
	{
		boolean result=port.open();
		try {
			Thread.sleep(DefaultArduinoSerialMonitor.ARDUINO_BOOT);
		} catch (InterruptedException e) {
			port.close();
			result=false;
		}
		return result;
	}
	
	/**
	 * Close serial connection
	 * @return true if success, false otherwise
	 * */
	public boolean close()
	{
		return port.close();
	}
	
	/**
	 * Set End char(s) for sending data
	 * @param endCh ArduinoSerialMonitor.NLCR_END, ArduinoSerialMonitor.CR_END or ArduinoSerialMonitor.NL_END
	 * @return true if success, false otherwise
	 * */
	public boolean setEndChar(String endCh)
	{
		if(!isReady()){
			this.end_with=endCh;
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * Check if port is connected
	 * @return true if success, false otherwise
	 * */
	public boolean isReady()
	{
		return port.isReady();
	}
	
	/**
	 * @return true if buffer not empty, false otherwise
	 * */
	public boolean bufferAvailable()
	{
		return port.bufferAvailable();
	}

}
