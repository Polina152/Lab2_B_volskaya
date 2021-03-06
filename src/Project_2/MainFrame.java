package Project_2;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static java.lang.Math.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private static final int WIDTH = 500;
	private static final int HEIGHT = 320;
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	private JTextField textFieldResult;
	private ButtonGroup radioButtons = new ButtonGroup();
	private Box hboxFormulaType = Box.createHorizontalBox();
	private ButtonGroup radioMemoryButtons = new ButtonGroup();
	private Box hBoxMemoryType = Box.createHorizontalBox();
	private JTextField memoryTextField;
	private int formulaId = 1;
	private int memoryId = 1;
	
	private Double mem1 = 0.;
	private Double mem2 = 0.;
	private Double mem3 = 0.;
	
	public Double formula1(Double x, Double y, Double z) {
		if (z <= 0)	{
            JOptionPane.showMessageDialog(MainFrame.this,
                    "z �����o ���� ���������������", "" +
                            "������ �����", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
		if ((log(z) + sin(PI*z*z)) < 0)	{
            JOptionPane.showMessageDialog(MainFrame.this,
                    "��������� ��� ������ ������ ���� �������������", "" +
                            "������ �����", JOptionPane.WARNING_MESSAGE);
            return 0.0;
		}
		return sqrt(sqrt(log(z) + sin(PI*z*z))) / pow((y*y+exp(cos(x))+sin(y)),sin(x));
	}
	public Double formula2(Double x, Double y, Double z) {
		if (y < 0)	{
            JOptionPane.showMessageDialog(MainFrame.this,
                    "y �����o ���� �������������", "" +
                            "������ �����", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
		return sqrt(y)*3*pow(z, x) / sqrt(1+y*y*y);
	}
	
	private void addRadioButton(String buttonName, final int formulaId) {
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MainFrame.this.formulaId = formulaId;
			}
		});
		radioButtons.add(button);
		hboxFormulaType.add(button);
	}
	
	private void addMemoryRadioButton (String buttonName, final int memoryId)	{
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)	{
                MainFrame.this.memoryId = memoryId;
                if (memoryId == 1)	memoryTextField.setText(mem1.toString());
                if (memoryId == 2)	memoryTextField.setText(mem2.toString());
                if (memoryId == 3)	memoryTextField.setText(mem3.toString());
            }
        });
        radioMemoryButtons.add(button);
        hBoxMemoryType.add(button);
	}
	public MainFrame() {
		
		super("���������� �������");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2,
				(kit.getScreenSize().height - HEIGHT)/2);
		
		hboxFormulaType.add(Box.createHorizontalGlue());
		addRadioButton("������� 1", 1);
		addRadioButton("������� 2", 2);
		radioButtons.setSelected(
				radioButtons.getElements().nextElement().getModel(), true);
		hboxFormulaType.add(Box.createHorizontalGlue());
		
		JLabel labelForX = new JLabel("X:");
		textFieldX = new JTextField("0", 10);
		textFieldX.setMaximumSize(textFieldX.getPreferredSize());
		JLabel labelForY = new JLabel("Y:");
		textFieldY = new JTextField("0", 10);
		textFieldY.setMaximumSize(textFieldY.getPreferredSize());
		JLabel labelForZ = new JLabel("Z:");
		textFieldZ = new JTextField("0", 10);
		textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
		Box hboxVariables = Box.createHorizontalBox();
		
		hboxVariables.add(Box.createHorizontalGlue());
		hboxVariables.add(labelForX);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldX);
		hboxVariables.add(Box.createHorizontalStrut(100));
		hboxVariables.add(labelForY);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldY);
		hboxVariables.add(Box.createHorizontalStrut(100));
		hboxVariables.add(labelForZ);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldZ);
		hboxVariables.add(Box.createHorizontalGlue());
		
		JLabel labelForResult = new JLabel("���������:");
		textFieldResult = new JTextField("0", 15);
		textFieldResult.setMaximumSize(
				textFieldResult.getPreferredSize());
		Box hboxResult = Box.createHorizontalBox();
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.add(labelForResult);
		hboxResult.add(Box.createHorizontalStrut(10));
		hboxResult.add(textFieldResult);
		hboxResult.add(Box.createHorizontalGlue());
		JButton buttonCalc = new JButton("���������");
		buttonCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Double x = Double.parseDouble(textFieldX.getText());
				Double y = Double.parseDouble(textFieldY.getText());
				Double z = Double.parseDouble(textFieldZ.getText());
				Double result;
				if (formulaId==1)
					result = formula1(x, y, z);
				else
					result = formula2(x, y, z);
				textFieldResult.setText(result.toString());
			}
		});
		JButton buttonReset = new JButton("�������� ����");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				textFieldX.setText("0");
				textFieldY.setText("0");
				textFieldZ.setText("0");
				textFieldResult.setText("0");
			}
		});

		hBoxMemoryType.add(Box.createHorizontalGlue());
		addMemoryRadioButton("������ 1",1);
		addMemoryRadioButton("������ 2",2);
		addMemoryRadioButton("������ 3",3);
		radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
		hBoxMemoryType.add(Box.createHorizontalGlue());

		Box memory=Box.createHorizontalBox();
		memory.add(Box.createHorizontalGlue());
		JButton MC=new JButton("MC");
		
		MC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (memoryId == 1)	mem1 = (double) 0;
				if (memoryId == 2)	mem2 = (double) 0;
				if (memoryId == 3)	mem3 = (double) 0;
				memoryTextField.setText("0.0");
	            }
	        });

		JButton M_plus=new JButton("M+");
		M_plus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Double result = Double.parseDouble(textFieldResult.getText());
				if (memoryId == 1) 	{mem1 += result;memoryTextField.setText(mem1.toString());}
				if (memoryId == 2)	{mem2 += result;memoryTextField.setText(mem2.toString());}
				if (memoryId == 3)	{mem3 += result;memoryTextField.setText(mem3.toString());}
			}
		});
		
		 memoryTextField = new JTextField("0.0", 15);
	     memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());

		memory.add(MC);
		memory.add(Box.createHorizontalStrut(10));
		memory.add(memoryTextField);
        memory.add(Box.createHorizontalStrut(10));
		memory.add(M_plus);
		memory.add(Box.createHorizontalGlue());
		
		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.add(buttonCalc);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonReset);
		hboxButtons.add(Box.createHorizontalGlue());
		
		Box contentBox = Box.createVerticalBox();
		contentBox.add(Box.createVerticalGlue());
		contentBox.add(hboxFormulaType);
		contentBox.add(hboxVariables);
		contentBox.add(hboxResult);
		contentBox.add(hboxButtons);
		contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxMemoryType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(memory);
		contentBox.add(Box.createVerticalGlue());
		getContentPane().add(contentBox, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
		