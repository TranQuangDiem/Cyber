/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice3SC.java
*@FileTitle : Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.practice3.practice3.basic.Practice3BC;
import com.clt.apps.opus.esm.clv.practice3.practice3.basic.Practice3BCImpl;
import com.clt.apps.opus.esm.clv.practice3.practice3.event.Practice003Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice3.practice3.vo.Practice3DetailV0VO;
import com.clt.apps.opus.esm.clv.practice3.practice3.vo.Practice3VO;


/**
 * ALPS-Practice3 Business Logic ServiceCommand - Process business transaction for ALPS-Practice3.
 * 
 * @author Diem Tran
 * @see Practice3DBDAO
 * @since J2EE 1.6
 */

public class Practice3SC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 *Practice3 system Work Scenario Prior Work<br>
	 *Creating related internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("Start Practice3SC");
		try {
			// First comment --> Login check part
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * Practice3 system work scenario finishing work<br>
	 * Release related internal objects when the work scenario is finished<br>
	 */
	public void doEnd() {
		log.debug("Exit Practice3SC");
	}

	/**
	 * Carry out business scenarios for each event<br>
	 * Branch processing of all events occurring in ALPS-Practice3 system work<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// The part to use when SC handles multiple events
		if (e.getEventName().equalsIgnoreCase("Practice003Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = search(e);
			}else if(e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = searchPartner(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = searchPartner(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manager(e);
			}else if (e.getFormCommand().isCommand(FormCommand.SEARCH02)) {
				eventResponse = searchLane(e);
			}else if (e.getFormCommand().isCommand(FormCommand.SEARCH03)) {
				eventResponse = searchTrade(e);
			}else if (e.getFormCommand().isCommand(FormCommand.SEARCH04)) {
				eventResponse = searchDetail(e);
			}
		}
		return eventResponse;
	}
	/**
	 * 
	 * search data
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse search(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice003Event event = (Practice003Event)e;
		Practice3BC command = new Practice3BCImpl();
		Map<String, String> param = new HashMap<String, String>();
		try{
			param.put("fr_acct_yrmon", event.getFryrmon());
			param.put("to_acct_yrmon", event.getToyrmon());
			param.put("jo_crr_cds", event.getPartners());
			param.put("rlane_cd", event.getLane());
			param.put("trd_cd", event.getTrades());
			List<Practice3VO> list = command.search(param);
			eventResponse.setRsVoList(list);
			eventResponse.setETCData("toal", command.total(param));
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * 
	 * search data detail
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchDetail(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice003Event event = (Practice003Event)e;
		Practice3BC command = new Practice3BCImpl();
		Map<String, String> param = new HashMap<String, String>();
		try{
			param.put("fr_acct_yrmon", event.getFryrmon());
			param.put("to_acct_yrmon", event.getToyrmon());
			param.put("jo_crr_cds", event.getPartners());
			param.put("rlane_cd", event.getLane());
			param.put("trd_cd", event.getTrades());
			List<Practice3DetailV0VO> list = command.searchDetail(param);
			eventResponse.setRsVoList(list);
			eventResponse.setETCData("toal", command.total(param));
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 *  search partner
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchPartner(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice3BC command = new Practice3BCImpl();

		try{
			String[] list = command.searchPartner();
			eventResponse.setETCData("partner", java.util.Arrays.toString(list));
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 *  get list lane by partner
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchLane(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice003Event event = (Practice003Event)e;
		Practice3BC command = new Practice3BCImpl();
		
		StringBuilder lane = new StringBuilder();
		String temp = "";
		
		try{
			Map<String, String> list = command.searchLane(event.getPartners());
			
			for(Map.Entry<String, String> entry : list.entrySet()){
				lane.append("|"+entry.getValue());
			}
			if(lane.toString().length()>0){
				temp = lane.toString().substring(1);
			}
			eventResponse.setETCData("laneCb", temp);			
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * get list trade by partner and lane
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchTrade(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice003Event event = (Practice003Event)e;
		Practice3BC command = new Practice3BCImpl();
		
		StringBuilder trade = new StringBuilder();
		String temp = "";

		try{
			Map<String, String> list = command.searchTrade(event.getLane(),event.getPartners());
			for(Map.Entry<String, String> entry : list.entrySet()){
				trade.append("|"+entry.getValue());
			}
			if(trade.toString().length()>0){
				temp = trade.toString().substring(1);
			}
			eventResponse.setETCData("tradeCb", temp);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * update,insert, delete for table master
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manager(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice003Event event = (Practice003Event)e;
		Practice3BC command = new Practice3BCImpl();
		try{
			begin();
			command.manager(event.getPractice3VOS(),account);
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