/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice_DOUSC.java
*@FileTitle : ErrorMessageManager
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.07
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.07 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice_dou;

import java.util.List;
import com.clt.apps.opus.esm.clv.practice_dou.errms.basic.ErrMsBC;
import com.clt.apps.opus.esm.clv.practice_dou.errms.basic.ErrMsBCImpl;
import com.clt.apps.opus.esm.clv.practice_dou.errms.event.PracticeTrnEvent;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice_dou.errms.vo.PracticeVO;


/**
 * ALPS-Practice_DOU Business Logic ServiceCommand - ALPS-Process business transaction for Practice_DOU.
 * 
 * @author Diem Tran
 * @see ErrMsDBDAO
 * @since J2EE 1.6
 */

public class Practice_DOUSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * Practice_DOU system Work Scenario Prior Work<br>
	 * Creating related internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("Start Practice_DOUSC");
		try {
			// Once in the comment --> login check part
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * Practice_DOU system work scenario finishing work<br>
	 * Release related internal objects when the work scenario is finished<br>
	 */
	public void doEnd() {
		log.debug("Exit Practice_DOUSC");
	}

	/**
	 *Carry out business scenarios corresponding to each event<br>
	 * Branch processing of all events that occur in the ALPS-Practice_DOU system work<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// The part to use when SC handles multiple events
		if (e.getEventName().equalsIgnoreCase("PracticeTrnEvent")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchPractice(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = managePractice(e);
			}
		}
		return eventResponse;
	}
	/**
	 * search data
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchPractice(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		PracticeTrnEvent event = (PracticeTrnEvent)e;
		ErrMsBC command = new ErrMsBCImpl();

		try{
			List<PracticeVO> list = command.searchPractice(event.getErrMsgVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * INSERT, UPDATE, DELETE DATA
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse managePractice(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		PracticeTrnEvent event = (PracticeTrnEvent)e;
		ErrMsBC command = new ErrMsBCImpl();
		try{
			begin();
			command.managerPactice(event.getErrMsgVOS(),account);
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