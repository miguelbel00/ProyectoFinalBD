/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Access.IndicacionDAO;
import Access.CiudadDAO;
import Access.CiudadTarifaDAO;
import Access.ServicioDAO;
import Access.TarifaDAO;
import Controller.ControladorMain;
import Controller.ControladorServicio;
import Model.Ciudad;
import Model.CiudadTarifa;
import Model.Indicacion;
import Model.Servicio;
import Model.Tarifa;
import View.GuiMain;
import View.GuiRegisClien;
import View.GuiServicio;

/**
 *
 * @author migue
 */
public class Main {
    public static void main(String[] args) {
        
        GuiMain guiMain =new GuiMain();
        ControladorMain controladorMain = new ControladorMain(guiMain);
        guiMain.setVisible(true);
    }
}
