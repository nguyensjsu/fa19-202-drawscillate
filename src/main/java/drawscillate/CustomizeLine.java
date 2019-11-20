package drawscillate;

import java.net.URL;
import java.util.Optional;

import javax.swing.JButton;

import processing.core.PApplet;
import processing.core.PImage;

public class CustomizeLine extends PApplet{

    private char key;
    
    private IColorInvoker redCol ;
    private IColorInvoker yellowCol;
    
    
    public CustomizeLine() {
        
        redCol = new ColorOption() ;
        yellowCol = new ColorOption() ;
        
    }
   
    public void setKey(char key) {
        this.key = key; 
    }
    
    public char getKey() {
        return key; 
    }
    
    
    
    public void initialize(char key) {
        switch(key) {
            case 'r': 
                
                redCol.invoke();
                break;
            case 'y': yellowCol.invoke();
                break;
        }
    }
    
public void setColorItem(char key, IColorCommand color) {
        
        switch(key) {
        case('r'):
           redCol.setCommand(color);
           break;
        case('y'):
            yellowCol.setCommand(color);
            break;
                
        }
        
    }

    
    
//    JButton r = new JButton("r");
//    JButton y = new JButton("y");
//    JButton p = new JButton("p");
//    JButton o = new JButton("o");
//    JButton space = new JButton(" ");
//    JButton g = new JButton("g");
//    JButton b = new JButton("b");
//    
//    r.setReceiver(new IReceiver() {
//        @Override
//        public void doAction() {
//            
//        }
//    });
//    ctrlX.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            executeCommand(new CutCommand(editor));
//        }
//    });
//    ctrlV.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            executeCommand(new PasteCommand(editor));
//        }
//    });
//    ctrlZ.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            undo();
//        }
//    });
    
}
