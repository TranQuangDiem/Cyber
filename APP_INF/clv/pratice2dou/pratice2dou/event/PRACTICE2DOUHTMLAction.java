package com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.Practice2douVO;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.PracticeVO;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class PRACTICE2DOUHTMLAction extends HTMLActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create a PRACTICE_TRNHTMLAction object
	 */
	public PRACTICE2DOUHTMLAction() {
	}
	/**
	 * Parsing the HTML DOM object's Value as a Java variable<br>
	 * Parsing the information of HttpRequst as Practice_DOUEvent and setting it in the request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event An object that implements the Event interface.
	 * @exception HTMLActionException
	 */
	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		Practice2Event event = new Practice2Event();
		if (command.isCommand(FormCommand.MULTI)) {
			event.setPractice2douVOs((Practice2douVO[]) getVOs(request,Practice2douVO .class, ""));
		} else if (command.isCommand(FormCommand.SEARCH)) {
			event.setPractice2douVO((Practice2douVO) getVO(request,Practice2douVO .class));
		} else if(command.isCommand(FormCommand.SEARCH01)){
			event.setPracticeVO((PracticeVO)getVO(request, PracticeVO .class));
		} else if(command.isCommand(FormCommand.MULTI01)){
			event.setPracticeVOs((PracticeVO[]) getVOs(request,PracticeVO .class, ""));
		}

		return event;
	}

	/**
	/**
	 * Saving the value of the task scenario execution result in the attribute of HttpRequest<br>
	 * Setting the ResultSet that transmits the execution result from ServiceCommand to View (JSP) in the request<br>
	 *
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse An object that implements the EventResponse interface.
	 *
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
