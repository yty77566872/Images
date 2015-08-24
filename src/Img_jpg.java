import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Img_jpg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	byte[] Y, U, V;
	int height, width;
	int skipY, skipU, skipV;

	public Img_bmp toBmp() {
		Img_bmp a = new Img_bmp(height, width);
		a.lastY = this.Y;
		a.lastU = this.U;
		a.lastV = this.V;
		a.skip_y = skipY;
		a.skip_u = skipU;
		a.skip_v = skipV;
		return a;
	}

	public void save(String filepath) throws IOException {
		System.out.println("save file: ------");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				filepath));
		oos.writeObject(this);
		oos.close();
	}

	static public Img_jpg load(String filepath) throws Exception {
		System.out.println("load file: -------");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				filepath));
		Img_jpg img = (Img_jpg) ois.readObject();
		ois.close();
		System.out.println("loaded file:--------");
		return img;
	}
}
