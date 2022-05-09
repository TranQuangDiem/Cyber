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
 * -  Practice_TRNHTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Diem Tran
 * @see Practice_TRNHTMLAction 참조
 * @since J2EE 1.6
 */

public class PracticeTrnEvent extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	PracticeVO errMsgVO = null;
	
	/** Table Value Object Multi Data 처리 */
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