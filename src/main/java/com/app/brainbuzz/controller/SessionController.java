package com.app.brainbuzz.controller;

import com.app.brainbuzz.dto.CreateSessionRequest;
import lombok.RequiredArgsConstructor;
import com.app.brainbuzz.model.ClassroomSession;

import java.util.List;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ConditionalOnPublicKeyJwtDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.brainbuzz.service.SessionService;


@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
@CrossOrigin
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<ClassroomSession> createSession(@RequestBody CreateSessionRequest request) {
        ClassroomSession session = sessionService.createSession(request);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomSession> getSessionById(@PathVariable Long id){
        ClassroomSession session  = sessionService.getSessionById(id);
        return ResponseEntity.ok(session);
    }

    @GetMapping
    public List<ClassroomSession> getAllSessions(){
        return sessionService.getAllSessions();
    }

    @GetMapping("/join/{joinCode}")
    public ResponseEntity<ClassroomSession> getSessionByJoinCode(@PathVariable String joinCode){
        ClassroomSession session = sessionService.getSessionByJoinCode(joinCode);
        return ResponseEntity.ok(session);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClassroomSession> updateSession(@PathVariable Long id, @RequestBody ClassroomSession updatedSession){
        return sessionService.updateSession(id, updatedSession)
            .map(session -> new ResponseEntity<>(session , HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable Long id){
        boolean deleted = sessionService.deleteSession(id);
        return deleted ? ResponseEntity.ok("Session deleted successfully") : new ResponseEntity<>("Session not found" , HttpStatus.NOT_FOUND);
    }

    
}
