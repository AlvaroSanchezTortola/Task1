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
public abstract class Problema {
    protected String[][] matrix;
    protected State inicial; 
    protected State [] fina;

    public Problema(String[][] matrix) {
        this.matrix = matrix;
    }

    public abstract Action [] actions(State s);
    public abstract State result (State s, Action a); 
    public abstract boolean goalTest (State s);
    public abstract double stepCost (State s,Action a,State s1);
    public abstract double pathCost (Path p);
    public abstract void inicialState();
      
}
