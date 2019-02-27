package br.gov.mt.mti.fiplangrf.service.scheduler;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Exemplo para a criação de rotinas agendadas.
 * 
 * @author Unknown
 *
 */
@Stateless
public class SchedulerService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/* Utilizado para criar "Timer" */
	@Resource
    private TimerService timerService;
	
	@PreDestroy
	private void cleanUp() {
		if (timerService.getTimers() != null) {
			for (Timer timer : timerService.getTimers()) {
				if (timer.getInfo().equals("TimerService")) {
					timer.cancel();
				}
			}
		}
	}
	
	@PostConstruct
	public void init(){
	}

	public String getMemoryReport() {
		StringBuffer report = new StringBuffer() ;
		GregorianCalendar reportCalendar = new GregorianCalendar() ;
		Date reportDate = reportCalendar. getTime() ;
		Runtime runtime = Runtime. getRuntime() ;
		DateFormat dateFormat =
		DateFormat. getDateTimeInstance(DateFormat. MEDIUM,
		DateFormat. MEDIUM) ;
		report.append(" \n#################################################\n")
			  .append(dateFormat. format(reportDate));
		report.append("\nSystemReportManager: displayMemoryReport occurred");
		report. append("\nTotal Memory: ").append(runtime. totalMemory());
		report. append("\n");
		report. append("Maximum Memory: ").append(runtime. maxMemory());
		report. append("\n");
		report. append("Free Memory: ").append(runtime. freeMemory());		
		report.append("\n#################################################\n");
		return report.toString();
	}
	
	/*
	 * Static timer
	 * Abaixo um exemplo  de agendador. Para ativar o agendador é necessário descomentar
	 * a anotação abaixo contendo a periodicidade do agendador.
	 */
	
	/*
	@Schedules(
			{
				@Schedule(persistent=false, second = "0, 10, 20, 30, 40, 50", minute="*", hour = "*"),
			}
	)
	*/
	public void displayMemoryReport(Timer timer)  {
		LOGGER.info(getMemoryReport() ) ;
	}
}
