/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice4BCImpl.java
*@FileTitle : Practice4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.05
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.05 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.practice4.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.practice4.practice4.integration.Practice4DBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice4.practice4.vo.Practice4VO;

/**
 * ALPS-Practice4 Business Logic Command Interface<br>
 * - ALPS-Practice4에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */
public class Practice4BCImpl extends BasicCommandSupport implements Practice4BC {

	// Database Access Object
	private transient Practice4DBDAO dbDao = null;

	/**
	 * Practice4BCImpl 객체 생성<br>
	 * Practice4DBDAO를 생성한다.<br>
	 */
	public Practice4BCImpl() {
		dbDao = new Practice4DBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param Practice4VO practice4VO
	 * @return List<Practice4VO>
	 * @exception EventException
	 */
	public List<Practice4VO> search(Practice4VO practice4VO) throws EventException {
		try {
			return dbDao.search(practice4VO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param Practice4VO[] practice4VO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manager(Practice4VO[] practice4VO, SignOnUserAccount account) throws EventException{
		try {
			List<Practice4VO> insertVoList = new ArrayList<Practice4VO>();
			List<Practice4VO> updateVoList = new ArrayList<Practice4VO>();
			List<Practice4VO> deleteVoList = new ArrayList<Practice4VO>();
			for ( int i=0; i<practice4VO .length; i++ ) {
				if ( practice4VO[i].getIbflag().equals("I")){
					practice4VO[i].setCreUsrId(account.getUsr_id());
					insertVoList.add(practice4VO[i]);
				} else if ( practice4VO[i].getIbflag().equals("U")){
					practice4VO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(practice4VO[i]);
				} else if ( practice4VO[i].getIbflag().equals("D")){
					deleteVoList.add(practice4VO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				if(dbDao.isSearchByCarrierAndLane(insertVoList))
					throw new EventException(new ErrorHandler("ERR00009").getMessage());
				else if(dbDao.isSearchByVendor(insertVoList))
					throw new EventException(new ErrorHandler("ERR00010").getMessage());
				else if(dbDao.isSearchCusCode(insertVoList))
					throw new EventException(new ErrorHandler("ERR00011").getMessage());
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
	@Override
	public String searchCarrier() throws EventException {
		try{
			return dbDao.searchCarrier();
			
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	@Override
	public String searchLane() throws EventException {
		try{
			return dbDao.searchLane();
			
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
}