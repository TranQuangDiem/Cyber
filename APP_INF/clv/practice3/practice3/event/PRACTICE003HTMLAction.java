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
 * - com.clt.apps.opus.esm.clv.practice3 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 Practice3SC로 실행요청<br>
 * - Practice3SC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Diem Tran
 * @see Practice3Event 참조
 * @since J2EE 1.6
 */

public class PRACTICE003HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * PRACTICE003HTMLAction 객체를 생성
	 */
	public PRACTICE003HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 Practice3Event로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
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
			event.setPartners(JSPUtil.getParameter(request, "jo_crr_cd", ""));
		}
		else if(command.isCommand(FormCommand.SEARCH03)){
			event.setPartners(JSPUtil.getParameter(request, "jo_crr_cd", ""));
			event.setLane(JSPUtil.getParameter(request, "rlane_cd", ""));
		}
		
		return  event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}