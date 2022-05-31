/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice4DBDAO.java
*@FileTitle : Practice4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.05
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.05 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.practice4.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.practice4.practice4.basic.Practice4BCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.practice4.practice4.vo.Practice4VO;


/**
 * ALPS Practice4DBDAO <br>
 * - JDBC operation to process ALPS-Practice4 system business logic.<br>
 * 
 * @author Diem Tran
 * @see Practice4BCImpl
 * @since J2EE 1.6
 */
public class Practice4DBDAO extends DBDAOSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * search data
	 * @param Practice4VO practice4VO
	 * @return List<Practice4VO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<Practice4VO> search(Practice4VO practice4VO) throws DAOException {
		DBRowSet dbRowset = null;
		List<Practice4VO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(practice4VO != null){
				Map<String, String> mapVO = practice4VO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				List<String> listCarrier = new ArrayList<String>();
				String [] carriers = practice4VO.getCarrier().split(",");
				for (String carrier : carriers) {
					listCarrier.add(carrier);
				}
				if(!"All".equalsIgnoreCase(listCarrier.get(0))&&!"".equalsIgnoreCase(listCarrier.get(0)))
					velParam.put("jo_crr_cds",listCarrier);
				else
					velParam.put("jo_crr_cds","All");
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new Practice4DBDAOPractice4VORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, Practice4VO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	 /**
	  * get list carrier
	  * @return String
	  * @throws DAOException
	  */
	 public String searchCarrier( ) throws DAOException {
			DBRowSet dbRowset = null;
			StringBuilder list = new StringBuilder();
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new Practice4DBDAOCarrierRSQL(), param, velParam);
				while(dbRowset.next()){
					list.append(dbRowset.getString("jo_crr_cd")+"|");
				}
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list.substring(0,list.length()-1).toString();
		}
	 /**
	  * get list lane
	  * @return String
	  * @throws DAOException
	  */
	 public String searchLane( ) throws DAOException {
			DBRowSet dbRowset = null;
			StringBuilder list = new StringBuilder();
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new Practice4DBDAOLaneRSQL(), param, velParam);
				while(dbRowset.next()){
					list.append(dbRowset.getString("rlane_cd")+"|");
				}
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list.substring(0,list.length()-1).toString();
		}
	/**
	 * check carrier and lane exist
	 * @param practice4vos
	 * @return boolean
	 * @throws Exception
	 */
	 public boolean isSearchByCarrierAndLane(Practice4VO practice4vo) throws Exception{
		 DBRowSet dbRowset = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{				
					param.putAll(practice4vo.getColumnValues());
					velParam.putAll(practice4vo.getColumnValues());
					dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new Practice4DBDAOSearchLaneRSQL(), param, velParam);
					while(dbRowset.next()){
						return true;
					}	
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
		 return false;
	 }
	 /**
	  * check vendor code exist
	  * @param practice4vos
	  * @return boolean
	  * @throws Exception
	  */
	 public boolean isSearchByVendor(List<Practice4VO> practice4vos) throws Exception{
		 DBRowSet dbRowset = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			try{
				for (Practice4VO practice4vo : practice4vos) {
					param.putAll(practice4vo.getColumnValues());
					velParam.putAll(practice4vo.getColumnValues());
					dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new Practice4DBDAOSearchVendorRSQL(), param, velParam);
					if(!dbRowset.next()){
						return false;
					}
				}
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
		 return true;
	 }
	 /**
	  * check customer code exist
	  * @param practice4vos
	  * @return boolean
	  * @throws Exception
	  */
	 public boolean isSearchCusCode(List<Practice4VO> practice4vos) throws Exception{
		 DBRowSet dbRowset = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{
				for (Practice4VO practice4vo : practice4vos) {
					param.putAll(practice4vo.getColumnValues());
					velParam.putAll(practice4vo.getColumnValues());
					dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new Practice4DBDAOSearchCustomerCodeRSQL(), param, velParam);
					if(!dbRowset.next()){
						return false;
					}
				}
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
		 return true;
	 }
	/**
	 * add data
	 * @param Practice4VO practice4VO
	 * @exception DAOException
	 * @exception Exception
	 */
	public void addmanager(Practice4VO practice4VO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = practice4VO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate((ISQLTemplate)new Practice4DBDAOPractice4VOCSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
	}
	
	/**
	 * update data
	 * @param Practice4VO practice4VO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int modifymanager(Practice4VO practice4VO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = practice4VO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new Practice4DBDAOPractice4VOUSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to update SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}
	
	/**
	 * delete data
	 * @param Practice4VO practice4VO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int removemanager(Practice4VO practice4VO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = practice4VO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new Practice4DBDAOPractice4VODSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to delete SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}

	/**
	 * add list data
	 * @param List<Practice4VO> practice4VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmanagerS(List<Practice4VO> practice4VO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(practice4VO .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new Practice4DBDAOPractice4VOCSQL(), practice4VO,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	/**
	 * update list data
	 * @param List<Practice4VO> practice4VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymanagerS(List<Practice4VO> practice4VO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(practice4VO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new Practice4DBDAOPractice4VOUSQL(), practice4VO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	/**
	 * delete list data
	 * @param List<Practice4VO> practice4VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemanagerS(List<Practice4VO> practice4VO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(practice4VO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new Practice4DBDAOPractice4VODSQL(), practice4VO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
	
}