/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice3VO.java
*@FileTitle : Practice3VO
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.26
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.26  
* 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.practice3.practice3.vo;

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
 *A Value Object that is created in the related event and performs the role of data delivery when a server execution request is made.
 *
 * @author 
 * @since J2EE 1.6
 * @see AbstractValueObject
 */

public class Practice3VO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;
	
	private Collection<Practice3VO> models = new ArrayList<Practice3VO>();
	
	/* Column Info */
	private String csrNo = null;
	/* Column Info */
	private String invRevActAmt = null;
	/* Column Info */
	private String loclCurrCd = null;
	/* Column Info */
	private String custVndrSeq = null;
	/* Column Info */
	private String joCrrCd = null;
	/* Column Info */
	private String rlaneCd = null;
	/* Column Info */
	private String revExp = null;
	/* Column Info */
	private String custVndrCntCd = null;
	/* Column Info */
	private String joStlItmCd = null;
	/* Page Number */
	private String pagerows = null;
	/* Column Info */
	private String invNo = null;
	/* Column Info */
	private String custVndrEngNm = null;
	/* Column Info */
	private String invExpActAmt = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String prnrRefNo = null;
	/* Column Info */
	private String aproFlg = null;

	/* Hashtable to store table column values */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/* Hashtable storing member variables corresponding to table columns */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();
	
	public Practice3VO() {}

	public Practice3VO(String ibflag, String pagerows, String joCrrCd, String rlaneCd, String loclCurrCd, String invNo, String csrNo, String aproFlg, String custVndrCntCd, String custVndrSeq, String prnrRefNo, String custVndrEngNm, String joStlItmCd, String revExp, String invRevActAmt, String invExpActAmt) {
		this.csrNo = csrNo;
		this.invRevActAmt = invRevActAmt;
		this.loclCurrCd = loclCurrCd;
		this.custVndrSeq = custVndrSeq;
		this.joCrrCd = joCrrCd;
		this.rlaneCd = rlaneCd;
		this.revExp = revExp;
		this.custVndrCntCd = custVndrCntCd;
		this.joStlItmCd = joStlItmCd;
		this.pagerows = pagerows;
		this.invNo = invNo;
		this.custVndrEngNm = custVndrEngNm;
		this.invExpActAmt = invExpActAmt;
		this.ibflag = ibflag;
		this.prnrRefNo = prnrRefNo;
		this.aproFlg = aproFlg;
	}
	
	/**
	 * Returns the value to be stored in the table column as Hashtable<"column_name", "value">
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues(){
		this.hashColumns.put("csr_no", getCsrNo());
		this.hashColumns.put("inv_rev_act_amt", getInvRevActAmt());
		this.hashColumns.put("locl_curr_cd", getLoclCurrCd());
		this.hashColumns.put("cust_vndr_seq", getCustVndrSeq());
		this.hashColumns.put("jo_crr_cd", getJoCrrCd());
		this.hashColumns.put("rlane_cd", getRlaneCd());
		this.hashColumns.put("rev_exp", getRevExp());
		this.hashColumns.put("cust_vndr_cnt_cd", getCustVndrCntCd());
		this.hashColumns.put("jo_stl_itm_cd", getJoStlItmCd());
		this.hashColumns.put("pagerows", getPagerows());
		this.hashColumns.put("inv_no", getInvNo());
		this.hashColumns.put("cust_vndr_eng_nm", getCustVndrEngNm());
		this.hashColumns.put("inv_exp_act_amt", getInvExpActAmt());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("prnr_ref_no", getPrnrRefNo());
		this.hashColumns.put("apro_flg", getAproFlg());
		return this.hashColumns;
	}
	
	/**
	 * Stores member variable names corresponding to column names and returns them as Hashtable<"column_name", "variable"> 
	 * @return
	 */
	public HashMap<String, String> getFieldNames(){
		this.hashFields.put("csr_no", "csrNo");
		this.hashFields.put("inv_rev_act_amt", "invRevActAmt");
		this.hashFields.put("locl_curr_cd", "loclCurrCd");
		this.hashFields.put("cust_vndr_seq", "custVndrSeq");
		this.hashFields.put("jo_crr_cd", "joCrrCd");
		this.hashFields.put("rlane_cd", "rlaneCd");
		this.hashFields.put("rev_exp", "revExp");
		this.hashFields.put("cust_vndr_cnt_cd", "custVndrCntCd");
		this.hashFields.put("jo_stl_itm_cd", "joStlItmCd");
		this.hashFields.put("pagerows", "pagerows");
		this.hashFields.put("inv_no", "invNo");
		this.hashFields.put("cust_vndr_eng_nm", "custVndrEngNm");
		this.hashFields.put("inv_exp_act_amt", "invExpActAmt");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("prnr_ref_no", "prnrRefNo");
		this.hashFields.put("apro_flg", "aproFlg");
		return this.hashFields;
	}
	
	/**
	 * Column Info
	 * @return csrNo
	 */
	public String getCsrNo() {
		return this.csrNo;
	}
	
	/**
	 * Column Info
	 * @return invRevActAmt
	 */
	public String getInvRevActAmt() {
		return this.invRevActAmt;
	}
	
	/**
	 * Column Info
	 * @return loclCurrCd
	 */
	public String getLoclCurrCd() {
		return this.loclCurrCd;
	}
	
	/**
	 * Column Info
	 * @return custVndrSeq
	 */
	public String getCustVndrSeq() {
		return this.custVndrSeq;
	}
	
	/**
	 * Column Info
	 * @return joCrrCd
	 */
	public String getJoCrrCd() {
		return this.joCrrCd;
	}
	
	/**
	 * Column Info
	 * @return rlaneCd
	 */
	public String getRlaneCd() {
		return this.rlaneCd;
	}
	
	/**
	 * Column Info
	 * @return revExp
	 */
	public String getRevExp() {
		return this.revExp;
	}
	
	/**
	 * Column Info
	 * @return custVndrCntCd
	 */
	public String getCustVndrCntCd() {
		return this.custVndrCntCd;
	}
	
	/**
	 * Column Info
	 * @return joStlItmCd
	 */
	public String getJoStlItmCd() {
		return this.joStlItmCd;
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
	 * @return invNo
	 */
	public String getInvNo() {
		return this.invNo;
	}
	
	/**
	 * Column Info
	 * @return custVndrEngNm
	 */
	public String getCustVndrEngNm() {
		return this.custVndrEngNm;
	}
	
	/**
	 * Column Info
	 * @return invExpActAmt
	 */
	public String getInvExpActAmt() {
		return this.invExpActAmt;
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
	 * @return prnrRefNo
	 */
	public String getPrnrRefNo() {
		return this.prnrRefNo;
	}
	
	/**
	 * Column Info
	 * @return aproFlg
	 */
	public String getAproFlg() {
		return this.aproFlg;
	}
	

	/**
	 * Column Info
	 * @param csrNo
	 */
	public void setCsrNo(String csrNo) {
		this.csrNo = csrNo;
	}
	
	/**
	 * Column Info
	 * @param invRevActAmt
	 */
	public void setInvRevActAmt(String invRevActAmt) {
		this.invRevActAmt = invRevActAmt;
	}
	
	/**
	 * Column Info
	 * @param loclCurrCd
	 */
	public void setLoclCurrCd(String loclCurrCd) {
		this.loclCurrCd = loclCurrCd;
	}
	
	/**
	 * Column Info
	 * @param custVndrSeq
	 */
	public void setCustVndrSeq(String custVndrSeq) {
		this.custVndrSeq = custVndrSeq;
	}
	
	/**
	 * Column Info
	 * @param joCrrCd
	 */
	public void setJoCrrCd(String joCrrCd) {
		this.joCrrCd = joCrrCd;
	}
	
	/**
	 * Column Info
	 * @param rlaneCd
	 */
	public void setRlaneCd(String rlaneCd) {
		this.rlaneCd = rlaneCd;
	}
	
	/**
	 * Column Info
	 * @param revExp
	 */
	public void setRevExp(String revExp) {
		this.revExp = revExp;
	}
	
	/**
	 * Column Info
	 * @param custVndrCntCd
	 */
	public void setCustVndrCntCd(String custVndrCntCd) {
		this.custVndrCntCd = custVndrCntCd;
	}
	
	/**
	 * Column Info
	 * @param joStlItmCd
	 */
	public void setJoStlItmCd(String joStlItmCd) {
		this.joStlItmCd = joStlItmCd;
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
	 * @param invNo
	 */
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	
	/**
	 * Column Info
	 * @param custVndrEngNm
	 */
	public void setCustVndrEngNm(String custVndrEngNm) {
		this.custVndrEngNm = custVndrEngNm;
	}
	
	/**
	 * Column Info
	 * @param invExpActAmt
	 */
	public void setInvExpActAmt(String invExpActAmt) {
		this.invExpActAmt = invExpActAmt;
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
	 * @param prnrRefNo
	 */
	public void setPrnrRefNo(String prnrRefNo) {
		this.prnrRefNo = prnrRefNo;
	}
	
	/**
	 * Column Info
	 * @param aproFlg
	 */
	public void setAproFlg(String aproFlg) {
		this.aproFlg = aproFlg;
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
		setCsrNo(JSPUtil.getParameter(request, prefix + "csr_no", ""));
		setInvRevActAmt(JSPUtil.getParameter(request, prefix + "inv_rev_act_amt", ""));
		setLoclCurrCd(JSPUtil.getParameter(request, prefix + "locl_curr_cd", ""));
		setCustVndrSeq(JSPUtil.getParameter(request, prefix + "cust_vndr_seq", ""));
		setJoCrrCd(JSPUtil.getParameter(request, prefix + "jo_crr_cd", ""));
		setRlaneCd(JSPUtil.getParameter(request, prefix + "rlane_cd", ""));
		setRevExp(JSPUtil.getParameter(request, prefix + "rev_exp", ""));
		setCustVndrCntCd(JSPUtil.getParameter(request, prefix + "cust_vndr_cnt_cd", ""));
		setJoStlItmCd(JSPUtil.getParameter(request, prefix + "jo_stl_itm_cd", ""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
		setInvNo(JSPUtil.getParameter(request, prefix + "inv_no", ""));
		setCustVndrEngNm(JSPUtil.getParameter(request, prefix + "cust_vndr_eng_nm", ""));
		setInvExpActAmt(JSPUtil.getParameter(request, prefix + "inv_exp_act_amt", ""));
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setPrnrRefNo(JSPUtil.getParameter(request, prefix + "prnr_ref_no", ""));
		setAproFlg(JSPUtil.getParameter(request, prefix + "apro_flg", ""));
	}

	/**
	 * Converts Request data to VO array and returns it.
	 * @param request
	 * @return Practice3VO[]
	 */
	public Practice3VO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Several requests passed over are DATA in VO Class. 
	 * @param request
	 * @param prefix
	 * @return Practice3VO[]
	 */
	public Practice3VO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		Practice3VO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] csrNo = (JSPUtil.getParameter(request, prefix	+ "csr_no", length));
			String[] invRevActAmt = (JSPUtil.getParameter(request, prefix	+ "inv_rev_act_amt", length));
			String[] loclCurrCd = (JSPUtil.getParameter(request, prefix	+ "locl_curr_cd", length));
			String[] custVndrSeq = (JSPUtil.getParameter(request, prefix	+ "cust_vndr_seq", length));
			String[] joCrrCd = (JSPUtil.getParameter(request, prefix	+ "jo_crr_cd", length));
			String[] rlaneCd = (JSPUtil.getParameter(request, prefix	+ "rlane_cd", length));
			String[] revExp = (JSPUtil.getParameter(request, prefix	+ "rev_exp", length));
			String[] custVndrCntCd = (JSPUtil.getParameter(request, prefix	+ "cust_vndr_cnt_cd", length));
			String[] joStlItmCd = (JSPUtil.getParameter(request, prefix	+ "jo_stl_itm_cd", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows", length));
			String[] invNo = (JSPUtil.getParameter(request, prefix	+ "inv_no", length));
			String[] custVndrEngNm = (JSPUtil.getParameter(request, prefix	+ "cust_vndr_eng_nm", length));
			String[] invExpActAmt = (JSPUtil.getParameter(request, prefix	+ "inv_exp_act_amt", length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag", length));
			String[] prnrRefNo = (JSPUtil.getParameter(request, prefix	+ "prnr_ref_no", length));
			String[] aproFlg = (JSPUtil.getParameter(request, prefix	+ "apro_flg", length));
			
			for (int i = 0; i < length; i++) {
				model = new Practice3VO();
				if (csrNo[i] != null)
					model.setCsrNo(csrNo[i]);
				if (invRevActAmt[i] != null)
					model.setInvRevActAmt(invRevActAmt[i]);
				if (loclCurrCd[i] != null)
					model.setLoclCurrCd(loclCurrCd[i]);
				if (custVndrSeq[i] != null)
					model.setCustVndrSeq(custVndrSeq[i]);
				if (joCrrCd[i] != null)
					model.setJoCrrCd(joCrrCd[i]);
				if (rlaneCd[i] != null)
					model.setRlaneCd(rlaneCd[i]);
				if (revExp[i] != null)
					model.setRevExp(revExp[i]);
				if (custVndrCntCd[i] != null)
					model.setCustVndrCntCd(custVndrCntCd[i]);
				if (joStlItmCd[i] != null)
					model.setJoStlItmCd(joStlItmCd[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				if (invNo[i] != null)
					model.setInvNo(invNo[i]);
				if (custVndrEngNm[i] != null)
					model.setCustVndrEngNm(custVndrEngNm[i]);
				if (invExpActAmt[i] != null)
					model.setInvExpActAmt(invExpActAmt[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (prnrRefNo[i] != null)
					model.setPrnrRefNo(prnrRefNo[i]);
				if (aproFlg[i] != null)
					model.setAproFlg(aproFlg[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getPractice3VOs();
	}

	/**
	 * return VO array
	 * @return Practice3VO[]
	 */
	public Practice3VO[] getPractice3VOs(){
		Practice3VO[] vos = (Practice3VO[])models.toArray(new Practice3VO[models.size()]);
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
		this.csrNo = this.csrNo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.invRevActAmt = this.invRevActAmt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.loclCurrCd = this.loclCurrCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.custVndrSeq = this.custVndrSeq .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.joCrrCd = this.joCrrCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.rlaneCd = this.rlaneCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.revExp = this.revExp .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.custVndrCntCd = this.custVndrCntCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.joStlItmCd = this.joStlItmCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.invNo = this.invNo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.custVndrEngNm = this.custVndrEngNm .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.invExpActAmt = this.invExpActAmt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.prnrRefNo = this.prnrRefNo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.aproFlg = this.aproFlg .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}
}
