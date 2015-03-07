package sample;

import java.util.List;
import java.util.TimeZone;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import sample.bo.shcedulerBo;
import sample.model.MyScheduler;

public class RegistScheduler {
	
    public static void main(String[] args) throws Exception {
    	List<MyScheduler> schedulerList = shcedulerBo.getSchedulerList();
        for (MyScheduler myScheduler : schedulerList){
        	String jobName = myScheduler.getJobName();
        	String jobclass = myScheduler.getJobClass();
        	String jobSchedule = myScheduler.getJobSchedule();
        	
        	JobDetail jobDetail = createjobDetail(jobName, jobclass);
        	CronTrigger cronTrigger = createCronTrigger(jobName, jobSchedule);
        	createScheduler(jobDetail, cronTrigger);
        }
    }
    
    private static void createScheduler(JobDetail jobDetail, CronTrigger cronTrigger) throws SchedulerException {
    	Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    	scheduler.scheduleJob(jobDetail, cronTrigger);
    	scheduler.start();
    	System.out.println("JOBÇÃìoò^Ç™äÆóπÇµÇ‹ÇµÇΩÅB");
	}

	private static CronTrigger createCronTrigger(String triggerName, String triggerSchedule) {		
        CronTrigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName)
                .withSchedule(CronScheduleBuilder.cronSchedule(triggerSchedule).inTimeZone(TimeZone.getTimeZone("Asia/Tokyo")))
                .startNow()
                .build();
		return trigger;
	}

	@SuppressWarnings("unchecked")
	private static JobDetail createjobDetail(String jobName, String jobclass) throws Exception {
		Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(jobclass);
		
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobName)
                .storeDurably()
                .build();
        
		return jobDetail;
	}
    
}
