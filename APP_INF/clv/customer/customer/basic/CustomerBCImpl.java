package com.clt.apps.opus.esm.clv.customer.customer.basic;

import java.util.List;

import com.clt.apps.opus.esm.clv.customer.customer.integration.CustomerDBDAO;
import com.clt.apps.opus.esm.clv.customer.customer.vo.CustomerVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;

/**
 * ALPS-Customer Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Customer<br>
 *
 * @author Diem Tran
 * @since J2EE 1.6
 */
public class CustomerBCImpl extends BasicCommandSupport implements CustomerBC {

	// Database Access Object
	private transient CustomerDBDAO dbDao = null;

	/**
	 * Create CustomerBCImpl object<br>
	 * Create CustomerDBDAO.<br>
	 */
	public CustomerBCImpl() {
		dbDao = new CustomerDBDAO();
	}
	/**
	 * search data
	 * 
	 * @param CustomerVO customerVO
	 * @return List<CustomerVO>
	 * @exception EventException
	 */
	public List<CustomerVO> search(CustomerVO customerVO) throws EventException {
		try {
			return dbDao.search(customerVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
}