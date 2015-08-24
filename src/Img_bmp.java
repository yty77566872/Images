import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Img_bmp {
	/**
	 * 
	 */
	private static final int EOB = 234325;
	private Matrix[][] MatrixListY, MatrixListU, MatrixListV;
	int image_width;
	int image_heigh;
	private int image_buffwidth;
	private int image_buffheigh;
	private ArrayList<ArrayList<Integer>> rleY, rleU, rleV;
	Graphics g;
	int blue, green, red;
	int skip_width;
	int skip_y, skip_v, skip_u;
	String sbitY = "", sbitU = "", sbitV = "";
	double[][] imageR, imageG, imageB;
	double[][] imageY, imageU, imageV;
	byte[] lastY, lastU, lastV;

	public Img_jpg toMyjpg(int y, int u, int v) {
		Img_jpg a = new Img_jpg();
		a.height = this.image_heigh;
		a.width = this.image_width;
		a.Y = this.lastY;
		a.U = this.lastU;
		a.V = this.lastV;
		a.skipY = y;
		a.skipU = u;
		a.skipV = v;
		return a;
	}

	public Img_bmp() {

	}

	public Img_bmp(int h, int w) {
		image_heigh = h;
		image_width = w;
		if (image_width % 8 == 0)
			image_buffwidth = image_width;
		else
			image_buffwidth = image_width + 8 - image_width % 8;
		if (image_heigh % 8 == 0)
			image_buffheigh = image_heigh;
		else
			image_buffheigh = image_heigh + 8 - image_heigh % 8;
	}

	public void ToJPG(String filepath) throws IOException {
		RGBtoYUV();
		SplitMartix();
		QuantizationYUV();
		zigzag();
		RLE();
		BIT();
		Img_jpg jpg = export();
		jpg.save(filepath);
	}

	public void ToBMP() throws Exception {
		Iexport();
		IBIT();
		IRLE();
		reQuantizationYUV();
		ReturMartix();
		YUVtoRGB();
	}

	public void reQuantizationYUV() {
		for (int i = 0; i < image_buffheigh / 8; i++)
			for (int j = 0; j < image_buffwidth / 8; j++) {
				MatrixListY[i][j].initquantizaY();
				MatrixListY[i][j].requantization();
				MatrixListY[i][j].iDct();
				MatrixListU[i][j].initquantizaUV();
				MatrixListU[i][j].requantization();
				MatrixListU[i][j].iDct();
				MatrixListV[i][j].initquantizaUV();
				MatrixListV[i][j].requantization();
				MatrixListV[i][j].iDct();
			}
	}

	private void IRLE() {
		MatrixListY = new Matrix[image_buffheigh / 8][image_buffwidth / 8];
		MatrixListU = new Matrix[image_buffheigh / 8][image_buffwidth / 8];
		MatrixListV = new Matrix[image_buffheigh / 8][image_buffwidth / 8];
		for (int i = 0; i < image_buffheigh / 8; i++)
			for (int j = 0; j < image_buffwidth / 8; j++) {
				if (MatrixListY[i][j] == null)
					MatrixListY[i][j] = new Matrix();
				if (MatrixListU[i][j] == null)
					MatrixListU[i][j] = new Matrix();
				if (MatrixListV[i][j] == null)
					MatrixListV[i][j] = new Matrix();
				MatrixListY[i][j].zigzag = IRLE(rleY, i * (image_buffwidth / 8)
						+ j);
				MatrixListU[i][j].zigzag = IRLE(rleU, i * (image_buffwidth / 8)
						+ j);
				MatrixListV[i][j].zigzag = IRLE(rleV, i * (image_buffwidth / 8)
						+ j);
				MatrixListY[i][j].igetZigzag();
				MatrixListU[i][j].igetZigzag();
				MatrixListV[i][j].igetZigzag();
			}
	}

	private ArrayList<Integer> IRLE(ArrayList<ArrayList<Integer>> a, int x) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ArrayList<Integer> tmp = a.get(x);
		for (int i = 0; i < tmp.size(); i++) {
			int k = tmp.get(i);
			if (k == EOB) {
				int last = ans.size();
				for (int j = 0; j < 64 - last; j++)
					ans.add(0);
			} else {
				if (i % 2 == 1)
					ans.add(k);
				else
					for (int j = 0; j < k; j++)
						ans.add(0);
			}
		}
		return ans;
	}

	private void IBIT() {
		rleY = IBIT(sbitY);
		rleU = IBIT(sbitU);
		rleV = IBIT(sbitV);
	}

	private ArrayList<ArrayList<Integer>> IBIT(String sbit) {
		String tmp = "";
		ArrayList<ArrayList<Integer>> rle = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < sbit.length(); i++) {
			tmp = tmp + sbit.charAt(i);
			int index = Integer.valueOf(tmp, 2);
			int value = Huffman.rehuff(tmp, index);
			if (value >= 0) {
				if (tmp.equals("1010")) {
					a.add(EOB);
					rle.add(a);
					tmp = "";
					a = new ArrayList<Integer>();
				} else {
					tmp = "";
					int high = value / 16;
					int low = value % 16;
					String xx = sbit.substring(i + 1, i + low + 1);
					i += low;
					a.add(high);
					if (high != 15) {
						if (xx.charAt(0) == '1')
							a.add(Integer.valueOf(xx, 2));
						else
							a.add(Integer.valueOf(xx, 2)
									- ((1 << xx.length()) - 1));
					} else {
						if (xx.equals(""))
							a.add(0);
						else if (xx.charAt(0) == '1')
							a.add(Integer.valueOf(xx, 2));
						else
							a.add(Integer.valueOf(xx, 2)
									- ((1 << xx.length()) - 1));
					}
				}
			}
		}
		return rle;
	}

	private void Iexport() {
		sbitY = Iexport(lastY, skip_y);
		sbitU = Iexport(lastU, skip_u);
		sbitV = Iexport(lastV, skip_v);
	}

	private String Iexport(byte[] last, int skip) {
		int i;
		String ans = "";
		for (i = 0; i < last.length - 1; i++) {
			String pos = Integer.toBinaryString(last[i]);
			if (last[i] < 0)
				ans = ans + pos.substring(24);
			else {
				String skipzero = "";
				for (int k = 0; k < 8 - pos.length(); k++)
					skipzero += "0";
				ans = ans + skipzero + pos;
			}
		}
		String pos = Integer.toBinaryString((last[i]));
		if (last[i] < 0)
			ans = ans + pos.substring(24, 32 - skip);
		else {
			String skipzero = "";
			for (int k = 0; k < 8 - pos.length(); k++)
				skipzero += "0";
			String temp = skipzero + pos;
			ans = ans + temp.substring(0, 8 - skip);

		}
		return ans;

	}

	private void BIT() {
		sbitY = BIT(rleY);
		sbitU = BIT(rleU);
		sbitV = BIT(rleV);
	}

	private Img_jpg export() {
		int y, u, v;
		lastY = new byte[(int) Math.ceil(sbitY.length() / 8.0)];
		lastU = new byte[(int) Math.ceil(sbitU.length() / 8.0)];
		lastV = new byte[(int) Math.ceil(sbitV.length() / 8.0)];
		y = export(sbitY, lastY);
		u = export(sbitU, lastU);
		v = export(sbitV, lastV);
		return toMyjpg(y, u, v);
	}

	private int export(String sbitAll, byte[] myjpg) {
		int count = 0;
		int tmp = 0;
		for (int i = 0; i < sbitAll.length(); i++) {
			if (i % 8 == 7) {
				if (sbitAll.charAt(i) == '1')
					tmp = (tmp << 1) + 1;
				else
					tmp = tmp << 1;
				myjpg[count++] = (byte) tmp;
				tmp = 0;
			} else {
				if (sbitAll.charAt(i) == '1')
					tmp = (tmp << 1) + 1;
				else
					tmp = tmp << 1;
			}
		}
		if (sbitAll.length() % 8 != 0)
			myjpg[count++] = (byte) (tmp << (8 - sbitAll.length() % 8));
		if (sbitAll.length() % 8 != 0)
			return 8 - sbitAll.length() % 8;
		else
			return 0;
	}

	private static String getbinay(int x) {
		String ans = "";
		if (x == 0)
			ans = "";
		if (x > 0) {
			ans = Integer.toBinaryString(x);
		}
		if (x < 0) {
			String temp = Integer.toBinaryString(-x);
			for (int i = 0; i < temp.length(); i++)
				if (temp.charAt(i) == '1')
					ans += "0";
				else
					ans += "1";
		}
		return ans;
	}

	private String BIT(ArrayList<ArrayList<Integer>> rle) {
		String ans = new String();
		for (int i = 0; i < rle.size(); i++) {
			ArrayList<Integer> a = rle.get(i);
			for (int j = 0; j < a.size() - 1; j += 2) {
				String temp = "";
				temp = getbinay(a.get(j + 1));
				int high = a.get(j);
				int low = temp.length();
				int num = high * 16 + low;
				temp = Huffman.gethuff(num) + temp;
				ans = ans + temp;
			}
			ans = ans + Huffman.gethuff(0);
		}
		return ans;
	}

	private void RLE() {
		rleY = new ArrayList<ArrayList<Integer>>();
		rleU = new ArrayList<ArrayList<Integer>>();
		rleV = new ArrayList<ArrayList<Integer>>();
		RLE(MatrixListY, rleY);
		RLE(MatrixListU, rleU);
		RLE(MatrixListV, rleV);
	}

	private void RLE(Matrix[][] Mlist, ArrayList<ArrayList<Integer>> rle) {
		int count;
		for (int i = 0; i < image_buffheigh / 8; i++)
			for (int j = 0; j < image_buffwidth / 8; j++) {
				count = 0;
				Matrix rel_i = Mlist[i][j];
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for (int k = 0; k < rel_i.zigzag.size(); k++) {
					if (rel_i.zigzag.get(k) != 0) {
						while (count >= 16) {
							temp.add(15);
							temp.add(0);
							count -= 16;
						}
						temp.add(count);
						temp.add(rel_i.zigzag.get(k));
						count = 0;
					} else
						count++;
				}
				temp.add(EOB);
				rle.add(temp);
			}
	}

	private void zigzag() {
		for (int i = 0; i < image_buffheigh / 8; i++)
			for (int j = 0; j < image_buffwidth / 8; j++) {
				MatrixListY[i][j].getZigzag();
				MatrixListU[i][j].getZigzag();
				MatrixListV[i][j].getZigzag();
			}
	}

	public void read(String path) {
		try {

			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bis = new BufferedInputStream(fis);

			int bflen = 14;
			byte[] bf = new byte[bflen];
			bis.read(bf, 0, bflen);
			int bilen = 40;
			byte[] bi = new byte[bilen];
			bis.read(bi, 0, bilen);
			image_width = ChangeInt(bi, 7);
			image_heigh = ChangeInt(bi, 11);
			if (image_width % 8 == 0)
				image_buffwidth = image_width;
			else
				image_buffwidth = image_width + 8 - image_width % 8;
			if (image_heigh % 8 == 0)
				image_buffheigh = image_heigh;
			else
				image_buffheigh = image_heigh + 8 - image_heigh % 8;
			showRGB24(bis);
			fis.close();
			bis.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ReturMartix() {
		imageY = new double[image_buffheigh][image_buffwidth];
		imageU = new double[image_buffheigh][image_buffwidth];
		imageV = new double[image_buffheigh][image_buffwidth];
		for (int i = 0; i < image_buffheigh; i++)
			for (int j = 0; j < image_buffwidth; j++) {
				imageY[i][j] = MatrixListY[i / 8][j / 8].getNew(i % 8, j % 8);
				imageU[i][j] = MatrixListU[i / 8][j / 8].getNew(i % 8, j % 8);
				imageV[i][j] = MatrixListV[i / 8][j / 8].getNew(i % 8, j % 8);
			}
	}

	public void QuantizationYUV() {
		for (int i = 0; i < image_buffheigh / 8; i++)
			for (int j = 0; j < image_buffwidth / 8; j++) {
				MatrixListY[i][j].initquantizaY();
				MatrixListY[i][j].Dct();
				MatrixListY[i][j].quantization();
				MatrixListU[i][j].initquantizaUV();
				MatrixListU[i][j].Dct();
				MatrixListU[i][j].quantization();
				MatrixListV[i][j].initquantizaUV();
				MatrixListV[i][j].Dct();
				MatrixListV[i][j].quantization();
			}
	}

	public void SplitMartix() {
		MatrixListY = new Matrix[image_buffheigh / 8][image_buffwidth / 8];
		MatrixListU = new Matrix[image_buffheigh / 8][image_buffwidth / 8];
		MatrixListV = new Matrix[image_buffheigh / 8][image_buffwidth / 8];
		for (int i = 0; i < image_buffheigh; i++)
			for (int j = 0; j < image_buffwidth; j++) {
				if (MatrixListY[i / 8][j / 8] == null)
					MatrixListY[i / 8][j / 8] = new Matrix();
				MatrixListY[i / 8][j / 8]
						.initMatrix(i % 8, j % 8, imageY[i][j]);
				if (MatrixListU[i / 8][j / 8] == null)
					MatrixListU[i / 8][j / 8] = new Matrix();
				MatrixListU[i / 8][j / 8]
						.initMatrix(i % 8, j % 8, imageU[i][j]);
				if (MatrixListV[i / 8][j / 8] == null)
					MatrixListV[i / 8][j / 8] = new Matrix();
				MatrixListV[i / 8][j / 8]
						.initMatrix(i % 8, j % 8, imageV[i][j]);
			}
	}

	public int ChangeInt(byte[] bi, int start) {
		return (((int) bi[start] & 0xff) << 24)
				| (((int) bi[start - 1] & 0xff) << 16)
				| (((int) bi[start - 2] & 0xff) << 8) | (int) bi[start - 3]
				& 0xff;

	}

	public void showRGB24(BufferedInputStream dis) throws IOException {
		if (!(image_width * 3 % 4 == 0)) {
			skip_width = 4 - image_width * 3 % 4;
		}
		imageR = new double[image_buffheigh][image_buffwidth];
		imageG = new double[image_buffheigh][image_buffwidth];
		imageB = new double[image_buffheigh][image_buffwidth];
		for (int h = image_heigh - 1; h >= 0; h--) {
			for (int w = 0; w < image_width; w++) {
				blue = dis.read();
				green = dis.read();
				red = dis.read(); 
				imageR[h][w] = red;
				imageG[h][w] = green;
				imageB[h][w] = blue;

				if (w == image_width - 1) {
					dis.skip(skip_width);
				}
			}
		}
	}

	public void RGBtoYUV() {
		double r, g, b;
		imageY = new double[image_buffheigh][image_buffwidth];
		imageU = new double[image_buffheigh][image_buffwidth];
		imageV = new double[image_buffheigh][image_buffwidth];
		for (int i = 0; i < image_buffheigh; i++)
			for (int j = 0; j < image_buffwidth; j++) {
				r = imageR[i][j];
				g = imageG[i][j];
				b = imageB[i][j];
				imageY[i][j] = 0.299 * r + 0.587 * g + 0.114 * b;
				imageU[i][j] = -0.1687 * r - 0.3313 * g + 0.5 * b;
				imageV[i][j] = 0.5 * r - 0.4178 * g - 0.0813 * b;
			}
	}

	public void YUVtoRGB() {
		imageR = new double[image_buffheigh][image_buffwidth];
		imageG = new double[image_buffheigh][image_buffwidth];
		imageB = new double[image_buffheigh][image_buffwidth];
		double y, u, v;
		for (int i = 0; i < image_buffheigh; i++)
			for (int j = 0; j < image_buffwidth; j++) {
				y = imageY[i][j];
				u = imageU[i][j];
				v = imageV[i][j];
				imageR[i][j] = y + 1.4075 * v;
				imageG[i][j] = y - 0.34414 * u - 0.71414 * v;
				imageB[i][j] = y + 1.772 * u;
				if (imageR[i][j] < 0)
					imageR[i][j] = 0;
				else if (imageR[i][j] > 255)
					imageR[i][j] = 255;
				if (imageG[i][j] < 0)
					imageG[i][j] = 0;
				else if (imageG[i][j] > 255)
					imageG[i][j] = 255;
				if (imageB[i][j] < 0)
					imageB[i][j] = 0;
				else if (imageB[i][j] > 255)
					imageB[i][j] = 255;
			}

	}
}
