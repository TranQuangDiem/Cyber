/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE_TRNHTMLAction.java
*@FileTitle : ErrorMessageManager
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.07
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.07 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice_dou.errms.event;

import javax.servlet.http.HttpServletRequest;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

import com.clt.apps.opus.esm.clv.practice_dou.errms.vo.PracticeVO;

/**
 * HTTP Parser<br>
 * -Parsing the Value of the HTML DOM object sent to the server through the com.clt.apps.opus.esm.clv.practice_dou screen as a Java variable<br>
  * - Parsing information is converted into Event, put in request and executed as Practice_DOUSC<br>
  * - Set EventResponse to request to send execution result from Practice_DOUSC to View (JSP)<br>
 * @author Diem Tran
 * @see Practice_DOUEvent 참조
 * @since J2EE 1.6
 */

public class PRACTICE_TRNHTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * Create a PRACTICE_TRNHTMLAction object
	 */
	public PRACTICE_TRNHTMLAction() {}

	/**
	 * Parsing the HTML DOM object's Value as a Java variable<br>
	 * Parsing the information of HttpRequst as Practice_DOUEvent and setting it in the request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event An object that implements the Event interface.
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		PracticeTrnEvent event = new PracticeTrnEvent();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setErrMsgVOS((PracticeVO[])getVOs(request, PracticeVO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			event.setErrMsgVO((PracticeVO)getVO(request, PracticeVO .class));
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
	 * Save HttpRequest parsing result value in HttpRequest attribute<br>
	 * HttpRequest parsing result value set in request<br>
	 *
	 * @param request HttpServletRequest HttpRequest
	 * @param event An object that implements the Event interface.
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}