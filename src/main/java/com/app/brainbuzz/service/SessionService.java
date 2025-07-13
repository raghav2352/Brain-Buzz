package service;

import dto.CreateSessionRequest;
import lombok.RequiredArgsConstructor;
import model.ClassroomSession;
import org.springframework.stereotype.Service;
import repository.SessionRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;


    public ClassroomSession createSession(CreateSessionRequest request){
        ClassroomSession session = ClassroomSession.builder()
                .title(request.getTitle())
                .joinCode(generateJoinCode())
                .build();
        return sessionRepository.save(session);
    }
    private String generateJoinCode(){
        return UUID.randomUUID().toString().substring(0,6);
    }
}
