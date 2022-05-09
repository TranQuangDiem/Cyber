/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice2douDAOPractice2RSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.04
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Diem Tran
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class Practice2douDAOPractice2RSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * search
	  * </pre>
	  */
	public Practice2douDAOPractice2RSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("intg_cd_id",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.integration ").append("\n"); 
		query.append("FileName : Practice2douDAOPractice2RSQL").append("\n"); 
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
		query.append("	UPD_DT" ).append("\n"); 
		query.append(",	UPD_USR_ID" ).append("\n"); 
		query.append(",	CRE_DT" ).append("\n"); 
		query.append(",	CRE_USR_ID" ).append("\n"); 
		query.append(",	INTG_CD_USE_FLG" ).append("\n"); 
		query.append(",	MNG_TBL_NM" ).append("\n"); 
		query.append(",	OWNR_SUB_SYS_CD" ).append("\n"); 
		query.append(",	INTG_CD_LEN" ).append("\n"); 
		query.append(",	INTG_CD_DAT_TP_NM" ).append("\n"); 
		query.append(",	INTG_CD_TP_CD" ).append("\n"); 
		query.append(",	INTG_CD_DESC" ).append("\n"); 
		query.append(",	INTG_CD_NM" ).append("\n"); 
		query.append(",	INTG_CD_ID" ).append("\n"); 
		query.append("FROM COM_INTG_CD" ).append("\n"); 
		query.append("WHERE	INTG_CD_ID = @[intg_cd_id]" ).append("\n"); 

	}
}