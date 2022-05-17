/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE003HTMLAction.java
*@FileTitle : Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.practice3.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.practice3.practice3.vo.Practice3VO;

/**
 * HTTP Parser<br>
 * - Parsing the Value of the HTML DOM object sent to the server through the com.clt.apps.opus.esm.clv.practice3 screen as a Java variable<br>
 * - Parsed information is converted into Event, put in request and executed by Practice3SC<br>
 * - Set EventResponse to request to send execution result from Practice3SC to View (JSP)<br>
 * @author Diem Tran
 * @see Practice3Event 참조
 * @since J2EE 1.6
 */

public class PRACTICE003HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * Create a PRACTICE003HTMLAction object
	 */
	public PRACTICE003HTMLAction() {}

	/**
	 *Parsing HTML DOM object's Value into Java variable<br>
	 * Parsing the information of HttpRequst as Practice3Event and setting it in the request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event An object that implements the Event interface.
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		Practice003Event event = new Practice003Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setPractice3VOS((Practice3VO[])getVOs(request, Practice3VO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			event.setPractice3VO((Practice3VO)getVO(request, Practice3VO .class));
			event.setFryrmon(JSPUtil.getParameter(request, "fr_yrmon", ""));
			event.setToyrmon(JSPUtil.getParameter(request, "to_yrmon", ""));
			event.setPartners(JSPUtil.getParameter(request, "partner_text", ""));
			event.setLane(JSPUtil.getParameter(request, "lane", ""));
			event.setTrades(JSPUtil.getParameter(request, "trade", ""));
		}
		else if(command.isCommand(FormCommand.SEARCH02)){
			event.setPartners(JSPUtil.getParameter(request, "partner_text", ""));
		}
		else if(command.isCommand(FormCommand.SEARCH03)){
			event.setPartners(JSPUtil.getParameter(request, "partner_text", ""));
			event.setLane(JSPUtil.getParameter(request, "lane", ""));
		}
		
		return  event;
	}

	/**
	 * Saving the value of the task scenario execution result in the attribute of HttpRequest<br>
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