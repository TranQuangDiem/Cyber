package com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.Practice2douVO;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.PracticeVO;
import com.clt.framework.component.util.JSPUtil;
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
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @param eventResponse
	 *            EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request
	 *            HttpServletRequest HttpRequest
	 * @param event
	 *            Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}

}
