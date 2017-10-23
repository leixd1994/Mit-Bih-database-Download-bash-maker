import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        final String storeLocation="/media/leixd/DevelopMentingCo/ECGMeachine/QTDataBase/";
        Frame frame=new Frame("自制Mit-Bih数据库批量下载脚本生成器");
        TextField textField=new TextField(100);
        frame.add(textField);
        frame.add(new Label("数据库网页网址:"),BorderLayout.WEST);


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        Button startButton=new Button("开始");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog2=new FileDialog(frame,"选择MD5文件");

                fileDialog2.setVisible(true);

                File md5File=new File(fileDialog2.getDirectory()+fileDialog2.getFile());
                File shFile=new File(storeLocation+"DownloadSh.sh");

                try {
                    FileWriter fileWriter=new FileWriter(shFile);
                    Scanner scanner=new Scanner(md5File);
                    while (scanner.hasNext())
                    {
                        scanner.next();
                        fileWriter.write("wget "+textField.getText()+scanner.next()+"\n");
                    }
                    fileWriter.close();
                    JOptionPane.showMessageDialog(frame,"成功生成下载脚本");
                } catch (FileNotFoundException e3) {
                    e3.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });

        frame.add(startButton,BorderLayout.EAST);
        frame.setVisible(true);
        frame.pack();

    }
}
