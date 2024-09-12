package cn.lidan.start;

import cn.lidan.util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Carculator extends JFrame implements ActionListener {
    /*************************北面的控件*****************************/
    private JPanel jp_north =new JPanel();//设置了一个面板
    private JTextField input_text = new JTextField();//一个输入框
    private JButton c_Btn =new JButton("C");//一个按钮

    /*************************中间的控件*****************************/
    private JPanel jp_center=new JPanel();



    public Carculator() throws HeadlessException {//计算器构造函数
        this.init();
        this.addNorthComponent();
        this.addCenterButton();
    }

    public void init(){//构造函数分离
        this.setTitle(Const.TITLE);//标题
        this.setSize(Const.FRAME_W,Const.FRAME_H);//大小
        this.setLayout(new BorderLayout());//布局管理器
        this.setResizable(false);//不准调整大小
        this.setLocation(Const.FRAME_X,Const.FRAME_Y);//设置初始位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户关闭窗口时，应用程序将退出
    }

    public void addNorthComponent(){//添加北面的控件
        this.input_text.setPreferredSize(new Dimension(230,30));//输入框尺寸
        jp_north.add(input_text);//添加输入框控件到窗体
        jp_north.add(c_Btn);//添加按钮到窗体
        this.c_Btn.setForeground(Color.RED);//按钮改成红色
        c_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_text.setText("");
            }
        });
        this.add(jp_north,BorderLayout.NORTH);//添加到BorderLayout布局的北边
    }

    public void addCenterButton(){//添加中间的控件
        String btn_text = "123+456-789*0.=/";
        String regex = "[\\+\\-*/.=]";
        this.jp_center.setLayout(new GridLayout(4,4));
        for (int i=0;i<16;i++){
            String temp = btn_text.substring(i,i+1);
            JButton btn = new JButton(temp);
            btn.setText(temp);//设置按钮的输入文本
            if(temp.matches(regex)){
                btn.setFont(new Font("粗体",Font.BOLD,16));
                btn.setForeground(Color.RED);
            }
            btn.addActionListener(this);
            jp_center.add(btn);
        }
        this.add(jp_center,BorderLayout.CENTER);
    }


    public static void main(String[] args) {//启动类
        Carculator carculator =new Carculator();
        carculator.setVisible(true);
    }

    private String firstInput = null;
    private String operator = null;

    @Override
    public void actionPerformed(ActionEvent e) {//监听器
     String clickStr = e.getActionCommand();//获取点击事件的值
        if(".0123456789".indexOf(clickStr) != -1) {
//           JOptionPane.showMessageDialog(this,clickStr);//显示出来获取到的值
            this.input_text.setText(input_text.getText()+clickStr);
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);
        }else if (clickStr.matches("[\\+\\-*/]")){
            operator = clickStr;//把现在点击的运算符存到operator
            firstInput = this.input_text.getText();//把第一个输入的值暂存到firstinput
            this.input_text.setText("");//清空输入框
          } else if (clickStr.equals("=")) {
                Double a = Double.valueOf(firstInput);
                Double b = Double.valueOf(this.input_text.getText());
                Double result = null;
            switch (operator){
                case "+":
                    result=a+b;
                    break;
                case "-":
                    result=a-b;
                    break;
                case "*":
                    result=a*b;
                    break;
                case "/":
                    if (b!=0){
                    result=a/b;
                    }break;
            }this.input_text.setText(result.toString());
        }
    }
}
