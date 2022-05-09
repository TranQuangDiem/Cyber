/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice003Event.java
*@FileTitle : Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.practice3.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.practice3.practice3.vo.LaneVO;
import com.clt.apps.opus.esm.clv.practice3.practice3.vo.Practice3VO;


/**
 * Practice003 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  Practice003HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Diem Tran
 * @see Practice003HTMLAction 참조
 * @since J2EE 1.6
 */

public class Practice003Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	Practice3VO practice3VO = null;
	
	/** Table Value Object Multi Data 처리 */
	Practice3VO[] practice3VOs = null;
	LaneVO laneVO = null;
	String partners = null;
	String lane = null;
	String fryrmon = null;
	String toyrmon = null;
	String trades = null;
	public String getFryrmon() {
		return fryrmon;
	}

	public void setFryrmon(String fryrmon) {
		this.fryrmon = fryrmon;
	}

	public String getToyrmon() {
		return toyrmon;
	}

	public void setToyrmon(String toyrmon) {
		this.toyrmon = toyrmon;
	}

	public String getTrades() {
		return trades;
	}

	public void setTrades(String trades) {
		this.trades = trades;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getPartners() {
		return partners;
	}

	public void setPartners(String partners) {
		this.partners = partners;
	}

	public Practice003Event(){}
	
	public void setPractice3VO(Practice3VO practice3VO){
		this. practice3VO = practice3VO;
	}

	public void setPractice3VOS(Practice3VO[] practice3VOs){
		this. practice3VOs = practice3VOs;
	}

	public Practice3VO getPractice3VO(){
		return practice3VO;
	}

	public Practice3VO[] getPractice3VOS(){
		return practice3VOs;
	}

	public LaneVO getLaneVO() {
		return laneVO;
	}

	public void setLaneVO(LaneVO laneVO) {
		this.laneVO = laneVO;
	}

}