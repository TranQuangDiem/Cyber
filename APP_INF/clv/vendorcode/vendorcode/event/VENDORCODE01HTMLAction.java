/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : VENDORCODE01HTMLAction.java
*@FileTitle : VendorCode
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.vendorcode.vendorcode.event;

import javax.servlet.http.HttpServletRequest;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.vo.VendorCodeVO;

/**
 * HTTP Parser<br>
 * -Parsing the Value of the HTML DOM object sent to the server through the com.clt.apps.opus.esm.clv.vendorcode screen as a Java variable<br>
 * - Parsing information is converted into Event, put in request and executed by VendorCodeSC<br>
 * - Set EventResponse in request to send execution result from VendorCodeSC to View (JSP)<br>
 * @author Diem Tran
 * @see VendorCodeEvent 참조
 * @since J2EE 1.6
 */

public class VENDORCODE01HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * Create a VENDORCODE01HTMLAction object
	 */
	public VENDORCODE01HTMLAction() {}

	/**
	 * Parsing the HTML DOM object's Value as a Java variable<br>
	 * Parsing the information of HttpRequst as VendorCodeEvent and setting it in the request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event An object that implements the Event interface.
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		Vendorcode01Event event = new Vendorcode01Event();
		

		if(command.isCommand(FormCommand.SEARCH)) {
			event.setVendorCodeVO((VendorCodeVO)getVO(request, VendorCodeVO .class));
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