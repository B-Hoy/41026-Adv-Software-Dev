package uts.advsoft;
import uts.advsoft.Database;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent context_event){
		context_event.getServletContext().setAttribute("database", new Database()); // makes a new DB on server startup, avoids lag of doing this via JSP page
	}
	@Override
	public void contextDestroyed(ServletContextEvent context_event){
	}
}
