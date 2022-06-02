/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice4BC.java
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

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice4.practice4.vo.Practice4VO;

/**
 * ALPS-Practice4 Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Practice4<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */

public interface Practice4BC {

	/**
	 * search data
	 * @param Practice4VO	practice4VO
	 * @return List<Practice4VO>
	 * @exception EventException
	 */
	public List<Practice4VO> search(Practice4VO practice4VO) throws EventException;
	
	/**
	 * insert, delete, update data
	 * @param Practice4VO[] practice4VO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manager(Practice4VO[] practice4VO,SignOnUserAccount account) throws EventException;
	/**
	 * get list Carrier
	 * @return String
	 * @throws EventException
	 */
	public String searchCarrier () throws EventException;
	/**
	 * get list lane
	 * @return String
	 * @throws EventException
	 */
	public String searchLane () throws EventException;
	/**
	 * check duplicate carrier and lane
	 * @param practice4VO
	 * @return boolean
	 * @throws EventException
	 */
	public boolean checkCarrierAndLane(Practice4VO practice4VO)throws EventException;
}