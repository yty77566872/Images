import java.util.ArrayList;

class Matrix {
	final int MaxLenth = 8;
	final int DCcoeffient = 1024;
	ArrayList<Integer> zigzag = new ArrayList<Integer>();
	private double f[][] = new double[MaxLenth][MaxLenth];
	private double F[][] = new double[MaxLenth][MaxLenth]; // after dct change;
	private double new_f[][] = new double[MaxLenth][MaxLenth]; // after idct
																// change;
	private double quantiza[][];

	double C(int x) {
		return x == 0 ? Math.sqrt(2) / 2 : 1;
	}

	double getNew(int x, int y) {
		return (new_f[x][y]);
	}

	void initMatrix(int x, int y, double date) {
		f[x][y] = date;
	}

	void initquantizaY() {
		quantiza = new double[][] { { 16, 11, 10, 16, 24, 40, 51, 61, },
				{ 12, 12, 14, 19, 26, 58, 60, 55, },
				{ 14, 13, 16, 24, 40, 57, 69, 56, },
				{ 14, 17, 22, 29, 51, 87, 80, 62, },
				{ 18, 22, 37, 56, 68, 109, 103, 77, },
				{ 24, 35, 55, 64, 81, 104, 113, 92, },
				{ 49, 64, 78, 87, 103, 121, 120, 101, },
				{ 72, 92, 95, 98, 112, 100, 103, 99, } };
	}

	void initquantizaUV() {
		quantiza = new double[][] { { 17, 18, 24, 47, 99, 99, 99, 99, },
				{ 18, 21, 26, 66, 99, 99, 99, 99, },
				{ 24, 26, 66, 99, 99, 99, 99, 99, },
				{ 47, 66, 99, 99, 99, 99, 99, 99, },
				{ 99, 99, 99, 99, 99, 99, 99, 99, },
				{ 99, 99, 99, 99, 99, 99, 99, 99, },
				{ 99, 99, 99, 99, 99, 99, 99, 99, },
				{ 99, 99, 99, 99, 99, 99, 99, 99, } };
	}

	void Dct() {
		// f --> F;
		for (int i = 0; i < MaxLenth; i++)
			for (int j = 0; j < MaxLenth; j++)
				for (int u = 0; u < MaxLenth; u++)
					for (int v = 0; v < MaxLenth; v++)
						F[u][v] += C(u) * C(v) / 4
								* Math.cos((2 * i + 1) * u * Math.PI / 16)
								* Math.cos((2 * j + 1) * v * Math.PI / 16)
								* f[i][j];
		F[0][0] -= DCcoeffient;
	}

	void quantization() {
		// F-->newF
		for (int i = 0; i < MaxLenth; i++)
			for (int j = 0; j < MaxLenth; j++)
				F[i][j] = Math.round(F[i][j] / quantiza[i][j]);
	}

	void requantization() {
		// F-->newF
		for (int i = 0; i < MaxLenth; i++)
			for (int j = 0; j < MaxLenth; j++)
				F[i][j] *= quantiza[i][j];
	}

	void iDct() {
		// F'--> f'
		for (int i = 0; i < MaxLenth; i++)
			for (int j = 0; j < MaxLenth; j++)
				for (int u = 0; u < MaxLenth; u++)
					for (int v = 0; v < MaxLenth; v++)
						new_f[i][j] += C(u)
								* C(v)
								/ 4
								* Math.cos((2 * i + 1) * u * Math.PI / 16)
								* Math.cos((2 * j + 1) * v * Math.PI / 16)
								* (F[u][v] + (u == 0 && v == 0 ? DCcoeffient
										: 0));
	}

	void getZigzag() {
		for (int cross = 0; cross < MaxLenth; cross++) {
			if (cross % 2 == 0) {
				for (int row = cross; row >= 0; row--) {
					zigzag.add((int) F[row][cross - row]);
				}
			} else {
				for (int col = cross; col >= 0; col--) {
					zigzag.add((int) F[cross - col][col]);
				}
			}
		}
		int limit = 1;
		for (int cross = MaxLenth - 2; cross >= 0; cross--) {
			if (cross % 2 == 0) {
				for (int row = MaxLenth - 1; row >= limit; row--) {
					zigzag.add((int) F[row][limit + MaxLenth - 1 - row]);
				}
				limit++;
			} else {
				for (int col = MaxLenth - 1; col >= limit; col--) {
					zigzag.add((int) F[limit + MaxLenth - 1 - col][col]);
				}
				limit++;
			}
		}
	}

	void igetZigzag() {
		int count = 0;
		for (int cross = 0; cross < MaxLenth; cross++) {
			if (cross % 2 == 0) {
				for (int row = cross; row >= 0; row--) {
					F[row][cross - row] = zigzag.get(count);
					count++;
				}
			} else {
				for (int col = cross; col >= 0; col--) {
					F[cross - col][col] = zigzag.get(count);
					count++;
				}
			}
		}
		int limit = 1;
		for (int cross = MaxLenth - 2; cross >= 0; cross--) {
			if (cross % 2 == 0) {
				for (int row = MaxLenth - 1; row >= limit; row--) {
					F[row][limit + MaxLenth - 1 - row] = zigzag.get(count);
					count++;
				}
				limit++;
			} else {
				for (int col = MaxLenth - 1; col >= limit; col--) {
					F[limit + MaxLenth - 1 - col][col] = zigzag.get(count);
					count++;
				}
				limit++;
			}
		}
	}
}