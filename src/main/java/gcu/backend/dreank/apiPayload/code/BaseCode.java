package gcu.backend.dreank.apiPayload.code;

import java.awt.desktop.UserSessionEvent;

public interface BaseCode {
    //Status에서 두 개의 메소드를 override하도록 함
    //맞는지 모르겠음 수정할수도!!
    public UserSessionEvent.Reason getReason();

    public UserSessionEvent.Reason getReasonHttpStatus();
}
