package com.fabio.phaseListeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.fabio.util.JPAUtil;

public class StatisticsHibernate implements PhaseListener{
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent arg0) {
		
	}

	public void beforePhase(PhaseEvent arg0) {
//		JPAUtil.printStatistics();
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
