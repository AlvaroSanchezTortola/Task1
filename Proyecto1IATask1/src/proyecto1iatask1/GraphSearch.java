/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1iatask1;

import java.util.ArrayList;
import java.util.Stack;


/**
 *
 * @author ALVARO
 */
public class GraphSearch {
    private Problema problema;
 

    public GraphSearch(Problema problema) {
        this.problema = problema;
    }
    
    public Path getSolution(){
        Path patha = null;
         
        ArrayList<State> explorados=new ArrayList();
        
        ArrayList<Path> frontera=new ArrayList();
        
        
        Path aux=new Path(); 
        aux.addState(problema.inicial);
        
        frontera.add(aux);
        
        
        while(true){
            if(frontera.isEmpty()==false){
                
                System.out.println("Tamaño front: "+ frontera.size());
                System.out.println("Tamaño explorados: "+explorados.size());
               
                patha=frontera.remove(0);
                    
               
                State sta=patha.getEnd();
                System.out.println("actual: "+patha);
                explorados.add(sta);
                sta.setVisitado(true);
                patha.getEnd().setVisitado(true);
                if(problema.goalTest(sta)){
                    return patha;
                }
                
                Action[] acciones=problema.actions(sta);
                
                for (int n=0;n<acciones.length;n++) {
                    State resul=problema.result(sta,acciones[n]);
                    if(inExplored(resul,explorados)==false){
                        if(resul.isVisited()==false){
                            explorados.add(resul);
                            resul.setVisitado(true);
                            Path new_path= new Path();
                            new_path.addStates(patha.getRuta());
                            new_path.setCost(patha.getCost());
                            new_path.addState(resul);
                            new_path.addCost(new_path.getCost()+problema.stepCost(sta, acciones[n], resul));

                            frontera.add(new_path);
                            
                        }
                        //calcular el costo y agregar los antiguos
                    }
                }
            }
            else{
                System.out.println("FRONTERA VACIAAAAA");
                return null; 
            }
            
        }
        //return null;
    }
    
    public Path getSolutionStack() {
        Path patha = null;

        ArrayList<State> explorados = new ArrayList();

        
        Stack<Path> front = new Stack();

        Path aux = new Path();
        aux.addState(problema.inicial);

       
        front.push(aux);

        while (true) {
            //if(frontera.isEmpty()==false){
            if (front.empty() == false) {
                System.out.println("Tamaño front: " + front.size());
                System.out.println("Tamaño explorados: " + explorados.size());
                

               
                patha = front.pop();
                
                State sta = patha.getEnd();
                System.out.println("actual: " + patha);
                explorados.add(sta);
                sta.setVisitado(true);
                patha.getEnd().setVisitado(true);
                if (problema.goalTest(sta)) {
                    return patha;
                }

                Action[] acciones = problema.actions(sta);

                for (int n = 0; n < acciones.length; n++) {
                    State resul = problema.result(sta, acciones[n]);
                    if (inExplored(resul, explorados) == false) {
                        if (resul.isVisited() == false) {
                            explorados.add(resul);
                            resul.setVisitado(true);
                            Path new_path = new Path();
                            new_path.addStates(patha.getRuta());
                            new_path.setCost(patha.getCost());
                            new_path.addState(resul);
                            new_path.addCost(new_path.getCost() + problema.stepCost(sta, acciones[n], resul));

                          

                            front.push(new_path);
                        }
                        //calcular el costo y agregar los antiguos
                    }
                }
            } else {
                System.out.println("FRONTERA VACIAAAAA");
                return null;
            }

        }
        //return null;
    }
    
    public int getShortPath(ArrayList<Path> paths){
        int meno=-1;
        Path menor=new Path();
        menor.setCost(1000000000);
        int tamanio=paths.size();
        double pathmenor=problema.pathCost(menor);
        for(int i=0; i<tamanio;i++){
            if(problema.pathCost(paths.get(i))<pathmenor){
                menor=paths.get(i);
                pathmenor=problema.pathCost(menor);
                meno=i;
            }
        }
        return meno; 
    }

    public boolean inExplored(State s, ArrayList<State> explorados){
        int tamanio=explorados.size();
        int x=s.getX();
        int y=s.getY();
        for(int i=0; i<tamanio;i++){
            State aux=explorados.get(i);
            if(aux.getX()==x &&aux.getY()==y){
                return true; 
            }
        }
        return false; 
    }
    
    
}
