/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice3BC.java
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

import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.practice3.practice3.vo.Practice3VO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Practice3 Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Practice3<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */

public interface Practice3BC {

	/**
	 * search data
	 * @param Practice3VO	practice3VO
	 * @return List<Practice3VO>
	 * @exception EventException
	 */
	public List<Practice3VO> search(Map<String, String> param) throws EventException;
	
	/**
	 * update,insert, delete
	 * @param Practice3VO[] practice3VO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manager(Practice3VO[] practice3VO,SignOnUserAccount account) throws EventException;
	/**
	 * search partner
	 * @return String[]
	 * @throws EventException
	 */
	public String[] searchPartner()  throws EventException;
	/**
	 * get list lane by partner
	 * @param partners
	 * @return Map<String, String>
	 * @throws EventException
	 */
	public Map<String, String> searchLane(String partners)  throws EventException;
	/**
	 * get list trade by partner and lane
	 * @param lane
	 * @param partners
	 * @return Map<String, String>
	 * @throws EventException
	 */
	public Map<String, String> searchTrade(String lane,String partners)  throws EventException;
	/**
	 * totalSum
	 * @param params
	 * @return String
	 * @throws EventException
	 */
	public String total(Map<String, String> params)throws EventException;
}