package com.clt.apps.opus.esm.clv.pratice2dou;

import java.util.List;

import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.basic.Practice2douBC;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.basic.Practice2douBCImpl;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.event.Practice2Event;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.Practice2douVO;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.PracticeVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class Practice2douSC extends ServiceCommandSupport{
	// Login User Information
		private SignOnUserAccount account = null;

		/**
		 * Practice2 system Work Scenario Prior Work<br>
		 * Creating related internal objects when calling a business scenario<br>
		 */
		public void doStart() {
			log.debug("start Practice2douSC");
			try {
				// First comment --> Login check part
				account = getSignOnUserAccount();
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
			}
		}

		/**
		 * Practice2 system work scenario finishing work<br>
		 * Release related internal objects when the work scenario is finished<br>
		 */
		public void doEnd() {
			log.debug("Exit Practice2douSC");
		}

		/**
		 * Carry out business scenarios corresponding to each event<br>
		 * Branch processing of all events that occur in the ALPS-Practice2 system work<br>
		 * 
		 * @param e Event
		 * @return EventResponse
		 * @exception EventException
		 */
		public EventResponse perform(Event e) throws EventException {
			// RDTO(Data Transfer Object including Parameters)
			EventResponse eventResponse = null;

			// The part to use when SC handles multiple events
			if (e.getEventName().equalsIgnoreCase("Practice2Event")) {
				if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
					eventResponse = searchPractice(e);
				}
				else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
					eventResponse = managerPractice(e);
				}
				else if(e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
					eventResponse = searchPracticeDetail(e);
				}else if(e.getFormCommand().isCommand(FormCommand.MULTI01)){
					eventResponse = managerPracticeDetail(e);
				}
			}
			return eventResponse;
		}
		/**
		 * search for table master
		 * @param Event e
		 * @return EventResponse
		 * @exception EventException
		 */
		private EventResponse searchPractice(Event e) throws EventException {
			// PDTO(Data Transfer Object including Parameters)
			GeneralEventResponse eventResponse = new GeneralEventResponse();
			Practice2Event event = (Practice2Event)e;
			Practice2douBC command = new Practice2douBCImpl();

			try{
				List<Practice2douVO> list = command.searchPractice(event.getPractice2douVO());
				eventResponse.setRsVoList(list);
			}catch(EventException ex){
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			}catch(Exception ex){
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			}	
			return eventResponse;
		}
		
		/**
		 * search for table detail
		 * @param e
		 * @return EventResponse
		 * @throws EventException
		 */
		private EventResponse searchPracticeDetail(Event e) throws EventException {
			// PDTO(Data Transfer Object including Parameters)
			GeneralEventResponse eventResponse = new GeneralEventResponse();
			Practice2Event event = (Practice2Event)e;
			Practice2douBC command = new Practice2douBCImpl();

			try{
				List<PracticeVO> list = command.searchPracticeDetail(event.getPracticeVO());
				eventResponse.setRsVoList(list);
			}catch(EventException ex){
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			}catch(Exception ex){
				throw new EventException(new ErrorHandler(ex).getMessage(),ex);
			}	
			return eventResponse;
		}
		/**
		 * update, insert, delete for table master
		 * @param e
		 * @return EventResponse
		 * @throws EventException
		 */
		private EventResponse managerPractice(Event e) throws EventException {
			// PDTO(Data Transfer Object including Parameters)
			GeneralEventResponse eventResponse = new GeneralEventResponse();
			Practice2Event event = (Practice2Event)e;
			Practice2douBC command = new Practice2douBCImpl();
			try{
				begin();
				command.managerPractice(event.getPractice2douVOs(),account);
//				eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
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
		/**
		 * update, insert, delete for table detail
		 * @param e
		 * @param e
		 * @return
		 * @throws EventException
		 */
		private EventResponse managerPracticeDetail(Event e) throws EventException {
			// PDTO(Data Transfer Object including Parameters)
			GeneralEventResponse eventResponse = new GeneralEventResponse();
			Practice2Event event = (Practice2Event)e;
			Practice2douBC command = new Practice2douBCImpl();
			try{
				begin();
				command.managerPracticeDetail(event.getPracticeVOs(),account);
//				eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
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
