
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Zakhaev
 */
public class vc {

    Timer timer;
    JFrame frame;
    JPanel mainpanel, panel1, panel2;
    String s1;
    int a = 0, temp = 0;
    int chek[][] = new int[100][100];
    JLabel[][] grid = new JLabel[5][5];
    //milliseconds
    int x = 0, y = 0, px = 0, py = 0, cnt = 0;
    int delay = 1000;
    JLabel label3, label4, label5, sl2, label6;
    ImageIcon imageicon1 = new ImageIcon("tiles.jpg");
    ImageIcon imageicon2 = new ImageIcon("dirt.jpg");
    ImageIcon imageicon3 = new ImageIcon("vcc.jpg");
    // ImageIcon imageicon1 = new ImageIcon("tiles.jpg");

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public vc() {
        frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainpanel = new JPanel();
        mainpanel.setBackground(Color.GRAY);

        frame.add(mainpanel);
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(550, 600));
        panel1.setBackground(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new JLabel(imageicon1);
                grid[i][j].setBackground(Color.green);
                panel1.add(grid[i][j]);

            }
        }
        mainpanel.add(panel1);
        panel2 = new JPanel();
        panel2.setBackground(new Color(148, 184, 184));
        panel2.setPreferredSize(new Dimension(400, 600));
        mainpanel.add(panel2);
        GridBagLayout l = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();

        JLabel label1 = new JLabel("  Vaccum Cleaner");
        label1.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        label1.setPreferredSize(new Dimension(250, 30));
        panel2.add(label1);
        gbc.gridx = 0;
        gbc.gridy = 10;
        JLabel sl1 = new JLabel();
        sl1.setPreferredSize(new Dimension(250, 30));
        panel2.add(sl1, gbc);
        JLabel label2 = new JLabel("Number of Dirty Tiles");
        gbc.gridx = 0;
        gbc.gridy = 1;
        label2.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        label2.setPreferredSize(new Dimension(250, 30));
        panel2.add(label2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        final JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(250, 30));
        panel2.add(tf, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        JButton button1 = new JButton("Submit");
        button1.setBackground(Color.WHITE);
        button1.setPreferredSize(new Dimension(200, 30));
        panel2.add(button1, gbc);
        try {

            button1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    s1 = tf.getText();

                    if (s1 != null && !s1.isEmpty()) {
                        boolean boo = isNumeric(s1);
                        if (boo == true) {
                            a = Integer.parseInt(s1);
                            System.out.println("" + a);

                            if (a < 1 || a > 25) {
                                tf.setText(null);
                                a = 0;
                                JOptionPane.showMessageDialog(null, "Please input a number between 1 and 25");
                            } else {

                                for (int o = 0; temp != a; o++) {

                                    int m = getRandomNumberInRange(0, 4);
                                    int n = getRandomNumberInRange(0, 4);

                                    if (chek[m][n] != 1) {
                                        chek[m][n] = 1;
                                        temp++;
                                        grid[m][n].setIcon(null);
                                        grid[m][n].revalidate();
                                        grid[m][n].setIcon(imageicon2);
                                    }

                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please take only Number");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Please Press Submit Button after taken input");
                    }

                }
            });

        } catch (Exception e) {
        }

        gbc.gridx = 0;
        gbc.gridy = 7;
        JButton button2 = new JButton("Clear");
        button2.setBackground(Color.WHITE);
        button2.setPreferredSize(new Dimension(200, 30));
        panel2.add(button2, gbc);
        try {

            button2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (a > 0) {

                        cnt = -1;
                        x = -1;
                        y = 0;

                        ActionListener a = new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                if (cnt == 25) {
                                    timer.stop();
                                }

                                if (y == 0 || y == 2 || y == 4) {
                                    x++;
                                    if (x == 0) {
                                        if (y > 1) {
                                            grid[px][py].setIcon(null);
                                            grid[px][py].revalidate();
                                            grid[px][py].setIcon(imageicon1);
                                        }
                                        grid[x][y].setIcon(null);
                                        grid[x][y].revalidate();
                                        grid[x][y].setIcon(imageicon3);

                                        label3.setText("Current Location : " + x + " ," + y);
                                        if (chek[x][y] != 1) {
                                            label4.setText("Tiles Status : CLEAN ");
                                            label5.setText("Action : None");
                                        } else {
                                            label4.setText("Tiles Status : DIRTY ");
                                            label5.setText("Action : Cleaned");
                                        }

                                        px = x;
                                        py = y;
                                        cnt++;

                                    } else {
                                        grid[px][py].setIcon(null);
                                        grid[px][py].revalidate();
                                        grid[px][py].setIcon(imageicon1);
                                        grid[x][y].setIcon(null);
                                        grid[x][y].revalidate();
                                        grid[x][y].setIcon(imageicon3);

                                        label3.setText("Current Location :" + x + " ," + y);
                                        if (chek[x][y] != 1) {
                                            label4.setText("Tiles Status : CLEAN ");
                                            label5.setText("Action : None");
                                        } else {
                                            label4.setText("Tiles Status : DIRTY ");
                                            label5.setText("Action : Cleaned");
                                        }

                                        if (x == 4) {
                                            if (x == 4 && y == 4) {
                                                label6.setText("Next Move : Finished");
                                            } else {
                                                label6.setText("Next Move : Left");
                                            }

                                        } else {
                                            label6.setText("Next Move : Forward ");
                                        }
                                        px = x;
                                        py = y;
                                        if (x == 4) {
                                            y++;
                                        }
                                        cnt++;
                                    }

                                } else if (y == 1 || y == 3) {

                                    grid[px][py].setIcon(null);
                                    grid[px][py].revalidate();
                                    grid[px][py].setIcon(imageicon1);

                                    grid[x][y].setIcon(null);
                                    grid[x][y].revalidate();
                                    grid[x][y].setIcon(imageicon3);
                                    label3.setText("Current Location :" + x + " ," + y);
                                    if (chek[x][y] != 1) {
                                        label4.setText("Tiles Status : CLEAN ");
                                        label5.setText("Action : None");
                                    } else {
                                        label4.setText("Tiles Status : DIRTY ");
                                        label5.setText("Action : Cleaned");
                                    }

                                    px = x;
                                    py = y;
                                    x--;

                                    if (x == -1) {
                                        label6.setText("Next Move : Right ");
                                    } else {
                                        label6.setText("Next Move : Forward ");
                                    }
                                    if (x == -1) {
                                        y++;
                                    }
                                    cnt++;
                                }

                            }
                        };
                        timer = new Timer(1000, a);
                        timer.start();
                    } else {
                        JOptionPane.showMessageDialog(null, "All Tiles are Clean");
                    }

                }
            });

        } catch (Exception e) {
        }

        gbc.gridx = 0;
        gbc.gridy = 7;
        JButton button3 = new JButton("Reset");
        button3.setBackground(Color.WHITE);
        button3.setPreferredSize(new Dimension(200, 30));
        panel2.add(button3, gbc);
        try {

            button3.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    a = 0;

                    px = 0;
                    py = 0;
                    temp = 0;

                    tf.setText(null);
                    s1 = null;
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            grid[i][j].setIcon(imageicon1);

                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {

                            chek[i][j] = 0;
                        }
                    }
                    label3.setText(null);

                    label4.setText(null);
                    label5.setText(null);
                    label6.setText(null);

                }
            });

        } catch (Exception e) {
        }

        gbc.gridx = 0;
        gbc.gridy = 10;

        sl2 = new JLabel();

        sl2.setPreferredSize(new Dimension(250, 30));
        sl2.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        panel2.add(sl2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;

        label3 = new JLabel();

        label3.setPreferredSize(new Dimension(250, 30));
        label3.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        panel2.add(label3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;

        label4 = new JLabel();

        label4.setPreferredSize(new Dimension(250, 30));
        label4.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        panel2.add(label4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;

        label5 = new JLabel();

        label5.setPreferredSize(new Dimension(250, 30));
        label5.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        panel2.add(label5, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;

        label6 = new JLabel();

        label6.setPreferredSize(new Dimension(250, 30));
        label6.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        panel2.add(label6, gbc);

        frame.setBounds(100, 20, 1000, 605);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String args[]) {
        vc abc = new vc();
    }
}
