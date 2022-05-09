package com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.integration;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.clt.framework.support.db.ISQLTemplate;

public class Practice2douBDBDAOPractice2douVOCSQL implements ISQLTemplate {
	private StringBuffer query = new StringBuffer();

	Logger log = Logger.getLogger(this.getClass());
	/** Parameters definition in params/param elements */
	private HashMap<String, String[]> params = null;

	public Practice2douBDBDAOPractice2douVOCSQL() {
		setQuery();
		params = new HashMap<String, String[]>();
		query.append("/*").append("\n");
		query.append(
				"Path : com.clt.apps.opus.esm.clv.practice2.practice2.integration")
				.append("\n");
		query.append("FileName : Practice2DBDAOPractice2VOCSQL").append("\n");
		query.append("*/").append("\n");
	}

	@Override
	public HashMap getParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	public StringBuffer getQuery() {
		return query;
	}

	public void setQuery() {
	}

}
