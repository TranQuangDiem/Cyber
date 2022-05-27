/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : VendorCodeSC.java
*@FileTitle : VendorCode
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.vendorcode;

import java.util.List;
import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.basic.VendorCodeBC;
import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.basic.VendorCodeBCImpl;
import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.event.Vendorcode01Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.vo.VendorCodeVO;


/**
 * ALPS-VendorCode Business Logic ServiceCommand -Process business transaction for ALPS-VendorCode.
 * 
 * @author Diem Tran
 * @see VendorCodeDBDAO
 * @since J2EE 1.6
 */

public class VendorCodeSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * VendorCode system work scenario precedent work<br>
	 * Creating related internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("VendorCodeSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * VendorCode system work scenario finishing work<br>
	 * Release related internal objects when the work scenario is finished<br>
	 */
	public void doEnd() {
		log.debug("VendorCodeSC 종료");
	}

	/**
	 * Carry out business scenarios corresponding to each event<br>
	 * Branch processing of all events that occur in the ALPS-VendorCode system business<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// The part to use when SC handles multiple events
		if (e.getEventName().equalsIgnoreCase("Vendorcode01Event")) {
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
		Vendorcode01Event event = (Vendorcode01Event)e;
		VendorCodeBC command = new VendorCodeBCImpl();

		try{
			List<VendorCodeVO> list = command.search(event.getVendorCodeVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
}