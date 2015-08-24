import java.util.ArrayList;

class Huff {
	public byte size;
	public int code;

	public Huff(byte size, int code) {
		this.size = size;
		this.code = code;
	}

	public boolean equals(Huff x) {
		return this.size == x.size && this.code == x.code;
	}
}

public class Huffman {
	static final ArrayList<Huff> huff = new ArrayList<Huff>();

	static String gethuff(int num) {
		int b = huff.get(num).code;
		if (b == 1 || b == 0)
			return "0" + Integer.toBinaryString(b);
		else
			return Integer.toBinaryString(b);
	}

	static int getrehuff(String s) {
		Huff a = new Huff((byte) s.length(), Integer.valueOf(s, 16));
		for (int i = 0; i < huff.size(); i++)
			if (huff.get(i).equals(a))
				return i;
		return -1;
	}

	static int rehuff(String s, int index) {
		for (int i = 0; i < huff.size(); i++) {
			if (huff.get(i).code == index && huff.get(i).size == s.length())
				return i;
		}
		return -1;
	}

	static {
		huff.add(new Huff((byte) 4, 0x000a));/* 0x00 1010 */
		huff.add(new Huff((byte) 2, 0x0000));/* 0x01 00 */
		huff.add(new Huff((byte) 2, 0x0001)); /* 0x02: 01 */
		huff.add(new Huff((byte) 3, 0x0004)); /* 0x03: 100 */
		huff.add(new Huff((byte) 4, 0x000b)); /* 0x04: 1011 */
		huff.add(new Huff((byte) 5, 0x001a)); /* 0x05: 11010 */
		huff.add(new Huff((byte) 7, 0x0078)); /* 0x06: 1111000 */
		huff.add(new Huff((byte) 8, 0x00f8)); /* 0x07: 11111000 */
		huff.add(new Huff((byte) 10, 0x03f6)); /* 0x08:1111110110 */
		huff.add(new Huff((byte) 16, 0xff82)); /* 0x09: 1111111110000010 */
		huff.add(new Huff((byte) 16, 0xff83)); /* 0x0a: 1111111110000011 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x0b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x0c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x0d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x0e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x0f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x10: */
		huff.add(new Huff((byte) 4, 0x000c)); /* 0x11: 1100 */
		huff.add(new Huff((byte) 5, 0x001b)); /* 0x12: 11011 */
		huff.add(new Huff((byte) 7, 0x0079)); /* 0x13: 1111001 */
		huff.add(new Huff((byte) 9, 0x01f6)); /* 0x14: 111110110 */
		huff.add(new Huff((byte) 11, 0x07f6)); /* 0x15:11111110110 */
		huff.add(new Huff((byte) 16, 0xff84)); /* 0x16: 1111111110000100 */
		huff.add(new Huff((byte) 16, 0xff85)); /* 0x17: 1111111110000101 */
		huff.add(new Huff((byte) 16, 0xff86)); /* 0x18: 1111111110000110 */
		huff.add(new Huff((byte) 16, 0xff87)); /* 0x19: 1111111110000111 */
		huff.add(new Huff((byte) 16, 0xff88)); /* 0x1a: 1111111110001000 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x1b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x1c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x1d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x1e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x1f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x20: */
		huff.add(new Huff((byte) 5, 0x001c)); /* 0x21: 11100 */
		huff.add(new Huff((byte) 8, 0x00f9)); /* 0x22: 11111001 */
		huff.add(new Huff((byte) 10, 0x03f7)); /* 0x23:1111110111 */
		huff.add(new Huff((byte) 12, 0x0ff4)); /* 0x24: 111111110100 */
		huff.add(new Huff((byte) 16, 0xff89)); /* 0x25: 1111111110001001 */
		huff.add(new Huff((byte) 16, 0xff8a)); /* 0x26: 1111111110001010 */
		huff.add(new Huff((byte) 16, 0xff8b)); /* 0x27: 1111111110001011 */
		huff.add(new Huff((byte) 16, 0xff8c)); /* 0x28: 1111111110001100 */
		huff.add(new Huff((byte) 16, 0xff8d)); /* 0x29: 1111111110001101 */
		huff.add(new Huff((byte) 16, 0xff8e)); /* 0x2a: 1111111110001110 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x2b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x2c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x2d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x2e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x2f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x30: */
		huff.add(new Huff((byte) 6, 0x003a)); /* 0x31: 111010 */
		huff.add(new Huff((byte) 9, 0x01f7)); /* 0x32: 111110111 */
		huff.add(new Huff((byte) 12, 0x0ff5)); /* 0x33: 111111110101 */
		huff.add(new Huff((byte) 16, 0xff8f)); /* 0x34: 1111111110001111 */
		huff.add(new Huff((byte) 16, 0xff90)); /* 0x35: 1111111110010000 */
		huff.add(new Huff((byte) 16, 0xff91)); /* 0x36: 1111111110010001 */
		huff.add(new Huff((byte) 16, 0xff92)); /* 0x37: 1111111110010010 */
		huff.add(new Huff((byte) 16, 0xff93)); /* 0x38: 1111111110010011 */
		huff.add(new Huff((byte) 16, 0xff94)); /* 0x39: 1111111110010100 */
		huff.add(new Huff((byte) 16, 0xff95)); /* 0x3a: 1111111110010101 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x3b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x3c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x3d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x3e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x3f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x40: */
		huff.add(new Huff((byte) 6, 0x003b)); /* 0x41: 111011 */
		huff.add(new Huff((byte) 10, 0x03f8)); /* 0x42:1111111000 */
		huff.add(new Huff((byte) 16, 0xff96)); /* 0x43: 1111111110010110 */
		huff.add(new Huff((byte) 16, 0xff97)); /* 0x44: 1111111110010111 */
		huff.add(new Huff((byte) 16, 0xff98)); /* 0x45: 1111111110011000 */
		huff.add(new Huff((byte) 16, 0xff99)); /* 0x46: 1111111110011001 */
		huff.add(new Huff((byte) 16, 0xff9a)); /* 0x47: 1111111110011010 */
		huff.add(new Huff((byte) 16, 0xff9b)); /* 0x48: 1111111110011011 */
		huff.add(new Huff((byte) 16, 0xff9c)); /* 0x49: 1111111110011100 */
		huff.add(new Huff((byte) 16, 0xff9d)); /* 0x4a: 1111111110011101 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x4b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x4c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x4d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x4e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x4f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x50: */
		huff.add(new Huff((byte) 7, 0x007a)); /* 0x51: 1111010 */
		huff.add(new Huff((byte) 11, 0x07f7)); /* 0x52:11111110111 */
		huff.add(new Huff((byte) 16, 0xff9e)); /* 0x53: 1111111110011110 */
		huff.add(new Huff((byte) 16, 0xff9f)); /* 0x54: 1111111110011111 */
		huff.add(new Huff((byte) 16, 0xffa0)); /* 0x55: 1111111110100000 */
		huff.add(new Huff((byte) 16, 0xffa1)); /* 0x56: 1111111110100001 */
		huff.add(new Huff((byte) 16, 0xffa2)); /* 0x57: 1111111110100010 */
		huff.add(new Huff((byte) 16, 0xffa3)); /* 0x58: 1111111110100011 */
		huff.add(new Huff((byte) 16, 0xffa4)); /* 0x59: 1111111110100100 */
		huff.add(new Huff((byte) 16, 0xffa5)); /* 0x5a: 1111111110100101 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x5b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x5c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x5d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x5e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x5f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x60: */
		huff.add(new Huff((byte) 7, 0x007b)); /* 0x61: 1111011 */
		huff.add(new Huff((byte) 12, 0x0ff6)); /* 0x62: 111111110110 */
		huff.add(new Huff((byte) 16, 0xffa6)); /* 0x63: 1111111110100110 */
		huff.add(new Huff((byte) 16, 0xffa7)); /* 0x64: 1111111110100111 */
		huff.add(new Huff((byte) 16, 0xffa8)); /* 0x65: 1111111110101000 */
		huff.add(new Huff((byte) 16, 0xffa9)); /* 0x66: 1111111110101001 */
		huff.add(new Huff((byte) 16, 0xffaa)); /* 0x67: 1111111110101010 */
		huff.add(new Huff((byte) 16, 0xffab)); /* 0x68: 1111111110101011 */
		huff.add(new Huff((byte) 16, 0xffac)); /* 0x69: 1111111110101100 */
		huff.add(new Huff((byte) 16, 0xffad)); /* 0x6a: 1111111110101101 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x6b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x6c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x6d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x6e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x6f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x70: */
		huff.add(new Huff((byte) 8, 0x00fa)); /* 0x71: 11111010 */
		huff.add(new Huff((byte) 12, 0x0ff7)); /* 0x72: 111111110111 */
		huff.add(new Huff((byte) 16, 0xffae)); /* 0x73: 1111111110101110 */
		huff.add(new Huff((byte) 16, 0xffaf)); /* 0x74: 1111111110101111 */
		huff.add(new Huff((byte) 16, 0xffb0)); /* 0x75: 1111111110110000 */
		huff.add(new Huff((byte) 16, 0xffb1)); /* 0x76: 1111111110110001 */
		huff.add(new Huff((byte) 16, 0xffb2)); /* 0x77: 1111111110110010 */
		huff.add(new Huff((byte) 16, 0xffb3)); /* 0x78: 1111111110110011 */
		huff.add(new Huff((byte) 16, 0xffb4)); /* 0x79: 1111111110110100 */
		huff.add(new Huff((byte) 16, 0xffb5)); /* 0x7a: 1111111110110101 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x7b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x7c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x7d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x7e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x7f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x80: */
		huff.add(new Huff((byte) 9, 0x01f8)); /* 0x81: 111111000 */
		huff.add(new Huff((byte) 15, 0x7fc0)); /* 0x82: 111111111000000 */
		huff.add(new Huff((byte) 16, 0xffb6)); /* 0x83: 1111111110110110 */
		huff.add(new Huff((byte) 16, 0xffb7)); /* 0x84: 1111111110110111 */
		huff.add(new Huff((byte) 16, 0xffb8)); /* 0x85: 1111111110111000 */
		huff.add(new Huff((byte) 16, 0xffb9)); /* 0x86: 1111111110111001 */
		huff.add(new Huff((byte) 16, 0xffba)); /* 0x87: 1111111110111010 */
		huff.add(new Huff((byte) 16, 0xffbb)); /* 0x88: 1111111110111011 */
		huff.add(new Huff((byte) 16, 0xffbc)); /* 0x89: 1111111110111100 */
		huff.add(new Huff((byte) 16, 0xffbd)); /* 0x8a: 1111111110111101 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x8b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x8c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x8d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x8e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x8f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x90: */
		huff.add(new Huff((byte) 9, 0x01f9)); /* 0x91: 111111001 */
		huff.add(new Huff((byte) 16, 0xffbe)); /* 0x92: 1111111110111110 */
		huff.add(new Huff((byte) 16, 0xffbf)); /* 0x93: 1111111110111111 */
		huff.add(new Huff((byte) 16, 0xffc0)); /* 0x94: 1111111111000000 */
		huff.add(new Huff((byte) 16, 0xffc1)); /* 0x95: 1111111111000001 */
		huff.add(new Huff((byte) 16, 0xffc2)); /* 0x96: 1111111111000010 */
		huff.add(new Huff((byte) 16, 0xffc3)); /* 0x97: 1111111111000011 */
		huff.add(new Huff((byte) 16, 0xffc4)); /* 0x98: 1111111111000100 */
		huff.add(new Huff((byte) 16, 0xffc5)); /* 0x99: 1111111111000101 */
		huff.add(new Huff((byte) 16, 0xffc6)); /* 0x9a: 1111111111000110 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x9b: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x9c: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x9d: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x9e: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0x9f: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xa0: */
		huff.add(new Huff((byte) 9, 0x01fa)); /* 0xa1: 111111010 */
		huff.add(new Huff((byte) 16, 0xffc7)); /* 0xa2: 1111111111000111 */
		huff.add(new Huff((byte) 16, 0xffc8)); /* 0xa3: 1111111111001000 */
		huff.add(new Huff((byte) 16, 0xffc9)); /* 0xa4: 1111111111001001 */
		huff.add(new Huff((byte) 16, 0xffca)); /* 0xa5: 1111111111001010 */
		huff.add(new Huff((byte) 16, 0xffcb)); /* 0xa6: 1111111111001011 */
		huff.add(new Huff((byte) 16, 0xffcc)); /* 0xa7: 1111111111001100 */
		huff.add(new Huff((byte) 16, 0xffcd)); /* 0xa8: 1111111111001101 */
		huff.add(new Huff((byte) 16, 0xffce)); /* 0xa9: 1111111111001110 */
		huff.add(new Huff((byte) 16, 0xffcf)); /* 0xaa: 1111111111001111 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xab: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xac: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xad: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xae: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xaf: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xb0: */
		huff.add(new Huff((byte) 10, 0x03f9)); /* 0xb1:1111111001 */
		huff.add(new Huff((byte) 16, 0xffd0)); /* 0xb2: 1111111111010000 */
		huff.add(new Huff((byte) 16, 0xffd1)); /* 0xb3: 1111111111010001 */
		huff.add(new Huff((byte) 16, 0xffd2)); /* 0xb4: 1111111111010010 */
		huff.add(new Huff((byte) 16, 0xffd3)); /* 0xb5: 1111111111010011 */
		huff.add(new Huff((byte) 16, 0xffd4)); /* 0xb6: 1111111111010100 */
		huff.add(new Huff((byte) 16, 0xffd5)); /* 0xb7: 1111111111010101 */
		huff.add(new Huff((byte) 16, 0xffd6)); /* 0xb8: 1111111111010110 */
		huff.add(new Huff((byte) 16, 0xffd7)); /* 0xb9: 1111111111010111 */
		huff.add(new Huff((byte) 16, 0xffd8)); /* 0xba: 1111111111011000 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xbb: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xbc: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xbd: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xbe: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xbf: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xc0: */
		huff.add(new Huff((byte) 10, 0x03fa)); /* 0xc1:1111111010 */
		huff.add(new Huff((byte) 16, 0xffd9)); /* 0xc2: 1111111111011001 */
		huff.add(new Huff((byte) 16, 0xffda)); /* 0xc3: 1111111111011010 */
		huff.add(new Huff((byte) 16, 0xffdb)); /* 0xc4: 1111111111011011 */
		huff.add(new Huff((byte) 16, 0xffdc)); /* 0xc5: 1111111111011100 */
		huff.add(new Huff((byte) 16, 0xffdd)); /* 0xc6: 1111111111011101 */
		huff.add(new Huff((byte) 16, 0xffde)); /* 0xc7: 1111111111011110 */
		huff.add(new Huff((byte) 16, 0xffdf)); /* 0xc8: 1111111111011111 */
		huff.add(new Huff((byte) 16, 0xffe0)); /* 0xc9: 1111111111100000 */
		huff.add(new Huff((byte) 16, 0xffe1)); /* 0xca: 1111111111100001 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xcb: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xcc: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xcd: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xce: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xcf: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xd0: */
		huff.add(new Huff((byte) 11, 0x07f8)); /* 0xd1:11111111000 */
		huff.add(new Huff((byte) 16, 0xffe2)); /* 0xd2: 1111111111100010 */
		huff.add(new Huff((byte) 16, 0xffe3)); /* 0xd3: 1111111111100011 */
		huff.add(new Huff((byte) 16, 0xffe4)); /* 0xd4: 1111111111100100 */
		huff.add(new Huff((byte) 16, 0xffe5)); /* 0xd5: 1111111111100101 */
		huff.add(new Huff((byte) 16, 0xffe6)); /* 0xd6: 1111111111100110 */
		huff.add(new Huff((byte) 16, 0xffe7)); /* 0xd7: 1111111111100111 */
		huff.add(new Huff((byte) 16, 0xffe8)); /* 0xd8: 1111111111101000 */
		huff.add(new Huff((byte) 16, 0xffe9)); /* 0xd9: 1111111111101001 */
		huff.add(new Huff((byte) 16, 0xffea)); /* 0xda: 1111111111101010 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xdb: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xdc: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xdd: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xde: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xdf: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xe0: */
		huff.add(new Huff((byte) 16, 0xffeb)); /* 0xe1: 1111111111101011 */
		huff.add(new Huff((byte) 16, 0xffec)); /* 0xe2: 1111111111101100 */
		huff.add(new Huff((byte) 16, 0xffed)); /* 0xe3: 1111111111101101 */
		huff.add(new Huff((byte) 16, 0xffee)); /* 0xe4: 1111111111101110 */
		huff.add(new Huff((byte) 16, 0xffef)); /* 0xe5: 1111111111101111 */
		huff.add(new Huff((byte) 16, 0xfff0)); /* 0xe6: 1111111111110000 */
		huff.add(new Huff((byte) 16, 0xfff1)); /* 0xe7: 1111111111110001 */
		huff.add(new Huff((byte) 16, 0xfff2)); /* 0xe8: 1111111111110010 */
		huff.add(new Huff((byte) 16, 0xfff3)); /* 0xe9: 1111111111110011 */
		huff.add(new Huff((byte) 16, 0xfff4)); /* 0xea: 1111111111110100 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xeb: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xec: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xed: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xee: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xef: */
		huff.add(new Huff((byte) 11, 0x07f9)); /* 0xf0:11111111001 */
		huff.add(new Huff((byte) 16, 0xfff5)); /* 0xf1: 1111111111110101 */
		huff.add(new Huff((byte) 16, 0xfff6)); /* 0xf2: 1111111111110110 */
		huff.add(new Huff((byte) 16, 0xfff7)); /* 0xf3: 1111111111110111 */
		huff.add(new Huff((byte) 16, 0xfff8)); /* 0xf4: 1111111111111000 */
		huff.add(new Huff((byte) 16, 0xfff9)); /* 0xf5: 1111111111111001 */
		huff.add(new Huff((byte) 16, 0xfffa)); /* 0xf6: 1111111111111010 */
		huff.add(new Huff((byte) 16, 0xfffb)); /* 0xf7: 1111111111111011 */
		huff.add(new Huff((byte) 16, 0xfffc)); /* 0xf8: 1111111111111100 */
		huff.add(new Huff((byte) 16, 0xfffd)); /* 0xf9: 1111111111111101 */
		huff.add(new Huff((byte) 16, 0xfffe)); /* 0xfa: 1111111111111110 */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xfb: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xfc: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xfd: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xfe: */
		huff.add(new Huff((byte) 0, 0x0000)); /* 0xff: */
	}
}
