/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsDBDAOErrMsgVORSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.01
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.01 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice_dou.errms.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Diem Tran
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class ErrMsDBDAOErrMsgVORSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  *    
	  * </pre>
	  */
	public ErrMsDBDAOErrMsgVORSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_msg",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_msg_cd",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.practice_dou.errms.integration").append("\n"); 
		query.append("FileName : ErrMsDBDAOErrMsgVORSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("SELECT " ).append("\n"); 
		query.append("	EDW_UPD_DT" ).append("\n"); 
		query.append(",	UPD_DT" ).append("\n"); 
		query.append(",	UPD_USR_ID" ).append("\n"); 
		query.append(",	CRE_DT" ).append("\n"); 
		query.append(",	CRE_USR_ID" ).append("\n"); 
		query.append(",	ERR_DESC" ).append("\n"); 
		query.append(",	ERR_MSG" ).append("\n"); 
		query.append(",	ERR_LVL_CD" ).append("\n"); 
		query.append(",	ERR_TP_CD" ).append("\n"); 
		query.append(",	LANG_TP_CD" ).append("\n"); 
		query.append(",	ERR_MSG_CD" ).append("\n"); 
		query.append("FROM COM_ERR_MSG" ).append("\n"); 
		query.append("#if(${err_msg}!=''||${err_msg_cd}!='')" ).append("\n"); 
		query.append("WHERE" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if(${err_msg}!='')" ).append("\n"); 
		query.append("ERR_MSG LIKE '%' ||@[err_msg] ||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if(${err_msg}!=''&&${err_msg_cd}!='')" ).append("\n"); 
		query.append("AND" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if(${err_msg_cd}!='')" ).append("\n"); 
		query.append("	ERR_MSG_CD LIKE '%' || @[err_msg_cd] ||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 

	}
}