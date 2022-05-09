package com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.event;

import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.Practice2douVO;
import com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.vo.PracticeVO;
import com.clt.framework.support.layer.event.EventSupport;

public class Practice2Event extends EventSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Practice2douVO practice2douVO = null;
	Practice2douVO[] practice2douVOs = null;
	PracticeVO practiceVO = null;
	PracticeVO[] practiceVOs = null;
	/**
	 * 
	 * @return  PracticeVO[]
	 */
	public PracticeVO[] getPracticeVOs() {
		return practiceVOs;
	}
	/**
	 * 
	 * @param practiceVOs
	 */
	public void setPracticeVOs(PracticeVO[] practiceVOs) {
		this.practiceVOs = practiceVOs;
	}
	/**
	 * 
	 * @return
	 */
	public Practice2douVO getPractice2douVO() {
		return practice2douVO;
	}
	public void setPractice2douVO(Practice2douVO practice2douVO) {
		this.practice2douVO = practice2douVO;
	}
	public Practice2douVO[] getPractice2douVOs() {
		return practice2douVOs;
	}
	public void setPractice2douVOs(Practice2douVO[] practice2douVOs) {
		this.practice2douVOs = practice2douVOs;
	}
	public PracticeVO getPracticeVO() {
		return practiceVO;
	}
	public void setPracticeVO(PracticeVO practiceVO) {
		this.practiceVO = practiceVO;
	}

}
