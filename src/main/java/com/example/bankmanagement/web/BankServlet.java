package com.example.bankmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bankmanagement.dao.BankDAO;
import com.example.bankmanagement.model.Bank;
@WebServlet("/")
public class BankServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BankDAO bankDAO;

    public void init() {
        bankDAO = new BankDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        String action = request.getServletPath();

    	        try {
    	            switch (action) {
    	                case "/new":
    	                    showNewForm(request, response);
    	                    break;
    	                case "/insert":
    	                    insertBank(request, response);
    	                    break;
    	                case "/delete":
    	                    deleteBank(request, response);
    	                    break;
    	                case "/edit":
    	                    showEditForm(request, response);
    	                    break;
    	                case "/update":
    	                    updateBank(request, response);
    	                    break;
    	                default:
    	                    listBank(request, response);
    	                    break;
    	            }
    	        } catch (SQLException ex) {
    	            throw new ServletException(ex);
    	        }
    	    }
    private void listBank(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	        List < Bank > listBank = bankDAO.selectBankdetails();
    	        request.setAttribute("listBank", listBank);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("bank-list.jsp");
    	        dispatcher.forward(request, response);
    	    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("bank-form.jsp");
    	        dispatcher.forward(request, response);
    	    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        Bank existingBank = bankDAO.selectBank(id);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("bank-form.jsp");
    	        request.setAttribute("bank", existingBank);
    	        dispatcher.forward(request, response);

    	    }
    private void insertBank(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	       
    	        String account_holder = request.getParameter("account_holder");
    	        String account_number = request.getParameter("account_number");
    	        String phone_number = request.getParameter("phone_number");
    	        int balence = Integer.parseInt(request.getParameter("balence"));
    	        
    	        Bank newBank = new Bank(account_holder,account_number,phone_number,balence);
    	        bankDAO.insertBank(newBank);
    	        response.sendRedirect("list");
    	    }
    private void updateBank(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String account_holder = request.getParameter("account_holder");
        String account_number = request.getParameter("account_number");
        String phone_number = request.getParameter("phone_number");
        int balence = Integer.parseInt(request.getParameter("balence"));
        


    	        Bank book= new Bank(id,account_holder,account_number,phone_number,balence);
    	        bankDAO.updateBank(book);
    	        response.sendRedirect("list");
    	    }
    private void deleteBank(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        bankDAO.deleteBank(id);
    	        response.sendRedirect("list");

    	    }
    	}