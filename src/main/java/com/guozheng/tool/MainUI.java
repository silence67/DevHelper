package com.guozheng.tool;

import com.guozheng.tool.gen.GenFile;
import com.guozheng.tool.jdbc.IDBTableInfo;
import com.guozheng.tool.jdbc.MysqlDBTableInfo;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.util.Enumeration;

public class MainUI {

    IDBTableInfo tableInfo;

    JFrame mainFrame;
    JTextField tableNameField;

    JTextField packageField;

    JTextField destField;

    JTextField databaseUrlField;

    JTextField userNameField;

    JTextField passwordField;

    JButton goBtn;

    JButton fileBtn;

    public void init() {
        tableInfo = new MysqlDBTableInfo();
        //tableInfo.getTableColumnInfo("t_carousel","");
        initUI();
        initListener();
    }


    public void initUI() {
        mainFrame = new JFrame();
        mainFrame.setTitle("tool");
        mainFrame.setPreferredSize(new Dimension(450,400));
        JPanel panel = new JPanel();
        JPanel bottomPanel = new JPanel();
        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        bottomPanel.setLayout(flowLayout);
        goBtn = new JButton("开始");
        bottomPanel.add(goBtn);
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));

        JPanel dbUrlPanel = new JPanel();
        dbUrlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        databaseUrlField = new JTextField();
        databaseUrlField.setPreferredSize(new Dimension(300,30));
        JLabel dbLabel = new JLabel("dbUrl：", JLabel.RIGHT);
        dbLabel.setPreferredSize(new Dimension(60, 30));
        dbUrlPanel.add(dbLabel);
        dbUrlPanel.add(databaseUrlField);
        databaseUrlField.setText("jdbc:mysql://127.0.0.1:3306/kaifeng_db?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false");
        panel.add(dbUrlPanel);

        JPanel dbUserPanel = new JPanel();
        dbUserPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        userNameField = new JTextField();
        userNameField.setPreferredSize(new Dimension(300,30));
        JLabel dbUserLabel = new JLabel("用户名：", JLabel.RIGHT);
        dbUserLabel.setPreferredSize(new Dimension(60, 30));
        dbUserPanel.add(dbUserLabel);
        dbUserPanel.add(userNameField);
        userNameField.setText("root");
        panel.add(dbUserPanel);

        JPanel dbPwdPanel = new JPanel();
        dbPwdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(300,30));
        JLabel pwdLabel = new JLabel("密码：", JLabel.RIGHT);
        pwdLabel.setPreferredSize(new Dimension(60, 30));
        dbPwdPanel.add(pwdLabel);
        dbPwdPanel.add(passwordField);
        passwordField.setText("123456");
        panel.add(dbPwdPanel);

        JPanel tableNamePanel = new JPanel();
        tableNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tableNameLabel = new JLabel("表名：", JLabel.RIGHT);
        tableNameLabel.setPreferredSize(new Dimension(60, 30));
        tableNamePanel.add(tableNameLabel);
//        tableNamePanel.setPreferredSize(new Dimension(400, 50));
        tableNameField = new JTextField();
        tableNameField.setPreferredSize(new Dimension(300,30));
        tableNamePanel.add(tableNameField);
        panel.add(tableNamePanel);
        JPanel packagePanel = new JPanel();
        packagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel packageLabel = new JLabel("包名：", JLabel.RIGHT);
        packageLabel.setPreferredSize(new Dimension(60, 30));
        packagePanel.add(packageLabel);
        packageField = new JTextField();
        packageField.setPreferredSize(new Dimension(300,30));
        packagePanel.add(packageField);
        panel.add(packagePanel);

        JPanel destPanel = new JPanel();
        destPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel destLabel = new JLabel("文件路径：", JLabel.RIGHT);
        destLabel.setPreferredSize(new Dimension(60, 30));
        destPanel.add(destLabel);
        destField = new JTextField();
        destField.setPreferredSize(new Dimension(300,30));
        destPanel.add(destField);
        fileBtn = new JButton("...");
        destPanel.add(fileBtn);
        panel.add(destPanel);


        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        tableNameField.setText("t_carousel");
        packageField.setText("com.prohouduan");
        destField.setText("F:/code");
    }


    public void initListener() {
        fileBtn.addActionListener((e)->{
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int ret = jFileChooser.showOpenDialog(mainFrame);
            if(ret == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                destField.setText(file.getAbsolutePath());
            }
        });

        goBtn.addActionListener((e)->{
            String talbeName = tableNameField.getText();
            String packageName = packageField.getText();
            String filePath = destField.getText();
            String dbUrl = databaseUrlField.getText();
            String userName = userNameField.getText();
            String password = passwordField.getText();
            new GenFile(talbeName, packageName, filePath).generateFiles();
        });
    }

    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel(com.sun.java.swing.plaf.windows.WindowsLookAndFeel.class.getName());
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            initGlobalFont();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        new MainUI().init();
    }

    private static void initGlobalFont(){
        FontUIResource fontUIResource = new FontUIResource(new Font("微软雅黑",Font.PLAIN, 12));
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value= UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontUIResource);
            }
        }
    }

}
