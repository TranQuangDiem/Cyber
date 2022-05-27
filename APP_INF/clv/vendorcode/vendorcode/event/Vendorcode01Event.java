/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Vendorcode01Event.java
*@FileTitle : VendorCode
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.vendorcode.vendorcode.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.vo.VendorCodeVO;


/**
 * VendorCode01 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * - Created in VendorCode01HTMLAction<br>
 * - Used as PDTO delivered to ServiceCommand Layer<br>
 *
 * @author Diem Tran
 * @see VendorCode01HTMLAction 참조
 * @since J2EE 1.6
 */

public class Vendorcode01Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object search condition and single event processing */
	VendorCodeVO vendorCodeVO = null;
	
	/** Table Value Object Multi Data processing */
	VendorCodeVO[] vendorCodeVOs = null;

	public Vendorcode01Event(){}
	
	public void setVendorCodeVO(VendorCodeVO vendorCodeVO){
		this. vendorCodeVO = vendorCodeVO;
	}

	public void setVendorCodeVOS(VendorCodeVO[] vendorCodeVOs){
		this. vendorCodeVOs = vendorCodeVOs;
	}

	public VendorCodeVO getVendorCodeVO(){
		return vendorCodeVO;
	}

	public VendorCodeVO[] getVendorCodeVOS(){
		return vendorCodeVOs;
	}

}