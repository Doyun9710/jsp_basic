package com.exam.event;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class SessionAttributeListenerEx01
 *
 */
@WebListener
public class SessionAttributeListenerEx01 implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public SessionAttributeListenerEx01() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	System.out.println( "추가" );
    	System.out.println( event.getName() + " : " + event.getValue() );
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	System.out.println( "삭제" );
    	System.out.println( event.getName() + " : " + event.getValue() );
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	System.out.println( "변경" );
    	System.out.println( event.getName() + " : " + event.getValue() );
    }
	
}
