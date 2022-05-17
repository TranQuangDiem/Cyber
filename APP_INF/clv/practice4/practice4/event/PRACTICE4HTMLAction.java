/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE4HTMLAction.java
*@FileTitle : Practice4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.05
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.05 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.practice4.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.practice4.practice4.vo.Practice4VO;

/**
 * HTTP Parser<br>
 * - Parsing the Value of the HTML DOM object sent to the server through the com.clt.apps.opus.esm.clv.practice4 screen as a Java variable<br>
 * - Parsing information is converted into Event, put in request and executed by Practice4SC<br>
 * - Set EventResponse to request to send execution result from Practice4SC to View (JSP)<br>
 * @author Diem Tran
 * @see Practice4Event 참조
 * @since J2EE 1.6
 */

public class PRACTICE4HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * Create a PRACTICE4HTMLAction object
	 */
	public PRACTICE4HTMLAction() {}

	/**
	 *Parsing HTML DOM object's Value into Java variable<br>
	 * Parsing the information of HttpRequst as Practice4Event and setting it in the request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event An object that implements the Event interface.
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		Practice4Event event = new Practice4Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setPractice4VOS((Practice4VO[])getVOs(request, Practice4VO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			event.setPractice4VO((Practice4VO)getVO(request, Practice4VO .class));
		}

		return  event;
	}

	/**
	 * Saving the business scenario execution result value in the attribute of HttpRequest<br>
	 * Setting the ResultSet that transmits the execution result from ServiceCommand to View (JSP) in the request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse An object that implements the EventResponse interface.
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Saving HttpRequest parsing result value in HttpRequest attribute<br>
	 * HttpRequest parsing result value set in request<br>
	 *
	 * @param request HttpServletRequest HttpRequest
	 * @param event An object that implements the Event interface.
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}