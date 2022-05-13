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
 * - com.clt.apps.opus.esm.clv.practice4 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 Practice4SC로 실행요청<br>
 * - Practice4SC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Diem Tran
 * @see Practice4Event 참조
 * @since J2EE 1.6
 */

public class PRACTICE4HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * PRACTICE4HTMLAction 객체를 생성
	 */
	public PRACTICE4HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 Practice4Event로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		Practice4Event event = new Practice4Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setPractice4VOS((Practice4VO[])getVOs(request, Practice4VO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
//			event.setCarrier(JSPUtil.getParameter(request, "carrier_text"));
//			event.setVendor(JSPUtil.getParameter(request, "vendor"));
//			event.setFrYm(JSPUtil.getParameter(request, "fr_ym"));
//			event.setToYm(JSPUtil.getParameter(request, "to_ym"));
			event.setPractice4VO((Practice4VO)getVO(request, Practice4VO .class));
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