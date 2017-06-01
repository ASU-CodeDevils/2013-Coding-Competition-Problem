package com.sf.codingcomp.subnetutilities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class SubnetImpl implements Subnet {
	
	private InetAddress networkAddress;
	private int netMask;
	private InetAddress broadcastAddress;
	
	public SubnetImpl(InetAddress subnet, int mask) throws InvalidMaskException, UnknownHostException {
		// TODO Auto-generated method stub
		System.out.println("Made it!");
		if ((mask > 32) || (mask <= 0)) { 
			throw new InvalidMaskException();
		}
		this.networkAddress = calcNetworkAddress(subnet, mask);
		this.broadcastAddress = calcBroadcastAddress(networkAddress, mask);
		this.netMask = mask;
		

	}
	
	public boolean isIPInRange(InetAddress ipAddress) {
		// TODO Auto-generated method stub
		boolean result = true;
		
		return result;
	}
	
	private InetAddress calcNetworkAddress(InetAddress subnet, int mask) throws UnknownHostException { 
		int finalOctet;
		
		if (mask == 32) { 
			finalOctet = 4;
			return subnet;
		}
		else { 
			finalOctet = (mask/8) + 1;
		}
		int finalOctetBits = mask % 8;
		int finalOctetValue = 0;
		int exponent = 7;
		
		for (int i = finalOctetBits; i > 0; i--) { 
			finalOctetValue+= (int)Math.pow(2, exponent);
			exponent--;
		}
		int numAddressPerSubnet = 256 - finalOctetValue;
		int netAddressOctet = 0;
		
		while (subnet.getAddress()[finalOctet - 1] > netAddressOctet ) { 
			netAddressOctet+=numAddressPerSubnet;
		}
		netAddressOctet-= numAddressPerSubnet;
		
		byte[] netAddress = new byte[4];
		
		for (int i = 1; i < finalOctet; i++) { 
			netAddress[i-1] = subnet.getAddress()[i-1];
		}
		netAddress[finalOctet - 1] = (byte) netAddressOctet;
		
		for (int i = 4; i > finalOctet; i--) { 
			netAddress[i - 1] = 0;
		}
		
		InetAddress networkAddress = InetAddress.getByAddress(netAddress);
		
		return networkAddress;
	}

	public InetAddress getNetworkAddress() {
		// TODO Auto-generated method stub
		
		return networkAddress;
	}
	
	private InetAddress calcBroadcastAddress(InetAddress netAddress, int mask) throws UnknownHostException { 
		int finalOctet;
		
		if (mask == 32) { 
			finalOctet = 4;
			return netAddress;
		}
		else { 
			finalOctet = (mask/8) + 1;
		}
		int finalOctetBits = mask % 8;
		int finalOctetValue = 0;
		int exponent = 7;
		for (int i = finalOctetBits; i > 0; i--) { 
			finalOctetValue+= (int)Math.pow(2, exponent);
			exponent--;
		}
		int numAddressPerSubnet = 256 - finalOctetValue;
		int broadAddressOctet = 0;
		
		broadAddressOctet = netAddress.getAddress()[finalOctet - 1] + numAddressPerSubnet - 1;
		
		byte[] broadAddress = new byte[4];
		
		for (int i = 1; i < finalOctet; i++) { 
			broadAddress[i - 1] = netAddress.getAddress()[i - 1 ];
		}
		
		broadAddress[finalOctet - 1] = (byte) broadAddressOctet;
		
		for (int i = 4; i > finalOctet; i--) { 
			broadAddress[i - 1] = (byte) 255;
		}
		
		InetAddress broadcastAddress = InetAddress.getByAddress(broadAddress);
		
		return broadcastAddress;
	}

	public InetAddress getBroadcastAddress() {
		// TODO Auto-generated method stub
		return broadcastAddress;
	}

	public List<InetAddress> getAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getAddressCount() {
		// TODO Auto-generated method stub
		return 0;
	}	
}
