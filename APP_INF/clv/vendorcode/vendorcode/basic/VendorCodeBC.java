/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : VendorCodeBC.java
*@FileTitle : VendorCode
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.vendorcode.vendorcode.basic;

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.vo.VendorCodeVO;

/**
 * ALPS-Vendorcode Business Logic Command Interface<br>
 * - ALPS-Vendorcode에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */

public interface VendorCodeBC {

	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param VendorCodeVO	vendorCodeVO
	 * @return List<VendorCodeVO>
	 * @exception EventException
	 */
	public List<VendorCodeVO> search(VendorCodeVO vendorCodeVO) throws EventException;
}