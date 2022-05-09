package com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.integration.Practice2douDAO;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.Practice2douVO;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.PracticeVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class Practice2douBCImpl implements Practice2douBC{
	private transient Practice2douDAO dbDao = null;
	
	public Practice2douBCImpl() {
		dbDao = new Practice2douDAO();
	}
	/**
	 * search for table master
	 * @param Practice2VO	practice2VO
	 * @return List<Practice2VO>
	 * @exception EventException
	 */
	@Override
	public List<Practice2douVO> searchPractice(Practice2douVO practice2douVO)
			throws EventException {
		try {
			return dbDao.searchPractice(practice2douVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * update,insert, delete for table master
	 * @param Practice2VO[] practice2VO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	@Override
	public void managerPractice(Practice2douVO[] practice2douVOs, SignOnUserAccount account) throws EventException{
		try {
			List<Practice2douVO> insertVoList = new ArrayList<Practice2douVO>();
			List<Practice2douVO> updateVoList = new ArrayList<Practice2douVO>();
			List<Practice2douVO> deleteVoList = new ArrayList<Practice2douVO>();
			for ( int i=0; i<practice2douVOs .length; i++ ) {
				if ( practice2douVOs[i].getIbflag().equals("I")){
					practice2douVOs[i].setCreUsrId(account.getUsr_id());
					insertVoList.add(practice2douVOs[i]);
				} else if ( practice2douVOs[i].getIbflag().equals("U")){
					practice2douVOs[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(practice2douVOs[i]);
				} else if ( practice2douVOs[i].getIbflag().equals("D")){
					deleteVoList.add(practice2douVOs[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				if(dbDao.isSearchCdId(insertVoList)){
					throw new EventException(new ErrorHandler("AGT00005").getMessage());
				}
				dbDao.addmanagerPracticeS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanagerPracticeS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				for (Practice2douVO practice2douVO : deleteVoList) {
					PracticeVO practiceVO = new PracticeVO();
					practiceVO.setIntgCdId(practice2douVO.getIntgCdId());
					if(dbDao.searchPracticeDetail(practiceVO).size()>0){
						throw new EventException(new ErrorHandler("ERR00007").getMessage());
					}
				}
				dbDao.removemanagerPracticeS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * update,insert, delete for table detail
	 * @param practiceVOs
	 * @param account
	 * @throws EventException
	 */
	@Override
	public void managerPracticeDetail(PracticeVO[] practiceVOs, SignOnUserAccount account) throws EventException{
		try {
			List<PracticeVO> insertVoList = new ArrayList<PracticeVO>();
			List<PracticeVO> updateVoList = new ArrayList<PracticeVO>();
			List<PracticeVO> deleteVoList = new ArrayList<PracticeVO>();
			for ( int i=0; i<practiceVOs .length; i++ ) {
				if ( practiceVOs[i].getIbflag().equals("I")){
					practiceVOs[i].setCreUsrId(account.getUsr_id());
					insertVoList.add(practiceVOs[i]);
				} else if ( practiceVOs[i].getIbflag().equals("U")){
					practiceVOs[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(practiceVOs[i]);
				} else if ( practiceVOs[i].getIbflag().equals("D")){
					deleteVoList.add(practiceVOs[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				if(dbDao.isSearchCdVal(insertVoList)){
					throw new EventException(new ErrorHandler("ERR00006").getMessage());
				}
				dbDao.addmanagerDetail(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanagerDetail(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanagerDetail(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * search for table detail
	 * @param practiceVO
	 * @return
	 * @throws EventException
	 */
	@Override
	public List<PracticeVO> searchPracticeDetail(PracticeVO practiceVO)
			throws EventException {
		try {
			return dbDao.searchPracticeDetail(practiceVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}


}
