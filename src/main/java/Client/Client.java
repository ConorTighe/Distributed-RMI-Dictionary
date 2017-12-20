package Client;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.rmi.NotBoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/SearchMenu")
public class Client extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    public Client() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/SearchMenu.jsp").forward(
				request, response);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getParameter("word") != null) {
	    	String word = request.getParameter("word");
	        String res;
	        System.out.println("The word to find: "+word);
	        
	        // Get handle on our pool
	        WorkerPool pool = WorkerPool.getInstance( );
	        // Create Job
	        LookupWorker wordWork = new LookupWorker(word.toLowerCase());
	        // Add job to pool
	        res = pool.addJob(wordWork);
	        request.getSession().setAttribute("result", res);
			response.sendRedirect("/SearchResults"); 
    	}
    	else if (request.getParameter("delete") != null) {
    		String word = request.getParameter("delete");
	        String res;
	        System.out.println("The word to delete: "+word);
	        
	        // Get handle on our pool
	        WorkerPool pool = WorkerPool.getInstance( );
	        // Create Job
	        WorkerPlan deleteWork = new DeleteWorker(word);
	        // Add job to pool
	        pool.addJob(deleteWork);
			System.out.println(deleteWork.toString());
			res = deleteWork.getServerResult();
	        request.getSession().setAttribute("result", res);
			response.sendRedirect("/SearchResults"); 
    	}
    	else if (request.getParameter("editWord") != null) {
    		String word = request.getParameter("editWord");
    		String newWord = request.getParameter("newWord");
	        String res;
	        System.out.println("The word to delete: "+word);
	        
	        // Get handle on our pool
	        WorkerPool pool = WorkerPool.getInstance( );
	        // Create Job
	        WorkerPlan editWork = new EditWordWorker(word, newWord);
	        // Add job to pool
	        pool.addJob(editWork);
			System.out.println(editWork.toString());
			res = editWork.getServerResult();
	        request.getSession().setAttribute("result", res);
			response.sendRedirect("/SearchResults"); 
    	}
    	else if (request.getParameter("editDef") != null) {
    		String def = request.getParameter("editDef");
    		String newDef = request.getParameter("newDef");
	        String res;
	        System.out.println("The word being edited: "+def);
	        
	        // Get handle on our pool
	        WorkerPool pool = WorkerPool.getInstance( );
	        // Create Job
	        WorkerPlan editDefWork = new EditDefinitionWorker(def, newDef);
	        // Add job to pool
	        pool.addJob(editDefWork);
			System.out.println(editDefWork.toString());
			res = editDefWork.getServerResult();
	        request.getSession().setAttribute("result", res);
			response.sendRedirect("/SearchResults"); 
    	}
    }   
}
