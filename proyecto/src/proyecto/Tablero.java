/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.applet.AudioClip;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
import static sun.audio.AudioPlayer.player;

class Tablero implements ActionListener {

    public JFrame ventana;
    public Dado dado = new Dado();
    public Obstaculos portal[] = new Obstaculos[10];
    public JTabbedPane tabPane;
    public JPanel mainPanel, introPanel, gamePanel, playerPassPanel, diePanel;
    public JPanel gameCenter, gameEast, gameWest, gameNorth, gameSouth;

    public Jugador Jugador1;
    public Jugador Jugador2;
    public JLabel header;

    public Random randomNo;

    public Icon icon[][] = new Icon[10][10];
    public Icon winericon[][] = new Icon[10][10];

    public Icon dieIcon;
    public JLabel dadol;

    public JButton introB[] = new JButton[5];
    public JButton b[][] = new JButton[10][10];
    public JButton start, restart;
    public JButton JBjugador1, JBjugador2;

    public int i, j;

    public int path;
    // public int p1value, p2value;

    // public int player, computer;
    public int gameover;
    //public int cimageFlag = 0;
    //public int cnoFlag = 0;

    public String str;

    int game[][] = {
        {100, 99, 98, 97, 96, 95, 94, 93, 92, 91},
        {81, 82, 83, 84, 85, 86, 87, 88, 89, 90},
        {80, 79, 78, 77, 76, 75, 74, 73, 72, 71},
        {61, 62, 63, 64, 65, 66, 67, 68, 69, 70},
        {60, 59, 58, 57, 56, 55, 54, 53, 52, 51},
        {41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
        {40, 39, 38, 37, 36, 35, 34, 33, 32, 31},
        {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
        {20, 19, 18, 17, 16, 15, 14, 13, 12, 11},
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},};
    
     public static void main(String args[]) {
        Tablero oa = new Tablero();

    }

    Tablero() {

        ventana = new JFrame("Serpientes y Escaleras");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* AudioClip Sonido;
        Sonido =java.applet.Applet.newAudioClip(getClass().getResource("sonido/pokemon.mp3"));
        
        Sonido.add(ventana, BorderLayout.CENTER);
        Sonido.play();*/

        mainPanel = new JPanel();
        tabPane = new JTabbedPane(JTabbedPane.TOP);

        ventana.setLayout(new BorderLayout());

        JBjugador1 = new JButton("player1");
        JBjugador2 = new JButton("player2");

        JBjugador1.setEnabled(false);
        JBjugador2.setEnabled(false);

        JBjugador1.addActionListener(this);
        JBjugador2.addActionListener(this);

        dadol = new JLabel();

        game();

        ventana.add(tabPane, BorderLayout.CENTER);
        ventana.setResizable(true);
        ventana.setSize(750, 700);
        //f.pack();
        ventana.setVisible(true);

    }

    public void game() {
        JLabel nombre1 = new JLabel();
        JLabel nombre2 = new JLabel();
        JLabel avatar1 = new JLabel();
        JLabel avatar2 = new JLabel();

        restart = new JButton("Restart");
        restart.setEnabled(false);
        restart.addActionListener(this);

        start = new JButton("Start");
        start.addActionListener(this);

        gamePanel = new JPanel();

        gameCenter = new JPanel();
        gameWest = new JPanel();
        gameNorth = new JPanel();
        gameSouth = new JPanel();

        gameCenter.setLayout(new GridLayout(10, 10));
        gameEast = new JPanel(new GridLayout(2, 1, 10, 10));

        gameWest.setLayout(new FlowLayout());
        gameNorth = new JPanel(new GridLayout(3, 1));
        gameSouth = new JPanel(new GridLayout(7, 1));

        gamePanel.setLayout(new BorderLayout());

        Jugador1 = createJugador();
        Jugador2 = createJugador();

        avatar1.setIcon(Jugador1.getJugador());
        nombre1.setText(Jugador1.getName());

        avatar2.setIcon(Jugador2.getJugador());
        nombre2.setText(Jugador2.getName());

        createObstaculos();
        repint();

     
        str = Integer.toString(1);

        dadol.setIcon(dado.getDado());

        gameSouth.add(avatar1, BorderLayout.CENTER);
        gameSouth.add(nombre1, BorderLayout.CENTER);
        gameSouth.add(JBjugador1, BorderLayout.CENTER);
        gameSouth.add(dadol, BorderLayout.CENTER);
        gameSouth.add(JBjugador2, BorderLayout.CENTER);
        gameSouth.add(avatar2, BorderLayout.CENTER);
        gameSouth.add(nombre2, BorderLayout.CENTER);

        gameNorth.add(start);
        gameNorth.add(restart);

        gameEast.add(gameNorth, BorderLayout.NORTH);
        gameEast.add(gameSouth, BorderLayout.SOUTH);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                gameCenter.add(b[i][j]);
            }
        }

        gamePanel.add(gameCenter, BorderLayout.CENTER);
        gamePanel.add(gameEast, BorderLayout.EAST);

        gameEast.add(gameSouth, BorderLayout.SOUTH);
        gamePanel.add(gameEast, BorderLayout.EAST);

