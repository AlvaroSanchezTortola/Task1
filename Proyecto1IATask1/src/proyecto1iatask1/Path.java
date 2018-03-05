/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1iatask1;
import java.util.ArrayList;

/**
 *
 * @author ALVARO
 */
public class Path {
    private double cost=0.0;
    private ArrayList<State> ruta; 

    public Path() {
        ruta=new ArrayList();
    }
    public State getEnd(){
        return ruta.get(ruta.size()-1);
    }
    public void addState(State s){
        ruta.add(s);
    }
    public void addStates(ArrayList<State> states){
        ruta.addAll(states);
    }
    public void addCost(double cos){
        cost=cost+cos; 
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<State> getRuta() {
        return ruta;
    }

    public void setRuta(ArrayList<State> ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        String result="";
        for(int i=0; i<ruta.size();i++){
            result=result+" X: "+ruta.get(i).getX()+" y: "+ruta.get(i).getY();
        }
        return result; 
    }
    
    
    
}
