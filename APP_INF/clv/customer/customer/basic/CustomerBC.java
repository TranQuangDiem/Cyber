package com.clt.apps.opus.esm.clv.customer.customer.basic;



import java.util.List;

import com.clt.apps.opus.esm.clv.customer.customer.vo.CustomerVO;
import com.clt.framework.core.layer.event.EventException;

public interface CustomerBC {

	/**
	 * search data
	 * 
	 * @param CustomerVO customerVO
	 * @return List<CustomerVO>
	 * @exception EventException
	 */
	public List<CustomerVO> search(CustomerVO customerVO) throws EventException;
}