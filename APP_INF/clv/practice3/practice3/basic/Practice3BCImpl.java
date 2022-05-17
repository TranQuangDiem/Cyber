/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice3BCImpl.java
*@FileTitle : Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.practice3.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.practice3.practice3.integration.Practice3DBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice3.practice3.vo.Practice3VO;

/**
 * ALPS-Practice3 Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Practice3<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */
public class Practice3BCImpl extends BasicCommandSupport implements Practice3BC {

	// Database Access Object
	private transient Practice3DBDAO dbDao = null;

	/**
	 * Creating Practice3BCImpl Objects<br>
	 * Create Practice3DBDAO.<br>
	 */
	public Practice3BCImpl() {
		dbDao = new Practice3DBDAO();
	}
	/**
	 * search data
	 * @param Practice3VO practice3VO
	 * @return List<Practice3VO>
	 * @exception EventException
	 */
	public List<Practice3VO> search(Map<String, String> param) throws EventException {
		try {
			return dbDao.search(param);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * update,insert, delete
	 * @param Practice3VO[] practice3VO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manager(Practice3VO[] practice3VO, SignOnUserAccount account) throws EventException{
		try {
			List<Practice3VO> insertVoList = new ArrayList<Practice3VO>();
			List<Practice3VO> updateVoList = new ArrayList<Practice3VO>();
			List<Practice3VO> deleteVoList = new ArrayList<Practice3VO>();
			for ( int i=0; i<practice3VO .length; i++ ) {
//				if ( practice3VO[i].getIbflag().equals("I")){
//					practice3VO[i].setCreUsrId(account.getUsr_id());
//					insertVoList.add(practice3VO[i]);
//				} else if ( practice3VO[i].getIbflag().equals("U")){
//					practice3VO[i].setUpdUsrId(account.getUsr_id());
//					updateVoList.add(practice3VO[i]);
//				} else if ( practice3VO[i].getIbflag().equals("D")){
//					deleteVoList.add(practice3VO[i]);
//				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmanagerS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanagerS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanagerS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * search partner
	 * @return String[]
	 */
	@Override
	public String[] searchPartner() throws EventException {
		try {
			return dbDao.searchPartner();
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	/**
	 * get list lane by partner
	 * @param partners
	 * @return Map<String, String>
	 */
	@Override
	public Map<String, String> searchLane(String partners) throws EventException {
		try {
			return dbDao.searchLane(partners);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * get list trade by partner and lane
	 * @param partners
	 * @param lane
	 * @return Map<String, String>
	 */
	@Override
	public Map<String, String> searchTrade(String lane,String partners) throws EventException {
		try {
			return dbDao.searchTrade(lane,partners);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/** totalSum
	 * @param params
	 * @return String
	 */
	@Override
	public String total(Map<String, String> params) throws EventException {
		try {
			return dbDao.total(params);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}