package simpletexteditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author CodingWithEliasLab
 */
public class SimpleTextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    public SimpleTextEditor(){
        setTitle("Simple text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu filMenu = new JMenu("File");
        
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        
        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        
        
        filMenu.add(newMenuItem);
        filMenu.add(openMenuItem);
        filMenu.add(saveMenuItem);
        filMenu.add(exitMenuItem);
        
        menuBar.add(filMenu);
        setJMenuBar(menuBar);
        
        textArea = new JTextArea();
        JScrollPane scrollPane =new JScrollPane(textArea);
        add(scrollPane);
        
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text File", "txt")); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "New":
                newFile();
                break;
            case "Open":
               openFile();
                break;
            case "Save":
               saveFile();
                break;
            case "Exit":
                 System.exit(0);
                break;
        }
        
    }
    public void saveFile(){
        fileChooser.setDialogTitle("Save File");
        fileChooser.setApproveButtonText("Save");
        
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                textArea.write(writer);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(this, "Error Saving file");
            }
            
        }
    }
    public void newFile(){
        textArea.setText("");
    }
    public void openFile(){
        fileChooser.setDialogTitle("Open File");
        fileChooser.setApproveButtonText("Open");
        
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                textArea.read(reader, null);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(this, "Error opening file");
            }
            
        }
    }
    public static void main(String[] args) {
        SimpleTextEditor editor = new SimpleTextEditor();
        editor.setVisible(true);
        
    }   
}
