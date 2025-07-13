package com.app.brainbuzz.service;

import com.app.brainbuzz.dto.CreateSessionRequest;
import lombok.RequiredArgsConstructor;
import com.app.brainbuzz.model.ClassroomSession;
import org.springframework.stereotype.Service;
import com.app.brainbuzz.repository.SessionRepository;

import java.util.List;
import java.util.Optional;
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

    public ClassroomSession getSessionById(Long id){
        return sessionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Session not found with id: " + id));
    }

    public ClassroomSession getSessionByJoinCode(String joinCode){
        return sessionRepository.findByJoinCode(joinCode)
        .orElseThrow(() -> new RuntimeException("Session not found  for join code: " + joinCode));
    }

    public List<ClassroomSession> getAllSessions(){
        return sessionRepository.findAll();
    }

    public Optional<ClassroomSession> updateSession(Long id, ClassroomSession updatedSession){
        return sessionRepository.findById(id).map(session -> {
            session.setTitle(updatedSession.getTitle());
            return sessionRepository.save(session);
        });
    }

    public boolean deleteSession(Long id){
        if(sessionRepository.existsById(id)){
            sessionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
