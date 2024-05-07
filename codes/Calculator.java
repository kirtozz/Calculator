import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GraphicsPanel extends JFrame implements ActionListener {                                                                               //绘制图形界面
    Panel show_panel = new Panel();
    Panel operate_panel = new Panel();
    JTextField output = new JTextField(20);    
    char pattern[] = { '+', '7', '8', '9', '-', '4', '5', '6', 'X', '1', '2', '3', '/', '0', '.', '=' };
    JButton cebt = new JButton("CE");
    JButton bt[] =new JButton[16];
        {
            for(int i=0;i<16;i++)
            {
                bt[i] = new JButton(""+pattern[i]);
                bt[i].addActionListener(this);
            }
        }
    GraphicsPanel()
    {
        setSize(300,300);
        setVisible(true);
        setTitle("辣鸡计算器");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(show_panel,BorderLayout.NORTH);
        show_panel.add(cebt);
        cebt.addActionListener(this);
        show_panel.add(output);
        add(operate_panel,BorderLayout.CENTER);
        operate_panel.setLayout(new GridLayout(4,4));
        for(int i=0;i<16;i++)
        {
            operate_panel.add(bt[i]);
        }
        validate();
        output.setHorizontalAlignment(JTextField.RIGHT);                                                                                      //设置文本框显示为右对齐
        output.setEditable(false);
        output.setText("0");
    }

    String inputs = "";
    public void actionPerformed(ActionEvent e) {
        String btAction = e.getActionCommand();
        if(btAction.equals("+")||btAction.equals("-")||btAction.equals("X")||btAction.equals("/"))
        {
            inputs +=" "+btAction+" ";
        }
        else if(btAction.equals("CE"))
        {
            inputs = "";
        }
        else if(btAction.equals("="))
        {
            inputs=calculate(inputs);
            output.setText(inputs);
        }
        else
        {
            inputs +=btAction;
        }
        output.setText(inputs);
    }

    String calculate(String inputs)                                                                                                         //处理数据
    {
        int sign = 0;
        float reslut = 0;
        String data[] = inputs.split(" ");
        float num[] = new float[100];
        for(int i=0;i<100;i++)
    	{
    		num[i]=-9999;
    	}
        // int j = 0;
        for(int i=0;i<data.length;i++)
        {
            if((i+1) %2==1)
               {
                    num[i] =Float.parseFloat(data[i]);
                    // j++;
               }
            if(data[i].equals("+"))
                sign = 1;
            else if(data[i].equals("-"))
                sign = 2;
            else if(data[i].equals("X"))
                sign = 3;
            else if(data[i].equals("/"))
                sign = 4;
           
            switch(sign)
            {
                case 1:
                    {   
                        if(num[i]!=-9999)
                            reslut += num[i];
                            break;
                    }    
                case 2:
                    {   
                        if(num[i]!=-9999)
                            reslut -= num[i];  
                            break;
                    }
                case 3:
                    {
                        if(num[i]!=-9999)
                            reslut = reslut * num[i];
                            break;
                    }   
        
                case 4:
                    {    
                        if(num[i]!=-9999)
                            reslut  = reslut / num[i];
                            break;
                    }
                default:
                    {
                        reslut = num[i];
                    }
            }   
        }
        return String.valueOf(reslut);
    }
}

public class Calculator {
    public static void main(String[] args) {
        new GraphicsPanel();
    }
}