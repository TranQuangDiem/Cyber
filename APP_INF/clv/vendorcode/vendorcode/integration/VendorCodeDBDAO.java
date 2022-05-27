/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : VendorCodeDBDAO.java
*@FileTitle : VendorCode
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.23 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.vendorcode.vendorcode.integration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.basic.VendorCodeBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.vendorcode.vendorcode.vo.VendorCodeVO;


/**
 * ALPS VendorCodeDBDAO <br>
 * - JDBC operation to process ALPS-VendorCode system Business Logic.<br>
 * 
 * @author Diem Tran
 * @see VendorCodeBCImpl 참조
 * @since J2EE 1.6
 */
public class VendorCodeDBDAO extends DBDAOSupport {

	/**
	 * Search data
	 * 
	 * @param VendorCodeVO vendorCodeVO
	 * @return List<VendorCodeVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<VendorCodeVO> search(VendorCodeVO vendorCodeVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<VendorCodeVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(vendorCodeVO != null){
				Map<String, String> mapVO = vendorCodeVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new VendorCodeDBDAOVendorCodeVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, VendorCodeVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
}