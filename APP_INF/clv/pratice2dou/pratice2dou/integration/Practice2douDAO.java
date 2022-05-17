package com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.Practice2douVO;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.PracticeVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class Practice2douDAO extends DBDAOSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * search for table master
	 * @param practice2douVO
	 * @return List<Practice2douVO>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<Practice2douVO> searchPractice(Practice2douVO practice2douVO)
			throws DAOException {
		DBRowSet dbRowset = null;
		List<Practice2douVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			if (practice2douVO != null) {
				Map<String, String> mapVO = practice2douVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery(
					(ISQLTemplate) new Practice2douDAOPractice2douVORSQL(),
					param, velParam);
			list = (List) RowSetUtil
					.rowSetToVOs(dbRowset, Practice2douVO.class);
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
	 * search for table detail
	 * @param practiceVO
	 * @return List<PracticeVO>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<PracticeVO> searchPracticeDetail(PracticeVO practiceVO)
			throws DAOException {
		DBRowSet dbRowset = null;
		List<PracticeVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (practiceVO != null) {
				Map<String, String> mapVO = practiceVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new Practice2douDAOPracticeVORSQL(), param,velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, PracticeVO.class);
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
	 * check cd id exists
	 * @param practice2douVOs
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean isSearchCdId(List<Practice2douVO> practice2douVOs)
			throws DAOException {
		DBRowSet dbRowset = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			for (Practice2douVO practice2douVO : practice2douVOs) {
				Map<String, String> mapVO = practice2douVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new Practice2douDAOPractice2RSQL(),param, velParam);
					if (dbRowset.next()) {
						return true;
					}
				}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return false;
	}
	/**
	 * check cd val exists
	 * @param practiceVOs
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean isSearchCdVal(List<PracticeVO> practiceVOs)throws DAOException {
		DBRowSet dbRowset = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			for (PracticeVO practiceVO : practiceVOs) {
				Map<String, String> mapVO = practiceVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				System.out.println(mapVO);
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new Practice2douDAOPracticeDetailRSQL(),param, velParam);
					if (dbRowset.next()) {
						return true;
					}
				}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return false;
	}
	/**
	 * add data
	 * @param List
	 *            <Practice2VO> practice2VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmanagerPracticeS(List<Practice2douVO> practice2douVO)
			throws DAOException, Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (practice2douVO.size() > 0) {
				insCnt = sqlExe.executeBatch((ISQLTemplate) new Practice2douDAOPractice2douVOCSQL(),practice2douVO, null);
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
	 * update data
	 * @param List
	 *            <Practice2VO> practice2VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymanagerPracticeS(List<Practice2douVO> practice2douVO)
			throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (practice2douVO.size() > 0) {
				updCnt = sqlExe.executeBatch(
						(ISQLTemplate) new Practice2douDAOPractice2douVOUSQL(),
						practice2douVO, null);
				for (int i = 0; i < updCnt.length; i++) {
					if (updCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to update No" + i + " SQL");
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
	 * delete data
	 * @param List
	 *            <Practice2VO> practice2VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemanagerPracticeS(List<Practice2douVO> practice2douVO)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (practice2douVO.size() > 0) {
				delCnt = sqlExe.executeBatch(
						(ISQLTemplate) new Practice2douDAOPractice2douVODSQL(),
						practice2douVO, null);
				for (int i = 0; i < delCnt.length; i++) {
					if (delCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to delete No" + i + " SQL");
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

	/**
	 * add list data
	 * @param List
	 *            <Practice2VO> practice2VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmanagerDetail(List<PracticeVO> practiceVO)
			throws DAOException, Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (practiceVO.size() > 0) {
				insCnt = sqlExe.executeBatch(
						(ISQLTemplate) new Practice2douDAOPracticeVOCSQL(),
						practiceVO, null);
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
	 * update list data
	 * @param List
	 *            <Practice2VO> practice2VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymanagerDetail(List<PracticeVO> practiceVO)
			throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (practiceVO.size() > 0) {
				updCnt = sqlExe.executeBatch(
						(ISQLTemplate) new Practice2douDAOPracticeVOUSQL(),
						practiceVO, null);
				for (int i = 0; i < updCnt.length; i++) {
					if (updCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to update No" + i + " SQL");
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
	 * delete list data
	 * @param List
	 *            <Practice2VO> practice2VO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemanagerDetail(List<PracticeVO> practiceVO)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (practiceVO.size() > 0) {
				delCnt = sqlExe.executeBatch(
						(ISQLTemplate) new Practice2douDAOPracticeVODSQL(),
						practiceVO, null);
				for (int i = 0; i < delCnt.length; i++) {
					if (delCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to delete No" + i + " SQL");
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
