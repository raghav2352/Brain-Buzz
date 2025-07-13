package controller;

import dto.CreateSessionRequest;
import lombok.RequiredArgsConstructor;
import model.ClassroomSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SessionService;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
@CrossOrigin
public class SessionController {

    private  SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @PostMapping
    public ResponseEntity<ClassroomSession> createSession(@RequestBody CreateSessionRequest request) {
        ClassroomSession session = sessionService.createSession(request);
        return ResponseEntity.ok(session);
    }
}
