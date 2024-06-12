package gcu.backend.dreank.controller.chat;


import gcu.backend.dreank.domain.calendar.Calendar;
import gcu.backend.dreank.domain.chat.Chatting;
import gcu.backend.dreank.dto.CalendarRequest;
import gcu.backend.dreank.dto.request.chat.ChattingRequest;
import gcu.backend.dreank.service.chat.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatting/{userId}")
@RequiredArgsConstructor
public class ChattingController {
    private final ChattingService chattingService;

    @PostMapping
    public ResponseEntity<Chatting> createChatting(@PathVariable Long userId, @RequestBody ChattingRequest request) {
        request.setUserId(userId);
        Chatting chatting = chattingService.createChatting(request);
        return ResponseEntity.ok(chatting);
    }
}