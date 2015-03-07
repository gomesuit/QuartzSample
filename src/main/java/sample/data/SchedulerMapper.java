package sample.data;

import java.util.List;

import sample.model.MyScheduler;

public interface SchedulerMapper {
	List<MyScheduler> selectSchedulerList();
}
