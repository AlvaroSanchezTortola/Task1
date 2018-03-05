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

public class ProblemaBFS extends Problema {

    public ProblemaBFS(String[][] matrix) {
        super(matrix);
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
        System.out.println("TAMAÑOOOO " +fina.length);
        for(int i=0;i<fin.size();i++){
            fina[i]=(State)fin.get(i);
            System.out.println(fina[i].getX()+" "+fina[i].getY());
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
            return new State(s.getX()+1,s.getY()); 
        }
        if(a.getTipo()==1){
            return new State(s.getX()-1,s.getY()); 
        }
        if(a.getTipo()==2){
            return new State(s.getX(),s.getY()-1);
        }
        if(a.getTipo()==3){
            return new State(s.getX(),s.getY()+1);
        }
    return new_state; 
    }

    @Override
    public boolean goalTest(State s) {
        boolean finali=false;
        for (State fina1 : fina) {
            if (s.getX() == fina1.getX() && s.getY() == fina1.getY()) {
                return true;
            }
        }
        return finali; 
    }

    @Override
    public double stepCost(State s, Action a, State s1) {
        return 1.0; 
    }

    @Override
    public double pathCost(Path p) {
        return p.getCost(); 
    }

   
  
}