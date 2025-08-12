package naitei.group5.workingspacebooking.service.impl;

import org.springframework.stereotype.Service;
import naitei.group5.workingspacebooking.service.TokenBlacklist;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryTokenBlacklist implements TokenBlacklist {
    private final Map<Integer, Long> revoked = new ConcurrentHashMap<>();

    @Override
    public void revokeSession(Integer sessionId, long ttlMillis) {
        revoked.put(sessionId, System.currentTimeMillis() + ttlMillis);
    }

    @Override
    public boolean isRevoked(Integer sessionId) {
        Long exp = revoked.get(sessionId);
        if (exp == null) return false;
        if (exp < System.currentTimeMillis()) { revoked.remove(sessionId); return false; }
        return true;
    }
}
