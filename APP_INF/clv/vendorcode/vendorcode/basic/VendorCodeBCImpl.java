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
 * -Interface to business logic for ALPS-VendorCode<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */
public class VendorCodeBCImpl extends BasicCommandSupport implements VendorCodeBC {

	// Database Access Object
	private transient VendorCodeDBDAO dbDao = null;

	/**
	 * Create VendorCodeBCImpl object<br>
	 * Create VendorCodeDBDAO.<br>
	 */
	public VendorCodeBCImpl() {
		dbDao = new VendorCodeDBDAO();
	}
	/**
	 * Search data
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