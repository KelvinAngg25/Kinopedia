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
public class Logic extends JFrame implements Runnable{
    private int menit,detik;
    boolean pause;
    private View view;
    private JLabel timerLabel;
    private JLabel livesLabel;
    private JLabel sudokuLabel;
    private JButton tombolPause;
    private boolean gameRunning = true;

    

    public Logic(int menit, int detik, boolean pause) {
        this.menit = menit;
        this.detik = detik;
        this.pause = pause;
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
    
    public static int[][] generatePuzzle() {
        return new int[][] {
            {5, 2, 3, 0, 0, 0, 6, 8, 7},
            {0, 0, 0, 0, 4, 0, 0, 0, 9},
            {1, 0, 0, 0, 0, 0, 3, 0, 0},
            {4, 0, 0, 5, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 9, 0, 6, 0, 0, 0, 1},
            {0, 0, 0, 7, 0, 0, 0, 0, 2},
            {7, 4, 0, 0, 8, 0, 0, 0, 0},
            {8, 3, 1, 0, 0, 0, 6, 7, 0}
        };
        
    }

    @Override
    public void run() {
        while (!pause){
            
            try{
                Thread.sleep(1000);
                timer();
            }catch(InterruptedException e){

            }
        }
    }
    
    public void game(){
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton tombolPause = new JButton("| |");
        tombolPause.setFont(new Font("Poppins", Font.BOLD, 30));
        tombolPause.setContentAreaFilled(false);
        tombolPause.setBorderPainted(false);
        tombolPause.setFocusPainted(false);
        tombolPause.addActionListener(e -> {
            pause = true;
            pause();
        });

        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Poppins", Font.BOLD, 40));

        livesLabel = new JLabel("Lives : 3/3");
        livesLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        JPanel TLPanel = new JPanel(new BorderLayout());
        TLPanel.add(timerLabel, BorderLayout.WEST);
        TLPanel.add(livesLabel, BorderLayout.EAST);

        JPanel pausePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pausePanel.add(tombolPause);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(pausePanel, BorderLayout.NORTH);
        topPanel.add(TLPanel,BorderLayout.SOUTH );


        add(topPanel, BorderLayout.NORTH);
        Thread timerThread = new Thread(this);
        timerThread.setDaemon(true);
        timerThread.start();

        JPanel sudokuPanel = new JPanel(new java.awt.GridLayout(9, 9));
        sudokuPanel.setPreferredSize(new Dimension(400, 400));
        sudokuPanel.setMaximumSize(new Dimension(400, 400));
        sudokuPanel.setMinimumSize(new Dimension(400, 400));
        sudokuPanel.setBackground(Color.WHITE);
        sudokuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(sudokuPanel);
        add(centerPanel, BorderLayout.CENTER);


        int[][] puzzle = generatePuzzle();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Poppins", Font.BOLD, 20));
                cell.setPreferredSize(new Dimension(55, 55));
                int top    = (row % 3 == 0) ? 3 : 1;
                int left   = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == 8)     ? 3 : 1;
                int right  = (col == 8)     ? 3 : 1;
                cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                if (puzzle[row][col] != 0) {
                    cell.setText(String.valueOf(puzzle[row][col]));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                }
                sudokuPanel.add(cell);
            }
        }   
        
        
    }
    
    public void gameVictory(){
        SwingUtilities.invokeLater(() -> {
            JLabel resultLabel = new JLabel ("VICTORY",JLabel.CENTER);
            resultLabel.setFont(new Font("Poppins", Font.BOLD, 100));
            
            
            JButton playAgain = new JButton("PLAY AGAIN");
            playAgain.setFont(new Font("Monospaced", Font.BOLD, 60));
            playAgain.addActionListener(e -> {
                dispose();
                Logic game = new Logic(5, 0, false);
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
                Logic game = new Logic(5, 0, false);
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
            
            revalidate();
            repaint();
        
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
            getContentPane().removeAll();
            Logic game = new Logic(5, 1, false);
            game.game();
            game.setVisible(true);
            revalidate();
            repaint();
        });
        button.add(btnStart,BorderLayout.NORTH);
        
        JButton btnExit = new JButton("EXIT");
        btnExit.setFont(new Font("Poppins",Font.BOLD,60));
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.BLACK);
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
        btnExit.addActionListener(e ->{
            System.exit(0);
        });
        button.add(btnExit, BorderLayout.SOUTH);
        
        add(button, BorderLayout.SOUTH);
        
        
        
    }


    
    
    
}
