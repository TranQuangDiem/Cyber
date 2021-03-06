/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PracticeVO.java
*@FileTitle : PracticeVO
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.14
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.14  
* 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

/**
 * Table Value Ojbect<br>
 * A Value Object that is created in the related event and performs the role of data delivery when a server execution request is made.
 *
 * @author 
 * @since J2EE 1.6
 * @see AbstractValueObject
 */

public class PracticeVO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;
	
	private Collection<PracticeVO> models = new ArrayList<PracticeVO>();
	
	/* Column Info */
	private String updDt = null;
	/* Column Info */
	private String intgCdValDpSeq = null;
	/* Column Info */
	private String intgCdValDpDesc = null;
	/* Column Info */
	private String aplyEndDt = null;
	/* Column Info */
	private String creDt = null;
	/* Page Number */
	private String pagerows = null;
	/* Column Info */
	private String intgCdId = null;
	/* Column Info */
	private String aplyStDt = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String creUsrId = null;
	/* Column Info */
	private String intgCdValDesc = null;
	/* Column Info */
	private String intgCdValCtnt = null;
	/* Column Info */
	private String updUsrId = null;

	/* Hashtable to store table column values */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/* Hashtable storing member variables corresponding to table columns */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();
	
	public PracticeVO() {}

	public PracticeVO(String ibflag, String pagerows, String updDt, String updUsrId, String creDt, String creUsrId, String aplyEndDt, String aplyStDt, String intgCdValDpSeq, String intgCdValDesc, String intgCdValDpDesc, String intgCdValCtnt, String intgCdId) {
		this.updDt = updDt;
		this.intgCdValDpSeq = intgCdValDpSeq;
		this.intgCdValDpDesc = intgCdValDpDesc;
		this.aplyEndDt = aplyEndDt;
		this.creDt = creDt;
		this.pagerows = pagerows;
		this.intgCdId = intgCdId;
		this.aplyStDt = aplyStDt;
		this.ibflag = ibflag;
		this.creUsrId = creUsrId;
		this.intgCdValDesc = intgCdValDesc;
		this.intgCdValCtnt = intgCdValCtnt;
		this.updUsrId = updUsrId;
	}
	
	/**
	 * Returns the value to be stored in the table column as Hashtable<"column_name", "value">
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues(){
		this.hashColumns.put("upd_dt", getUpdDt());
		this.hashColumns.put("intg_cd_val_dp_seq", getIntgCdValDpSeq());
		this.hashColumns.put("intg_cd_val_dp_desc", getIntgCdValDpDesc());
		this.hashColumns.put("aply_end_dt", getAplyEndDt());
		this.hashColumns.put("cre_dt", getCreDt());
		this.hashColumns.put("pagerows", getPagerows());
		this.hashColumns.put("intg_cd_id", getIntgCdId());
		this.hashColumns.put("aply_st_dt", getAplyStDt());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("cre_usr_id", getCreUsrId());
		this.hashColumns.put("intg_cd_val_desc", getIntgCdValDesc());
		this.hashColumns.put("intg_cd_val_ctnt", getIntgCdValCtnt());
		this.hashColumns.put("upd_usr_id", getUpdUsrId());
		return this.hashColumns;
	}
	
	/**
	 * Stores member variable names corresponding to column names and returns them as Hashtable<"column_name", "variable"> 
	 * @return
	 */
	public HashMap<String, String> getFieldNames(){
		this.hashFields.put("upd_dt", "updDt");
		this.hashFields.put("intg_cd_val_dp_seq", "intgCdValDpSeq");
		this.hashFields.put("intg_cd_val_dp_desc", "intgCdValDpDesc");
		this.hashFields.put("aply_end_dt", "aplyEndDt");
		this.hashFields.put("cre_dt", "creDt");
		this.hashFields.put("pagerows", "pagerows");
		this.hashFields.put("intg_cd_id", "intgCdId");
		this.hashFields.put("aply_st_dt", "aplyStDt");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("cre_usr_id", "creUsrId");
		this.hashFields.put("intg_cd_val_desc", "intgCdValDesc");
		this.hashFields.put("intg_cd_val_ctnt", "intgCdValCtnt");
		this.hashFields.put("upd_usr_id", "updUsrId");
		return this.hashFields;
	}
	
	/**
	 * Column Info
	 * @return updDt
	 */
	public String getUpdDt() {
		return this.updDt;
	}
	
	/**
	 * Column Info
	 * @return intgCdValDpSeq
	 */
	public String getIntgCdValDpSeq() {
		return this.intgCdValDpSeq;
	}
	
	/**
	 * Column Info
	 * @return intgCdValDpDesc
	 */
	public String getIntgCdValDpDesc() {
		return this.intgCdValDpDesc;
	}
	
	/**
	 * Column Info
	 * @return aplyEndDt
	 */
	public String getAplyEndDt() {
		return this.aplyEndDt;
	}
	
	/**
	 * Column Info
	 * @return creDt
	 */
	public String getCreDt() {
		return this.creDt;
	}
	
	/**
	 * Page Number
	 * @return pagerows
	 */
	public String getPagerows() {
		return this.pagerows;
	}
	
	/**
	 * Column Info
	 * @return intgCdId
	 */
	public String getIntgCdId() {
		return this.intgCdId;
	}
	
	/**
	 * Column Info
	 * @return aplyStDt
	 */
	public String getAplyStDt() {
		return this.aplyStDt;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @return ibflag
	 */
	public String getIbflag() {
		return this.ibflag;
	}
	
	/**
	 * Column Info
	 * @return creUsrId
	 */
	public String getCreUsrId() {
		return this.creUsrId;
	}
	
	/**
	 * Column Info
	 * @return intgCdValDesc
	 */
	public String getIntgCdValDesc() {
		return this.intgCdValDesc;
	}
	
	/**
	 * Column Info
	 * @return intgCdValCtnt
	 */
	public String getIntgCdValCtnt() {
		return this.intgCdValCtnt;
	}
	
	/**
	 * Column Info
	 * @return updUsrId
	 */
	public String getUpdUsrId() {
		return this.updUsrId;
	}
	

	/**
	 * Column Info
	 * @param updDt
	 */
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	
	/**
	 * Column Info
	 * @param intgCdValDpSeq
	 */
	public void setIntgCdValDpSeq(String intgCdValDpSeq) {
		this.intgCdValDpSeq = intgCdValDpSeq;
	}
	
	/**
	 * Column Info
	 * @param intgCdValDpDesc
	 */
	public void setIntgCdValDpDesc(String intgCdValDpDesc) {
		this.intgCdValDpDesc = intgCdValDpDesc;
	}
	
	/**
	 * Column Info
	 * @param aplyEndDt
	 */
	public void setAplyEndDt(String aplyEndDt) {
		this.aplyEndDt = aplyEndDt;
	}
	
	/**
	 * Column Info
	 * @param creDt
	 */
	public void setCreDt(String creDt) {
		this.creDt = creDt;
	}
	
	/**
	 * Page Number
	 * @param pagerows
	 */
	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	
	/**
	 * Column Info
	 * @param intgCdId
	 */
	public void setIntgCdId(String intgCdId) {
		this.intgCdId = intgCdId;
	}
	
	/**
	 * Column Info
	 * @param aplyStDt
	 */
	public void setAplyStDt(String aplyStDt) {
		this.aplyStDt = aplyStDt;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @param ibflag
	 */
	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}
	
	/**
	 * Column Info
	 * @param creUsrId
	 */
	public void setCreUsrId(String creUsrId) {
		this.creUsrId = creUsrId;
	}
	
	/**
	 * Column Info
	 * @param intgCdValDesc
	 */
	public void setIntgCdValDesc(String intgCdValDesc) {
		this.intgCdValDesc = intgCdValDesc;
	}
	
	/**
	 * Column Info
	 * @param intgCdValCtnt
	 */
	public void setIntgCdValCtnt(String intgCdValCtnt) {
		this.intgCdValCtnt = intgCdValCtnt;
	}
	
	/**
	 * Column Info
	 * @param updUsrId
	 */
	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
	}
	
	/**
	 * Extract the request data and set it in the member variable of VO.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request) {
		fromRequest(request,"");
	}

	/**
	 * Extract the request data and set it in the member variable of VO.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request, String prefix) {
		setUpdDt(JSPUtil.getParameter(request, prefix + "upd_dt", ""));
		setIntgCdValDpSeq(JSPUtil.getParameter(request, prefix + "intg_cd_val_dp_seq", ""));
		setIntgCdValDpDesc(JSPUtil.getParameter(request, prefix + "intg_cd_val_dp_desc", ""));
		setAplyEndDt(JSPUtil.getParameter(request, prefix + "aply_end_dt", ""));
		setCreDt(JSPUtil.getParameter(request, prefix + "cre_dt", ""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
		setIntgCdId(JSPUtil.getParameter(request, prefix + "intg_cd_id", ""));
		setAplyStDt(JSPUtil.getParameter(request, prefix + "aply_st_dt", ""));
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setCreUsrId(JSPUtil.getParameter(request, prefix + "cre_usr_id", ""));
		setIntgCdValDesc(JSPUtil.getParameter(request, prefix + "intg_cd_val_desc", ""));
		setIntgCdValCtnt(JSPUtil.getParameter(request, prefix + "intg_cd_val_ctnt", ""));
		setUpdUsrId(JSPUtil.getParameter(request, prefix + "upd_usr_id", ""));
		setIntgCdId(JSPUtil.getParameter(request, prefix +  "Cd_Id", ""));
	}

	/**
	 * Converts Request data to VO array and returns it.
	 * @param request
	 * @return PracticeVO[]
	 */
	public PracticeVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Several requests passed over are DATA in VO Class.
	 * @param request
	 * @param prefix
	 * @return PracticeVO[]
	 */
	public PracticeVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		PracticeVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] updDt = (JSPUtil.getParameter(request, prefix	+ "upd_dt", length));
			String[] intgCdValDpSeq = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_dp_seq", length));
			String[] intgCdValDpDesc = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_dp_desc", length));
			String[] aplyEndDt = (JSPUtil.getParameter(request, prefix	+ "aply_end_dt", length));
			String[] creDt = (JSPUtil.getParameter(request, prefix	+ "cre_dt", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows", length));
			String[] intgCdId = (JSPUtil.getParameter(request, prefix	+ "intg_cd_id", length));
			String[] aplyStDt = (JSPUtil.getParameter(request, prefix	+ "aply_st_dt", length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag", length));
			String[] creUsrId = (JSPUtil.getParameter(request, prefix	+ "cre_usr_id", length));
			String[] intgCdValDesc = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_desc", length));
			String[] intgCdValCtnt = (JSPUtil.getParameter(request, prefix	+ "intg_cd_val_ctnt", length));
			String[] updUsrId = (JSPUtil.getParameter(request, prefix	+ "upd_usr_id", length));
			
			for (int i = 0; i < length; i++) {
				model = new PracticeVO();
				if (updDt[i] != null)
					model.setUpdDt(updDt[i]);
				if (intgCdValDpSeq[i] != null)
					model.setIntgCdValDpSeq(intgCdValDpSeq[i]);
				if (intgCdValDpDesc[i] != null)
					model.setIntgCdValDpDesc(intgCdValDpDesc[i]);
				if (aplyEndDt[i] != null)
					model.setAplyEndDt(aplyEndDt[i]);
				if (creDt[i] != null)
					model.setCreDt(creDt[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				if (intgCdId[i] != null)
					model.setIntgCdId(intgCdId[i]);
				if (aplyStDt[i] != null)
					model.setAplyStDt(aplyStDt[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (creUsrId[i] != null)
					model.setCreUsrId(creUsrId[i]);
				if (intgCdValDesc[i] != null)
					model.setIntgCdValDesc(intgCdValDesc[i]);
				if (intgCdValCtnt[i] != null)
					model.setIntgCdValCtnt(intgCdValCtnt[i]);
				if (updUsrId[i] != null)
					model.setUpdUsrId(updUsrId[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getPracticeVOs();
	}

	/**
	 * return VO array
	 * @return PracticeVO[]
	 */
	public PracticeVO[] getPracticeVOs(){
		PracticeVO[] vos = (PracticeVO[])models.toArray(new PracticeVO[models.size()]);
		return vos;
	}
	
	/**
	 * Convert the contents of VO Class to String
	 */
	public String toString() {
		   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE );
	   }

	/**
	* Remove special characters from formatted string ("-","/",",",":")
	*/
	public void unDataFormat(){
		this.updDt = this.updDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDpSeq = this.intgCdValDpSeq .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDpDesc = this.intgCdValDpDesc .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.aplyEndDt = this.aplyEndDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.creDt = this.creDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdId = this.intgCdId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.aplyStDt = this.aplyStDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.creUsrId = this.creUsrId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValDesc = this.intgCdValDesc .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdValCtnt = this.intgCdValCtnt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.updUsrId = this.updUsrId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}
}
