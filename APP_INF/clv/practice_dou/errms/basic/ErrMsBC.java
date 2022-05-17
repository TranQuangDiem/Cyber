/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsBC.java
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

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice_dou.errms.vo.PracticeVO;
/**
 * ALPS-Practice_dou Business Logic Command Interface<br>
 * -Interface to business logic for ALPS-Practice_dou<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */

public interface ErrMsBC {

	/**
	 * SEARCH DATA
	 * @param PracticeVO	errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
	public List<PracticeVO> searchPractice(PracticeVO errMsgVO) throws EventException;
	
	/**
	 * INSERT, UPDATE, DELETE DATA
	 * 
	 * @param ErrMsgVO[] errMsgVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void managerPactice(PracticeVO[] errMsgVO,SignOnUserAccount account) throws EventException;
}