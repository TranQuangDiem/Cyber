/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : VendorCodeVO.java
*@FileTitle : VendorCodeVO
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.23  
* 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.vendorcode.vendorcode.vo;

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

public class VendorCodeVO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;
	
	private Collection<VendorCodeVO> models = new ArrayList<VendorCodeVO>();
	
	/* Column Info */
	private String office = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String scac = null;
	/* Column Info */
	private String status = null;
	/* Column Info */
	private String location = null;
	/* Column Info */
	private String name = null;
	/* Column Info */
	private String code = null;
	/* Column Info */
	private String country = null;
	/* Page Number */
	private String pagerows = null;

	/* Hashtable to store table column values */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/* Hashtable storing member variables corresponding to table columns */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();
	
	public VendorCodeVO() {}

	public VendorCodeVO(String ibflag, String pagerows, String code, String name, String country, String location, String office, String status, String scac) {
		this.office = office;
		this.ibflag = ibflag;
		this.scac = scac;
		this.status = status;
		this.location = location;
		this.name = name;
		this.code = code;
		this.country = country;
		this.pagerows = pagerows;
	}
	
	/**
	 * Returns the value to be stored in the table column as Hashtable<"column_name", "value">
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues(){
		this.hashColumns.put("office", getOffice());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("scac", getScac());
		this.hashColumns.put("status", getStatus());
		this.hashColumns.put("location", getLocation());
		this.hashColumns.put("name", getName());
		this.hashColumns.put("code", getCode());
		this.hashColumns.put("country", getCountry());
		this.hashColumns.put("pagerows", getPagerows());
		return this.hashColumns;
	}
	
	/**
	 * Stores member variable names corresponding to column names and returns them as Hashtable<"column_name", "variable">
	 * @return
	 */
	public HashMap<String, String> getFieldNames(){
		this.hashFields.put("office", "office");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("scac", "scac");
		this.hashFields.put("status", "status");
		this.hashFields.put("location", "location");
		this.hashFields.put("name", "name");
		this.hashFields.put("code", "code");
		this.hashFields.put("country", "country");
		this.hashFields.put("pagerows", "pagerows");
		return this.hashFields;
	}
	
	/**
	 * Column Info
	 * @return office
	 */
	public String getOffice() {
		return this.office;
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
	 * @return scac
	 */
	public String getScac() {
		return this.scac;
	}
	
	/**
	 * Column Info
	 * @return status
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * Column Info
	 * @return location
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * Column Info
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Column Info
	 * @return code
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * Column Info
	 * @return country
	 */
	public String getCountry() {
		return this.country;
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
	 * @param office
	 */
	public void setOffice(String office) {
		this.office = office;
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
	 * @param scac
	 */
	public void setScac(String scac) {
		this.scac = scac;
	}
	
	/**
	 * Column Info
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Column Info
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Column Info
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Column Info
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Column Info
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Page Number
	 * @param pagerows
	 */
	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	
/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
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
		setOffice(JSPUtil.getParameter(request, prefix + "office", ""));
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setScac(JSPUtil.getParameter(request, prefix + "scac", ""));
		setStatus(JSPUtil.getParameter(request, prefix + "status", ""));
		setLocation(JSPUtil.getParameter(request, prefix + "location", ""));
		setName(JSPUtil.getParameter(request, prefix + "name", ""));
		setCode(JSPUtil.getParameter(request, prefix + "code", ""));
		setCountry(JSPUtil.getParameter(request, prefix + "country", ""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
	}

	/**
	 * Converts Request data to VO array and returns it.
	 * @param request
	 * @return VendorCodeVO[]
	 */
	public VendorCodeVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Several requests passed over are DATA in VO Class. 
	 * @param request
	 * @param prefix
	 * @return VendorCodeVO[]
	 */
	public VendorCodeVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		VendorCodeVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] office = (JSPUtil.getParameter(request, prefix	+ "office", length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag", length));
			String[] scac = (JSPUtil.getParameter(request, prefix	+ "scac", length));
			String[] status = (JSPUtil.getParameter(request, prefix	+ "status", length));
			String[] location = (JSPUtil.getParameter(request, prefix	+ "location", length));
			String[] name = (JSPUtil.getParameter(request, prefix	+ "name", length));
			String[] code = (JSPUtil.getParameter(request, prefix	+ "code", length));
			String[] country = (JSPUtil.getParameter(request, prefix	+ "country", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows", length));
			
			for (int i = 0; i < length; i++) {
				model = new VendorCodeVO();
				if (office[i] != null)
					model.setOffice(office[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (scac[i] != null)
					model.setScac(scac[i]);
				if (status[i] != null)
					model.setStatus(status[i]);
				if (location[i] != null)
					model.setLocation(location[i]);
				if (name[i] != null)
					model.setName(name[i]);
				if (code[i] != null)
					model.setCode(code[i]);
				if (country[i] != null)
					model.setCountry(country[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getVendorCodeVOs();
	}

	/**
	 * return VO array
	 * @return VendorCodeVO[]
	 */
	public VendorCodeVO[] getVendorCodeVOs(){
		VendorCodeVO[] vos = (VendorCodeVO[])models.toArray(new VendorCodeVO[models.size()]);
		return vos;
	}
	
	/**
	 *Convert the contents of VO Class to String
	 */
	public String toString() {
		   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE );
	   }

	/**
	* Remove special characters from formatted string ("-","/",",",":")
	*/
	public void unDataFormat(){
		this.office = this.office .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.scac = this.scac .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.status = this.status .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.location = this.location .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.name = this.name .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.code = this.code .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.country = this.country .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}
}
