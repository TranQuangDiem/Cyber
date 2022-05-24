/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CUSTOMER001HTMLAction.java
*@FileTitle : Customer
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.customer.customer.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.customer.customer.vo.CustomerVO;

/**
 * HTTP Parser<br>
 * - Parsing the Value of the HTML DOM object sent to the server through the com.clt.apps.opus.esm.clv.customer screen as a Java variable<br>
 * - Convert the parsing information into an Event, put it in the request and request execution to CustomerSC<br>
 * - Set EventResponse to request to send execution result from CustomerSC to View (JSP)<br>
 * @author Diem Tran
 * @see CustomerEvent 참조
 * @since J2EE 1.6
 */

public class CUSTOMER001HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * Create a CUSTOMER001HTMLAction object
	 */
	public CUSTOMER001HTMLAction() {}

	/**
	 * Parsing HTML DOM object's Value into Java variable<br>
	 * Parsing the information of HttpRequest as CustomerEvent and setting it in the request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event An object that implements the Event interface.
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		Customer001Event event = new Customer001Event();
		

		if(command.isCommand(FormCommand.SEARCH)) {
			CustomerVO customerVO = new CustomerVO();
			customerVO.setCustSeq(JSPUtil.getParameter(request, "s_cust_seq"));
			customerVO.setCustCntCd(JSPUtil.getParameter(request, "s_cust_cnt_cd"));
			event.setCustomerVO(customerVO);
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