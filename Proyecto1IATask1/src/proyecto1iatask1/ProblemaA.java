/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1iatask1;

import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author ALVARO
 */
public class ProblemaA extends Problema {
    int tipo=0; 

    public ProblemaA(String[][] matrix, int tipo) {
        super(matrix);
        this.tipo=tipo; 
        inicialState();
    }

   
    @Override
    public void inicialState() {
        ArrayList fin=new ArrayList();
        for(int i=0;i<matrix.length; i++){
            for (int j=0;j<matrix.length; j++) {
                if ("R".equals(matrix[j][i])) {
                    inicial=new State(true,false,j,i);
                }
                if("V".equals(matrix[j][i])){
                    fin.add(new State(false,true,j,i)); 
                }
            }
        }
        fina=new State[fin.size()];
        for(int i=0;i<fin.size();i++){
            fina[i]=(State)fin.get(i);
        }
    }

    @Override
    public Action[] actions(State s) {
        int x=s.getX();
        int y=s.getY();
        ArrayList acciones=new ArrayList();
        if(x<matrix.length-1){
            if(!"N".equals(matrix[x+1][y]))
                acciones.add(new Action(0));  
        }
        if(x>0){
            if(!"N".equals(matrix[x-1][y]))
                acciones.add(new Action(1));  
        }
        if(y>0){
            if(!"N".equals(matrix[x][y-1]))
                acciones.add(new Action(2));  
        }
        if(y<matrix.length-1){
            if(!"N".equals(matrix[x][y+1]))
                acciones.add(new Action(3));  
        }
        Action[] accions=new Action[acciones.size()];
        for(int i=0; i<accions.length; i++){
            accions[i]= (Action)acciones.get(i);
        }
        return accions; 
    
    }
    @Override
    public State result(State s, Action a) {
        State new_state=null; 
        if(a.getTipo()==0){
            new_state=new State(s.getX()+1,s.getY()); 
        }
        if(a.getTipo()==1){
            new_state=new State(s.getX()-1,s.getY()); 
        }
        if(a.getTipo()==2){
            new_state=new State(s.getX(),s.getY()-1);
        }
        if(a.getTipo()==3){
            new_state=new State(s.getX(),s.getY()+1);
        }
    return new_state; 
    }

    @Override
    public boolean goalTest(State s) {
        boolean finali=false;
        for(int i=0; i<fina.length; i++){
            if(s.getX()==fina[i].getX() && s.getY()==fina[i].getY()){
                return true;
            }
        }
        return finali; 
    }

    @Override
    public double pathCost(Path p) {
        double cost=p.getCost();
        double total=0.0; 
        //Heuristica 1 distancia lineal
        if(tipo==0){
            ArrayList<State> estados=p.getRuta();
            if(p.getRuta().isEmpty()){
                return p.getCost();
            }
            State  last=estados.get(estados.size()-1);
            ArrayList<Double> totales=new ArrayList(); 
            for (State fina1 : fina) {
                total=cost+ Math.sqrt(Math.pow(last.getX()-fina1.getX(),2)+Math.pow(last.getY()-fina1.getY(),2));
                totales.add(total);
            }
            double menor=1000000000; 
            for(int i=0; i<totales.size();i++){
                if(menor>totales.get(i)){
                    menor=totales.get(i);
                }
            }
            total=menor;
        }
        //Heuristica 2 distancia x + distancia en y
        else{
            ArrayList<State> estados=p.getRuta();
            State  last=estados.get(estados.size()-1);
            ArrayList<Double> totales=new ArrayList(); 
            for (State fina1 : fina) {
                total=cost+abs(last.getX()-fina1.getX())+abs(last.getY()-fina1.getY());
                totales.add(total);
            }
            double menor=1000000000; 
            for(int i=0; i<totales.size();i++){
                if(menor>totales.get(i)){
                    menor=totales.get(i);
                }
            }
            total=menor;
        }
        return total; 
    }

    @Override
    public double stepCost(State s, Action a, State s1) {
        return 1;
    }
  
}
