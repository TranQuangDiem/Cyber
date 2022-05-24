/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CustomerDBDAO.java
*@FileTitle : Customer
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.customer.customer.integration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.customer.customer.basic.CustomerBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.customer.customer.vo.CustomerVO;


/**
 * ALPS CustomerDBDAO <br>
 * - JDBC operation to process ALPS-Customer system business logic.<br>
 * 
 * @author Diem Tran
 * @see CustomerBCImpl 참조
 * @since J2EE 1.6
 */
public class CustomerDBDAO extends DBDAOSupport {

	/**
	 * search data
	 * 
	 * @param CustomerVO customerVO
	 * @return List<CustomerVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<CustomerVO> search(CustomerVO customerVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CustomerVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(customerVO != null){
				Map<String, String> mapVO = customerVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CustomerDBDAOCustomerVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CustomerVO .class);
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