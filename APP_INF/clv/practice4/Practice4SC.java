/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice4SC.java
*@FileTitle : Practice4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.05
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.05 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4;

import java.util.List;

import com.clt.apps.opus.esm.clv.practice4.practice4.basic.Practice4BC;
import com.clt.apps.opus.esm.clv.practice4.practice4.basic.Practice4BCImpl;
import com.clt.apps.opus.esm.clv.practice4.practice4.event.Practice4Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice4.practice4.vo.Practice4VO;


/**
 * ALPS-Practice4 Business Logic ServiceCommand - ALPS-Practice4 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Diem Tran
 * @see Practice4DBDAO
 * @since J2EE 1.6
 */

public class Practice4SC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * Practice4 system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 */
	public void doStart() {
		log.debug("Practice4SC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * Practice4 system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("Practice4SC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-Practice4 system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("Practice4Event")) {
			if (e.getFormCommand().isCommand(FormCommand.DEFAULT)){
				eventResponse = searchCarrierAndLane(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = search(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manager(e);
			}
			
		}
		return eventResponse;
	}
	/**
	 * Practice4 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * search data
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse search(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice4Event event = (Practice4Event)e;
		Practice4BC command = new Practice4BCImpl();

		try{
			List<Practice4VO> list = command.search(event.getPractice4VO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * get list carrier and lane
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchCarrierAndLane(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice4Event event = (Practice4Event)e;
		Practice4BC command = new Practice4BCImpl();

		try{
			String list = command.searchCarrier();
			eventResponse.setETCData("carrier",list);;
			list = command.searchLane();
			eventResponse.setETCData("lane",list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * Practice4 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * insert, delete, update data
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manager(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice4Event event = (Practice4Event)e;
		Practice4BC command = new Practice4BCImpl();
		try{
			begin();
			command.manager(event.getPractice4VOS(),account);
			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
}