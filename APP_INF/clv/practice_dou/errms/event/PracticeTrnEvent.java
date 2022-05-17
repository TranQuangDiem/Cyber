/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PracticeTrnEvent.java
*@FileTitle : ErrorMessageManager
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.07
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.07 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice_dou.errms.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.practice_dou.errms.vo.PracticeVO;


/**
 * Practice_TRN 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * - Created from Practice_TRNHTMLAction<br>
  * - Used as PDTO delivered to ServiceCommand Layer<br>
 *
 * @author Diem Tran
 * @see Practice_TRNHTMLAction
 * @since J2EE 1.6
 */

public class PracticeTrnEvent extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object inquiry condition and single case processing  */
	PracticeVO errMsgVO = null;
	
	/** Table Value Object Multi Data processing */
	PracticeVO[] errMsgVOs = null;

	public PracticeTrnEvent(){}
	
	public void setErrMsgVO(PracticeVO errMsgVO){
		this. errMsgVO = errMsgVO;
	}

	public void setErrMsgVOS(PracticeVO[] errMsgVOs){
		this. errMsgVOs = errMsgVOs;
	}

	public PracticeVO getErrMsgVO(){
		return errMsgVO;
	}

	public PracticeVO[] getErrMsgVOS(){
		return errMsgVOs;
	}

}