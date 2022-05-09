/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice4Event.java
*@FileTitle : Practice4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.05
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.05 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.practice4.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.practice4.practice4.vo.Practice4VO;


/**
 * Practice4 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  Practice4HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Diem Tran
 * @see Practice4HTMLAction 참조
 * @since J2EE 1.6
 */

public class Practice4Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	Practice4VO practice4VO = null;
	
	/** Table Value Object Multi Data 처리 */
	Practice4VO[] practice4VOs = null;
	String carrier = null;
	String vendor =null;
	String frYm = null;
	String toYm = null;
	

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getFrYm() {
		return frYm;
	}

	public void setFrYm(String frYm) {
		this.frYm = frYm;
	}

	public String getToYm() {
		return toYm;
	}

	public void setToYm(String toYm) {
		this.toYm = toYm;
	}

	public Practice4Event(){}
	
	public void setPractice4VO(Practice4VO practice4VO){
		this. practice4VO = practice4VO;
	}

	public void setPractice4VOS(Practice4VO[] practice4VOs){
		this. practice4VOs = practice4VOs;
	}

	public Practice4VO getPractice4VO(){
		return practice4VO;
	}

	public Practice4VO[] getPractice4VOS(){
		return practice4VOs;
	}

}