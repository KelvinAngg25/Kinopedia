/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.sudoku;

/**
 *
 * @author William
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
public class Logic extends JFrame implements Runnable{
    private int menit,detik;
    boolean pause;
    private View view;
    private JLabel timerLabel;
    private JLabel livesLabel;
    private JLabel sudokuLabel;
    private JButton tombolPause;
    private boolean gameRunning = true;
    private JButton angkadiPilih = null;
    private int life;
    
    public Logic() {
        this.menit = 5;
        this.detik = 0;
        this.pause = false;
        this.life = 3;
    }
    
    public void timer(){
        if(detik == 0){
            if(menit==0){
                pause =true;
                gameOver();
            }else {
                menit-=1;
                detik =59;
            }
        }else{
            detik-=1;
        }
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(String.format("%02d : %02d", menit, detik));
        });
    }
    
    private int[][] Puzzle1() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{5, 2, 3, 0, 0, 0, 6, 8, 7};
        puzzle[1] = new int[]{0, 0, 0, 0, 4, 0, 0, 0, 9};
        puzzle[2] = new int[]{1, 0, 0, 0, 0, 0, 3, 0, 0};
        puzzle[3] = new int[]{4, 0, 0, 5, 0, 0, 8, 0, 0};
        puzzle[4] = new int[]{0, 0, 0, 0, 2, 0, 0, 0, 0};
        puzzle[5] = new int[]{0, 0, 9, 0, 6, 0, 0, 0, 1};
        puzzle[6] = new int[]{0, 0, 0, 7, 0, 0, 0, 0, 2};
        puzzle[7] = new int[]{7, 4, 0, 0, 8, 0, 0, 0, 0};
        puzzle[8] = new int[]{8, 3, 1, 0, 0, 0, 6, 7, 0};
        return puzzle;
        
    }
    
    public static int [][] Puzzle2(){
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{5, 2, 3, 0, 0, 0, 6, 8, 7};
        puzzle[1] = new int[]{0, 0, 0, 0, 4, 0, 0, 0, 9};
        puzzle[2] = new int[]{1, 0, 0, 0, 0, 0, 3, 0, 0};
        puzzle[3] = new int[]{4, 0, 0, 5, 0, 0, 8, 0, 0};
        puzzle[4] = new int[]{0, 0, 0, 0, 2, 0, 0, 0, 0};
        puzzle[5] = new int[]{0, 0, 9, 0, 6, 0, 0, 0, 1};
        puzzle[6] = new int[]{0, 0, 0, 7, 0, 0, 0, 0, 2};
        puzzle[7] = new int[]{7, 4, 0, 0, 8, 0, 0, 0, 0};
        puzzle[8] = new int[]{8, 3, 1, 0, 0, 0, 6, 7, 0};
        return puzzle;
    }

    @Override
    public void run() {
        while (!pause){
            timer();
            try{
                Thread.sleep(1000);
                
            }catch(InterruptedException e){

            }
        }
    }
    
    private int p = 0;
    private int pilihAngka = 0;
    Random rand = new Random();
    
    public void game(){
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton tombolPause = new JButton("| |");
        tombolPause.setFont(new Font("Poppins", Font.BOLD, 30));
        tombolPause.setContentAreaFilled(false);
        tombolPause.setBorderPainted(false);
        tombolPause.setFocusPainted(false);
        tombolPause.setBounds(170,-20,100,100);
        tombolPause.addActionListener(e -> {
            if(p==0){
                p=1;
                pause = true;
                pause();
            }
           
        });
        add(tombolPause);

        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Poppins", Font.BOLD, 40));
        timerLabel.setBounds(10,20,150,50);
        add(timerLabel);

        livesLabel = new JLabel("Lives : 3/3");
        livesLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        livesLabel.setBounds(280,20,150,50);
        add(livesLabel);
       
        Thread timerThread = new Thread(this);
        timerThread.setDaemon(true);
        timerThread.start();

        
        for (int i = 0;i<9;i++){
            final int valueAngka = i+1;
            String a = ""+(i+1);
            int kanan = i*60+90;
            JButton angka = new JButton(a);
            angka.setFont(new Font("Poppins", Font.BOLD, 40));
            angka.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            angka.setFocusPainted(false);
            angka.setBackground(Color.LIGHT_GRAY);
            
            
            if(i>4 && i!=8){
                kanan = (i%4)*60+60;
                angka.setBounds(kanan,640,50,50);
            }else if(i==8){
                kanan = 4*60+60;
                angka.setBounds(kanan,640,50,50);
            }else{
                angka.setBounds(kanan,570,50,50);
            }
            add(angka);
            
            angka.addActionListener(e ->{
                if(angkadiPilih != null){
                    angkadiPilih.setBackground(Color.LIGHT_GRAY);
                }
                pilihAngka = valueAngka;
                angkadiPilih = angka;
                angka.setBackground(Color.WHITE);
                
            });
        }
        
        JPanel sudokuPanel = new JPanel(new java.awt.GridLayout(9, 9));
        sudokuPanel.setBounds(30,100,400,400);
        sudokuPanel.setBackground(Color.WHITE);
        sudokuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        int random = rand.nextInt(10+1);
        
//        if(random ==1){
//            int[][] puzzle = Puzzle1();
//        }else if(random ==2){
//            int[][] puzzle = Puzzle2();
//        }else if(random ==3){
//            int[][] puzzle = Puzzle3();
//        }else if(random ==4){
//            int[][] puzzle = Puzzle4();
//        }else if(random ==5){
//            int[][] puzzle = Puzzle5();
//        }else if(random ==6){
//            int[][] puzzle = Puzzle6();
//        }else if(random ==7){
//            int[][] puzzle = Puzzle7();
//        }else if(random ==8){
//            int[][] puzzle = Puzzle8();
//        }else if(random ==9){
//            int[][] puzzle = Puzzle9();
//        }else if(random ==10){
//            int[][] puzzle = Puzzle10();
//        }

        int[][] puzzle = Puzzle1();
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JButton cell = new JButton();
                cell.setHorizontalAlignment(JButton.CENTER);
                cell.setFont(new Font("Poppins", Font.BOLD, 20));
                cell.setPreferredSize(new Dimension(55, 55));
                if((row ==2 && col==2) || (row==2 && col ==5) || (row ==5 && col ==2)|| (row==5 && col==5)){
                    cell.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
                }else if(row ==2 || row==5){
                    cell.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
                }else if(col==2 || col==5){
                    cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
                }else{
                    cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
                

                if (puzzle[row][col] != 0) {
                    cell.setText(String.valueOf(puzzle[row][col]));
                    cell.setEnabled(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                    cell.setDisabledIcon(null);
                }else{
                    cell.setBackground(Color.WHITE);
                    cell.setOpaque(true);
                    cell.addActionListener(e -> {
                    if (pilihAngka != 0) {
                        cell.setText(String.valueOf(pilihAngka));
                        cell.setForeground(new Color(0, 0, 180));
                        cell.setBackground(Color.LIGHT_GRAY);
                    }
                    });
                }
                sudokuPanel.add(cell);
            }
        }
        add(sudokuPanel);
        
        
    }
    
    public void gameVictory(){
        SwingUtilities.invokeLater(() -> {
            JLabel resultLabel = new JLabel ("VICTORY",JLabel.CENTER);
            resultLabel.setFont(new Font("Poppins", Font.BOLD, 100));
            
            
            JButton playAgain = new JButton("PLAY AGAIN");
            playAgain.setFont(new Font("Monospaced", Font.BOLD, 60));
            playAgain.addActionListener(e -> {
                dispose();
                Logic game = new Logic();
                game.game();
                game.setVisible(true);
            });
            
            JButton exit = new JButton("EXIT");
            exit.setFont(new Font("Monospaced", Font.BOLD, 60));
            exit.addActionListener(e -> System.exit(0));
            
            JPanel tombolPlayExit = new JPanel();
            tombolPlayExit.setLayout(new GridLayout(2, 1, 10, 10));
            tombolPlayExit.setBorder(BorderFactory.createEmptyBorder(10,60,40,60));
            tombolPlayExit.add(playAgain);
            tombolPlayExit.add(exit);
            
            JPanel result = new JPanel(new BorderLayout());
            result.add(resultLabel,BorderLayout.NORTH);
            result.add(tombolPlayExit,BorderLayout.CENTER);
            
            add(result, BorderLayout.SOUTH);
            
            revalidate();
            repaint();
        });
    }
    
    public void gameOver(){
        SwingUtilities.invokeLater(() -> {
            JLabel resultLabel = new JLabel ("GAME OVER",JLabel.CENTER);
            resultLabel.setFont(new Font("Poppins", Font.BOLD, 70));
            
            
            JButton playAgain = new JButton("PLAY AGAIN");
            playAgain.setFont(new Font("Poppins", Font.BOLD, 40));
            playAgain.setForeground(Color.BLACK);
            playAgain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
            playAgain.setFocusPainted(false);
            playAgain.setContentAreaFilled(false);
            playAgain.addActionListener(e -> {
                dispose();
                Logic game = new Logic();
                game.game();
                game.setVisible(true);
            });
            
            JButton exit = new JButton("EXIT");
            exit.setFont(new Font("Poppins", Font.BOLD, 60));
            exit.setForeground(Color.BLACK);
            exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
            exit.setFocusPainted(false);
            exit.setContentAreaFilled(false);
            exit.addActionListener(e -> System.exit(0));
            
            JPanel tombolPlayExit = new JPanel();
            tombolPlayExit.setLayout(new GridLayout(2, 1, 10, 10));
            tombolPlayExit.setBorder(BorderFactory.createEmptyBorder(10,60,40,60));
            tombolPlayExit.add(playAgain);
            tombolPlayExit.add(exit);
            
            JPanel result = new JPanel(new BorderLayout());
            result.add(resultLabel,BorderLayout.NORTH);
            result.add(tombolPlayExit,BorderLayout.CENTER);
            
            add(result, BorderLayout.SOUTH);
        
        });
           
    }
    
    public void pause(){
        SwingUtilities.invokeLater(() -> {
            JPanel overlay = new JPanel(new GridBagLayout());
            overlay.setOpaque(false);
            
            JPanel kotak = new JPanel(new GridLayout(2,1,10,10));
            kotak.setBackground(Color.WHITE);
            kotak.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK,2),BorderFactory.createEmptyBorder(20, 40, 20, 40)));
            kotak.setPreferredSize(new Dimension(350,200));
            
            JButton btnContinue = new JButton("CONTINUE");
            btnContinue.setFont(new Font("Poppins", Font.BOLD, 40));
            btnContinue.setBackground(new Color(0, 180, 0));
            btnContinue.setForeground(Color.WHITE);
            btnContinue.setFocusPainted(false);
            btnContinue.addActionListener(e -> {
                pause=false;
                setGlassPane(new JPanel());
                getGlassPane().setVisible(false);
                Thread t = new Thread(this);
                t.setDaemon(true);
                t.start();
                p=0;
                
                
                
            });
            kotak.add(btnContinue);
            
            JButton btnExit = new JButton("EXIT");
            btnExit.setFont(new Font("Poppins", Font.BOLD, 40));
            btnExit.setBackground(new Color(180,0, 0));
            btnExit.setForeground(Color.WHITE);
            btnExit.setFocusPainted(false);
            btnExit.addActionListener(e-> {
                getContentPane().removeAll();
                menuGame();
                revalidate();
                repaint();
                overlay.setVisible(false);
            });
            kotak.add(btnExit);
            overlay.add(kotak);
            setGlassPane(overlay);
            overlay.setVisible(true);
        });
    }
    
    
    public void menuGame(){
        setSize(470,844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("SUDOKU",JLabel.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 100));
        title.setBorder(BorderFactory.createEmptyBorder(30,0,10,0));
        add(title,BorderLayout.NORTH);
        
        URL url = getClass().getResource("sudoku.png");
        JLabel gambar;
        ImageIcon file = new ImageIcon(getClass().getResource("sudoku.png"));
        Image scale = file.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        gambar = new JLabel(new ImageIcon(scale), JLabel.CENTER);
        add(gambar, BorderLayout.CENTER);
        
        JPanel button = new JPanel(new GridLayout(2, 1, 10, 10));
        button.setBorder(BorderFactory.createEmptyBorder(10, 60, 90, 60));
        
        JButton btnStart = new JButton("START");
        btnStart.setFont(new Font("Poppins", Font.BOLD,60));
        btnStart.setBackground(new Color(0,180,0));
        btnStart.setForeground(Color.BLACK);
        btnStart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
        btnStart.setFocusPainted(false);
        btnStart.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> {
                Logic game = new Logic();
                game.game();
                game.setVisible(true);
            });
        });
        button.add(btnStart,BorderLayout.NORTH);
        
        JButton btnExit = new JButton("EXIT");
        btnExit.setFont(new Font("Poppins",Font.BOLD,60));
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.BLACK);
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
        btnExit.addActionListener(e ->{
            new Kinopedia.minigames.MainMiniGames().setVisible(true);
            dispose();
        });
        button.add(btnExit, BorderLayout.SOUTH);
        
        add(button, BorderLayout.SOUTH);
    }
}
