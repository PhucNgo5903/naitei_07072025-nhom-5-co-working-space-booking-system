package naitei.group5.workingspacebooking.service;

public interface TokenBlacklist {
    void revokeSession(Integer sessionId, long ttlMillis);
    boolean isRevoked(Integer sessionId);
}
