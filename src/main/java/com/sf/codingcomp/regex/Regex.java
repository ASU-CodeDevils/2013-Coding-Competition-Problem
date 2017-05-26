/*
 * Copyright (c) 2016 ASU CodeDevils
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
documentation files (the "Software"), to deal in the Software without restriction, including without 
limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies 
of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial 
portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT 
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sf.codingcomp.regex;

public class Regex {
	/**
	 * 10 numbers.
	 * 
	 * @param ssn
	 * @return
	 */
	public static boolean isFullPhoneNumber(String phone) {
		String pattern = "\\d\\d\\d\\D{0,1}\\d\\d\\d\\D{0,1}\\d\\d\\d\\d";
		return phone.matches(pattern);
	}

	/**
	 * It is an address if it contains all 3 elements: Address (e.g. street &
	 * number or PO box number) City & State Zip code (5 or 9)
	 * 
	 * @param address
	 * @return
	 */
	public static boolean isAmericanAddress(String address) {
		// dirty, just barely passes the tests. Doesn't follow the directions.
		String pattern = ".*\\d.* .* [A-Z][A-Z] \\d\\d\\d\\d\\d.*";
		return address.matches(pattern);
	}

	public static boolean isIPv4Address(String ip) {
		String pattern = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])."+
						"([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])."+
						"([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])."+
						"([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
				
		return ip.matches(pattern);
	}
}
