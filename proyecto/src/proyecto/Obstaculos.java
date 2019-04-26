/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class Obstaculos {

  public  Icon portalInit;
  public Icon portalEnd;
    public int inicio;
   public int fin;
   public int type;

   
   public Obstaculos(int inicio, int fin, int type){

         this.inicio = inicio;
         this.fin = fin;
         this.type = type;
         
         if(type == 1){
             
             portalInit = new ImageIcon("images/portal1.jpeg");
             portalEnd = new ImageIcon("images/portal2.jpeg");
             
         }else{
             portalInit = new ImageIcon("images/portal4.jpeg");
             portalEnd = new ImageIcon("images/portal3.jpeg");
         }


}
    public Icon getPortalInit() {
        return portalInit;
    }

    public void setPortalInit(Icon portalInit) {
        this.portalInit = portalInit;
    }

    public Icon getPortalEnd() {
        return portalEnd;
    }

    public void setPortalEnd(Icon portalEnd) {
        this.portalEnd = portalEnd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
   
    public int getInicio() {

        return inicio;
    }

    public void setIncio(int inicio) {

        this.inicio = inicio;

    }

    public int getFin() {

        return fin;
    }

    public void setFin(int fin) {

        this.fin = fin;
    }

}

























