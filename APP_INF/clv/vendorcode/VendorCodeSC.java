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
 * ALPS-VendorCode Business Logic ServiceCommand - ALPS-VendorCode 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Diem Tran
 * @see VendorCodeDBDAO
 * @since J2EE 1.6
 */

public class VendorCodeSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * VendorCode system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
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
	 * VendorCode system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("VendorCodeSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-VendorCode system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("Vendorcode01Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = search(e);
			}
		}
		return eventResponse;
	}
	/**
	 * VendorCode01 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
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