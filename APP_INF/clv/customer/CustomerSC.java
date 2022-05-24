package com.clt.apps.opus.esm.clv.customer;

/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CustomerSC.java
*@FileTitle : Customer
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.23 
* 1.0 Creation
=========================================================*/

import java.util.List;

import com.clt.apps.opus.esm.clv.customer.customer.basic.CustomerBC;
import com.clt.apps.opus.esm.clv.customer.customer.basic.CustomerBCImpl;
import com.clt.apps.opus.esm.clv.customer.customer.event.Customer001Event;
import com.clt.apps.opus.esm.clv.customer.customer.vo.CustomerVO;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;


/**
 * ALPS-Customer Business Logic ServiceCommand - Process business transaction for ALPS-Customer.
 * 
 * @author Diem Tran
 * @see 
 * @since J2EE 1.6
 */

public class CustomerSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	* Customer system task scenario precedent work<br>
	* Creating related internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("CustomerSC start");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * Customer system work scenario closing work<br>
	 * Release related internal objects when the work scenario is finished<br>
	 */
	public void doEnd() {
		log.debug("CustomerSC end");
	}

	/**
	 * Carry out business scenarios for each event<br>
	 * Branch processing of all events occurring in the ALPS-Customer system business<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// The part to use when SC handles multiple events
		if (e.getEventName().equalsIgnoreCase("Customer001Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = search(e);
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
	private EventResponse search(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Customer001Event event = (Customer001Event)e;
		CustomerBC command = new CustomerBCImpl();

		try{
			List<CustomerVO> list = command.search(event.getCustomerVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
}