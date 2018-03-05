/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1iatask1;

/**
 *
 * @author ALVARO
 */
public class Action {
    private int tipo; 
    //0 derecha
    //1 izquierda
    //2 arriba
    //3 abajo

    public Action(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
   
}
