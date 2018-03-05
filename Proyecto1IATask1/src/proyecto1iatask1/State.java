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
public class State {
    private boolean inicial=false; 
    private boolean fina=false;
    private boolean visitado=false;
    private int x; 
    private int y; 

    public State(boolean inicial, boolean fina, int x, int y) {
        this.inicial = inicial;
        this.fina = fina;
        this.x = x;
        this.y = y;
    }

    public State(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isInicial() {
        return inicial;
    }

    public void setInicial(boolean inicial) {
        this.inicial = inicial;
    }

    public boolean isFina() {
        return fina;
    }

    public void setFina(boolean fina) {
        this.fina = fina;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setVisitado(boolean v){
        this.visitado = true;
    }
    
    public boolean isVisited(){
        return visitado;
    }
}
