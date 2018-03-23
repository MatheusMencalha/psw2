package br.cefetrj.psw.trabalho;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus
 * 
 * No doPost o servidor recebe os valores que o usuario escrever no formulario como parametro
 * e verifica(try) se todos foram preenchidos, se não(catch) o servidor escreve "erro", se sim escreve em
 * uma tabela o resultado do formulario.
 * 
 */
@WebServlet(name = "Servidor", urlPatterns = {"/Servidor"})
public class Servidor extends HttpServlet {

   ArrayList<Aluno> a = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           
        try{
                 
        String aluno = (String)req.getParameter("aluno");

        double p1 = Double.parseDouble(req.getParameter("p1"));  
        double trabalho = Double.parseDouble(req.getParameter("trabalho"));    
        double projeto = Double.parseDouble(req.getParameter("projeto"));    
        double pf = Double.parseDouble(req.getParameter("pf"));    
        double frequencia = Double.parseDouble(req.getParameter("frequencia")); 
        
        double m1 = (0.7 * ((p1 + trabalho) / 2)) + (projeto * 0.3);
  
    
        
        String situacao = situacao(frequencia, p1, trabalho, projeto, pf);
        
        a.add(new Aluno(aluno, p1, frequencia, trabalho, projeto, pf, m1, situacao));
        
        resp.getWriter().append("<html>");
        resp.getWriter().append("<head>");
        resp.getWriter().append("<style>");
        resp.getWriter().append("table{background-color: #66CDAA; border-collapse: collapse; font-family: Verdana 14; padding: 100px; margin-top: 100px; width: 1000px;}");
        resp.getWriter().append("</style>");
        resp.getWriter().append("</head>");
        
            resp.getWriter().append("<body>");
                resp.getWriter().append("<table border='1px solid black collapse' >");
                    resp.getWriter().append("<tr>");
                        resp.getWriter().append("<td>Aluno</td>");
                        resp.getWriter().append("<td>Frequencia</td>");
                        resp.getWriter().append("<td>P1</td>");
                        resp.getWriter().append("<td>Trabalho</td>");
                        resp.getWriter().append("<td>Projeto</td>");
                        resp.getWriter().append("<td>Média</td>");
                        resp.getWriter().append("<td>PF</td>");                       
                        resp.getWriter().append("<td>Situacao</td>");
                    
                    for (int i = 0; i < a.size(); i++) {
                
                    resp.getWriter().append("</tr>");
                    
                    resp.getWriter().append("<tr>");
                        resp.getWriter().append("<td>" + a.get(i).getNome() + "</td>");
                        resp.getWriter().append("<td>" + a.get(i).getFrequencia() + "</td>");
                        resp.getWriter().append("<td>" + a.get(i).getP1() + "</td>");
                        resp.getWriter().append("<td>" + a.get(i).getTrabalho() + "</td>");
                        resp.getWriter().append("<td>" + a.get(i).getProjeto() + "</td>");
                        resp.getWriter().append("<td>" + a.get(i).getM1() + "</td>");
                        resp.getWriter().append("<td>" + a.get(i).getPF() + "</td>");                        
                        resp.getWriter().append("<td>" + a.get(i).getSituacao() + "</td>");

      
                    resp.getWriter().append("</tr>");
                    
                    }  
                    
                resp.getWriter().append("</table>");
            resp.getWriter().append("</body>");
        resp.getWriter().append("</html>");
        
         
        }catch(Exception e){
            
            resp.getWriter().print("erro");
        }
        
    }
    
    /**
     * Aqui está o algoritmo de resultado que verifica se o aluno está aprovado ou reprovado,
     * calculando as notas que foram providas no formulario.
     */
    
    public String situacao(double frequencia, double p1, double trabalho, double projeto, double pf){
         double m1 = (0.7 * ((p1 + trabalho) / 2)) + (projeto * 0.3);
         String situacao;
         
        if(frequencia >= 75){
            if(m1 >= 7) 
                situacao= "Aprovado";
            else if (m1 < 3) 
                situacao = "Reprovado";
            else{
                
                if( ((pf + m1) / 2) >= 5)
                    situacao = "Aprovado";
                else
                    situacao = "Reprovado";
            }
        }else{
            situacao= "Reprovado";
        }
    
        return situacao;
    }

    

}