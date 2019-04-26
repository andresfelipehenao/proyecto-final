/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import javax.swing.Icon;

/**
 *
 * @author Usuario
 */
public class Jugador {

    public String name;
    public Icon jugador;
    public int valor;
    public int turno;
    public int lanzamientos=0;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Jugador(String name, Icon jugador) {

        this.name = name;
        this.jugador = jugador;

    }

    public String getName() {

        return name;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Icon getJugador() {

        return jugador;
    }

    public void setJugador(Icon jugador) {

        this.jugador = jugador;
    }




 public int getLanzamientos() {
        return lanzamientos;
    }

    public void setLanzamientos(int lanzamientos) {
        this.lanzamientos += lanzamientos;
    }
}

