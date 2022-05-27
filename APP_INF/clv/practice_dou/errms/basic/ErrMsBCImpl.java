/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : ErrMsBCImpl.java
 *@FileTitle : ErrorMessageManager
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.04.07
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.04.07 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice_dou.errms.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.clt.apps.opus.esm.clv.practice_dou.errms.integration.ErrMsDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice_dou.errms.vo.PracticeVO;

/**
 * ALPS-Practice_DOU Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Practice_DOU<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */
public class ErrMsBCImpl extends BasicCommandSupport implements ErrMsBC {

	// Database Access Object
	private transient ErrMsDBDAO dbDao = null;

	/**
	 * Create ErrMsBCImpl object<br>
	 * Create ErrMsDBDAO.<br>
	 */
	public ErrMsBCImpl() {
		dbDao = new ErrMsDBDAO();
	}

	/**
	 * SEARH DATA
	 * @param PracticeVO  errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
	public List<PracticeVO> searchPractice(PracticeVO errMsgVO)
			throws EventException {
		try {
			errMsgVO.setErrMsgCd(errMsgVO.getS_msg_cd());
			errMsgVO.setErrMsg(errMsgVO.getS_msg());
			return dbDao.searchPracticeVO(errMsgVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}

	}

	/**
	 * INSERT, UPDATE, DELETE DATA
	 * 
	 * @param ErrMsgVO [] errMsgVO
	 * @param account  SignOnUserAccount
	 * @exception EventException
	 */
	public void managerPactice(PracticeVO[] errMsgVO, SignOnUserAccount account)
			throws EventException {
		try {
			List<PracticeVO> insertVoList = new ArrayList<PracticeVO>();
			List<PracticeVO> updateVoList = new ArrayList<PracticeVO>();
			List<PracticeVO> deleteVoList = new ArrayList<PracticeVO>();
			for (int i = 0; i < errMsgVO.length; i++) {
				if (errMsgVO[i].getIbflag().equals("I")) {
						if (!Pattern.matches("^[A-Z]{3}\\d{5}",//validate err_msg_cd
								errMsgVO[i].getErrMsgCd())) {
							throw new EventException(new ErrorHandler("ERR12345").getMessage());
						} 
					errMsgVO[i].setCreUsrId(account.getUsr_id());
					insertVoList.add(errMsgVO[i]);
				} else if (errMsgVO[i].getIbflag().equals("U")) {
					errMsgVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(errMsgVO[i]);
				} else if (errMsgVO[i].getIbflag().equals("D")) {
					deleteVoList.add(errMsgVO[i]);
				}
			}
			
			if (insertVoList.size() > 0) {
				if(dbDao.isCheckMsCd(insertVoList)){
					throw new EventException(new ErrorHandler("ERR00001").getMessage());
				}
				dbDao.addErrMsgVOS(insertVoList);
			}

			if (updateVoList.size() > 0) {
				dbDao.modifyErrMsgVOS(updateVoList);
			}

			if (deleteVoList.size() > 0) {
				dbDao.removeErrMsgVOS(deleteVoList);
			}
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}
	// public void validation(HashMap<String, String> getColumnValues) throws
	// DAOException{
	// for (Map.Entry<String, String> entry : getColumnValues.entrySet()) {
	// System.out.println(entry.getKey() + " - " + entry.getValue());
	// if(, entry.getKey())){
	// throw new DAOException("s");
	// }
	// }
	//
	// }

}