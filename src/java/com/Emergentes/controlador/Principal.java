/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Emergentes.controlador;

import com.Emergentes.modelo.Libro;
import com.Emergentes.modelo.LibroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mujica
 */
@WebServlet(name = "Principal", urlPatterns = {"/Principal"})
public class Principal extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses =request.getSession();
        LibroDAO gestor=(LibroDAO) ses.getAttribute("gestor");// Todos los atributos se almacenan en el atributo gestor
        //verificamos si el atributo existe
        if(gestor == null){
            // en caso de que no exista se incluye el atributo
            LibroDAO auxi= new LibroDAO();
            ses.setAttribute("gestor", auxi);
        }

        String op= request.getParameter("op");
        if(op == null){
            op="";
        }
        
        //el controlador verifica  la opcion para redireccionar al recurso correspondiente
        if(op.trim().equals("")){
            response.sendRedirect("vista/listado.jsp");
            
        }
         if(op.trim().equals("nuevo")){
             ses=request.getSession();
             // Todos los libros se almacenan en el atributo gestor
             Libro obj= new Libro();
             //Colocaos el id para que se muestre
             obj.setId(gestor.getCorrelativo()+1);
             request.setAttribute("item", obj);
             request.getRequestDispatcher("vista/nuevo.jsp").forward(request, response);
         }
          if(op.trim().equals("editar")){
          int pos=gestor.posicion(Integer.parseInt(request.getParameter ("id")));
          Libro obj=gestor.getLibros().get(pos);
          
          request.setAttribute("item", obj);
          request.getRequestDispatcher("vista/editar.jsp").forward(request, response);
          
          }
          if(op.trim().equals("eliminar")){
              int pos =gestor.posicion(Integer.parseInt(request.getParameter("id")));
              gestor.getLibros().remove(pos);
              //request.setAttribute("item",obj);
              response.sendRedirect("vista/listado.jsp");
          }
    }

   
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses=request.getSession();
        
        LibroDAO gestor=(LibroDAO) ses.getAttribute("gestor");
        Libro obj= new Libro();
        
        //Incrementando el contador
        //llena obj con los campos del formulario
        
        gestor.setCorrelativo(gestor.getCorrelativo()+1);
        obj.setId(Integer.parseInt(request.getParameter("id")));
        obj.setAutor(request.getParameter("autor"));
        obj.setTitulo(request.getParameter("titulo"));
        obj.setEstado(Integer.parseInt(request.getParameter("estado")));
        
        String tipo= request.getParameter("tipo");
        
        
        if(tipo.equals("-1")){
           obj.setId(gestor.getCorrelativo());
            gestor.insertar(obj);
        }else{
            gestor.modificar(obj.getId(), obj);
        }
        response.sendRedirect("vista/listado.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
