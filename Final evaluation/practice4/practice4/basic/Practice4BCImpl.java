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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 *- Interface to business logic for ALPS-Practice4<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */
public class Practice4BCImpl extends BasicCommandSupport implements Practice4BC {

	// Database Access Object
	private transient Practice4DBDAO dbDao = null;

	/**
	 * Create Practice4BCImpl object<br>
	 * Create Practice4DBDAO.<br>
	 */
	public Practice4BCImpl() {
		dbDao = new Practice4DBDAO();
	}
	/**
	 * search data
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
	 * insert, delete, update data
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
					practice4VO[i].setUpdUsrId(account.getUsr_id());
					insertVoList.add(practice4VO[i]);
				} else if ( practice4VO[i].getIbflag().equals("U")){
					/**
					 * get current date
					 */
					Date date = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			        String strDate = formatter.format(date);
			        practice4VO[i].setUpdDt(strDate);
					practice4VO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(practice4VO[i]);
				} else if ( practice4VO[i].getIbflag().equals("D")){
					deleteVoList.add(practice4VO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				checkValidate(insertVoList);// validation
				for (Practice4VO practice4vo : insertVoList) {
					if(dbDao.isSearchByCarrierAndLane(practice4vo)){
						String [] msg = {practice4vo.getJoCrrCd()+","+practice4vo.getRlaneCd(),practice4vo.getJoCrrCd()+","+practice4vo.getRlaneCd()};
						throw new EventException(new ErrorHandler("ERR00011",msg).getMessage());
					}
				}
				 if(!dbDao.isSearchByVendor(insertVoList)){
					String [] msg = {"Vendor Code"};
					throw new EventException(new ErrorHandler("ERR00010",msg).getMessage());
				}else if(!dbDao.isSearchCusCode(insertVoList)){
					String [] msg = {"Customer Code"};
					throw new EventException(new ErrorHandler("ERR00010",msg).getMessage());
				}
				dbDao.addmanagerS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				if(!dbDao.isSearchByVendor(updateVoList)){
					String [] msg = {"Vendor Code"};
					throw new EventException(new ErrorHandler("ERR00010",msg).getMessage());
				}
				else if(!dbDao.isSearchCusCode(updateVoList)){
					String [] msg = {"Customer Code"};
					throw new EventException(new ErrorHandler("ERR00010",msg).getMessage());
				}
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
	 * check duplicate and validate data
	 * @param practice4VOs
	 * @throws EventException
	 */
	public void checkValidate(List<Practice4VO> practice4VOs) throws EventException{
		for (Practice4VO practice4VO : practice4VOs) {
//			int i=0;
			if(!practice4VO.getVndrSeq().matches("[0-9]{5,6}"))
				throw new EventException(new ErrorHandler("ERR00012").getMessage());
			if(!practice4VO.getCustSeq().matches("[0-9]{5,6}"))
				throw new EventException(new ErrorHandler("ERR00013").getMessage());
			if(!practice4VO.getCustCntCd().matches("[A-z]{2}"))
				throw new EventException(new ErrorHandler("ERR00014").getMessage());
//			for (int j = i+1; j < practice4VOs.size(); j++) {
//				if(practice4VO.getJoCrrCd().equals(practice4VOs.get(j).getJoCrrCd())&&practice4VO.getRlaneCd().equals(practice4VOs.get(j).getRlaneCd())){
//					String [] msg = {"Carrier and Rev.Lane","Carrier and Rev.Lane"};
//					throw new EventException(new ErrorHandler("ERR00011",msg).getMessage());
//				}
//				else if(practice4VO.getVndrSeq().equals(practice4VOs.get(j).getVndrSeq())){
//					throw new EventException(new ErrorHandler("ERR00010").getMessage());
//				}else if(practice4VO.getCustSeq().equals(practice4VOs.get(j).getCustSeq())&&practice4VO.getCustCntCd().equals(practice4VOs.get(j).getCustCntCd())){
//					throw new EventException(new ErrorHandler("ERR00011").getMessage());
//				}
//			}
//			i++;
			
		}
	}
	/**
	 * get list Carrier
	 * @return String
	 * @throws EventException
	 */
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
	/**
	 * get list lane
	 * @return String
	 * @throws EventException
	 */
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
	/**
	 * check duplicate carrier and lane
	 * @param practice4VO
	 * @return boolean
	 * @throws EventException
	 */
	@Override
	public boolean checkCarrierAndLane(Practice4VO practice4vo) throws EventException {
		try{
			return dbDao.isSearchByCarrierAndLane(practice4vo);
			
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
}