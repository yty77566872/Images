import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class MyMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JMenu jmFile = new JMenu("file");
	protected JMenu jmHelp = new JMenu("help");
	protected JMenuItem open = new JMenuItem("打开BMP图片");
	protected JMenuItem save = new JMenuItem("保存成JPG格式");
	protected JMenuItem load = new JMenuItem("打开压缩文件");
	protected JMenuItem exit = new JMenuItem("exit");

	public MyMenuBar() {
		jmFile.add(open);
		jmFile.add(save);
		jmFile.add(load);
		jmFile.addSeparator();
		jmFile.add(exit);
		add(jmFile);
		add(jmHelp);
	}
}

public class ReaderIDE extends JFrame {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static ReaderIDE frame = new ReaderIDE();
	protected MyMenuBar menuBar = new MyMenuBar();
	protected MyJPanel mypanel = new MyJPanel();

	public ReaderIDE() {
		// setLayout(new FlowLayout());
		setJMenuBar(menuBar);
		menuBar.open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser file = new JFileChooser();
				file.setDialogTitle("打开BMP图片");
				int result = file.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String filepath = file.getSelectedFile().getPath();
					mypanel.bt.read(filepath);
					frame.setTitle("显示BMP图片");
					showUI();
				}
			}
		});
		menuBar.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser file = new JFileChooser();
				file.setDialogTitle("保存成JPG文件");
				int result = file.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String filepath = file.getSelectedFile().getPath();
					frame.setTitle("正在保存JPG文件中...");
					try {
						mypanel.bt.ToJPG(filepath);
						frame.setTitle("保存JPG成功");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		menuBar.load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser file = new JFileChooser();
				file.setDialogTitle("打开JPG文件");
				int result = file.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String filepath = file.getSelectedFile().getPath();
					frame.setTitle("正在打开JPG文件...");
					try {
						mypanel.bt = Img_jpg.load(filepath).toBmp();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					frame.setTitle("打开成功，正在解码...");
					try {
						mypanel.bt.ToBMP();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.setTitle("解码成功显示BMP图片");
					showUI();
				}
			}
		});
		menuBar.exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		add(mypanel);
	}

	public static void main(String[] args) {
		frame.setTitle("BMP");
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void showUI() {
		this.setSize(mypanel.bt.image_width, mypanel.bt.image_heigh + 100);
		repaint(); // 重绘
	}
}

class MyJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Img_bmp bt = new Img_bmp();

	public void paint(Graphics g) {
		if (bt != null) {
			for (int h = 0; h < bt.image_heigh; h++) {
				for (int w = 0; w < bt.image_width; w++) {
					g.setColor(new Color((int) bt.imageR[h][w],
							(int) bt.imageG[h][w], (int) bt.imageB[h][w]));// 像素点RGB值
					g.drawLine(w, h, w, h); // 画图
				}
			}
		}
		setSize(bt.image_width, bt.image_heigh);
	}

}