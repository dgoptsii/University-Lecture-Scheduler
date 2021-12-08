package ukma.fi.scheduler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.CustomCache;
import ukma.fi.scheduler.entities.Lesson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShedulerService {
    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CacheManager cacheManager;

//    //cron for every 2 minutes
//    @Scheduled(cron = "0 0/2 * * * ?", zone = "Europe/Kiev")
//    public void happyNewDay() {
//        log.warn("Happy new day! Now we have - " + userService.findAllStudents().size()
//                + " students and  " + userService.findAllTeachers().size() + " teachers. Yohoo..We still alive..");
//    }
//
//    //cron for every 30 seconds
//    @Scheduled(fixedDelay = 30000)
//    public void happyNewYear() {
//        log.warn("Happy half minute! Now we have - " + subjectService.findAll().size() + " subjects.");
//    }
//
//    @Scheduled(fixedRate = 300000, initialDelay= 30000)
//    // reset cache every 5 min, with delay of 30 sec after app start
//    public void cleanCashTask() {
//        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
//    }

}
