/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1iatask1;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author ALVARO
 */
public class AnalisisImagen {
    private String  imagen; 
    private int height; 
    private int width; 
    private String [][] matrix; 
    private int n=20; 


    public AnalisisImagen(String  imagen, int n) {
        this.imagen = imagen;
        this.n=n; 
    }

    public AnalisisImagen() {
    }
    
    public void discretizar() throws IOException{
      
      File f = new File(imagen);
      if(f.exists()){
          System.out.println("asdkjasdkj");
      }
      BufferedImage img = ImageIO.read(f);
      width=img.getWidth();
      height=img.getHeight();
      matrix=new String[n][n];
      int cantPixel=(img.getHeight()/n)*(img.getWidth()/n);
      System.out.println(cantPixel);
      for(int l=0; l<n;l++){
          for(int l1=0;l1<n;l1++){
              int pixelColorV=0; 
              int pixelColorR=0; 
              int pixelColorN=0; 
                for(int i=0; i<img.getHeight()/n;i++){
                    for(int j=0; j<img.getWidth()/n;j++){
                        int p = img.getRGB((l1*(img.getWidth()/n))+j,(l*(img.getHeight()/n))+i);
                        int r = (p>>16) & 0xff;
                        //get green
                        int g = (p>>8) & 0xff;
                        //get blue
                        int b = p & 0xff;
                        if(g>200 && r<100 && b<100){
                            pixelColorV++;
                            //System.out.println("simon");
                        }
                        if(g<50 && r<50 && b<50){
                            pixelColorN++;
                            //System.out.println("simon");
                        }
                        if(g<100 && r>200 && b<100){
                            pixelColorR++;
                            //System.out.println("simon");
                        }
                    }
                }
                //System.out.println(pixelColorV);
                if(pixelColorN>(cantPixel/2)){
                    matrix[l1][l]="N";
                }
                if(pixelColorV>(cantPixel/15)){
                    matrix[l1][l]="V";
                }
                if(pixelColorR>(cantPixel/15)){
                    matrix[l1][l]="R";
                }
          }
      }
      BufferedImage imagen = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
      for(int l=0; l<n;l++){
          for(int l1=0;l1<n;l1++){
              int a=255;
              int r1=255; 
              int g1=255; 
              int b1=255; 
              if("N".equals(matrix[l1][l])){
                r1=0; 
                g1=0; 
                b1=0;
              }
              if("V".equals(matrix[l1][l])){
                r1=0; 
                g1=255; 
                b1=0;
              }
              if("R".equals(matrix[l1][l])){
                r1=255; 
                g1=0; 
                b1=0;
              }
                for(int i=0; i<img.getHeight()/n;i++){
                    for(int j=0; j<img.getWidth()/n;j++){
                        int p=0;
                        p=(a<<24) | (r1<<16) | (g1<<8) | b1;
                        imagen.setRGB((l1*(img.getWidth()/n))+j,(l*(img.getHeight()/n))+i, p);
                    }
                }
            }
      }
        
      ImageIO.write(imagen, "jpg", new File("discretizacion.jpg"));
      
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }
    
    public void getImageSolution(Path solution, String nombre) throws IOException{
        ArrayList<State> ruta=solution.getRuta();
        BufferedImage imagen = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
        for(int l=0; l<n;l++){
            for(int l1=0;l1<n;l1++){
                int a=255;
                int r1=255; 
                int g1=255; 
                int b1=255; 
                if("N".equals(matrix[l1][l])){
                    r1=0; 
                    g1=0; 
                    b1=0;
                }
                if("V".equals(matrix[l1][l])){
                    r1=0; 
                    g1=255; 
                    b1=0;
                }
                if("R".equals(matrix[l1][l])){
                    r1=255; 
                    g1=0; 
                    b1=0;
                }
                for(int i=0; i<height/n;i++){
                    for(int j=0; j<width/n;j++){
                        int p=0;
                        p=(a<<24) | (r1<<16) | (g1<<8) | b1;
                        imagen.setRGB((l1*(width/n))+j,(l*(height/n))+i, p);
                    }
                }
            }
        }
        int a=255;
        int r1=0; 
        int g1=0; 
        int b1=255;
        System.out.println(ruta.size());
        for(int i=1;i<ruta.size()-1;i++){
            State sta=ruta.get(i);
            for(int l=0; l<height/n;l++){
                    for(int j=0; j<width/n;j++){
                        int p=0;
                        p=(a<<24) | (r1<<16) | (g1<<8) | b1;
                        imagen.setRGB((sta.getX()*(width/n))+j,(sta.getY()*(height/n))+l, p);
                    }
                }
        }
        System.out.println("imagen generada");
        ImageIO.write(imagen, "jpg", new File(nombre));
    }
     
}

    
    
    
    
    

