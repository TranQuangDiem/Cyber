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
 * ALPS-Practice4 Business Logic ServiceCommand - Process business transaction for ALPS-Practice4.
 * 
 * @author Diem Tran
 * @see Practice4DBDAO
 * @since J2EE 1.6
 */

public class Practice4SC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * Practice4 system work scenario precedent work<br>
	 * Creating related internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("Start Practice4SC");
		try {
			// First comment --> Login check part
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 *Practice4 system work scenario finishing work<br>
	 * Release related internal objects when the work scenario is finished<br>
	 */
	public void doEnd() {
		log.debug("Exit Practice4SC");
	}

	/**
	 * Carry out business scenarios corresponding to each event<br>
	 * Branch processing of all events occurring in ALPS-Practice4 system work<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// The part to use when SC handles multiple events
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
//			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
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