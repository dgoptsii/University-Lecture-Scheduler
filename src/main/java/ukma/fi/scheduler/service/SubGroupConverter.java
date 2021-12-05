package ukma.fi.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.controller.dto.SubjectGroupDTO;
import ukma.fi.scheduler.controller.dto.SubjectGroupListDTO;
import ukma.fi.scheduler.entities.Subject;
import javax.naming.InvalidNameException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SubGroupConverter {
    @Autowired
    private SubjectService subjectService;

    public Map<Subject, Integer> convertSubGroupDto(SubjectGroupListDTO input){
        List<SubjectGroupDTO> list = input.getChooseSubGroup().stream()
                .filter(x -> x.getGroupNum()!=null).collect(Collectors.toList());

        Map<Long, Integer> map = list.stream()
                .collect(Collectors.toMap(SubjectGroupDTO::getSubId, SubjectGroupDTO::getGroupNum));

        List<Long> subIds = list.stream().map(SubjectGroupDTO::getSubId).collect(Collectors.toList());
        List<Subject> subjects = subjectService.findSubjectByIdIn(subIds);

        Map<Subject,Integer> res = subjects.stream()
                .collect(Collectors.toMap(Function.identity(),item -> 0));

        res.forEach((k, v) -> {
            Integer group = map.get(k.getId());
            if(group >k.getMaxGroups()){
                try {
                    throw new InvalidNameException("GroupNum is bigger then Subject maxGroupNum");
                } catch (InvalidNameException e) {
                    e.printStackTrace();
                }
            }else{
                res.replace(k,group);
            }
        });

        return res;
    }
}
