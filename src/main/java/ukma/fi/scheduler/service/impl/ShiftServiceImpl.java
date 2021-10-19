package ukma.fi.scheduler.service.impl;
import com.sun.media.sound.InvalidDataException;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.ServiceMarker;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.repository.LessonRepository;
import ukma.fi.scheduler.repository.ShiftRepository;
import ukma.fi.scheduler.service.*;

@ServiceMarker
@Service
@Log4j2
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ShiftRepository shiftRepository;

    @Override
    public Shift createShift(Long OldLessonId,
                       Integer newWeek, String dayOfWeek,
                       Integer lessonNum) {
        if(!lessonRepository.findById(OldLessonId).isPresent()){
            try {
                throw new InvalidDataException("Lesson id is incorrect");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        if(newWeek<=0 || dayOfWeek == null || lessonNum<=0){
            try {
                throw new InvalidDataException("Invalid input data.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        Shift shift = new Shift().createShift(newWeek,dayOfWeek, lessonNum,
                lessonRepository.findById(OldLessonId).get());
        return shiftRepository.save(shift);
    }

    @Override
    public Shift edit(Shift shift) {
        if (!shiftRepository.findById(shift.getId()).isPresent()) {
            try {
                throw new NotFoundException("Shift/Cancel not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        if(shift.getIsCancel().equals("N") && (shift.getWeekNumber()<=0 || shift.getDayOfWeek() == null || shift.getNumber()<=0)){
            try {
                throw new InvalidDataException("Invalid input data.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }else if (shift.getIsCancel().equals("Y") && (shift.getWeekNumber()==null || shift.getWeekNumber()<=0
                    || shift.getDayOfWeek() != null || shift.getNumber()!=null)){
            try {
                throw new InvalidDataException("Invalid input data.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        return shiftRepository.save(shift);
    }

    @Override
    public boolean delete(Long id) {
        if (shiftRepository.findById(id).isPresent()) {
            shiftRepository.deleteById(id);
        } else {
            try {
                throw new NotFoundException("Shift/Cancel not found.");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }
        return !shiftRepository.findById(id).isPresent();
    }

    @Override
    public Shift cancelLesson(Long lessonId, Integer week) {
        if(shiftRepository.findByLesson_IdAndAndWeekNumberAndIsCancelEquals(
                lessonId, Long.valueOf(week),"Y").isPresent()){
            try {
                throw new InvalidDataException("This cancel is already exist.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        if(!lessonRepository.findById(lessonId).isPresent()){
            try {
                throw new InvalidDataException("Lesson id is incorrect");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        if(week<=0){
            try {
                throw new InvalidDataException("Week number is negative or zero.");
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
        }
        Shift cancel = new Shift().createCancel(lessonRepository.findById(lessonId).get(),week);
        return shiftRepository.save(cancel);
    }
}
