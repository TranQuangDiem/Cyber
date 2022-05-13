/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : Practice3DBDAO.java
 *@FileTitle : Practice 3
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.04.20
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.04.20 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.practice3.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.clt.apps.opus.esm.clv.practice3.practice3.basic.Practice3BCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.practice3.practice3.vo.PartnerVO;
import com.clt.apps.opus.esm.clv.practice3.practice3.vo.Practice3VO;

/**
 * ALPS Practice3DBDAO <br>
 * - ALPS-Practice3 system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Diem Tran
 * @see Practice3BCImpl 참조
 * @since J2EE 1.6
 */
public class Practice3DBDAO extends DBDAOSupport {

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * search
	 * @param Map<String, String>
	 * @return List<Practice3VO>
	 * @exception DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<Practice3VO> search(Map<String, String> params)
			throws DAOException {
		DBRowSet dbRowset = null;
		List<Practice3VO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if(params!=null){
				param.putAll(params);
				velParam.putAll(params);
				for(Map.Entry<String, String> entry : params.entrySet()){
					if(entry.getKey().equals("jo_crr_cds")){
						if(entry.getValue().length()>0){
						List<String> listpartner = new ArrayList<String>();
						String [] partners = entry.getValue().split(",");
						if("All".equalsIgnoreCase(partners[0])){
							param.put("jo_crr_cds", partners[0]);
							velParam.put("jo_crr_cds", partners[0]);
							break;
						}
						for (String partner : partners) {
							listpartner.add(partner);
						}
						param.put("jo_crr_cds", listpartner);
						velParam.put("jo_crr_cds", listpartner);
						}
					}
				}
				
			}
			dbRowset = new SQLExecuter("").executeQuery(new Practice3DBDAOPractice3VORSQL(), param,velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, Practice3VO.class);
			dbRowset = new SQLExecuter("").executeQuery(new Practice3DBDAOTotalRSQL(), param,velParam);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	/**
	 * totalSum by LOCL_CURR_CD 
	 * @param params
	 * @return String
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public String total(Map<String, String> params)
			throws DAOException {
		DBRowSet dbRowset = null;
		StringBuilder result = new StringBuilder();
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if(params!=null){
				param.putAll(params);
				velParam.putAll(params);
				for(Map.Entry<String, String> entry : params.entrySet()){
					if(entry.getKey().equals("jo_crr_cds")){
						if(entry.getValue().length()>0){
						List<String> listpartner = new ArrayList<String>();
						String [] partners = entry.getValue().split(",");
						if("All".equalsIgnoreCase(partners[0])){
							param.put("jo_crr_cds", partners[0]);
							velParam.put("jo_crr_cds", partners[0]);
							break;
						}
						for (String partner : partners) {
							listpartner.add(partner);
						}
						param.put("jo_crr_cds", listpartner);
						velParam.put("jo_crr_cds", listpartner);
						}
					}
				}
				
			}
			dbRowset = new SQLExecuter("").executeQuery(new Practice3DBDAOTotalRSQL(), param,velParam);
			while(dbRowset.next()){
				result.append(dbRowset.getString(1)+",");
				result.append(dbRowset.getString(2)+",");
				result.append(dbRowset.getString(3)+"|");
			}
			
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result.toString();
	}
	/**
	 * get list partner
	 * @return String[]
	 * @throws DAOException
	 */
	public String[] searchPartner() throws DAOException {
		DBRowSet dbRowSet = null;
		String [] list = null;
		Map<String, String> m = new TreeMap<String, String>();
		try {
			dbRowSet = new SQLExecuter("").executeQuery(
					new Practice3DBDAOPartnerVORSQL(), null, null);
			int index =0;
			list = new String[dbRowSet.getRowCount()];
			while (dbRowSet.next()) {
				list[index++] = dbRowSet.getString(1);
			}

		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	/**
	 * get list lane
	 * @param partners
	 * @return Map<String, String>
	 * @throws DAOException
	 */
	public Map<String, String> searchLane(String partners) throws DAOException {
		DBRowSet dbRowSet = null;
		List<PartnerVO> list = null;
		String[] partnerList = partners.split(",");
		Map<String, String> m = new TreeMap<String, String>();
		System.out.println(partnerList[0]);
		try {
			
			for (String partner : partnerList) {
				Map<String, Object> param = new HashMap<String, Object>();
				Map<String, Object> velparam = new HashMap<String, Object>();
				velparam.put("jo_crr_cd", partner);
				param.put("jo_crr_cd", partner);
				dbRowSet = new SQLExecuter("").executeQuery(
						new Practice3DBDAOLaneRSQL(), param, velparam);
				while (dbRowSet.next()) {
					m.put(dbRowSet.getString(1), dbRowSet.getString(1));
				}
				if("All".equals(partner)) break;
			}

		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return m;
	}
	/**
	 * get list trade
	 * @param lane
	 * @param partners
	 * @return Map<String, String>
	 * @throws DAOException
	 */
	public Map<String, String> searchTrade(String lane,String partners) throws DAOException {
		DBRowSet dbRowSet = null;
		List<PartnerVO> list = null;
		String[] partnerList = partners.split(",");
		Map<String, String> m = new TreeMap<String, String>();
		try {
			for (String partner : partnerList) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("jo_crr_cd", partner);
				param.put("rlane_cd", lane);
				Map<String, Object> velparam = new HashMap<String, Object>();
				velparam.put("jo_crr_cd", partner);
				dbRowSet = new SQLExecuter("").executeQuery(
						new Practice3DBDAOTradeRSQL(), param, velparam);
				while (dbRowSet.next()) {
					m.put(dbRowSet.getString(1), dbRowSet.getString(1));
				}
				if("All".equals(partner)) break;
			}

		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return m;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * add data
	 * @param Practice3VO
	 *            practice3VO
	 * @exception DAOException
	 * @exception Exception
	 */
	public void addmanager(Practice3VO practice3VO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = practice3VO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate(
					(ISQLTemplate) new Practice3DBDAOPractice3VOCSQL(), param,
					velParam);
			if (result == Statement.EXECUTE_FAILED)
				throw new DAOException("Fail to insert SQL");
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * update data
	 * @param Practice3VO
	 *            practice3VO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int modifymanager(Practice3VO practice3VO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = practice3VO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new Practice3DBDAOPractice3VOUSQL(), param,
					velParam);
			if (result == Statement.EXECUTE_FAILED)
				throw new DAOException("Fail to insert SQL");
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * delete data
	 * @param Practice3VO
	 *            practice3VO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int removemanager(Practice3VO practice3VO) throws DAOException,
			Exception {
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		int result = 0;
		try {
			Map<String, String> mapVO = practice3VO.getColumnValues();

			param.putAll(mapVO);
			velParam.putAll(mapVO);

			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate(
					(ISQLTemplate) new Practice3DBDAOPractice3VODSQL(), param,
					velParam);
			if (result == Statement.EXECUTE_FAILED)
				throw new DAOException("Fail to insert SQL");
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * add list data
	 * @param List
	 *            <Practice3VO> practice3VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmanagerS(List<Practice3VO> practice3VO)
			throws DAOException, Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (practice3VO.size() > 0) {
				insCnt = sqlExe.executeBatch(
						(ISQLTemplate) new Practice3DBDAOPractice3VOCSQL(),
						practice3VO, null);
				for (int i = 0; i < insCnt.length; i++) {
					if (insCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * update list data
	 * @param List
	 *            <Practice3VO> practice3VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymanagerS(List<Practice3VO> practice3VO)
			throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (practice3VO.size() > 0) {
				updCnt = sqlExe.executeBatch(
						(ISQLTemplate) new Practice3DBDAOPractice3VOUSQL(),
						practice3VO, null);
				for (int i = 0; i < updCnt.length; i++) {
					if (updCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 *  delete list data
	 * @param List
	 *            <Practice3VO> practice3VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemanagerS(List<Practice3VO> practice3VO)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (practice3VO.size() > 0) {
				delCnt = sqlExe.executeBatch(
						(ISQLTemplate) new Practice3DBDAOPractice3VODSQL(),
						practice3VO, null);
				for (int i = 0; i < delCnt.length; i++) {
					if (delCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}

}