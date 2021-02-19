package qnu.cntt.dacky.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

public interface AuditEventService {
	@Scheduled(cron = "0 0 12 * * ?")
	public void removeOldAuditEvents();

	@Transactional(readOnly = true)
	public Page<AuditEvent> findAll(Pageable pageable);
	@Transactional(readOnly = true)
    public Page<AuditEvent> findByDates(Instant fromDate, Instant toDate, Pageable pageable);
	@Transactional(readOnly = true)
    public Optional<AuditEvent> find(UUID id);
}
