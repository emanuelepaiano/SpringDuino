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

import serialduino.settings.DefaultComLinkDevice;
import serialduino.settings.DefaultLinkDevice;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * SerialDuino COM/TTY Port driver
 * @author Emanuele Paiano 
 * https://emanuelepaiano.github.io
 * */
public class ComLinkDevice implements LinkDevice{
	
	/**
	 * baudrate 9600bps value
	 * */
	public static int BAUDRATE_9600=SerialPort.BAUDRATE_9600;
	
	
	/**
	 * baudrate 19200bps value
	 * */
	public static int BAUDRATE_19200=SerialPort.BAUDRATE_19200;
	
	/**
	 * baudrate 38400bps value 
	 * */
	public static int BAUDRATE_38400=SerialPort.BAUDRATE_38400;
	
	/**
	 * baudrate 57600bps value
	 * */
	public static int BAUDRATE_57600=SerialPort.BAUDRATE_57600;
	
	/**
	 * baudrate 115200bps value
	 * */
	public static int BAUDRATE_115200=SerialPort.BAUDRATE_115200;
	
	/**
	 * databits 5 value
	 * */
	public static int DATABITS_5=SerialPort.DATABITS_5;
	
	/**
	 * databits 6 value
	 * */
	public static int DATABITS_6=SerialPort.DATABITS_6;
	
	/**
	 * databits 7 value
	 * */
	public static int DATABITS_7=SerialPort.DATABITS_7;
	
	/**
	 * databits 8 value
	 * */
	public static int DATABITS_8=SerialPort.DATABITS_8;
	
	
	/**
	 * Stop bits 1
	 * */
	public static int STOPBITS_1=SerialPort.STOPBITS_1;
	
	/**
	 * Stop bits 2
	 * */
	public static int STOPBITS_2=SerialPort.STOPBITS_2;
	
	
	/**
	 * Parity Check None
	 * */
	public static int PARITY_NONE=SerialPort.PARITY_NONE;
	
	/**
	 * Parity Check Odd
	 * */
	public static int PARITY_ODD=SerialPort.PARITY_ODD;	
	
	/**
	 * Parity Check Even
	 * */
	public static int PARITY_EVEN=SerialPort.PARITY_EVEN;
	
	/**
	 * Parity Check SPACE
	 * */
	public static int PARITY_SPACE=SerialPort.PARITY_SPACE;
	
	/**
	 * Parity Check MARK
	 * */
	public static int PARITY_MARK=SerialPort.PARITY_MARK;
	
	
	/**
	 * Serial COM port
	 * */
	private SerialPort port;
	
	/** 
	 * Port speed (in bps) 
	 * 
	 * */
	private int baudRate=SerialPort.BAUDRATE_9600;	
	
	
	/**
	 * Constructor.
	 * @param port COM or TTY port (like /dev/ttyS1 for Linux, or COM1 for Windows
	 * @param baudRate use ComLinkDevice.BAUDRATE settings. For advanced settings, use 
	 * getDevice() method
	 * */
	public ComLinkDevice(String port, int baudRate){
		this.port=new SerialPort(port);
		this.baudRate=baudRate;
	}
	
	/**
	 * Write data to Serial Port
	 * @return written bytes number if success, -1 otherwise
	 * @throws SerialPortException
	 * @throws InterruptedException
	 * */
	@Override
	public int write(String data) {
		if (isReady()) 
		{
			try {
				port.writeString(data);
				while(port.getOutputBufferBytesCount()>0)
					try {
						Thread.sleep(DefaultComLinkDevice.DELAY_SEND);
					} catch (InterruptedException e) {
						if (DefaultLinkDevice.VERBOSE_MODE) logger.severe(e.getMessage());
					}
				return data.length();
			} catch (SerialPortException e) {
				if (DefaultLinkDevice.VERBOSE_MODE) logger.severe(e.getMessage());
			}
		}
		return -1;
	}

	/**
	 * Read data from serial port
	 * @param bytes bytes number to read from buffer
	 * @return String data if success, null otherwise
	 * @throws SerialPortException
	 * */
	@Override
	public String read(int bytes) {
		String temp;
		if (isReady() && bufferAvailable()) 
		{
			try {
				temp=port.readString(bytes);
				return temp;
			} catch (SerialPortException e) {
				if (DefaultLinkDevice.VERBOSE_MODE) logger.severe(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * Read data from serial port
	 * @return String data if success, null otherwise
	 * @throws SerialPortException
	 * */
	@Override
	public String read() {
		String temp;
		if (this.isReady()  && bufferAvailable()) 
		{
			try {
				temp=port.readString();
				return temp;
			} catch (SerialPortException e) {
				if (DefaultLinkDevice.VERBOSE_MODE) logger.severe(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * Open a connection to serial port
	 * @return true if success, false otherwise
	 * @throws SerialPortException
	 * */
	@Override
	public boolean open() {
		try {
			boolean res=port.openPort();
			if (res){
				this.port.setParams(this.baudRate, DATABITS_8, STOPBITS_1, PARITY_NONE);
				return res;
			}
		} catch (SerialPortException e) {
			if (DefaultLinkDevice.VERBOSE_MODE) logger.severe(e.getMessage());
		}
		return false;
	}

	
	/**
	 * Close serial port connection
	 * @return true if success, false otherwise
	 * @throws SerialPortException
	 * */
	@Override
	public boolean close() {
		try {
			return port.closePort();
		} catch (SerialPortException e) {
			if (DefaultLinkDevice.VERBOSE_MODE) logger.severe(e.getMessage());
		}
		return false;
	}

	/**
	 * @return port device list
	 * */
	public static String[] getPorts() {
		return SerialPortList.getPortNames();
	}

	/**
	 * @return port device number
	 * */
	public static int getPortsNum() {
		return SerialPortList.getPortNames().length;
	}

	/**
	 * @return true if port ready to write/read, false otherwise
	 * */
	@Override
	public boolean isReady() {
		return port.isOpened();
	}

	@Override
	public boolean bufferAvailable() {
		try {
			return port.getInputBufferBytesCount()>0;
		} catch (SerialPortException e) {
			if (DefaultLinkDevice.VERBOSE_MODE) logger.severe(e.getMessage());
			return false;
		}
	}

	@Override
	public SerialPort getDevice(){	
		return port;
	}
	
	/**
	 * @return true if there is at least a COM port, false otherwise
	 * */
	public static boolean isPortAvailable()
	{
		return ComLinkDevice.getPortsNum()>0;
	}
	
	/**
	 * Find full path port by name
	 * @param matchName search name key (i.e. ttyUSB0)
	 * @return full path port (i.e. /dev/ttyUSB0) if port found,
	 * null otherwise
	 * */
	public static String getPortByName(String matchName)
	{
		int num=getPortsNum();
		for (int i=0;i<num;i++)
		{
			String tmp=(ComLinkDevice.getPorts()[i]);
			if (tmp.contains(matchName)){
				return tmp;
			}
		}
		return null;
	}

}
