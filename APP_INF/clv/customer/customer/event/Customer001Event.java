/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Customer001Event.java
*@FileTitle : Customer
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.customer.customer.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.customer.customer.vo.CustomerVO;


/**
 * Customer001 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * - Created by Customer001HTMLAction<br>
 * - Used as PDTO delivered to ServiceCommand Layer<br>
 *
 * @author Diem Tran
 * @see Customer001HTMLAction 참조
 * @since J2EE 1.6
 */

public class Customer001Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object search condition and single event processing */
	CustomerVO customerVO = null;
	
	/** Table Value Object Multi Data processing */
	CustomerVO[] customerVOs = null;

	public Customer001Event(){}
	
	public void setCustomerVO(CustomerVO customerVO){
		this. customerVO = customerVO;
	}

	public void setCustomerVOS(CustomerVO[] customerVOs){
		this. customerVOs = customerVOs;
	}

	public CustomerVO getCustomerVO(){
		return customerVO;
	}

	public CustomerVO[] getCustomerVOS(){
		return customerVOs;
	}

}