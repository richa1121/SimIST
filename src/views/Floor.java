/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author matthewtucker
 */
public class Floor extends JPanel implements ActionListener, KeyListener {
   private String floorName;
   private Image floorImage;
   private Timer refreshTimer;
   private ArrayList<Room> rooms;
   private player player1;
   
   public Floor(String floorName){
       refreshTimer = new Timer (50, this);
       refreshTimer.start();
       this.floorName = floorName;
       //this.floorImage = floorImage;
       rooms = new ArrayList<>();
       rooms.add(new Room(3, 50,50));
       player1 = new player();
       this.addKeyListener(this);
       setFocusable(true);
        requestFocusInWindow();
   }
   
   
   public String getFloorName(){
       return this.floorName;
   }
   
   public void setFloorName(String name){
       this.floorName = name;
   }
   
   public void setImage (Image newImage){
       this.floorImage = floorImage;
   }
   
   public void paintComponent(Graphics g){
       super.paintComponent(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
       for(int i = 0; i < rooms.size();i++){
           rooms.get(i).paintComponent(g);
       }
       player1.paintComponent(g);
       
       for(int i = 0; i<rooms.size();i++){
          if(player1.intersects(rooms.get(i))){
              JOptionPane.showConfirmDialog(this,"This is room number"+rooms.get(i).getNum()+"Do you want to enter?");
          }
       }
   }
   
   public void actionPerformed(ActionEvent e){
       Object o = e.getSource();
       
       if(o == refreshTimer){
           this.repaint();
       }
   }  
    public void keyPressed(KeyEvent e){
       if(e.getKeyCode()== KeyEvent.VK_LEFT){
        player1.setDx(-20);
       
        System.out.println("Typed");
       }
       
       else if(e.getKeyCode() ==KeyEvent.VK_RIGHT){
           player1.setDx(20);
       }
    }   
    
    public void keyReleased(KeyEvent e){
        player1.setDx(0);
    }
    public void keyTyped(KeyEvent e){
        
    }
    
    public void addPlayerToFloor(){
        
    }
   
   
    
    
    
    
    
    private class player extends Rectangle{
         private int dx;
        public player(){
            this.dx = 0;
            
            this.setBounds(100, 50, 30, 30);
        }
        
        public void move(){
            this.x+=dx;
        }
        
        public void setDx(int dx){
            this.dx = dx;
        }
        
        public void paintComponent(Graphics g){
            move();
            g.drawRect(x, y, 30, 30);
        }
    }
   
   
   
   
   
   
   private class Room extends Rectangle{
       private int roomNum;
       private int floor;
       private int capacity;
       private int xLocation;
       private int yLocation;
       private Image floorImage1; 
       private Image table;
       
       //private JPanel roomView;
       
       public Room(int roomNum, int x, int y){
           this.roomNum = roomNum;
           //this.roomView = roomView;
           this.xLocation = x;
           this.yLocation = y;
           this.capacity = 50;
           this.floor = 1;
           ImageIcon floorIcon1 = new ImageIcon("floor tiles.png");
           ImageIcon tableIcon1 = new ImageIcon("Table pixel.png");
          floorImage1 = floorIcon1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
          table = tableIcon1.getImage();
          
           this.setBounds(this.xLocation, this.yLocation, 30, 30);
       }
       
       public void paintComponent(Graphics g){
          
           g.drawRect(x, y, 30, 30); 
           g.drawImage(floorImage1, 0, 0, null);
           g.drawImage(table, 100, 100, null);
       }
       
       public int getNum(){
           return this.roomNum;
       }
   }
}
