/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : VendorCodeBCImpl.java
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
import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.integration.VendorCodeDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.vo.VendorCodeVO;

/**
 * ALPS-VendorCode Business Logic Command Interface<br>
 * - ALPS-VendorCode에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */
public class VendorCodeBCImpl extends BasicCommandSupport implements VendorCodeBC {

	// Database Access Object
	private transient VendorCodeDBDAO dbDao = null;

	/**
	 * VendorCodeBCImpl 객체 생성<br>
	 * VendorCodeDBDAO를 생성한다.<br>
	 */
	public VendorCodeBCImpl() {
		dbDao = new VendorCodeDBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param VendorCodeVO vendorCodeVO
	 * @return List<VendorCodeVO>
	 * @exception EventException
	 */
	public List<VendorCodeVO> search(VendorCodeVO vendorCodeVO) throws EventException {
		try {
			return dbDao.search(vendorCodeVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
}