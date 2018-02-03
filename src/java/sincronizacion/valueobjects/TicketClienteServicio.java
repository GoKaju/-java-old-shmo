/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sincronizacion.valueobjects;

import java.util.Date;
import java.util.List;

/**
 *
 * @author D4V3
 */
public class TicketClienteServicio {

        private Integer ticsId;
        private String ticsRegistradopor;
        private Date ticsFechacambio;
        private int tickId;
        private int clseId;
//    private List<TicketClienteservicio> anotaciones;

        public Integer getTicsId() {
            return ticsId;
        }

        public void setTicsId(Integer ticsId) {
            this.ticsId = ticsId;
        }

        public String getTicsRegistradopor() {
            return ticsRegistradopor;
        }

        public void setTicsRegistradopor(String ticsRegistradopor) {
            this.ticsRegistradopor = ticsRegistradopor;
        }

        public Date getTicsFechacambio() {
            return ticsFechacambio;
        }

        public void setTicsFechacambio(Date ticsFechacambio) {
            this.ticsFechacambio = ticsFechacambio;
        }

        public int getTickId() {
            return tickId;
        }

        public void setTickId(int tickId) {
            this.tickId = tickId;
        }

        public int getClseId() {
            return clseId;
        }

        public void setClseId(int clseId) {
            this.clseId = clseId;
        }

    
        
    

    }
