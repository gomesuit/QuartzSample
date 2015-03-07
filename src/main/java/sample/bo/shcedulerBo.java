package sample.bo;

import java.util.List;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import sample.data.SchedulerMapper;
import sample.model.MyScheduler;

public class shcedulerBo {
    private static final String MYBATIS_CONFIG = "sample/data/mybatis-config.xml";
    private static InputStream inputStream;
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession session;
    private static SchedulerMapper schedulerMapper;

    @SuppressWarnings("unused")
	private static shcedulerBo instance = new shcedulerBo();

    private shcedulerBo(){
        try{
            inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession(true);
            schedulerMapper = session.getMapper(SchedulerMapper.class);
        }catch(java.io.IOException e){
            e.printStackTrace();
        }
    }

    public static List<MyScheduler> getSchedulerList(){
        return schedulerMapper.selectSchedulerList();
    }
}