        tabPane.addTab("GAME", gamePanel);
    }

    public Jugador createJugador() {
        Icon op1 = new ImageIcon("images/avatar.jpg");
        Icon op2 = new ImageIcon("images/avatar2.png");
        Icon op3 = new ImageIcon("images/avatar3.jpg");
        Icon op4 = new ImageIcon("images/avatar4.jpg");

        String nombre = JOptionPane.showInputDialog(null, "Escriba un nombre para el jugador");

        Object jugador = JOptionPane.showInputDialog(null, "Selecione un icono para el jugador",
                "iconos", JOptionPane.QUESTION_MESSAGE, null, new Object[]{op1, op2, op3, op4}, null);

        Jugador temp = new Jugador(nombre, (Icon) jugador);

        return temp;

    }

    public void Turno() {

        Random random = new Random();

        int n = random.nextInt(2);

        if (n == 0) {

            Jugador1.setTurno(1);
            Jugador2.setTurno(0);
            JBjugador2.setEnabled(false);
            JBjugador1.setEnabled(true);
        } else if (n == 1) {
            Jugador1.setTurno(0);
            Jugador2.setTurno(1);
            JBjugador2.setEnabled(false);
            JBjugador1.setEnabled(true);
        }
    }

    public void createObstaculos() {
        for (int i = 0; i < portal.length; i++) {
            Random randomInit = new Random();
            Random randomEnd = new Random();
            int inicio = randomInit.nextInt(100);
            int fin = randomEnd.nextInt(8) + inicio;
            Random randomType = new Random();
            int type = randomType.nextInt(2);
            portal[i] = new Obstaculos(inicio, fin, type);

            for (int k = 0; k < 10; k++) {
                for (int j = 0; j < 10; j++) {
                    if (game[k][j] == inicio) {
                        game[k][j] = i + 1000;

                    }
                    if (game[k][j] == fin) {
                        game[k][j] = i + 2000;
                    }
                }
            }
        }
    }
    //public String Sonido(){

   

    @Override
    public void actionPerformed(ActionEvent e) {
        int n = 0;
        try {
            if (e.getSource() == start) {
                Jugador1.setValor(0);
                Jugador2.setValor(0);
                start.setEnabled(false);
                restart.setEnabled(true);
                Turno();

            } else if (e.getSource() == restart) {
                tabPane.removeAll();
                game();

            } else if (e.getSource() == JBjugador1) {
                do {
                    dado.playerPassNumber();
                    dadol.setIcon(dado.getDado());
                    n = dado.getNumber();
                } while (n == 0);
                int an = Jugador1.getValor();
                Jugador1.setValor(Jugador1.getValor() + n);

                if (Jugador1.getValor() >= 100) {
                    Jugador1.setValor(100);

                    JOptionPane.showMessageDialog(null, "felicidades ganaste " + Jugador1.getName()
                            + "\n tuviste " + Jugador1.getLanzamientos() + " lanzamientos",
                            "GANADOR", JOptionPane.PLAIN_MESSAGE, Jugador1.getJugador());

                    tabPane.removeAll();
                    game();
                }
                   Jugador1.setLanzamientos(1);
                 
            
            JBjugador2.setEnabled(true);
            JBjugador1.setEnabled(false);
            System.out.println("valor a mover " + Jugador1.getValor());

            playerMove(Jugador1.getValor(), an);
        }else if (e.getSource() == JBjugador2) {
                do {
                    dado.playerPassNumber();
                    dadol.setIcon(dado.getDado());
                    n = dado.getNumber();
                } while (n == 0);
                int an = Jugador1.getValor();
                Jugador2.setValor(Jugador2.getValor() + n);

                if (Jugador2.getValor() >= 100) {
                    Jugador2.setValor(100);
                     JOptionPane.showMessageDialog(null, "felicidades ganaste " + Jugador2.getName()
                            + "\n tuviste " + Jugador2.getLanzamientos() + " lanzamientos",
                            "GANADOR", JOptionPane.PLAIN_MESSAGE, Jugador2.getJugador());

                    tabPane.removeAll();
                    game();
                }

                  Jugador2.setLanzamientos(1);
                  
                JBjugador2.setEnabled(false);
                JBjugador1.setEnabled(true);
                System.out.println("valor a mover " + Jugador2.getValor());

                playerMove(Jugador2.getValor(), an);
            }

    }
    catch (Exception ee){

    
   

        }
    
    }

    public void playerTemp(int end) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                path = game[i][j];
                if (path >= 2000) {
                    System.out.println("entre final en 2000");
                    if (portal[(path - 2000)].getFin() == end) {
                        b[i][j].setText(null);
                        b[i][j].setIcon(null);
                        System.out.println(portal[(path - 2000)].getFin());
                        Jugador1.setValor(portal[(path - 2000)].getFin());
                        b[i][j].setEnabled(true);
                        b[i][j].setIcon(Jugador1.getJugador());
                        break;
                    }
                } else if (path >= 1000) {
                    System.out.println("entre final en 1000");

                    if (portal[(path - 1000)].getFin() == end) {
                        b[i][j].setText(null);
                        b[i][j].setIcon(null);
                        System.out.println(portal[(path - 1000)].getFin());
                        Jugador1.setValor(portal[(path - 1000)].getFin());
                        b[i][j].setEnabled(true);
                        b[i][j].setIcon(Jugador1.getJugador());
                        break;
                    }
                }
            }
        }
    }
  
    public void computerTemp(int end) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                path = game[i][j];
                if (path >= 2000) {
                    System.out.println("entre final en 2000");
                    if (portal[(path - 2000)].getFin() == end) {
                        b[i][j].setText(null);
                        b[i][j].setIcon(null);
                        System.out.println(portal[(path - 2000)].getFin());
                        Jugador2.setValor(portal[(path - 2000)].getFin());
                        b[i][j].setEnabled(true);
                        b[i][j].setIcon(Jugador2.getJugador());
                        break;
                    }
                } else if (path >= 1000) {
                    System.out.println("entre final en 1000");

                    if (portal[(path - 1000)].getFin() == end) {
                        b[i][j].setText(null);
                        b[i][j].setIcon(null);
                        System.out.println(portal[(path - 1000)].getFin());
                        Jugador2.setValor(portal[(path - 1000)].getFin());
                        b[i][j].setEnabled(true);
                        b[i][j].setIcon(Jugador2.getJugador());
                        break;
                    }
                }
            }
        }
    }

    public void playerMove(int n, int an) {
       
        if (Jugador1.getTurno() == 1) {
            
            Jugador1.setTurno(0);
            Jugador2.setTurno(1);
                       
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    path = game[i][j];
                    if (game[i][j] == n) {
                        System.out.println("entre");
                        b[i][j].setEnabled(true);
                        b[i][j].setIcon(Jugador1.getJugador());
                        break;
                    } else if (path >= 2000) {
                        System.out.println("entre en 2000");
                        if (portal[(path - 2000)].getInicio() == n) {
                            System.out.println(portal[(path - 2000)].getInicio());
                            playerTemp(portal[(path - 2000)].getFin());
                            break;
                        }
                    } else if (path >= 1000) {
                        System.out.println("entre en 1000");
                        if (portal[(path - 1000)].getInicio() == n) {
                            System.out.println(portal[(path - 1000)].getInicio());
                            playerTemp(portal[(path - 1000)].getFin());
                            break;
                        }
                    }

                }

            }
        } else if (Jugador2.getTurno() == 1){
            
            Jugador1.setTurno(1);
            Jugador2.setTurno(0);
                      
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    path = game[i][j];
                    if (game[i][j] == n) {
                        System.out.println("entre");
                        b[i][j].setEnabled(true);
                        b[i][j].setIcon(Jugador2.getJugador());
                        break;
                    } else if (path >= 2000) {
                        System.out.println("entre en 2000");
                        if (portal[(path - 2000)].getInicio() == n) {
                            System.out.println(portal[(path - 2000)].getInicio());
                            computerTemp(portal[(path - 2000)].getFin());
                            break;
                        }
                    } else if (path >= 1000) {
                        System.out.println("entre en 1000");
                        if (portal[(path - 1000)].getInicio() == n) {
                            System.out.println(portal[(path - 1000)].getInicio());
                            computerTemp(portal[(path - 1000)].getFin());
                            break;
                        }
                    }

                }

            
            }
        }   

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                path = game[i][j];
                String str = Integer.toString(path);
                if (game[i][j] == an) {
                    b[i][j].setText(null);
                    b[i][j].setIcon(null);
                    b[i][j].setEnabled(false);
                    b[i][j].setText(str);
                    break;
                } else if (path >= 2000) {
                    System.out.println("entre en 2000");
                    if (portal[(path - 2000)].getInicio() == an) {
                        b[i][j].setText(null);
                        b[i][j].setIcon(null);
                        b[i][j].setIcon(portal[(path - 2000)].getPortalEnd());
                        break;
                    }
                } else if (path >= 1000) {
                    System.out.println("entre en 1000");
                    if (portal[(path - 1000)].getInicio() == an) {
                        b[i][j].setText(null);
                        b[i][j].setIcon(null);
                        b[i][j].setIcon(portal[(path - 1000)].getPortalInit());

                        break;
                    }
                }
            }
        }
    }
        
    public void repint() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                b[i][j] = new JButton();
                //b[i][j].addActionListener(this);        
                path = game[i][j];
                str = Integer.toString(path);

                if (path >= 2000) {
                    b[i][j].setIcon(portal[(path - 2000)].getPortalEnd());
                    b[i][j].setText(str);
                } else if (path >= 1000) {
                    b[i][j].setIcon(portal[(path - 1000)].getPortalInit());
                    b[i][j].setText(str);
                } else if (path == 200) {
                    b[i][j].setIcon(Jugador1.getJugador());
                } else if (path == 300) {
                    b[i][j].setIcon(Jugador2.getJugador());
                } else {

                    b[i][j].setBackground(Color.red);
                    b[i][j].setText(str);
                    b[i][j].setEnabled(false);
                }

            }
        }
    }

}
