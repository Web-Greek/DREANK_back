package gcu.backend.dreank.service.chat;

import gcu.backend.dreank.domain.calendar.Calendar;
import gcu.backend.dreank.domain.chat.Chatting;
import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.dto.CalendarRequest;
import gcu.backend.dreank.dto.request.chat.ChattingRequest;
import gcu.backend.dreank.repository.ChattingRepository;
import gcu.backend.dreank.repository.StudyRepository;
import gcu.backend.dreank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChattingService {
    UserRepository userRepository;
    ChattingRepository chattingRepository;

    @Transactional
    public Chatting createChatting(ChattingRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Chatting chatting = Chatting.builder()
                .user(user)
                .content(request.getContent())
                .timestamp(request.getTimestamp())
                .build();

        return chattingRepository.save(chatting);
    }
}
