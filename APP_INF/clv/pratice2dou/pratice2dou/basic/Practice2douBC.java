package com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.basic;

import java.util.List;

import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.Practice2douVO;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.PracticeVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public interface Practice2douBC {
	/**
	 * search for table master
	 * @param Practice2VO	practice2VO
	 * @return List<Practice2VO>
	 * @exception EventException
	 */
	public List<Practice2douVO> searchPractice(Practice2douVO practice2douVO) throws EventException;
	
	/**
	 * update,insert, delete for table master
	 * @param Practice2VO[] practice2VO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void managerPractice(Practice2douVO[] practice2douVOs,SignOnUserAccount account) throws EventException;
	/**
	 * search for table detail
	 * @param practiceVO
	 * @return
	 * @throws EventException
	 */
	
	public List<PracticeVO> searchPracticeDetail(PracticeVO practiceVO) throws EventException;
	/**
	 * update,insert, delete for table detail
	 * @param practiceVOs
	 * @param account
	 * @throws EventException
	 */
	public void managerPracticeDetail(PracticeVO[] practiceVOs, SignOnUserAccount account) throws EventException;
}
