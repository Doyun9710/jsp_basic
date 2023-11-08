package com.exam.event;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class SecondListener implements ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		System.out.println( "추가" );
		System.out.println( event.getName() + " : " + event.getValue() );
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		System.out.println( "제거" );
		System.out.println( event.getName() + " : " + event.getValue() );
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		System.out.println( "수정" );
		System.out.println( event.getName() + " : " + event.getValue() );
	}

}
